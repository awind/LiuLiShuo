package com.liulishuo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.liulishuo.views.BallView;

public class MainActivity extends AppCompatActivity {

    private BallView mBallView;
    private Toolbar mToolbar;
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
        mToolbar.setTitle(R.string.app_name);
        mRefresh = (TextView) mToolbar.findViewById(R.id.refresh);
        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBallView.reGenerate();
            }
        });

        mBallView = (BallView) findViewById(R.id.balls);
    }
}
