package com.liulishuo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.liulishuo.R;
import com.liulishuo.utils.DeviceUtils;

/**
 * Created by fei on 16/6/8.
 */
public class BallLayout extends ViewGroup {

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;


    private int mNumberOfBalls = 5;
    private int mScreenWidth;
    private int mScreenHeight;

    private int mOriention = 0;


    public BallLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BallLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public BallLayout(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        mScreenHeight = DeviceUtils.getScreenHeight(getContext());
        mScreenWidth = DeviceUtils.getScreenWidth(getContext());

        if (attrs != null) {
            TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.BallLayout);
            mNumberOfBalls = attributes.getInt(R.styleable.BallLayout_balls, mNumberOfBalls);
            mOriention = attributes.getInt(R.styleable.BallLayout_orientation, mOriention);
            attributes.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            if (mOriention == HORIZONTAL) {
                int itemWidth = mScreenWidth / mNumberOfBalls;

                int widthSpec = MeasureSpec.makeMeasureSpec(itemWidth / 4, MeasureSpec.EXACTLY);
                measureChild(child, widthSpec, widthSpec);
            } else if (mOriention == VERTICAL) {
                int itemWidth = mScreenHeight / mNumberOfBalls;

                int widthSpec = MeasureSpec.makeMeasureSpec(itemWidth / 4, MeasureSpec.EXACTLY);
                measureChild(child, widthSpec, widthSpec);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mOriention == HORIZONTAL) {
            for (int i = 0; i < getChildCount(); i++) {
                int itemWidth = mScreenWidth / mNumberOfBalls;

                View childView = getChildAt(i);

                // 获取在onMeasure中计算的视图尺寸
                int measureHeight = childView.getMeasuredHeight();
                int measuredWidth = childView.getMeasuredWidth();


                int x = itemWidth * i;
                int y = (int) (Math.random() * mScreenHeight);
                childView.layout(x, y, x + measuredWidth * 2, y + measureHeight * 2);
            }
        } else if (mOriention == VERTICAL) {
            for (int i = 0; i < getChildCount(); i++) {
                int itemWidth = mScreenHeight / mNumberOfBalls;

                View childView = getChildAt(i);

                // 获取在onMeasure中计算的视图尺寸
                int measureHeight = childView.getMeasuredHeight();
                int measuredWidth = childView.getMeasuredWidth();


                int x = (int) (Math.random() * itemWidth);
                int y = itemWidth * i;
                childView.layout(x, y, x + measuredWidth * 2, y + measureHeight * 2);
            }
        }
    }
}
