package com.yigu.jgj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.SelCommunityAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.api.DailyApi;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.ControllerUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by brain on 2016/7/24.
 */
public class SelRootActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    SelCommunityAdapter mAdapter;
    List<MapiResourceResult> mList = new ArrayList<>();
    private int pos = -1;

    MapiItemResult itemResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sel_community);
        ButterKnife.bind(this);
        if(null!=getIntent().getExtras())
            itemResult = (MapiItemResult) getIntent().getSerializableExtra("item");
        if(null!=itemResult){
            initView();
            initListener();
            load();
        }
    }

    private void initView() {
        tvCenter.setText("汇报上级");
        tvRight.setText("确定");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new SelCommunityAdapter(this, mList);
        mAdapter.setPos(pos);
        recyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                pos = position;
            }
        });
    }

    private void load() {
        mList.clear();
        mList.add(new MapiResourceResult("9d0495a5ad044083903707e83b110baa","所长"));
        mList.add(new MapiResourceResult("fa77590189db4a12b6a18491a0d85b36","联络员"));
        mAdapter.notifyDataSetChanged();
    }



    @OnClick({R.id.back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                if (pos < 0) {
                    MainToast.showLongToast("请选择上级");
                    return;
                }
                submit();
                break;
        }
    }

    private void submit(){//上报
        showLoading();
        DailyApi.dailyPatrol(this, "1", itemResult.getID(), userSP.getUserBean().getUSER_ID(), itemResult.getPtioners() + "", itemResult.getHCATEN() + "", itemResult.getShowlicense() + "",
                itemResult.getHygiene() + "", itemResult.getInvoice() + "", itemResult.getSanitation() + "", itemResult.getOverdue() + "", itemResult.getFullmark() + "", itemResult.getTrain() + "",
                itemResult.getDisinfection() + "", itemResult.getPoster() + "",itemResult.getRemark() , mList.get(pos).getZD_ID(), itemResult.getImage(), new RequestCallback() {
                    @Override
                    public void success(Object success) {
                        hideLoading();
                        MainToast.showShortToast("上报成功");
                        ControllerUtil.go2Daily();
                        finish();
                    }
                }, new RequestExceptionCallback() {
                    @Override
                    public void error(String code, String message) {
                        hideLoading();
                    }
                });
    }

}
