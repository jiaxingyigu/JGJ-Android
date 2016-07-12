package com.yigu.jgj.activity.danger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.daily.DailyAdapter;
import com.yigu.jgj.adapter.danager.DanagerListAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.widget.BestSwipeRefreshLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DangerListActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    BestSwipeRefreshLayout swipeLayout;

    DanagerListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger_list);
        ButterKnife.bind(this);
        initView();
        initListener();
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    private void initView(){
        tvCenter.setText("隐患档案");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new DanagerListAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ControllerUtil.go2DanagerDetail();
            }
        });
        swipeLayout.setBestRefreshListener(new BestSwipeRefreshLayout.BestRefreshListener() {
            @Override
            public void onBestRefresh() {
                swipeLayout.setRefreshing(false);
            }
        });
    }

}
