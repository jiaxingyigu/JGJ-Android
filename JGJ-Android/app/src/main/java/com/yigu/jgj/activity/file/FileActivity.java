package com.yigu.jgj.activity.file;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yigu.jgj.R;
import com.yigu.jgj.adapter.daily.DailyAdapter;
import com.yigu.jgj.adapter.file.FileAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.result.MapiTaskResult;
import com.yigu.jgj.commom.util.DateUtil;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.util.RequestPageCallback;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.view.FilterDateLayout;
import com.yigu.jgj.widget.BestSwipeRefreshLayout;
import com.yigu.jgj.widget.pop.FilterAddsPop;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileActivity extends BaseActivity {

    @Bind(R.id.daily_tv)
    TextView dailyTv;
    @Bind(R.id.danger_tv)
    TextView dangerTv;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    BestSwipeRefreshLayout swipeLayout;
    @Bind(R.id.filter_adds)
    TextView filterAdds;
    @Bind(R.id.filter_date)
    TextView filterDate;
    @Bind(R.id.filterDateLayout)
    FilterDateLayout filterDateLayout;

    List<TextView> list = new ArrayList<>();
    FileAdapter mAdapter;
    FilterAddsPop filterAddsPop;
    List<MapiResourceResult> mList = new ArrayList<>();
    List<MapiTaskResult> itemList = new ArrayList<>();

    String type = "";
    String COMMUNITY = "";
    private Integer pageIndex=0;
    private Integer pageSize = 8;
    private Integer ISNEXT = 1;
    private String startime = "";
    private String endtime = "";
    private boolean isClick = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        ButterKnife.bind(this);
        initView();
        initListener();
        load();
    }

    private void initView() {
        list.add(dailyTv);
        list.add(dangerTv);
        type = "0";
        updateStatus(0);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new FileAdapter(this,itemList);
        recyclerView.setAdapter(mAdapter);
        if(null!=userSP.getResource()){
            JSONObject jsonObject = JSONObject.parseObject(userSP.getResource());
            Map<String, ArrayList<MapiResourceResult>> userBean = JSON.parseObject(jsonObject
                            .getJSONObject("data").toJSONString(),
                    new TypeReference<Map<String, ArrayList<MapiResourceResult>>>() {
                    });
            mList.clear();
            if(!userBean.get("SQ").isEmpty())
                mList.addAll(userBean.get("SQ"));
        }

        COMMUNITY  = userSP.getUserBean().getCOMMUNITY();
        DebugLog.i("COMMUNITY"+COMMUNITY);
        if(!TextUtils.isEmpty(COMMUNITY)&&!mList.isEmpty()) {
            for (MapiResourceResult resourceResult : mList) {
                if (COMMUNITY.equals(resourceResult.getZD_ID())) {
                    filterAdds.setText(resourceResult.getNAME());
                    break;
                }
            }
            isClick = false;
            filterAdds.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        filterAddsPop = new FilterAddsPop(this, mList);
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if("0".equals(type))
                    ControllerUtil.go2FileDetail(itemList.get(position),"日常巡查");
                else if("4".equals(type))
                    ControllerUtil.go2FileDetail(itemList.get(position),"隐患");
            }
        });
        swipeLayout.setBestRefreshListener(new BestSwipeRefreshLayout.BestRefreshListener() {
            @Override
            public void onBestRefresh() {
                refreshData();
            }
        });

        filterAddsPop.setOnPopItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                if (null != view) {
                    filterAdds.setText(mList.get(postion).getNAME());
                    COMMUNITY = mList.get(postion).getZD_ID();
                    refreshData();
                }
            }
        });

        filterDateLayout.setOnTiemInterface(new FilterDateLayout.OnTiemInterface() {
            @Override
            public void onTimeSelect(String date) {
                filterDate.setText(date);
                filterDateLayout.setVisibility(View.GONE);
                if(!TextUtils.isEmpty(date)){
                    if(date.contains("-")){
                        String[] arr = date.split("-");
                        startime = DateUtil.getInstance().YM_D2YMD_H(arr[0]);
                        endtime = DateUtil.getInstance().YM_D2YMD_H(arr[1]);
                    }else{
                        startime = DateUtil.getInstance().YM_D2YMD_H(date);
                    }
                    refreshData();
                }
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

    @OnClick({R.id.daily_tv, R.id.danger_tv, R.id.back, R.id.filter_adds, R.id.filter_date})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.daily_tv:
                type = "0";
                updateStatus(0);
                refreshData();
                break;
            case R.id.danger_tv:
                type = "4";
                updateStatus(1);
                refreshData();
                break;
            case R.id.back:
                if(filterDateLayout.getVisibility()==View.VISIBLE)
                    filterDateLayout.setVisibility(View.GONE);
                else
                    finish();
                break;
            case R.id.filter_adds:
                if(isClick){
                    if(filterDateLayout.getVisibility()==View.VISIBLE)
                        filterDateLayout.setVisibility(View.GONE);
                    else
                        filterAddsPop.showPopupWindow(view);
                }
                break;
            case R.id.filter_date:
                if(filterDateLayout.getVisibility()==View.GONE)
                    filterDateLayout.setVisibility(View.VISIBLE);
                else
                    filterDateLayout.setVisibility(View.GONE);
                break;
        }
    }

    private void updateStatus(int position) {
        list.get(position).setTextColor(Color.parseColor("#ffffff"));
        list.get(position).setTextSize(17);
        list.get(list.size() - position - 1).setTextColor(Color.parseColor("#90e0ce"));
        list.get(list.size() - position - 1).setTextSize(14);
    }

    public void load(){
        String COMPANY = userSP.getUserBean().getCOMPANY();
        DebugLog.i("COMPANY"+COMPANY);
        ItemApi.getFileList(this, type, COMPANY,COMMUNITY,startime,endtime, pageIndex+"",pageSize+"", new RequestPageCallback<List<MapiTaskResult>>() {
            @Override
            public void success(Integer isNext,List<MapiTaskResult> success) {
                swipeLayout.setRefreshing(false);
                ISNEXT = isNext;
                if(success.isEmpty())
                    return;
                itemList.addAll(success);
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
        if (null != itemList) {
            itemList.clear();
            pageIndex = 0;
            mAdapter.notifyDataSetChanged();
            load();
        }
    }

}
