package com.Automation.SharedLibrary;

/**
 * Created by ashvimehta on 13/12/2016.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ashvimehta on 13/12/2016.
 */
public class PropertyReader {

    public Properties prop = new Properties();
    InputStream inputStream ;
    public static final Logging log = Logging.getLogger();
    private static String User_directory= System.getProperty("user.dir")+ File.separator;
    private static String pageElementFilePath = User_directory+"src/test/resources/config/PageElements.properties";
    private static String testConfigFilePath=User_directory+"src/test/resources/config/TestConfig.properties";



    private PropertyReader(String path) {
        loadProperties(path);
    }

    static PropertyReader pageElementPropertyInstance = new PropertyReader(pageElementFilePath);
    //make the constructor private so that this class cannot be
    //instantiated


    //Get the only object available
    public static PropertyReader getPageElementInstance(){
        return pageElementPropertyInstance;
    }

    static PropertyReader testConfigPropertyInstance = new PropertyReader(testConfigFilePath);
    //make the constructor private so that this class cannot be
    //instantiated


    //Get the only object available
    public static PropertyReader getTestConfigInstance(){
        return testConfigPropertyInstance;
    }

 
    public void  loadProperties(String propFilePath) {
        log.log_library_track("in PropertyReader-LoadProperties- start");

        //String configLocation = System.getProperty("user.dir")+File.separator +"src/test/resources/config/";

                log.log_library_track("from main file user directory= "+User_directory);
                //System.out.println("from main file user directory= "+User_directory);
                log.log_library_track("from main file Property File Path= "+propFilePath);

        //inputStream = new FileInputStream(configLocation+propFileName);
        try {
            if (propFilePath!= null) {
                inputStream = new FileInputStream(propFilePath);
                prop.load(inputStream);
                log.log_library_track("     in LoadProperties- file loaded");
            }
            else {
                log.log_library_error("     in LoadProperties- file not loaded");
            }
        } catch (IOException e) {
                log.log_library_error("     in LoadProperties- Exception");
            e.printStackTrace();
        }
                log.log_library_track("in PropertyReader-LoadProperties- Finish");

    }

    public String readProperty(String key) {
        return prop.getProperty(key);
    }

    public String getDevice() {

        return readProperty("DEVICE");

    }
    public String getBrowser() {

        return readProperty("BROWSER");
    }
    public String getBrowserProfile() {

        return readProperty("BROWSERPROFILE");
    }
    public String getSeleniumGridURL() {

        return readProperty("GRIDURL");
    }

    public String getSauceLabsFlag() {
        System.out.println("SAUCELABS FLAG = "+ readProperty("SAUCELABS_FLAG"));
        return readProperty("SAUCELABS_FLAG");

    }
}
