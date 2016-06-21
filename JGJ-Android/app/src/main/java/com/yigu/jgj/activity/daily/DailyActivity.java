package com.yigu.jgj.activity.daily;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yigu.jgj.R;

import butterknife.ButterKnife;

public class DailyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        ButterKnife.bind(this);
    }
}
