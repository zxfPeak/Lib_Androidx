package com.zxf.lib_androidx.views;

import android.content.Context;
import android.widget.LinearLayout;

import com.zxf.lib_androidx.R;


/**
 * 网络不可用是在顶部显示的一个“网络不可用”的布局提示。
 */
public class NetworkStatusLayout extends LinearLayout {
    public NetworkStatusLayout(Context context) {
        super(context);
        inflate(context, R.layout.layout_network_status, this);
    }
}
