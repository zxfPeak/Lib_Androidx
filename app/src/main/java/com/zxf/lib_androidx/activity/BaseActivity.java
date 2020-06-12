package com.zxf.lib_androidx.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import androidx.fragment.app.FragmentActivity;

import com.zxf.lib_androidx.R;
import com.zxf.lib_androidx.utils.ConnectionUtils;
import com.zxf.lib_androidx.utils.StatusBarUtils;


/**
 * activity的基类示例
 * Created by zxf on 2018/9/10.
 */
public abstract class BaseActivity extends FragmentActivity {

    public static String TAG = "BaseActivity";
    public boolean isConnected;// 判断网络是否连接
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtils.with(this).setColor(getResources().getColor(R.color.bg_statusBar)).init();
        super.onCreate(savedInstanceState);
        checkConnected();
        initUI();
        initData();
        initListener();
        setView();
    }

    private void checkConnected() {
        isConnected = ConnectionUtils.isConnected(getApplication());
    }

    public abstract void initUI();

    public abstract void initData();

    public abstract void initListener();

    public abstract void setView();

    public static Context getContext() {
        return context;
    }
}
