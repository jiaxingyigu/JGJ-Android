package com.yigu.jgj.activity.danger;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.danager.DanagerDetailAdapter;
import com.yigu.jgj.adapter.task.TaskDetailAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.Config;
import com.yigu.jgj.commom.api.DailyApi;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.util.JGJDataSource;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DanagerDetailActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    DanagerDetailAdapter mAdapter;

    MapiItemResult itemResult;
    List<Integer> list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danager_detail);
        ButterKnife.bind(this);
        if(null!=getIntent().getExtras())
            itemResult = (MapiItemResult) getIntent().getSerializableExtra("item");
        if(null!=itemResult){
            initView();
            load();
        }
    }

    @OnClick({R.id.back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                taskcomplete();
                break;
        }
    }

    private void initView(){
        tvCenter.setText("隐患档案");
        tvRight.setText("完成");
        String role_id = userSP.getUserBean().getROLE_ID();
        if(role_id.equals(JGJDataSource.root_two_roleid)||role_id.equals(JGJDataSource.root_one_roleid)){
            tvRight.setVisibility(View.GONE);
        }
        list.add(Config.daily_head);
        list.add(Config.daily_project);
        if(itemResult.getFOODSALES()==1)
            list.add(Config.daily_sale);
        if(itemResult.getFOODSERVICE()==1||itemResult.getCANTEEN()==1)
            list.add(Config.daily_service_canteen);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new DanagerDetailAdapter(this,list);
        recyclerView.setAdapter(mAdapter);
    }

    private void load(){
        ItemApi.dailyPatrolDetails(this, itemResult.getID(), new RequestCallback<MapiItemResult>() {
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

    private void taskcomplete(){
        showLoading();
        DailyApi.taskcomplete(this, "4", itemResult.getID(), new RequestCallback() {
            @Override
            public void success(Object success) {
              hideLoading();
                MainToast.showShortToast("完成");
                setResult(Activity.RESULT_OK);
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
