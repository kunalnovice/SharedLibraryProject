package com.Automation.SharedLibrary;

import org.openqa.selenium.WebDriver;

/**
 * Created by ashvimehta on 13/12/2016.
 */
public interface Browser {

    WebDriver fireFoxDriver(String userAgent, boolean javaScriptEnabled);

    WebDriver chromeDriver(String userAgent, boolean javaScriptEnabled);


    WebDriver ieDriver(String userAgent, boolean javaScriptEnabled);
    WebDriver edgeDriver(String userAgent, boolean javaScriptEnabled);
    WebDriver safariDriver(String userAgent, boolean javaScriptEnabled);
    WebDriver appiumDriverIOS(String userAgent, boolean javaScriptEnabled);
    WebDriver appiumDriverAndroid(String userAgent, boolean javaScriptEnabled);
    WebDriver operaDriver(String userAgent, boolean javaScriptEnabled);
    WebDriver ghostDriver(String userAgent, boolean javaScriptEnabled);

}
