package com.zxf.lib_androidx.utils;

import android.util.Log;

/**
 * Created by zxf on 2018/7/6.
 * 日志工具类 自动识别调用日志函数的类名 方法名 与位置 不需要繁琐的TAG 可以方便的 设置debug模式 发布时候
 * 可以直接修改debug为false 就不会输出日志了
 */
public class LogUtil2 {

    /**
     * true:打开log  false:关闭所有的日志
     */
    public static boolean OPEN_LOG = true;
    /**
     * true : 打开debug 日志  false:关闭debug日志
     */
    public static boolean DEBUG = true;
    /**
     * TAG 名称
     */
    private static String TAG = "IntelligentDefence";
    private String mClassName;
    private static LogUtil2 log;
    private static final String USER_NAME = "user";

    private LogUtil2(String name) {
        mClassName = name;
    }

    /**
     * 获取方法名
     * @return 方法名
     */
    private String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }
            return mClassName + "[ " + Thread.currentThread().getName() + ": " + st.getFileName() + ":" + st.getLineNumber() + " " + st.getMethodName() + " ]";
        }
        return null;
    }

    public static void i(Object str) {
        print(Log.INFO, str);
    }

    public static void d(Object str) {
        print(Log.DEBUG, str);
    }

    public static void v(Object str) {
        print(Log.VERBOSE, str);
    }

    public static void w(Object str) {
        print(Log.WARN, str);
    }

    public static void e(Object str) {
        print(Log.ERROR, str);
    }

    /**
     * 用于区分不同接口数据 打印传入参数
     * @param index 日志级别
     * @param str   输出的日志内容
     */
    private static void print(int index, Object str) {
        if (!OPEN_LOG) {
            return;
        }
        if (log == null) {
            log = new LogUtil2(USER_NAME);
        }
        String name = log.getFunctionName();
        if (name != null) {
            str = name + " - " + str;
        }

        // 当DEBUG为false时关闭DEBUG日志
        if (!DEBUG) {
            if (index <= Log.DEBUG) {
                return;
            }
        }
        switch (index) {
            case Log.VERBOSE:
                Log.v(TAG, str.toString());
                break;
            case Log.DEBUG:
                Log.d(TAG, str.toString());
                break;
            case Log.INFO:
                Log.i(TAG, str.toString());
                break;
            case Log.WARN:
                Log.w(TAG, str.toString());
                break;
            case Log.ERROR:
                Log.e(TAG, str.toString());
                break;
            default:
                break;
        }
    }
}
