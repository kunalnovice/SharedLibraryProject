package com.Automation.SharedLibrary;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.rmi.Remote;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by ashvimehta on 13/12/2016.
 */
public class BrowserFactory implements Browser {
    public PropertyReader testConfig = PropertyReader.getTestConfigInstance();
    public WebDriver driver;
    public String iphoneUserAgent= "Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/9.0 Mobile/13B137 Safari/601.1";
    public String androidUserAgent= "Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/9.0 Mobile/13B137 Safari/601.1";
    public static final Logger LOGGER = LoggerFactory.getLogger(BrowserFactory.class);

    public static final String USERNAME = "saucelabsvcs";
    public static final String ACCESS_KEY = "9d404be2-afa6-4113-abba-43e378ca10c4";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
    public static final Logging log = Logging.getLogger();


    public WebDriver getDriver() {
        log.log_library_track("I am in getDriver Method of BrowserFactory");
        if (testConfig.getSauceLabsFlag().equals("TRUE"))
        {return getSauceLabsDriver();}
        else
       return getDriver(testConfig.getDevice(),
               testConfig.getBrowser(),
                        true);
    }

    public WebDriver getDriver(String device, String browser, boolean javaScriptEnabled) {
        DeviceType deviceType = DeviceType.valueOf(device.toUpperCase());
        BrowserType browserType = BrowserType.valueOf(browser.toUpperCase());
        switch(deviceType) {
            case DESKTOP:
                switch (browserType) {
                    case FIREFOXPROFILE:
                        driver = fireFoxDriver(null, javaScriptEnabled, "");
                        break;
                    case FIREFOX:
                        driver = fireFoxDriver(null, javaScriptEnabled);
                        break;
                    case CHROME:
                        driver = chromeDriver(null, javaScriptEnabled);
                        break;
                    case FIREFOX_REMOTE:
                        driver = getRemoteFirefox(null, javaScriptEnabled);
                        break;
                    case IE:
                        driver = ieDriver(null, javaScriptEnabled);
                        break;
                    case SAFARI:
                        driver = safariDriver(null, javaScriptEnabled);
                        break;
                    case OPERA:
                        driver = operaDriver(null, javaScriptEnabled);
                        break;
                    case GHOST:
                        driver = ghostDriver(null, javaScriptEnabled);
                        break;
                    default:
                        LOGGER.error("Invalid Browser. Please check config file");
                        break;
                }
                break;
            case IPHONE:
                switch (browserType) {
                    case FIREFOXPROFILE:
                        driver = fireFoxDriver(iphoneUserAgent, javaScriptEnabled);
                        break;
                    case FIREFOX:
                        driver = fireFoxDriver(iphoneUserAgent, javaScriptEnabled);
                        break;
                    case CHROME:
                        driver = fireFoxDriver(iphoneUserAgent, javaScriptEnabled);
                        break;
                    case FIREFOX_REMOTE:
                        break;
                    case IE:
                        driver = ieDriver(iphoneUserAgent, javaScriptEnabled);
                        break;
                    case SAFARI:
                        driver = safariDriver(iphoneUserAgent, javaScriptEnabled);
                        break;
                    case OPERA:
                        driver = operaDriver(iphoneUserAgent, javaScriptEnabled);
                        break;
                    case GHOST:
                        driver = ghostDriver(iphoneUserAgent, javaScriptEnabled);
                        break;
                    default:
                        LOGGER.error("Invalid Browser. Please check config file");
                        break;
                }
                break;
            case ANDROID:
                switch (browserType) {
                    case FIREFOXPROFILE:
                        driver = fireFoxDriver(androidUserAgent, javaScriptEnabled);
                        break;
                    case FIREFOX:
                        driver = fireFoxDriver(androidUserAgent, javaScriptEnabled);
                        break;
                    case CHROME:
                        driver = fireFoxDriver(androidUserAgent, javaScriptEnabled);
                        break;
                    case FIREFOX_REMOTE:
                        break;
                    case IE:
                        driver = ieDriver(androidUserAgent, javaScriptEnabled);
                        break;
                    case SAFARI:
                        driver = safariDriver(androidUserAgent, javaScriptEnabled);
                        break;
                    case OPERA:
                        driver = operaDriver(androidUserAgent, javaScriptEnabled);
                        break;
                    case GHOST:
                        driver = ghostDriver(androidUserAgent, javaScriptEnabled);
                        break;
                    default:
                        LOGGER.error("Invalid Browser. Please check config file");
                        break;
                }
                break;
            case ANDROID_DEVICE:
                switch (browserType) {
                    case APPIUMIOS:
                        driver = appiumDriverIOS(androidUserAgent, javaScriptEnabled);
                        break;
                    case APPIUMANDROID:
                        driver = appiumDriverAndroid(androidUserAgent, javaScriptEnabled);
                        break;
                    default:
                        LOGGER.error("Invalid Browser. Please check config file");
                        break;
                }
                break;
            case IPHONE_DEVICE:
                switch (browserType) {
                    case APPIUMIOS:
                        driver = appiumDriverIOS(androidUserAgent, javaScriptEnabled);
                        break;
                    case APPIUMANDROID:
                        driver = appiumDriverAndroid(androidUserAgent, javaScriptEnabled);
                        break;
                    default:
                        LOGGER.error("Invalid Browser. Please check config file");
                        break;
                }
                break;
        }
         driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (driver instanceof RemoteWebDriver) {
            ((RemoteWebDriver) driver).setLogLevel(Level.INFO);
        }
        if (testConfig.getDevice().equalsIgnoreCase("DESKTOP")) {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
            return driver;
        }
        if ((driver instanceof RemoteWebDriver)) {
            if (!(driver instanceof Rotatable)) {
                Dimension dim = new Dimension(480, 800);
                driver.manage().window().setSize(dim);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            }
        }
            return driver;
                }

    private WebDriver fireFoxDriver(String userAgent, boolean javaScriptEnabled, String profile) {
        profile = testConfig.getBrowserProfile();

        ProfilesIni profilesIni = new ProfilesIni();
        FirefoxProfile myProfile = profilesIni.getProfile(profile);
        return new FirefoxDriver(myProfile);
    }

    public WebDriver fireFoxDriver(String userAgent,boolean javaScriptEnabled){
        return new FirefoxDriver(getFirefoxProfile(userAgent,javaScriptEnabled));

}
// we can accept untrusted certificates
    private FirefoxProfile getFirefoxProfile(String userAgent, boolean javaScriptEnabled) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("javascript.enabled",javaScriptEnabled);
        profile.setAcceptUntrustedCertificates(true);
        if (null!=userAgent) {
            profile.setPreference("general.useragent.override",userAgent);
    }
    return profile;
}

    private WebDriver getRemoteFirefox(String userAgent, boolean javascriptEnabled) {
        try {
            DesiredCapabilities firefox = DesiredCapabilities.firefox();
            firefox.setBrowserName("firefox");
            firefox.setPlatform(Platform.ANY);
            driver = new RemoteWebDriver(new URL(testConfig.getSeleniumGridURL()), firefox);
            return driver;

        } catch (Exception e) {
            throw new IllegalStateException("not able to get remote firefox", e);
        }
    }

 private DesiredCapabilities getRemoteFirefoxCapabilities(String userAgent, boolean javascriptEnabled) {
     DesiredCapabilities firefox = DesiredCapabilities.firefox();
        firefox.setVersion("45.0.2");
        firefox.setPlatform(Platform.LINUX);
        firefox.setJavascriptEnabled(javascriptEnabled);
        firefox.setCapability("maxInstances", "1");
        firefox.setCapability("firefox_binary", "/usr/bin/firefox");
        if (null != userAgent) {

        }
        return firefox;
 }

    @Override
    public WebDriver chromeDriver(String userAgent,boolean javaScriptEnabled) {
        System.setProperty("webdriver.chrome.driver","src/test/resources/config/chromedriver.exe");
        return driver = new ChromeDriver(getChromeOptions(userAgent,javaScriptEnabled));
 }

    private ChromeOptions getChromeOptions(String userAgent, boolean javaScriptEnabled) {
        ChromeOptions opts = new ChromeOptions();
        if (null != userAgent) {
            opts.addArguments("user-agent=" + userAgent);
        } return opts;
    }

    @Override
    public WebDriver ieDriver(String userAgent, boolean javaScriptEnabled) {
        System.setProperty("webdriver.ie.driver","src/test/resources/config/IEDriver.exe");
        return driver = new InternetExplorerDriver();
    }

    @Override
    public WebDriver edgeDriver(String userAgent, boolean javaScriptEnabled) {
        System.setProperty("webdriver.ie.driver","src/test/resources/config/MicrosoftWebDriver.exe");
        return driver = new EdgeDriver();
    }

    @Override
    public WebDriver safariDriver(String userAgent, boolean javaScriptEnabled) {
        DesiredCapabilities safariCaps = DesiredCapabilities.safari();
        safariCaps.setCapability("safari.cleanSession",true);
        return new SafariDriver(safariCaps);
    }
    @Override
    public WebDriver appiumDriverIOS(String userAgent, boolean javaScriptEnabled) {

        return null;
    }
    @Override
    public WebDriver appiumDriverAndroid(String userAgent, boolean javaScriptEnabled) {

        return null;
    }
    @Override
    public WebDriver operaDriver(String userAgent, boolean javaScriptEnabled) {
        System.setProperty("webdriver.opera.driver","src/test/resources/config/operadriver.exe");
        return null;
    }
    @Override
    public WebDriver ghostDriver(String userAgent, boolean javaScriptEnabled) {

        return null;
    }

    private WebDriver getSauceLabsDriver() {
        try {
            DesiredCapabilities caps = DesiredCapabilities.firefox();
            caps.setCapability("platform", "Windows 10");
            caps.setCapability("version", "46.0");
            System.out.println(URL + " ");
            System.out.println(caps);
            WebDriver sauceLabsDriver = new RemoteWebDriver(new URL(URL), caps);
            return sauceLabsDriver;
        } catch (Exception e) {
            log.log_library_error("     Exception in getSauceLabsDriver unable to get driver.",e);
            throw new IllegalStateException("", e);
        }
    }

}
