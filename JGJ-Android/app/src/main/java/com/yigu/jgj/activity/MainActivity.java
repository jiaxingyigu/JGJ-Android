package com.yigu.jgj.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.MainAdapter;
import com.yigu.jgj.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    MainAdapter mAdapter;
    List<Integer> mList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }
    private void initView(){
        back.setVisibility(View.INVISIBLE);
        tvRight.setVisibility(View.GONE);
        tvCenter.setText(getResources().getString(R.string.main_title));
    }
    private void initData(){
        mList.add(R.mipmap.main_one);
        mList.add(R.mipmap.main_two);
        mList.add(R.mipmap.main_three);
        mList.add(R.mipmap.main_four);
        mList.add(R.mipmap.main_five);
        mList.add(R.mipmap.main_six);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        mAdapter = new MainAdapter(this,mList);
        recyclerView.setAdapter(mAdapter);
    }
}
