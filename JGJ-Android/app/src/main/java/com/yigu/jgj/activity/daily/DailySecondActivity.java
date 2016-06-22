package com.yigu.jgj.activity.daily;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DailySecondActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_second);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick({R.id.back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                break;
        }
    }

    private void initView(){
        tvCenter.setText("日常巡查");
        tvCenter.setText("下一步");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);

    }

}
