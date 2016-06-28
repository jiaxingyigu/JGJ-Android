package com.yigu.jgj.activity.task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.task.TaskDetailAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskDetailActivity extends AppCompatActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    TaskDetailAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvCenter.setText("任务详情");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new TaskDetailAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.back)
    public void onClick() {
    }
}
