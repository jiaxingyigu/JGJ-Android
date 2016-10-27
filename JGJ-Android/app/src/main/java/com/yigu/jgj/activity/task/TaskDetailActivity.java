package com.yigu.jgj.activity.task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.task.TaskDetailAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.Config;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiTaskResult;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskDetailActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    TaskDetailAdapter mAdapter;

    MapiTaskResult taskResult;
    List<Integer> list = new ArrayList();
    String title = "";
    boolean dell = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        ButterKnife.bind(this);
        if(null!=getIntent().getExtras()) {
            taskResult = (MapiTaskResult) getIntent().getSerializableExtra("item");
            title = getIntent().getStringExtra("title");
            dell = getIntent().getBooleanExtra("dell",true);
        }
        if(null!=taskResult){
            initView();
            load();
        }
    }

    private void initView() {
        if(TextUtils.isEmpty(title))
            tvCenter.setText("任务详情");
        else
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
        mAdapter = new TaskDetailAdapter(this,list);
        mAdapter.setItemID(taskResult.getID());
        recyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
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
                    if(dell)
                        list.add(Config.daily_deel);
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
