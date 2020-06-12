package com.zxf.lib_androidx.utils;

/**
 * 防止多次点击的工具类
 * Created by zxf on 2019/2/12.
 */

public class ClickUtils {
    private static final int DELAY = 1000;// 多次点击间隔不超过1000毫秒
    private static long lastClickTime = 0;// 最后一次点击的时间

    /**
     * @return 不是快速点击
     */
    public static boolean isNotFastClick() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > DELAY) {
            lastClickTime = currentTime;
            return true;
        } else {
            return false;
        }
    }
}
