package com.cxd.littletime.common.util;

import org.apache.log4j.Logger;

/**
 * @author YiBuBuHuiTou
 */
public class LogUtils {

    public static Logger getDebugLogger() {
        return Logger.getLogger(LogUtils.class);
    }

    public static Logger getAccessLogger() {
        return Logger.getLogger("accessLog");
    }
}
