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
import com.yigu.jgj.base.RequestCode;
import com.yigu.jgj.widget.BestSwipeRefreshLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssignDetailActivity extends AppCompatActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    BestSwipeRefreshLayout swipeLayout;
    AssignDetailAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_detail);
        ButterKnife.bind(this);
        initView();
        initListener();
    }

    private void initView() {
        tvCenter.setText("任务分派");
        tvRight.setText("人员");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new AssignDetailAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
        swipeLayout.setBestRefreshListener(new BestSwipeRefreshLayout.BestRefreshListener() {
            @Override
            public void onBestRefresh() {
                swipeLayout.setRefreshing(false);
            }
        });
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
                startActivityForResult(intent,RequestCode.assign_person);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case RequestCode.assign_person:

                    break;
            }
        }
    }
}
