package com.Automation.SharedLibrary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ashvimehta on 20/12/2016.
 */
public class Logging {

    public static final Logger LOGGER = LoggerFactory.getLogger(Logging.class);
    public static Logging log ;

    private Logging() {

    }

    public static Logging getLogger() {
        if (log == null) {
            log = new Logging();
        }
        return log;
    }


    public void log_library_error(String str, Exception e) {
        LOGGER.error(str,e);
    }
    public void log_library_error(String str) {
        LOGGER.error(str);
    }
    public void log_library_track(String str) { LOGGER.info(str);    }

    public void log_def_error(String str, Exception e) {
        LOGGER.warn(str,e);
        LOGGER.error(str,e);
    }
    public void log_def_track(String str) {
        LOGGER.warn(str);
    }
}
