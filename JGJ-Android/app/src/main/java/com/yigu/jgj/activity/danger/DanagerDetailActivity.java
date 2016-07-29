package com.yigu.jgj.activity.danger;

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
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DanagerDetailActivity extends AppCompatActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    DanagerDetailAdapter mAdapter;

    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danager_detail);
        ButterKnife.bind(this);
        if(null!=getIntent().getExtras())
            id = getIntent().getStringExtra("id");
        if(!TextUtils.isEmpty(id)){
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
                break;
        }
    }

    private void initView(){
        tvCenter.setText("隐患档案");
        tvRight.setText("完成");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new DanagerDetailAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    private void load(){
        ItemApi.dailyPatrolDetails(this, id, new RequestCallback<MapiItemResult>() {
            @Override
            public void success(MapiItemResult success) {
                if(null!=success){

                }
            }
        }, new RequestExceptionCallback() {
            @Override
            public void error(String code, String message) {

            }
        });
    }

}
