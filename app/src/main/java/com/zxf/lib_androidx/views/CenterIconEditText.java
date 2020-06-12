package com.zxf.lib_androidx.views;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * 图标居中的EditText
 * Created by zxf on 2018/11/6.
 */

public class CenterIconEditText extends AppCompatEditText {

    private Drawable searchDrawable;
    private int offset;
    private int searchWidth;
    private String hintString;
    private int w;
    private int flag = 0;

    public CenterIconEditText(Context context) {
        super(context);
        init();
    }

    public CenterIconEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CenterIconEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 获得焦点
                    setTextAlignment(TextView.TEXT_ALIGNMENT_VIEW_START);
                } else {
                    // 失去焦点
                    setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                }
            }
        });
        setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        searchWidth = getMeasuredWidth();
        hintString = getHint().toString();
        Paint paint = new Paint();
        Rect rect = new Rect();
        paint.getTextBounds(hintString, 0, hintString.length(), rect);
        w = dip2px(getContext(), rect.width());
        offset = searchWidth / 2 - w * 2;
        if (flag == 0) {
            setTextDrawable();
        }
        flag++;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (searchDrawable == null) {
            getDrawable();
        }
        if (length() > 0) {
            setTextAlignment(TextView.TEXT_ALIGNMENT_VIEW_START);
            setCompoundDrawables(null, null, null, null);
        } else if (length() == 0) {
            setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            setTextDrawable();
        }
    }

    void getDrawable() {
        Drawable[] compoundDrawables = getCompoundDrawables();
        searchDrawable = compoundDrawables[0];
    }

    void setTextDrawable() {
        searchDrawable.setBounds(offset, 0, offset + searchDrawable.getIntrinsicWidth(), searchDrawable.getIntrinsicHeight());
        setCompoundDrawables(searchDrawable, null, null, null);
    }

}
