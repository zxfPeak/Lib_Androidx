package com.zxf.lib_androidx.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zxf.lib_androidx.listener.CheckNetworkStatusChangeListener;


public class ConnectionUtils {
    /**
     * 是否是WIFI连接
     * @param context 上下文
     * @return 是否是WIFI连接
     */
    public static boolean isWIFI(Context context) {
        if (isConnected(context)) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            int type = info.getType();
            if (ConnectivityManager.TYPE_WIFI == type) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是手机移动网络
     * @param context 上下文
     * @return 是否是手机移动网络
     */
    public static boolean isMobile(Context context) {
        if (isConnected(context)) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (null != cm) {
                NetworkInfo info = cm.getActiveNetworkInfo();
                int type = info.getType();
                if (ConnectivityManager.TYPE_MOBILE == type) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 网络是否连接
     * @param context 上下文
     * @return 网络是否连接
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = null;
        if (cm != null) {
            info = cm.getActiveNetworkInfo();
        }
        if (info == null) {
            return false;
        }
        return info.isAvailable();
    }

    /**
     * 获取当前网络类型 CheckNetworkStatusChangeListener.Status
     * @return 返回网络类型 CheckNetworkStatusChangeListener.Status
     */
    public static CheckNetworkStatusChangeListener.Status getNetworkConnectionType(Context context) {
        //获取连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
            return CheckNetworkStatusChangeListener.Status.TYPE_NO_NETWORK;
        //获取网络连接信息
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return CheckNetworkStatusChangeListener.Status.TYPE_WIFI;
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return CheckNetworkStatusChangeListener.Status.TYPE_MOBILE;
            }
        }
        return CheckNetworkStatusChangeListener.Status.TYPE_NO_NETWORK;
    }

}
