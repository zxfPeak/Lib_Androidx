package com.zxf.lib_androidx.utils;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zxf.lib_androidx.R;


/**
 * Toast的工具类
 * Created by zxf on 2017/12/15.
 */

public class ToastUtils {

    private static Toast mToast;

    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
            mToast = null;// toast隐藏后，将其置为null
        }
    };

    public static void showShortToast(Context context, String message) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_toast, null);// 自定义布局
        TextView text = (TextView) view.findViewById(R.id.toast_message);// 显示的提示文字
        text.setText(message);
        mHandler.removeCallbacks(r);
        if (mToast == null) {// 只有mToast==null时才重新创建，否则只需更改提示文字
            mToast = new Toast(context);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.BOTTOM, 0, 150);
            mToast.setView(view);
        }
        mHandler.postDelayed(r, 1500);// 延迟1.5秒隐藏toast
        mToast.show();
    }
}
