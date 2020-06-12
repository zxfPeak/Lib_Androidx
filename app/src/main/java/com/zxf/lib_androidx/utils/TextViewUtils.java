package com.zxf.lib_androidx.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

public final class TextViewUtils {

    /**
     * 将图片设置在TextView的上方
     * @param context
     * @param drawableRes
     * @param tvName
     */
    public static void setTextDrawable(Context context, int drawableRes, TextView tvName) {
        Drawable drawableTop = context.getResources().getDrawable(drawableRes);
        // 必须设置图片大小，否则不显示
        drawableTop.setBounds(0, 0, drawableTop.getMinimumWidth(), drawableTop.getMinimumHeight());
        if (null != tvName) {
            tvName.setCompoundDrawables(drawableTop, null, null, null);
        }
    }

    public static void setTextTopDrawable(Context context, int drawableRes, TextView tvName) {
        Drawable drawableTop = context.getResources().getDrawable(drawableRes);
        // 必须设置图片大小，否则不显示
        drawableTop.setBounds(0, 0, drawableTop.getMinimumWidth(), drawableTop.getMinimumHeight());
        if (null != tvName) {
            tvName.setCompoundDrawables(null, drawableTop, null, null);
        }
    }

    public static void setTextLeftDrawable(Context context, int drawableRes, TextView tvName) {
        Drawable drawableLeft = context.getResources().getDrawable(drawableRes);
        // 必须设置图片大小，否则不显示
        drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
        if (null != tvName) {
            tvName.setCompoundDrawables(drawableLeft, null, null, null);
        }
    }

    public static void setTextDrawable(Context context, Bitmap bitmap, TextView tvName) {
        Drawable drawableTop = BitmapUtils.bitmap2Drawable(bitmap);
        // 必须设置图片大小，否则不显示
        drawableTop.setBounds(0, 0, drawableTop.getMinimumWidth(), drawableTop.getMinimumHeight());
        tvName.setCompoundDrawables(null, drawableTop, null, null);
    }

    //	public static void setTextDrawable(Context context, String url, final TextView textView) {
    //		ImageLoader.getInstance().loadImage(url, new ImageLoadingListener() {
    //			public void onLoadingStarted(String arg0, View arg1) {
    //			}
    //
    //			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
    //			}
    //
    //			public void onLoadingComplete(String arg0, View arg1, Bitmap bitmap) {
    //				Drawable drawableTop = BitmapUtils.bitmap2Drawable(bitmap);
    //				// 必须设置图片大小，否则不显示
    //				drawableTop.setBounds(0, 0, drawableTop.getMinimumWidth(), drawableTop.getMinimumHeight());
    //				textView.setCompoundDrawables(null, drawableTop, null, null);
    //			}
    //
    //			public void onLoadingCancelled(String arg0, View arg1) {
    //			}
    //		});
    //	}

}
