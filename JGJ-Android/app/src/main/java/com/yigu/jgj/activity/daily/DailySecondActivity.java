package com.yigu.jgj.activity.daily;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.daily.DailySecondAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.Config;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.view.DailyProjectLayout;
import com.yigu.jgj.view.DailySaleLayout;
import com.yigu.jgj.view.DailyServiceLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DailySecondActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    DailySecondAdapter mAdapter;
    MapiItemResult itemResult;
    List<Integer> list = new ArrayList();
    MapiItemResult item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(null!=getIntent().getExtras())
            itemResult = (MapiItemResult) getIntent().getSerializableExtra("item");
        if(null!=itemResult){
            setContentView(R.layout.activity_daily_second);
            ButterKnife.bind(this);
            initView();
        }

    }

    private void initView(){
        tvCenter.setText("日常巡查");
        tvRight.setText("下一步");
        list.add(Config.daily_head);
        list.add(Config.daily_project);
        if(itemResult.getFOODSALES()==1)
            list.add(Config.daily_sale);
        if(itemResult.getFOODSERVICE()==1||itemResult.getCANTEEN()==1)
            list.add(Config.daily_service_canteen);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new DailySecondAdapter(this,list);
        mAdapter.setItemResult(itemResult);
        recyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                initInfo();
                if(null!=item)
                    ControllerUtil.go2DailyThird(item);
                break;
        }
    }

    private void initInfo(){
        item = new MapiItemResult();
        item.setID(itemResult.getID());
        for(int i=0;i<recyclerView.getChildCount();i++){
            View view = recyclerView.getChildAt(i);
            if(view instanceof DailyProjectLayout){
                DailyProjectLayout daily = (DailyProjectLayout)view;
                if(daily.vorify()){
                    item.setPtioners(TextUtils.isEmpty(daily.getPtioners())?0:Integer.valueOf(daily.getPtioners()));
                    item.setHCATEN(TextUtils.isEmpty(daily.getHcate())?0:Integer.valueOf(daily.getHcate()));
                    item.setShowlicense(daily.showlicenseCheck());
                    item.setHygiene(daily.hygieneCheck());
                    item.setInvoice(daily.invoiceCheck());
                }else {
                    item = null;
                    break;
                }
            }

            if(view instanceof DailySaleLayout){
                DailySaleLayout daily = (DailySaleLayout)view;
                if(daily.vorify()){
                    item.setShowlicense(daily.sanitationCheck());
                    item.setHygiene(daily.overdueCheck());
                    item.setInvoice(daily.fullmarkCheck());
                }else {
                    item = null;
                    break;
                }
            }

            if(view instanceof DailyServiceLayout){
                DailyServiceLayout daily = (DailyServiceLayout)view;
                if(daily.vorify()){
                    item.setShowlicense(daily.trainCheck());
                    item.setHygiene(daily.disinfectionCheck());
                    item.setInvoice(daily.posterCheck());
                }else {
                    item = null;
                    break;
                }
            }
        }
    }

}
