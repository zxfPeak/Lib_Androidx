package com.zxf.lib_androidx.apkUpdate;

import android.content.Context;
import android.util.Log;

public class UpdateChecker {


    public static void checkForDialog(Context context, String apkUrl, String content, int serviceVersion) {
        if (context != null) {
            new CheckUpdateTask(context, apkUrl, content, serviceVersion, Constants.TYPE_DIALOG, true).execute();
        } else {
            Log.e(Constants.TAG, "The arg context is null");
        }
    }


    public static void checkForNotification(Context context, String apkUrl,String content,  int serviceVersion) {
        if (context != null) {
            new CheckUpdateTask(context, apkUrl, content, serviceVersion, Constants.TYPE_NOTIFICATION, false).execute();
        } else {
            Log.e(Constants.TAG, "The arg context is null");
        }

    }


}
