package com.zxf.lib_androidx.model;

import android.text.TextUtils;

import com.zxf.lib_androidx.utils.GsonUtils;

import org.json.JSONObject;
import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;


/**
 * 解析器，自动解析请求结果
 * Created by zxf on 2018/10/10.
 */

public class JsonResponseParser implements ResponseParser {

    public void checkResponse(UriRequest request) throws Throwable {
    }

    @Override
    public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {
        try {
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            new JSONObject(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return GsonUtils.parseJSON(result, resultClass);
    }
}