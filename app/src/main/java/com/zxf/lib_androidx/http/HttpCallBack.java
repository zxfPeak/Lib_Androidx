package com.zxf.lib_androidx.http;

import android.text.TextUtils;

import com.zxf.lib_androidx.Constant;
import com.zxf.lib_androidx.activity.BaseActivity;
import com.zxf.lib_androidx.model.BaseModel;
import com.zxf.lib_androidx.model.login.LoginModel;
import com.zxf.lib_androidx.model.login.Value;
import com.zxf.lib_androidx.utils.GsonUtils;
import com.zxf.lib_androidx.utils.LogTool;
import com.zxf.lib_androidx.utils.SharedPreferencesUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * HTTP请求的回调
 * Created by zxf on 2018/9/19.
 */

public class HttpCallBack implements Callback.CommonCallback<String> {

    public boolean isRegetToken = false;// 是否重新获取token

    public void onResponse(String json) {
    }

    public void afterLogin() {
    }

    @Override
    public void onSuccess(final String json) {
        LogTool.info("请求成功：" + json);
        BaseModel baseModel = GsonUtils.parseJSON(json, BaseModel.class);
        String status = baseModel.getStatus();
        if (!TextUtils.isEmpty(status) && status.equals(Constant.CODE_INVALID_TOKEN)) {// token失效
            login();
        } else {
            onResponse(json);
        }
    }

    @Override
    public void onError(Throwable throwable, boolean b) {
        LogTool.info("请求失败：" + throwable.getMessage());
    }

    @Override
    public void onCancelled(CancelledException e) {
        LogTool.info("请求取消");
    }

    @Override
    public void onFinished() {
        LogTool.info("请求完成");
    }

    /**
     * 重新登录获取新的token
     */
    private void login() {
        final SharedPreferencesUtils instance = SharedPreferencesUtils.getInstance(BaseActivity.getContext());
        String userAccount = instance.getStringValue("userAccount");
        String userPassword = instance.getStringValue("userPassword");
        RequestParams requestParams = new RequestParams(Constant.LOGIN);
        requestParams.addBodyParameter("userAccount", userAccount);
        requestParams.addBodyParameter("userPassword", userPassword);

        x.http().post(requestParams, new HttpCallBack() {
            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                LoginModel loginModel = GsonUtils.parseJSON(json, LoginModel.class);
                Value value = loginModel.getValue();
                String token = value.getToken();
                instance.setStringValue("token", token);
                LogTool.info("回调中再次获取token");
                HttpCallBack.this.afterLogin();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                super.onError(throwable, b);
            }
        });
    }

}
