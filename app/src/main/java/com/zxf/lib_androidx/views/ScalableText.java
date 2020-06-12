package com.zxf.lib_androidx.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zxf.lib_androidx.R;


/**
 * Created by zxf on 2019/8/13.
 */
public class ScalableText extends LinearLayout {

    protected TextView contentView; //文本正文
    protected TextView expandView; //展开按钮

    //对应styleable中的属性
    protected int textColor;
    protected float textSize;
    protected int maxLine;
    protected String text;

    //默认属性值
    public int defaultTextColor = Color.BLACK;
    public int defaultTextSize = 12;
    public int defaultLine = 4;
    public int blueColor = Color.parseColor("#0000ff");
    public int defaultBottomSpace = 4;

    public ScalableText(Context context) {
        super(context, null);
        init();
        bindTextView(defaultTextColor, defaultTextSize, defaultLine, text);
        initAttr(context, null);
        bindListener();
    }

    public ScalableText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        bindTextView(defaultTextColor, defaultTextSize, defaultLine, text);
        initAttr(context, attrs);
        bindListener();
    }

    public ScalableText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        bindTextView(defaultTextColor, defaultTextSize, defaultLine, text);
        initAttr(context, attrs);
        bindListener();
    }

    private void init() {
        setOrientation(VERTICAL); //设置垂直布局
        setGravity(Gravity.LEFT); //左对齐
        int padding = dip2px(getContext(), 5);
        final int space = dip2px(getContext(), defaultBottomSpace);
        //初始化textView并添加
        contentView = new TextView(getContext());
        LayoutParams llpContent = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        contentView.setLayoutParams(llpContent);
        addView(contentView, llpContent);
        //初始化“展开/收起”文本并添加
        expandView = new TextView(getContext());
        LayoutParams llp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        llp.setMargins(0, padding, 0, 0);
        expandView.setLayoutParams(llp);
        expandView.setText("全文");
        addView(expandView, llp);

        expandView.setOnClickListener(new OnClickListener() {
            boolean isExpand;// 是否折叠

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                contentView.clearAnimation();
                final int deltaValue;// 动画执行的相对距离
                final int startValue = contentView.getHeight();// 开始的距离
                int durationMillis = 200;
                if (isExpand) {
                    deltaValue = contentView.getLineHeight() * contentView.getLineCount() - startValue + space;
                    //                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    //                    animation.setDuration(durationMillis);
                    //                    animation.setFillAfter(true);
                    //                    expandView.startAnimation(animation);
                    expandView.setText("收起");
                } else {
                    deltaValue = contentView.getLineHeight() * maxLine - startValue + space;
                    //                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    //                    animation.setDuration(durationMillis);
                    //                    animation.setFillAfter(true);
                    //                    expandView.startAnimation(animation);
                    expandView.setText("全文");
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        contentView.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }
                };
                animation.setDuration(durationMillis);
                contentView.startAnimation(animation);
            }
        });
    }

    private void initAttr(Context context, AttributeSet attrs) {
        if (null == attrs) {
            return;
        }
        @SuppressLint("CustomViewStyleable") TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScalableTextStyle);
        textColor = typedArray.getColor(R.styleable.ScalableTextStyle_textColor, defaultTextColor);  //取颜色值，默认defaultTextColor
        textSize = typedArray.getDimensionPixelSize(R.styleable.ScalableTextStyle_textSize, defaultTextSize);//取颜字体大小，默认defaultTextSize
        maxLine = typedArray.getInt(R.styleable.ScalableTextStyle_maxLine, defaultLine);//取颜显示行数，默认defaultLine
        text = typedArray.getString(R.styleable.ScalableTextStyle_text);//取文本内容

        //绑定到textView   bindTextView(textColor,textSize,maxLine,text);
        typedArray.recycle();//回收释放
    }

    //绑定到textView
    protected void bindTextView(int color, float size, final int line, String text) {
        contentView.setTextColor(color);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        contentView.setText(text);
        int space = dip2px(getContext(), defaultBottomSpace);
        contentView.setHeight(contentView.getLineHeight() * line + space);
        expandView.setTextColor(blueColor);
        post(new Runnable() {
            @Override
            public void run() {
                // 由于在OnCreate方法中定义设置的textView不会马上渲染并显示，
                // 所以textview的getLineCount()获取到的值一般都为零，
                // 因此使用post会在其绘制完成后对 TextView(展开、收起) 进行显示控制
                int visible = contentView.getLineCount() > line ? View.VISIBLE : View.GONE;
                expandView.setVisibility(visible);
            }
        });
    }

    protected void bindListener() {
        setOnClickListener(new OnClickListener() {
            boolean isExpand;// 是否折叠

            @Override
            public void onClick(View v) {

            }
        });
    }

    public TextView getTextView() {
        return contentView;
    }

    public void setText(CharSequence charSequence) {
        contentView.setText(charSequence);
    }

    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
