package com.yigu.jgj.activity.danger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.danager.DanagerListAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.RequestCode;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.application.AppContext;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.util.RequestPageCallback;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.JGJDataSource;
import com.yigu.jgj.widget.BestSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DangerListActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    BestSwipeRefreshLayout swipeLayout;
    @Bind(R.id.search_et)
    EditText searchEt;
    DanagerListAdapter mAdapter;
    List<MapiItemResult> mList;

    private Integer pageIndex = 0;
    private Integer pageSize = 8;
    private Integer ISNEXT = 1;
    String NAME = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger_list);
        ButterKnife.bind(this);
        initView();
        initListener();
        load();
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    private void initView() {
        mList = new ArrayList<>();
        tvCenter.setText("隐患跟踪");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new DanagerListAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(AppContext.getInstance(), DanagerDetailActivity.class);
                intent.putExtra("item", mList.get(position));
                startActivityForResult(intent, RequestCode.Danager_Detail);
//                ControllerUtil.go2DanagerDetail(mList.get(position));
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
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                NAME = charSequence.toString();
                refreshData();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void load() {
        String userId = userSP.getUserBean().getUSER_ID();
        String roleId = userSP.getUserBean().getROLE_ID();
        if (!roleId.equals(JGJDataSource.root_one_roleid) && !roleId.equals(JGJDataSource.root_two_roleid)) {
            roleId = "";
        }
        ItemApi.getDangerList(this, NAME, pageIndex + "", pageSize + "", userId, roleId, new RequestPageCallback<List<MapiItemResult>>() {
            @Override
            public void success(Integer isNext, List<MapiItemResult> success) {
                swipeLayout.setRefreshing(false);
                ISNEXT = isNext;
                if (success.isEmpty()) {
                    return;
                }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            if (requestCode == RequestCode.Danager_Detail) {
                refreshData();
            }
        }
    }


}
