package com.zxf.lib_androidx;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.zxf.lib_androidx.listener.CheckNetworkStatusChangeListener;
import com.zxf.lib_androidx.receiver.CheckNetworkStatusChangeReceiver;

import java.lang.ref.WeakReference;

/**
 * 自定义Handler，使用弱引用防止内存溢出
 */
public class SimpleHandler<T extends Activity> extends Handler {
    WeakReference<T> weakReference;

    public SimpleHandler(T t) {
        this.weakReference = new WeakReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (weakReference.get() != null) {
            //发送广播
            Intent mCheckNetworkIntent = new Intent();
            mCheckNetworkIntent.setAction(CheckNetworkStatusChangeReceiver.ACTION);
            CheckNetworkStatusChangeListener.Status status = (CheckNetworkStatusChangeListener.Status) msg.obj;
            mCheckNetworkIntent.putExtra(CheckNetworkStatusChangeReceiver.EVENT, status);
            weakReference.get().sendBroadcast(mCheckNetworkIntent);
        }
    }

}
