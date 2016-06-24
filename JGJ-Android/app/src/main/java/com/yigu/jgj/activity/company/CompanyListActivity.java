package com.yigu.jgj.activity.company;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.fragment.CompanyFragment;
import com.yigu.jgj.fragment.CompanyNoTitleFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompanyListActivity extends FragmentActivity implements View.OnClickListener {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.framelayout)
    FrameLayout framelayout;
    @Bind(R.id.title)
    LinearLayout title;
    @Bind(R.id.no_title)
    LinearLayout noTitle;
    private FragmentManager fragmentManager;
    public FragmentTransaction transaction;
    private CompanyFragment companyFragment;
    private CompanyNoTitleFragment companyNotitleFragment;
    public Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_company_list);
        ButterKnife.bind(this);
        inti();
    }

    private void inti() {
        companyFragment = new CompanyFragment();
        fragment = companyFragment;
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, companyFragment);
        transaction.commit();


    }


    @OnClick({R.id.back, R.id.title, R.id.no_title})
    public void onClick(View view) {
        transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.title:
                if (companyFragment == null) {
                    companyFragment = new CompanyFragment();
                }
                transaction.replace(R.id.framelayout, companyFragment);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.no_title:
                if (companyNotitleFragment == null) {
                    companyNotitleFragment = new CompanyNoTitleFragment();
                }

                transaction.replace(R.id.framelayout, companyNotitleFragment);
                transaction.commit();
                break;
        }
    }
}
