package com.zxf.lib_androidx.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxf.lib_androidx.R;


/**
 * 自定义加载进度对话框
 * Created by zxf on 2019-01-16.
 */

public class LoadingDialog extends Dialog {

    private Animation animation;
    private TextView textView;
    private boolean isShowing = false;
    private final ImageView mImgLoading;

    public LoadingDialog(Context context) {
        super(context);
        //设置对话框背景透明
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.layout_loading);
        mImgLoading = findViewById(R.id.img_loading);
        textView = (TextView) findViewById(R.id.loading_message);
        setCanceledOnTouchOutside(false);
        animation = AnimationUtils.loadAnimation(context, R.anim.anim_rotate);
    }

    /**
     * 为加载进度个对话框设置不同的提示消息
     * @param message 给用户展示的提示信息
     * @return build模式设计，可以链式调用
     */
    public LoadingDialog setMessage(String message) {
        textView.setText(message);
        return this;
    }

    /**
     * 关闭对话框
     */
    public void dismissDialog() {
        dismiss();
    }

    /**
     * 显示对话框
     */
    public void showDialog() {
        mImgLoading.setAnimation(animation);
        mImgLoading.startAnimation(animation);
        show();
    }

    public boolean isShowing() {
        return isShowing;
    }
}
