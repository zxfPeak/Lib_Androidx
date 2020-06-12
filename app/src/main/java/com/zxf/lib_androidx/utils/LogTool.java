package com.zxf.lib_androidx.utils;


import com.zxf.lib_androidx.activity.BaseActivity;

import org.apache.log4j.Logger;

/**
 * log4j工具类
 * Created by zxf on 2018/9/18.
 */

public class LogTool {

    private static Logger log;

    private static void init() {
        log = Logger.getLogger(BaseActivity.class);
    }

    public static void info(String str) {
        init();
        log.info(str);
    }

    public static void debug(String str) {
        init();
        log.debug(str);
    }

    public static void warn(String str) {
        init();
        log.warn(str);
    }

    public static void error(String str) {
        init();
        log.error(str);
    }
}
