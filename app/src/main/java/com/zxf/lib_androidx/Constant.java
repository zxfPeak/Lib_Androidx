package com.zxf.lib_androidx;

/**
 * 常量类
 * Created by zxf on 2018/10/8.
 */

public class Constant {

        private static final String HOST = "http://192.168.1.78:8769/";// 测试机服务器地址
//    private static final String HOST = "http://192.168.1.72:8769/";// 昕欣服务器地址
    //    private static final String HOST = "http://192.168.1.210:8769/";// 艺文服务器地址
    private static final String RELEASE_HOST = "http://110.80.46.92:8768/";// 外网服务器地址

    private static final String BASEINFO = HOST + "community-app/app/baseinfo/";// baseinfo
    public static final String CODE_INVALID_TOKEN = "401";
    public static final String CODE_INVALID_TOKEN_CCAD_WORKSTATION = "994";
    public static final String LOGIN = BASEINFO + "login";// 登录的接口
    public static final long COUNT_DOWN_TOTAL_TIME = 60 * 1000;// 倒计时时间
    public static final long COUNT_DOWN_INTERVAL_TIME = 1000;// 倒计时间隔
}
