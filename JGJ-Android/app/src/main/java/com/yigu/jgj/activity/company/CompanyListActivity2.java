package com.yigu.jgj.activity.company;


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
import com.yigu.jgj.fragment.CompanyFragment;
import com.yigu.jgj.fragment.CompanyNoTitleFragment;
import com.yigu.jgj.util.ControllerUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompanyListActivity2 extends FragmentActivity implements View.OnClickListener {


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
    public Fragment fragment;
    CompanyFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_company_list2);
        ButterKnife.bind(this);
        inti();
    }

    private void inti() {
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
//        setdefault();


    }

    private void setdefault() {
        companyFragment = new CompanyFragment();
        fragment = companyFragment;
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, companyFragment);
        transaction.commit();
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
}
