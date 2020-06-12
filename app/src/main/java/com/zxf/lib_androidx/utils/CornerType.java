package com.zxf.lib_androidx.utils;

/**
 * 圆角规则的枚举类型
 * Created by zxf on 2017/12/20.
 */

public enum CornerType {
    LEFT, // 左上角 + 左下角
    RIGHT, // 右上角 + 右下角
    TOP, // 左上角 + 右上角
    BOTTOM, // 左下角 + 右下角
    ALL // 四角
}
