package com.zxf.lib_androidx.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import com.zxf.lib_androidx.R;


/**
 * 自定义ViewPager的圆形指示器
 */
public class CustomIndicator extends View {

    private Paint paintStroke;
    private Paint paintFill;
    private int paintStrokeColor;
    private int paintFillColor;
    int radius = 10;// 圆的半径
    private float offset;
    private int num;

    public CustomIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        num = array.getInt(R.styleable.ViewPagerIndicator_circleNum, 2);
        // 这里16进制的颜色必须加ff，设置为不透明，否则不显示。
        paintStrokeColor = array.getColor(R.styleable.ViewPagerIndicator_paintStrokeColor, 0xFFE6E6E6);// 灰色
        paintFillColor = array.getColor(R.styleable.ViewPagerIndicator_paintFillColor, 0xFF059FFF);// 蓝色

        initPaint();
    }

    public void setCircleNum(int num) {
        this.num = num;
    }

    private void initPaint() {
        paintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintStroke.setStyle(Style.FILL);
        paintStroke.setColor(paintStrokeColor);
        paintStroke.setStrokeWidth(2);

        paintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintFill.setStyle(Style.FILL);
        paintFill.setColor(paintFillColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cx = getWidth() / 2 - (num - 1) * 1.5f * radius;
        int cy = getHeight() / 2;
        for (int i = 0; i < num; i++) {
            // 画空心圆
            canvas.drawCircle(cx + i * 3 * radius, cy, radius, paintStroke);
        }
        // 画实心圆
        canvas.drawCircle(cx + offset, cy, radius, paintFill);
    }

    /**
     * 指示器滑动，没有滑动过程的效果
     * @param position 指示器的当前个数
     */
    public void move(int position) {
        offset = position * 3 * radius;
        invalidate();
    }

    /**
     * 指示器滑动，有滑动过程的效果
     * @param position       指示器的当前个数
     * @param positionoffset 偏移量
     */
    public void move(int position, float positionoffset) {
        offset = position * 3 * radius + positionoffset * 3 * radius;
        if (position >= 2) {
            offset = position * 3 * radius;
        }
        // 刷新布局
        invalidate();
    }
}
