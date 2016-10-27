package com.yigu.jgj.activity.person;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.OtherPerManageAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.api.UserApi;
import com.yigu.jgj.commom.result.MapiUserResult;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.util.RequestPageCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.widget.BestSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherPersonActivity extends BaseActivity {
    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    BestSwipeRefreshLayout swipeLayout;
    @Bind(R.id.bg_color)
    View bgColor;
    @Bind(R.id.all_iv)
    ImageView allIv;
    @Bind(R.id.all_rl)
    RelativeLayout allRl;

    private List<MapiUserResult> mList = new ArrayList<>();
    OtherPerManageAdapter mAdapter;
    ArrayList<MapiUserResult> userList = new ArrayList<>();
    private String type = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_person);
        ButterKnife.bind(this);
        if (null != getIntent()) {
            userList = (ArrayList<MapiUserResult>) getIntent().getSerializableExtra("list");
            type = getIntent().getStringExtra("type");
        }
        initView();
        initListener();
        load();
    }

    private void initView() {
        tvCenter.setText("选择联系人");
        tvRight.setText("确定");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new OtherPerManageAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.back, R.id.tv_right,R.id.all_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                if (null != mAdapter) {
                    ArrayList<MapiUserResult> userList = mAdapter.getSelList();
                    if (userList.isEmpty()) {
                        MainToast.showShortToast("请选择联系人");
                        return;
                    }

                    Intent intent = new Intent();
                    intent.putExtra("list", userList);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.all_rl:
                if(null!=mAdapter.getSelList()&&mAdapter.getSelList().size()>0&&mAdapter.getSelList().size()==mList.size()){
                    allIv.setImageResource(R.mipmap.circle_white);
                    for(MapiUserResult item: mList){
                        item.setCheck(false);
                    }
                    mAdapter.setSelList(new ArrayList<MapiUserResult>());
                }else{
                    allIv.setImageResource(R.mipmap.circle_blue_right);
                    for(MapiUserResult item: mList){
                        item.setCheck(true);
                    }
                    mAdapter.setSelList(mList);
                }

                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void initListener() {
        swipeLayout.setBestRefreshListener(new BestSwipeRefreshLayout.BestRefreshListener() {
            @Override
            public void onBestRefresh() {
                refreshData();
            }
        });
    }

    public void setAll(boolean isAll){
        if(isAll)
            allIv.setImageResource(R.mipmap.circle_blue_right);
        else
            allIv.setImageResource(R.mipmap.circle_white);
    }

    private void load() {
        UserApi.getdropdownlist(this, type,userSP.getUserBean().getCOMPANY(),new RequestPageCallback<List<MapiUserResult>>() {
            @Override
            public void success(Integer isNext, List<MapiUserResult> success) {
                swipeLayout.setRefreshing(false);
                if (success.isEmpty())
                    return;

                for (MapiUserResult item : success) {
                    if (userList.contains(item))
                        item.setCheck(true);
                    else
                        item.setCheck(false);
                }
                mList.addAll(success);
                mAdapter.notifyDataSetChanged();
                DebugLog.i("userList.size()=>"+userList.size()+",mList.size()=>"+mList.size());
                if(!userList.isEmpty()&&userList.size()==mList.size()) {
                    mAdapter.setSelList(mList);
                    setAll(true);
                }
            }
        }, new RequestExceptionCallback() {
            @Override
            public void error(String code, String message) {
                swipeLayout.setRefreshing(false);
            }
        });
    }


    public void refreshData() {
        if (null != mList) {
            mList.clear();
            mAdapter.notifyDataSetChanged();
            load();
        }
    }

}
