package com.liulishuo.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.liulishuo.R;
import com.liulishuo.Utils;
import com.liulishuo.models.Ball;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by fei on 16/6/8.
 */
public class BallView extends View {

    private static final String TAG = "BallView";

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;


    private List<Ball> mBalls;
    private int mNumberOfBalls = 5;
    private int mScreenWidth;
    private int mScreenHeight;

    private int mOriention = 0;


    public BallView(Context context) {
        super(context);
        init(null);
    }

    public BallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mScreenHeight = Utils.getScreenHeight(getContext());
        mScreenWidth = Utils.getScreenWidth(getContext());

        if (attrs != null) {
            TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.BallView);
            mNumberOfBalls = attributes.getInt(R.styleable.BallView_balls, mNumberOfBalls);
            mOriention = attributes.getInt(R.styleable.BallView_orientation, mOriention);
            attributes.recycle();
        }
        createBalls();
    }

    private int generateRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }


    public void reGenerate() {
        mBalls.clear();
        createBalls();
        invalidate();
    }


    private void createBalls() {
        mBalls = new ArrayList<>();

        for (int i = 0; i < mNumberOfBalls; i++) {

            // Check Oriention
            if (mOriention == HORIZONTAL) {
                int itemWidth = mScreenWidth / mNumberOfBalls;
                // ball random position
                int offsetX = (int) (Math.random() * itemWidth);
                int offsetY = (int) (Math.random() * mScreenHeight);
                // ball size
                int size = 0;

                if (itemWidth - offsetX < offsetX) {
                    size = (itemWidth - offsetX) / 2;
                } else {
                    size = offsetX / 2;
                }

                Ball ball = new Ball(size, generateRandomColor());


                ball.setPosition(new Point(itemWidth * i + offsetX, offsetY));
                mBalls.add(ball);

            } else if (mOriention == VERTICAL) {

                int itemWidth = mScreenHeight / mNumberOfBalls;

                int offsetX = (int) (Math.random() * mScreenWidth);
                int offsetY = (int) (Math.random() * itemWidth);
                int size = 0;

                if (itemWidth - offsetY < offsetY) {
                    size = (itemWidth - offsetY) / 2;
                } else {
                    size = offsetY / 2;
                }

                Ball ball = new Ball(size, generateRandomColor());
                ball.setPosition(new Point(offsetX, itemWidth * i + offsetY));
                mBalls.add(ball);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Ball ball : mBalls) {
            ball.render(canvas);
        }
    }
}
