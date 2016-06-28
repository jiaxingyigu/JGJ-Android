package com.yigu.jgj.activity.person;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.PerManageAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.util.DPUtil;
import com.yigu.jgj.widget.BestSwipeRefreshLayout;
import com.yigu.jgj.widget.TopPopWindow;

import java.util.ArrayList;
import java.util.List;

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
    @Bind(R.id.search_et)
    EditText searchEt;
    @Bind(R.id.bg_color)
    View bgColor;
    @Bind(R.id.address_tv)
    TextView addressTv;

    PerManageAdapter mAdapter;
    TopPopWindow topPopWindow;
    List<String> mList = new ArrayList<>();
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
        mList.clear();
        mList.add("东栅街道");
        mList.add("南湖街道");
        mList.add("经济开发区");
        topPopWindow = new TopPopWindow(this, DPUtil.dip2px(149), mList, R.style.PopupWindowAnimation);
    }

    private void initListener() {
        swipeLayout.setBestRefreshListener(new BestSwipeRefreshLayout.BestRefreshListener() {
            @Override
            public void onBestRefresh() {
                swipeLayout.setRefreshing(false);
            }
        });
        topPopWindow.setOnPopItemClickListener(new TopPopWindow.OnPopItemClickListener() {
            @Override
            public void onPopItemClick(View view, int postion) {
                if(null!=view){
                    addressTv.setText(mList.get(postion));
                }
                bgColor.setVisibility(View.GONE);
            }
        });
    }

    @OnClick({R.id.back, R.id.tv_right,R.id.address_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.address_tv:
                topPopWindow.showPopupWindow(view);
                bgColor.setVisibility(View.VISIBLE);

                break;
        }
    }

}
