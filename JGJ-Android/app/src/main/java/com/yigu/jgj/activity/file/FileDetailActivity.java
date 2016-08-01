package com.yigu.jgj.activity.file;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.assign.AssignDetailAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.Config;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiTaskResult;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by brain on 2016/7/31.
 */
public class FileDetailActivity extends BaseActivity{

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    AssignDetailAdapter mAdapter;

    MapiTaskResult taskResult;
    List<Integer> list = new ArrayList();
    String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_detail);
        ButterKnife.bind(this);
        if(null!=getIntent().getExtras()) {
            taskResult = (MapiTaskResult) getIntent().getSerializableExtra("item");
            title = getIntent().getStringExtra("title");
        }
        if(null!=taskResult){
            initView();
            load();
        }
    }

    private void initView() {
        tvCenter.setText(title);
        list.add(Config.daily_head);
        list.add(Config.daily_project);
        if(taskResult.getFOODSALES()==1)
            list.add(Config.daily_sale);
        if(taskResult.getFOODSERVICE()==1||taskResult.getCANTEEN()==1)
            list.add(Config.daily_service_canteen);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new AssignDetailAdapter(this,list);
        recyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    private void load(){
        ItemApi.dailyPatrolDetails(this, taskResult.getID(), new RequestCallback<MapiItemResult>() {
            @Override
            public void success(MapiItemResult success) {
                if(null!=success){
                    if(!TextUtils.isEmpty(success.getRemark()))
                        list.add(Config.daily_remark);
                    if(null!=success.getImages()&&!success.getImages().isEmpty())
                        list.add(Config.daily_image);
                    mAdapter.setItemResult(success);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }, new RequestExceptionCallback() {
            @Override
            public void error(String code, String message) {

            }
        });
    }

}
