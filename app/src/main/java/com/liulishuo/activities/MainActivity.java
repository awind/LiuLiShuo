package com.liulishuo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.liulishuo.R;
import com.liulishuo.views.BallLayout;
import com.liulishuo.views.BallView;

public class MainActivity extends AppCompatActivity {

    private BallLayout mBallLayout;
    private Toolbar mToolbar;
    private TextView mTitleTv;
    private TextView mRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mTitleTv = (TextView) mToolbar.findViewById(R.id.title);
        mTitleTv.setText(R.string.app_name);
        mRefresh = (TextView) mToolbar.findViewById(R.id.refresh);
        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCircle();
            }
        });

        mBallLayout = (BallLayout) findViewById(R.id.balls);
        addCircle();
    }


    private void addCircle() {
        mBallLayout.removeAllViews();
        for (int i = 0; i < 5; i++) {
            final BallView ballView = new BallView(getApplicationContext());
            ballView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ballView.startAnimator();
                }
            });
            mBallLayout.addView(ballView);
        }
    }
}
