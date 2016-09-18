package com.yigu.jgj.activity.assign;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.yigu.jgj.broadcast.ReceiverAction;
import com.yigu.jgj.fragment.AssignTaskFragment;
import com.yigu.jgj.fragment.WithoutLicenseFragment;
import com.yigu.jgj.util.ControllerUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssignTaskTwoActivity extends BaseActivity implements View.OnClickListener {
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
    private AssignTaskFragment assignTaskFragment;
    private WithoutLicenseFragment withoutLicenseFragment;
    CompanyFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_assigntask_list);
        ButterKnife.bind(this);
        initView();
        initStockReceiver();
    }

    private void initView() {
        tvCenter.setText("任务指令");
        tvRight.setText("新增");
        assignTaskFragment = new AssignTaskFragment();
        withoutLicenseFragment = new WithoutLicenseFragment();
        list.add(assignTaskFragment);
        list.add(withoutLicenseFragment);
        list_title.add("隐患");
        list_title.add("无照上报");
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tablayout.addTab(tablayout.newTab().setText(list_title.get(0)));
        tablayout.addTab(tablayout.newTab().setText(list_title.get(1)));
        mAdapter = new CompanyFragmentAdapter(getSupportFragmentManager(), list, list_title);
        viewpager.setAdapter(mAdapter);
        tablayout.setupWithViewPager(viewpager);
    }

    @OnClick({R.id.back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                ControllerUtil.go2AssignAdd();
                break;
        }
    }

    private TaskBroadCast taskBroadCast;

    private void initStockReceiver() {
        taskBroadCast = new TaskBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ReceiverAction.assignLicense_action);
        filter.addAction(ReceiverAction.assignDanager_action);
        filter.setPriority(Integer.MAX_VALUE);
        registerReceiver(taskBroadCast, filter);
    }

    /**
     * 企业修改和新增的的广播接受者
     */
    public class TaskBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ReceiverAction.assignDanager_action)) {
                if(null!=assignTaskFragment)
                    assignTaskFragment.refreshData();
            }
            if (action.equals(ReceiverAction.assignLicense_action)) {
                if(null!=withoutLicenseFragment)
                    withoutLicenseFragment.refreshData();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != taskBroadCast)
            unregisterReceiver(taskBroadCast);
    }



}
