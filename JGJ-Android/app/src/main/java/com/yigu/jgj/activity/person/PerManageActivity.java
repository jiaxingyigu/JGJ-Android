package com.yigu.jgj.activity.person;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.PerManageAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.widget.BestSwipeRefreshLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerManageActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    BestSwipeRefreshLayout swipeLayout;
    PerManageAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_manage);
        ButterKnife.bind(this);
        initView();
        initListener();
    }

    private void initView() {
        tvCenter.setText("人员管理");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new PerManageAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
        swipeLayout.setBestRefreshListener(new BestSwipeRefreshLayout.BestRefreshListener() {
            @Override
            public void onBestRefresh() {
                swipeLayout.setRefreshing(false);
            }
        });
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
}
