package com.yigu.jgj.activity.daily;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    @Bind(R.id.submit)
    TextView submit;

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

    @OnClick({R.id.back, R.id.add, R.id.file,R.id.submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add:
                break;
            case R.id.file:
                break;
            case R.id.submit:

                break;
        }
    }
}
