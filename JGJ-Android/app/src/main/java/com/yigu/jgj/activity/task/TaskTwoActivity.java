package com.yigu.jgj.activity.task;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.company.CompanyFragmentAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.fragment.AssignTaskFragment;
import com.yigu.jgj.fragment.OtherTaskFragment;
import com.yigu.jgj.fragment.TaskDanagerFragment;
import com.yigu.jgj.fragment.TaskLicenseFragment;
import com.yigu.jgj.fragment.WithoutLicenseFragment;
import com.yigu.jgj.util.ControllerUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskTwoActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    private List<Fragment> list = new ArrayList<>();
    private List<String> list_title = new ArrayList<>();
    private FragmentManager fragmentManager;
    public FragmentTransaction transaction;
    private TaskDanagerFragment taskDanagerFragment;
    private TaskLicenseFragment taskLicenseFragment;
    private OtherTaskFragment otherTaskFragment;
    CompanyFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_task_list);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvCenter.setText("我的任务");
        taskDanagerFragment = new TaskDanagerFragment();
        taskLicenseFragment = new TaskLicenseFragment();
        otherTaskFragment= new OtherTaskFragment();
        list.add(taskDanagerFragment);
        list.add(taskLicenseFragment);
        list.add(otherTaskFragment);
        list_title.add("隐患");
        list_title.add("无照上报");
        list_title.add("其他");
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tablayout.addTab(tablayout.newTab().setText(list_title.get(0)));
        tablayout.addTab(tablayout.newTab().setText(list_title.get(1)));
        tablayout.addTab(tablayout.newTab().setText(list_title.get(2)));
        mAdapter = new CompanyFragmentAdapter(getSupportFragmentManager(), list, list_title);
        viewpager.setAdapter(mAdapter);
        tablayout.setupWithViewPager(viewpager);
    }

    @OnClick({R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
