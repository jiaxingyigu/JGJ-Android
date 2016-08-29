package com.yigu.jgj.activity.company;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.company.CompanyFragmentAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.broadCast.ReceiverAction;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.fragment.CompanyFragment;
import com.yigu.jgj.fragment.CompanyNoTitleFragment;
import com.yigu.jgj.util.ControllerUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompanyListActivity extends BaseActivity implements View.OnClickListener {
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
    private CompanyFragment companyFragment;
    private CompanyNoTitleFragment companyNotitleFragment;
    CompanyFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_company_list);
        ButterKnife.bind(this);
        initView();
        initStockReceiver();
    }

    private void initView() {
        tvCenter.setText("企业管理");
        tvRight.setText("新增");
        companyFragment = new CompanyFragment();
        companyNotitleFragment = new CompanyNoTitleFragment();
        list.add(companyFragment);
        list.add(companyNotitleFragment);
        list_title.add("有照");
        list_title.add("无照");
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
                ControllerUtil.go2CompanyAdd();
                break;
        }
    }

    private CompanyBroadCast companyBroadCast;

    private void initStockReceiver() {
        companyBroadCast = new CompanyBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ReceiverAction.addCompany_action);
        filter.addAction(ReceiverAction.addCompanyNoTitle_action);
        filter.addAction(ReceiverAction.updateCompany_action);
        filter.setPriority(Integer.MAX_VALUE);
        registerReceiver(companyBroadCast, filter);
    }

    /**
     * 企业修改和新增的的广播接受者
     */
    public class CompanyBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ReceiverAction.updateCompany_action)) {//修改
                if(null!=companyFragment)
                    companyFragment.refreshData();
                if(null!=companyNotitleFragment)
                    companyNotitleFragment.refreshData();
            }

            if(action.equals(ReceiverAction.addCompany_action)){//有照新增
                if(null!=companyFragment) {
                    companyFragment.refreshData();
                }
            }
            if(action.equals(ReceiverAction.addCompanyNoTitle_action)){//无照新增
                if(null!=companyNotitleFragment)
                    companyNotitleFragment.refreshData();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != companyBroadCast)
            unregisterReceiver(companyBroadCast);
    }
}
