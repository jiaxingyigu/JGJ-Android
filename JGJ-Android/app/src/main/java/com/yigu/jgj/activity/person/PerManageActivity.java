package com.yigu.jgj.activity.person;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yigu.jgj.R;
import com.yigu.jgj.adapter.PerManageAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.api.DailyApi;
import com.yigu.jgj.commom.api.UserApi;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.result.MapiUserResult;
import com.yigu.jgj.commom.util.DPUtil;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.util.RequestPageCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.widget.BestSwipeRefreshLayout;
import com.yigu.jgj.widget.TopPopWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerManageActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    BestSwipeRefreshLayout swipeLayout;
    @Bind(R.id.search_et)
    EditText searchEt;
    @Bind(R.id.bg_color)
    View bgColor;
    @Bind(R.id.address_tv)
    TextView addressTv;

    PerManageAdapter mAdapter;
    TopPopWindow topPopWindow;
    List<MapiResourceResult> sList = new ArrayList<>();
    int requestCode = 0;

    private List<MapiUserResult> mList = new ArrayList<>();
    private String search = "";
    private Integer pageIndex=0;
    private Integer pageSize = 8;
    private Integer ISNEXT = 1;
    private String COMPANY="";
    private String COMMUNITY="";

    private int pos = -1;
    private String ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_manage);
        ButterKnife.bind(this);
        initView();
        initListener();
        load();
    }

    private void initView() {
        if(null!=getIntent().getExtras()) {
            requestCode = getIntent().getExtras().getInt("requestCode", 0);
            ID = getIntent().getExtras().getString("ID","");
        }
        if(requestCode>0){
            tvCenter.setText("请选择分派人员");
            tvRight.setText("确定");
        }else{
            tvCenter.setText("人员管理");
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new PerManageAdapter(this,requestCode,mList);
        recyclerView.setAdapter(mAdapter);
        if(null!=userSP.getResource()){
            JSONObject jsonObject = JSONObject.parseObject(userSP.getResource());
              Map<String, ArrayList<MapiResourceResult>> userBean = JSON.parseObject(jsonObject
                                        .getJSONObject("data").toJSONString(),
                                new TypeReference<Map<String, ArrayList<MapiResourceResult>>>() {
                                });
            sList.clear();
            if(!userBean.get("SQ").isEmpty())
                sList.addAll(userBean.get("SQ"));
        }
        topPopWindow = new TopPopWindow(this, DPUtil.dip2px(149), sList, R.style.PopupWindowAnimation);
        COMPANY = userSP.getUserBean().getCOMPANY();
        COMMUNITY = userSP.getUserBean().getCOMMUNITY();
        if(TextUtils.isEmpty(COMMUNITY)&&!sList.isEmpty())
            addressTv.setVisibility(View.VISIBLE);
        else
            addressTv.setVisibility(View.GONE);

    }

    private void initListener() {
        swipeLayout.setBestRefreshListener(new BestSwipeRefreshLayout.BestRefreshListener() {
            @Override
            public void onBestRefresh() {
                refreshData();
            }
        });
        topPopWindow.setOnPopItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                if(null!=view){
                    if(postion>=0) {
                        addressTv.setText(sList.get(postion).getNAME());
                        COMMUNITY = sList.get(postion).getZD_ID();
                    }else{
                        addressTv.setText("区域");
                        COMMUNITY = "";
                    }
                    refreshData();
                }
                bgColor.setVisibility(View.GONE);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if ((newState == RecyclerView.SCROLL_STATE_IDLE) && manager.findLastVisibleItemPosition()>=0&&(manager.findLastVisibleItemPosition() == (manager.getItemCount() - 1))) {
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
                search = charSequence.toString();
                refreshData();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mAdapter.setOnItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                pos = position;
            }
        });

    }

    @OnClick({R.id.back, R.id.tv_right,R.id.address_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                if(pos<0) {
                    MainToast.showShortToast("请选择分配人员");
                    return;
                }
                assign();
                break;
            case R.id.address_tv:
                topPopWindow.showPopupWindow(view);
                bgColor.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void load(){

        UserApi.getUserList(this, search, COMPANY, COMMUNITY, pageIndex + "", pageSize+"",new RequestPageCallback< List<MapiUserResult>>() {
            @Override
            public void success(Integer isNext,List<MapiUserResult> success) {
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

    private void assign(){
        showLoading();
        DailyApi.tasksend(this, mList.get(pos).getUSER_ID(), ID, new RequestCallback() {
            @Override
            public void success(Object success) {
                hideLoading();
                MainToast.showShortToast("分配成功");
                ControllerUtil.go2AssignTask();
                finish();
            }
        }, new RequestExceptionCallback() {
            @Override
            public void error(String code, String message) {
                hideLoading();
            }
        });
    }

}
