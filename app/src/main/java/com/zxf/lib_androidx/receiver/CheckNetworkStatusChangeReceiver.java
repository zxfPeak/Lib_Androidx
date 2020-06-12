package com.zxf.lib_androidx.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zxf.lib_androidx.listener.CheckNetworkStatusChangeListener;


/**
 * 监听网络变化的广播
 */
public class CheckNetworkStatusChangeReceiver extends BroadcastReceiver {
    public static final String EVENT = "event";
    public static final String ACTION = "action";
    private CheckNetworkStatusChangeListener mCheckNetworkStatusChangeListener;

    public CheckNetworkStatusChangeReceiver() {

    }

    public void setCheckNetworkStatusChangeListener(CheckNetworkStatusChangeListener mCheckNetworkStatusChangeListener) {
        this.mCheckNetworkStatusChangeListener = mCheckNetworkStatusChangeListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        CheckNetworkStatusChangeListener.Status mStatus = (CheckNetworkStatusChangeListener.Status) intent.getSerializableExtra(EVENT);
        mCheckNetworkStatusChangeListener.onEvent(mStatus);
    }
}
