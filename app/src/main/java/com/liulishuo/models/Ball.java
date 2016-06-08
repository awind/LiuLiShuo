package com.liulishuo.models;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by fei on 16/6/8.
 */
public class Ball {
    private Paint mPaint;
    private float mSize;
    private Point mPosition;
    private int mColor;

    public Ball(int size) {
        this.mSize = size;
        init();
    }

    public Ball(float size, int color) {
        this.mColor = color;
        this.mSize = size;
        init();
    }

    private void init() {
        initPaint();
        initPoint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
    }

    private void initPoint() {
        mPosition = new Point();
    }

    public Paint getPaint() {
        return mPaint;
    }

    public void setPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }

    public float getSize() {
        return mSize;
    }

    public void setSize(float mSize) {
        this.mSize = mSize;
    }

    public Point getPosition() {
        return mPosition;
    }

    public void setPosition(Point mPosition) {
        this.mPosition = mPosition;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }

    public void render(Canvas canvas) {
        canvas.drawCircle(mPosition.x, mPosition.y, mSize, mPaint);
    }
}
