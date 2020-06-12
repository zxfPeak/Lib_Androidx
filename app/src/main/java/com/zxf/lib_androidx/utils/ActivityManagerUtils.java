package com.zxf.lib_androidx.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Activity管理工具类
 * Created by zxf on 2018/9/19.
 */

public class ActivityManagerUtils {
    /**
     * 存放Activity的map
     */
    private static Map<String, Activity> activitys = new HashMap<String, Activity>();

    /**
     * 获取管理类中注册的所有Activity的map
     * @return 所有Activity的map
     */
    public static Map<String, Activity> getActivitys() {
        return activitys;
    }

    /**
     * 根据键值取对应的Activity
     * @param key 键值
     * @return 键值对应的Activity
     */
    public static Activity getActivity(String key) {
        return activitys.get(key);
    }

    /**
     * 添加Activity
     * @param value 要添加的Activity
     * @param key   保存起来的key
     */
    public static void addActivity(Activity value, String key) {
        activitys.put(key, value);
    }

    /**
     * 判断一个activity是否已经加入到管理类中
     * @param key activity的key
     */
    public static void removeForegroundActivity(String key) {
        Map<String, Activity> activitys = getActivitys();
        for (Map.Entry<String, Activity> entry : activitys.entrySet()) {
            String key1 = entry.getKey();
            if (key1.equals(key)) {
                Activity value = entry.getValue();
                value.finish();
            }
        }
    }

    /**
     * 将key对应的Activity移除掉
     * @param key 要删除的Activity的key
     */
    public static void removeActivity(String key) {
        activitys.remove(key);
    }

    //    /**
    //     * 根据key找到并结束页面
    //     * @param key 要结束并删除的activity的key
    //     */
    //    public static void removeAndFinishActivity(String key) {
    //        isContainerActivity(key);
    //        activitys.remove(key);
    //        activitys.get(key).finish();
    //    }

    /**
     * finish掉所有的Activity移除所有的Activity
     */
    public static void removeAllActivity() {
        Iterator<Activity> iterActivity = activitys.values().iterator();
        while (iterActivity.hasNext()) {
            iterActivity.next().finish();
        }
        activitys.clear();
    }
    /**
     * 判断某个界面是否在前台
     *
     * @param activity 要判断的Activity
     * @return 是否在前台显示
     */
    public static boolean isForeground(Activity activity) {
        return isForeground(activity, activity.getClass().getName());
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context   Context
     * @param className 界面的类名
     * @return 是否在前台显示
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className))
            return false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                cpn = list.get(0).topActivity;
            }
            if (className.equals(cpn.getClassName()))
                return true;
        }
        return false;
    }

    /**
     * 这个方法需要app获取android.permission.REAL_GET_TASKS权限
     * Android 5.0以上只对系统应用开放。
     * Android5.0以下需要添加权限：
     * android.permission.GET_TASKS
     * @param context 上下文
     * @return 返回的栈顶activity名称为全路径名称。如：com.rihua.ccad_platform.trtc.TRTCAudioActivity
     */
    public static String getTopActivity(Context context) {
        String className = null;
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = mActivityManager.getRunningTasks(1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (!list.isEmpty() && list.get(0) != null && list.get(0).topActivity != null) {
                className = list.get(0).topActivity.getClassName();
            }
        }
        return className;
    }
}
