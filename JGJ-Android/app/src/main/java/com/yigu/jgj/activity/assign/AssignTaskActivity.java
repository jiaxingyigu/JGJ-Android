package com.yigu.jgj.activity.assign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.PerManageAdapter;
import com.yigu.jgj.adapter.assign.AssignTaskAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiTaskResult;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.util.RequestPageCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.widget.BestSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssignTaskActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    BestSwipeRefreshLayout swipeLayout;
    AssignTaskAdapter mAdapter;
    List<MapiTaskResult> mList = new ArrayList<>();
    private Integer pageIndex=0;
    private Integer pageSize = 10;
    private Integer ISNEXT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_task);
        ButterKnife.bind(this);
        initView();
        initListener();
        load();
    }

    private void initView(){
        tvCenter.setText("任务分派");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new AssignTaskAdapter(this,mList);
        recyclerView.setAdapter(mAdapter);
    }

    private void initListener(){
        mAdapter.setOnItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ControllerUtil.go2AssignDetail(mList.get(position));
            }
        });
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
        finish();
    }

    public void load(){
        String COMMUNITY = userSP.getUserBean().getCOMMUNITY();
        String COMPANY = userSP.getUserBean().getCOMPANY();
        String role = userSP.getUserBean().getROLE_ID();
        ItemApi.getAssignList(this, COMPANY, COMMUNITY,role, pageIndex + "", pageSize+"",  new RequestPageCallback<List<MapiTaskResult>>() {
            @Override
            public void success(Integer isNext, List<MapiTaskResult> success) {
                swipeLayout.setRefreshing(false);
                ISNEXT = isNext;
                if(success.isEmpty())
                    return;
                mList.addAll(success);
                mAdapter.notifyDataSetChanged();
            }
        }, new RequestExceptionCallback() {
            @Override
            public void error(String code, String message) {
                swipeLayout.setRefreshing(false);
            }
        });
    }

    private void loadNext() {
        if (ISNEXT != null && ISNEXT==0) {
            return;
        }
        pageIndex++;
        load();
    }

    public void refreshData() {
        if (null != mList) {
            mList.clear();
            pageIndex = 0;
            load();
        }
    }

}
