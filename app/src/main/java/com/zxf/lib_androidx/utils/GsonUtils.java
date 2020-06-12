package com.zxf.lib_androidx.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GsonUtils {

    /**
     * 解析json对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseJSON(String json, Class<T> clazz) {
        Gson gson = new Gson();
        T info = gson.fromJson(json, clazz);
        return info;
    }

    /**
     * Type type = new
     * TypeToken&lt;ArrayList&lt;TypeInfo>>(){}.getType();
     * <br>Type所在的包：java.lang.reflect
     * <br>TypeToken所在的包：com.google.gson.reflect.TypeToken
     * @param json json字符串
     * @param type
     * @return
     */
    public static <T> T parseJSONArray(String json, Type type) {
        Gson gson = new Gson();
        T infos = gson.fromJson(json, type);
        return infos;
    }

    public static <T> List<T> jsonToList(String json, Class<? extends T[]> clazz) {
        Gson gson = new Gson();
        T[] array = gson.fromJson(json, clazz);
        return Arrays.asList(array);
    }

    /**
     * 解析json数组
     * @param json  json字符串
     * @param clazz 数组里的实体
     * @param <T>   实体类型
     * @return 实体数组
     */
    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);
        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }


    private GsonUtils() {
    }
}
