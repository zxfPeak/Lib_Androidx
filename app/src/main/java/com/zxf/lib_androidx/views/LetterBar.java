package com.zxf.lib_androidx.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by zxf on 2019/08/28
 */
public class LetterBar extends View {
    // 绘制的列表导航字母
    private String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private Paint paint;
    private int index;
    private Paint touchPaint;
    boolean isPressed;

    ArrayList<String> letterList = new ArrayList<String>();

    public LetterBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initLetter();
    }

    private void initLetter() {
        for (int i = 0; i < letters.length; i++) {
            letterList.add(letters[i]);
        }
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(0xff0000ff);
        paint.setTextSize(30);
        paint.setStrokeWidth(2);
        paint.setTextAlign(Paint.Align.CENTER);

        touchPaint = new Paint();
        touchPaint.setColor(0xffff0000);
        touchPaint.setTextSize(35);
        touchPaint.setFakeBoldText(true);
        touchPaint.setStrokeWidth(2);
        touchPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth();
        int h = getHeight();
        //		float textW = paint.measureText(letters[0]);
        float x = w / 2;
        for (int i = 0; i < letterList.size(); i++) {
            float y = h / letterList.size() * i;
            if (i == index && isPressed == true) {
                canvas.drawText(letterList.get(i), x, y + 30, touchPaint);
                canvas.drawARGB(40, 88, 88, 88);
            } else {
                canvas.drawText(letterList.get(i), x, y + 30, paint);
                // canvas.drawARGB(255, 255, 255, 255);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        index = (int) (y / (getHeight() / letterList.size()));
        if (index > letterList.size() - 1) {
            index = letterList.size() - 1;
        }
        if (index < 0) {
            index = 0;
        }
        String letter = letterList.get(index);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isPressed = true;
                listener.onLetterTouch(letter);
                break;
            case MotionEvent.ACTION_UP:
                isPressed = false;
                listener.onLetterTouchUp();
                break;
            case MotionEvent.ACTION_MOVE:
                isPressed = true;
                listener.onLetterTouch(letter);
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }

    /*设置当前按下的是那个字母*/
    public void setTouchIndex(String word) {
        for (int i = 0; i < letters.length; i++) {
            if (letters[i].equals(word)) {
                index = i;
                invalidate();
                return;
            }
        }
    }

    public interface OnLetterTouchListener {
        void onLetterTouch(String letter);

        void onLetterTouchUp();
    }


    private OnLetterTouchListener listener;

    public void setOnLetterTouchListener(OnLetterTouchListener touchListener) {
        listener = touchListener;
    }

    public void setLetter(ArrayList<String> letterList) {
        this.letterList.clear();
        this.letterList.addAll(letterList);
        invalidate();
    }
}
