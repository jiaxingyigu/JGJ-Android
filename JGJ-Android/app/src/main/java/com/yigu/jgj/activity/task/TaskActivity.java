package com.yigu.jgj.activity.task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.task.TaskAdapter;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.widget.BestSwipeRefreshLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskActivity extends AppCompatActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    BestSwipeRefreshLayout swipeLayout;
    TaskAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.bind(this);
        initView();
        initListener();
    }

    private void initView() {
        tvCenter.setText("我的任务");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new TaskAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
        swipeLayout.setBestRefreshListener(new BestSwipeRefreshLayout.BestRefreshListener() {
            @Override
            public void onBestRefresh() {
                swipeLayout.setRefreshing(false);
            }
        });
        mAdapter.setOnItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ControllerUtil.go2TaskDetail();
            }
        });
    }


    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
