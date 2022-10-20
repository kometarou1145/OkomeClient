package com.Kometarou.OkomeClient.util.client;

import com.Kometarou.OkomeClient.okome;
import org.apache.logging.log4j.Logger;

public class LogUtil {
    private static final Logger logger = okome.logger;

    public static void info(Object obj) {
        logger.info(obj);
    }

    public static void warn(Object obj) {
        logger.warn(obj);
    }

    public static void error(Object obj) {
        logger.error(obj);
    }
}
