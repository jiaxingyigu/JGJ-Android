package com.yigu.jgj.activity.daily;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.yigu.jgj.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DailyThirdActivity extends AppCompatActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.editText)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_third);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvCenter.setText("日常巡查");
    }


    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
