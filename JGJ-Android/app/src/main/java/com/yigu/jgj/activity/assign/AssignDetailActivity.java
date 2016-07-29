package com.yigu.jgj.activity.assign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.activity.person.PerManageActivity;
import com.yigu.jgj.adapter.assign.AssignDetailAdapter;
import com.yigu.jgj.adapter.assign.AssignTaskAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.Config;
import com.yigu.jgj.base.RequestCode;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiTaskResult;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.widget.BestSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssignDetailActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    AssignDetailAdapter mAdapter;

    MapiTaskResult itemResult;
    List<Integer> list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_detail);
        ButterKnife.bind(this);
        if(null!=getIntent().getExtras())
            itemResult = (MapiTaskResult) getIntent().getSerializableExtra("item");
        if(null!=itemResult){
            initView();
            load();
        }
    }

    private void initView() {
        tvCenter.setText("任务分派");
        tvRight.setText("人员");
        list.add(Config.daily_head);
        list.add(Config.daily_project);
        if(itemResult.getFOODSALES()==1)
            list.add(Config.daily_sale);
        if(itemResult.getFOODSERVICE()==1||itemResult.getCANTEEN()==1)
            list.add(Config.daily_service_canteen);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new AssignDetailAdapter(this,list);
        recyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                Intent intent = new Intent(this, PerManageActivity.class);
                intent.putExtra("requestCode", RequestCode.assign_person);
                intent.putExtra("ID", itemResult.getID());
                startActivity(intent);
                break;
        }
    }

    private void load(){
        ItemApi.dailyPatrolDetails(this, itemResult.getID(), new RequestCallback<MapiItemResult>() {
            @Override
            public void success(MapiItemResult success) {
                if(null!=success){
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
