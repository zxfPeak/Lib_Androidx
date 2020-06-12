package com.zxf.lib_androidx.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * SharedPreferences工具类
 * Created by zxf on 2018/9/19.
 */
public class SharedPreferencesUtils {

    /**
     * SharedPreferences的名称
     */
    private static String SHARED_PREFERENCE_NAME = "rihuaSP";
    private static SharedPreferencesUtils sharedPreferencesUtils;
    private static SharedPreferences sharedPreferences;

    private SharedPreferencesUtils(Context context) {
        if (null == sharedPreferences) {
            synchronized (SharedPreferencesUtils.class) {
                sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
            }
        }
    }

    /**
     * @param context 上下文
     * @return SharedPreferences实例
     */
    public static SharedPreferencesUtils getInstance(Context context) {
        if (null == sharedPreferencesUtils) {
            return new SharedPreferencesUtils(context);
        }
        return sharedPreferencesUtils;
    }

    /**
     * 设置布尔值
     * @param key   要保存的key
     * @param value 要保存的value
     */
    public void setBooleanValue(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * @param key 要获取的key
     * @return 获取布尔值
     */
    public boolean getBooleanValue(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * 设置字符串值
     * @param key   要保存的key
     * @param value 要保存的value
     */
    public void setStringValue(String key, String value) {
        sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * @param key 要获取的key
     * @return 获取字符串值
     */
    public String getStringValue(String key) {
        return sharedPreferences.getString(key, "");
    }

    /**
     * 设置Long类型
     * @param key   要保持的key
     * @param value 要保持的值
     */
    public void setLongValue(String key, long value) {
        sharedPreferences.edit().putLong(key, value).commit();
    }

    /**
     * @param key 要获取的key
     * @return 获取long类型的值
     */
    public long getLongValue(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    /**
     * 设置整型值
     * @param key   要保存的key
     * @param value 要保存的值
     */
    public void setIntValue(String key, int value) {
        sharedPreferences.edit().putInt(key, value).commit();
    }

    /**
     * @param key 要获取的key
     * @return 获取整型值
     */
    public int getIntValue(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    /**
     * @return 是否清除所有数据
     */
    public boolean clearAll() {
        return sharedPreferences.edit().clear().commit();
    }

    /**
     * @param key 要清除的key
     * @return 是否清除某项数据
     */
    public boolean clearItem(String key) {
        return sharedPreferences.edit().remove(key).commit();
    }

    /**
     * @param key 要判断的key
     * @return 是否包含某项
     */
    public boolean isContainKey(String key) {
        return sharedPreferences.contains(key);
    }


    /**
     * 保存字符串数组
     * @param key
     * @param values
     */
    @SuppressLint("ApplySharedPref")
    public void setStringList(String key, List<String> values) {
        String listJson = new Gson().toJson(values);
        sharedPreferences.edit().putString(key, listJson).commit();
    }

    /**
     * 获取字符串数组
     * @param key
     * @return
     */
    public List<String> getStringList(String key) {
        String json = sharedPreferences.getString(key, "");
        ArrayList<String> stringList = GsonUtils.jsonToArrayList(json, String.class);
        return stringList;
    }
}

