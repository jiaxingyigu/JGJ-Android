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
import com.yigu.jgj.commom.api.CommonApi;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by brain on 2016/7/24.
 */
public class SelCommunityActivity extends BaseActivity {

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sel_community);
        ButterKnife.bind(this);
        if(null!=getIntent().getExtras()) {
            mList = (List<MapiResourceResult>) getIntent().getSerializableExtra("list");
            pos = getIntent().getIntExtra("pos",-1);
        }
        if(!mList.isEmpty()){
            if(pos>=0)
                mList.get(pos).setCheck(true);
            initView();
            initListener();
        }

    }

    private void initView() {
        tvCenter.setText("请选择社区");
        tvRight.setText("确定");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new SelCommunityAdapter(this,mList);
        mAdapter.setPos(pos);
        recyclerView.setAdapter(mAdapter);
    }

    private void initListener(){
        mAdapter.setOnItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                pos = position;
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
                if(pos<0){
                    MainToast.showLongToast("请选择社区");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("cid",mList.get(pos));
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
