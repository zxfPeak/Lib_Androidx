package com.zxf.lib_androidx.listener;


/**
 * Created by zxf on 2019/12/17.
 * 网络状态改变的监听
 */

public interface CheckNetworkStatusChangeListener {
    /*
     * 网络变化会调用
     */
    void onEvent(Status status);

    /**
     * 网络状态
     * TYPE_UN_NETWORK 沒有网络
     * TYPE_WIFI WiFi连接
     * TYPE_MOBILE 移动数据
     */
    enum Status {
        TYPE_NO_NETWORK, TYPE_WIFI, TYPE_MOBILE,
    }
}
