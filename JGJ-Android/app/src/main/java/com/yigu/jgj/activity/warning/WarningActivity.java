package com.yigu.jgj.activity.warning;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.activity.notify.NotifyAddActivity;
import com.yigu.jgj.adapter.notify.NotifyListAdapter;
import com.yigu.jgj.adapter.warning.WarningAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.RequestCode;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.application.AppContext;
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

public class WarningActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    BestSwipeRefreshLayout swipeLayout;

    private Integer pageIndex = 0;
    private Integer pageSize = 10;
    private Integer ISNEXT = 1;
    private WarningAdapter mAdapter;
    List<MapiMsgResult> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);
        ButterKnife.bind(this);
        initView();
        initListener();
        load();
    }

    @OnClick({R.id.back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                ControllerUtil.go2Main();
                finish();
                break;
            case R.id.tv_right:
                Intent intent = new Intent(AppContext.getInstance(), WarningAddActivity.class);
                startActivityForResult(intent, RequestCode.add_request);
//                ControllerUtil.go2NotifyAdd();
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        ControllerUtil.go2Main();
        finish();
    }

    private void initView() {
        tvCenter.setText("预警信息");
        tvRight.setText("新增");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.addItemDecoration(new DividerListItemDecoration(this,OrientationHelper.HORIZONTAL, DPUtil.dip2px(10), Color.parseColor("#ffffff")));
        recyclerView.setLayoutManager(manager);
        mAdapter = new WarningAdapter(this, mList);
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

    private void load(){
        ItemApi.getWarninglist(this,userSP.getUserBean().getUSER_ID(),pageIndex+"",pageSize+"",new RequestPageCallback<List<MapiMsgResult>>(){
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(Activity.RESULT_OK == resultCode){
            if(requestCode == RequestCode.add_request){
                refreshData();
            }
        }
    }

}
