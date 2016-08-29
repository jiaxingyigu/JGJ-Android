package com.yigu.jgj.activity.msg;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.msg.MsgListAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.result.MapiMsgResult;
import com.yigu.jgj.commom.util.DPUtil;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.util.RequestPageCallback;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.widget.BestSwipeRefreshLayout;
import com.yigu.jgj.widget.DividerListItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MsgListActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    BestSwipeRefreshLayout swipeLayout;

    private Integer pageIndex = 0;
    private Integer pageSize = 6;
    private Integer ISNEXT = 1;
    private MsgListAdapter mAdapter;
    List<MapiMsgResult> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_list);
        ButterKnife.bind(this);
        if(userSP.checkLogin()) {
            initView();
            initListener();
        }else {
            ControllerUtil.go2Login();
            finish();
        }
    }

    private void initView() {
        tvCenter.setText("消息");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.addItemDecoration(new DividerListItemDecoration(this,OrientationHelper.HORIZONTAL,DPUtil.dip2px(10),getResources().getColor(R.color.divider_line)));
        recyclerView.setLayoutManager(manager);
        mAdapter = new MsgListAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);
    }

    private void initListener(){
        swipeLayout.setBestRefreshListener(new BestSwipeRefreshLayout.BestRefreshListener() {
            @Override
            public void onBestRefresh() {
                refreshData();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if ((newState == RecyclerView.SCROLL_STATE_IDLE) && manager.findLastVisibleItemPosition() > 0 && (manager.findLastVisibleItemPosition() == (manager.getItemCount() - 1))) {
                    loadNext();
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @OnClick(R.id.back)
    public void onClick() {
        ControllerUtil.go2Main();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        load();
    }

    private void load(){
        ItemApi.getMessagelist(this,userSP.getUserBean().getUSER_ID(),pageIndex+"",pageSize+"",new RequestPageCallback<List<MapiMsgResult>>(){
            @Override
            public void success(Integer isNext,  List<MapiMsgResult> success) {
                swipeLayout.setRefreshing(false);
                ISNEXT = isNext;
                if (success.isEmpty())
                    return;
                mList.addAll(success);
                mAdapter.notifyDataSetChanged();
            }
        },new RequestExceptionCallback(){
            @Override
            public void error(String code, String message) {
                swipeLayout.setRefreshing(false);
            }
        });
    }



    private void loadNext() {
        if (ISNEXT != null && ISNEXT == 0) {
            return;
        }
        pageIndex++;
        load();
    }

    public void refreshData() {
        if (null != mList) {
            mList.clear();
            pageIndex = 0;
            mAdapter.notifyDataSetChanged();
            load();
        }
    }

}
