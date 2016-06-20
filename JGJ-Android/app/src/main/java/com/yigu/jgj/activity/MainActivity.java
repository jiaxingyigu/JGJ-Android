package com.yigu.jgj.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showLoading();
    }
}
