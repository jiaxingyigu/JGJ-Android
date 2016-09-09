package com.yigu.jgj.activity.company;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yigu.jgj.R;
import com.yigu.jgj.adapter.company.CompanyAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.broadCast.ReceiverAction;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.util.RequestPageCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.widget.BestSwipeRefreshLayout;
import com.yigu.jgj.widget.pop.FilterPop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompanyListTwoActivity extends BaseActivity {

    @Bind(R.id.catering_tv)
    TextView cateringTv;
    @Bind(R.id.product_tv)
    TextView productTv;
    @Bind(R.id.currency_tv)
    TextView currencyTv;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    BestSwipeRefreshLayout swipeLayout;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.filter_one)
    TextView filterOne;
    @Bind(R.id.filter_two)
    TextView filterTwo;
    @Bind(R.id.filter_three)
    TextView filterThree;
    @Bind(R.id.bgVieww)
    View bgVieww;
    @Bind(R.id.filter_rl)
    RelativeLayout filter_rl;
    @Bind(R.id.search_et)
    EditText searchEt;

    List<TextView> list = new ArrayList<>();
    CompanyAdapter mAdapter;
    FilterPop filterOnePop;
    FilterPop filterTwoPop;
    FilterPop filterThreePop;
    List<MapiResourceResult> mList = new ArrayList<>();
    List<MapiItemResult> itemList = new ArrayList<>();
    List<MapiResourceResult> listOne = new ArrayList<>();
    List<MapiResourceResult> listThree = new ArrayList<>();

    private Integer pageIndex = 0;
    private Integer pageSize = 8;
    private Integer ISNEXT = 1;

    private String search = "";
    private String CATEGORY = "1";
    private String TYPE = "";
    private String COMMUNITY = "";
    private String flag = "";
    private boolean isClick = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_companylist);
        ButterKnife.bind(this);
        initView();
        initListener();
        load();
        initStockReceiver();
    }

    private void initView() {
        tvRight.setText("新增");
        list.add(cateringTv);
        list.add(productTv);
        list.add(currencyTv);
        CATEGORY = "1";
        updateStatus(0);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new CompanyAdapter(this, itemList);
        recyclerView.setAdapter(mAdapter);
        if (null != userSP.getResource()) {
            JSONObject jsonObject = JSONObject.parseObject(userSP.getResource());
            Map<String, ArrayList<MapiResourceResult>> userBean = JSON.parseObject(jsonObject
                            .getJSONObject("data").toJSONString(),
                    new TypeReference<Map<String, ArrayList<MapiResourceResult>>>() {
                    });
            mList.clear();
            if (!userBean.get("SQ").isEmpty())
                mList.addAll(userBean.get("SQ"));
        }
        COMMUNITY  = userSP.getUserBean().getCOMMUNITY();
        DebugLog.i("COMMUNITY"+COMMUNITY);
        if(!TextUtils.isEmpty(COMMUNITY)&&!mList.isEmpty()) {
            for (MapiResourceResult resourceResult : mList) {
                if (COMMUNITY.equals(resourceResult.getZD_ID())) {
                    filterTwo.setText(resourceResult.getNAME());
                    break;
                }
            }
            isClick = false;
            filterTwo.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        mList.add(0, new MapiResourceResult("", "全部"));
        listOne.add(new MapiResourceResult("", "全部"));
        listOne.add(new MapiResourceResult("A", "A类"));
        listOne.add(new MapiResourceResult("B", "B类"));
        listOne.add(new MapiResourceResult("C", "C类"));
        filterOnePop = new FilterPop(this, listOne);
        filterTwoPop = new FilterPop(this, mList);
        listThree.add(new MapiResourceResult("", "全部"));
        listThree.add(new MapiResourceResult("1", "完成"));
        listThree.add(new MapiResourceResult("0", "未完成"));
        filterThreePop = new FilterPop(this, listThree);
    }

    private void initListener() {

        mAdapter.setOnItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ControllerUtil.go2CompanyMessage(itemList.get(position));
            }
        });

        swipeLayout.setBestRefreshListener(new BestSwipeRefreshLayout.BestRefreshListener() {
            @Override
            public void onBestRefresh() {
                refreshData();
            }
        });

        filterOnePop.setOnPopItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                bgVieww.setBackgroundColor(Color.parseColor("#00000000"));
                if (null != view) {
                    filterOne.setText(listOne.get(postion).getNAME());
                    TYPE = listOne.get(postion).getZD_ID();
                    refreshData();
                }
            }
        });

        filterTwoPop.setOnPopItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                bgVieww.setBackgroundColor(Color.parseColor("#00000000"));
                if (null != view) {
                    COMMUNITY = mList.get(postion).getZD_ID();
                    filterTwo.setText(mList.get(postion).getNAME());
                    refreshData();
                }
            }
        });

        filterThreePop.setOnPopItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                bgVieww.setBackgroundColor(Color.parseColor("#00000000"));
                if (null != view) {
                    flag = listThree.get(postion).getZD_ID();
                    filterThree.setText(listThree.get(postion).getNAME());
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

    }

    @OnClick({R.id.catering_tv, R.id.product_tv, R.id.currency_tv, R.id.back, R.id.filter_one, R.id.filter_two, R.id.filter_three, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.catering_tv:
                CATEGORY = "1";
                updateStatus(0);
                refreshData();
                break;
            case R.id.product_tv:
                CATEGORY = "2";
                updateStatus(1);
                refreshData();
                break;
            case R.id.currency_tv:
                CATEGORY = "3";
                updateStatus(2);
                refreshData();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.filter_one:
                filterOnePop.showPopupWindow(filter_rl);
                if (null != filterOnePop && filterOnePop.isShowing())
                    bgVieww.setBackgroundColor(Color.parseColor("#b0000000"));
                else
                    bgVieww.setBackgroundColor(Color.parseColor("#00000000"));

                break;
            case R.id.filter_two:
                if(isClick){
                    filterTwoPop.showPopupWindow(filter_rl);
                    if (null != filterTwoPop && filterTwoPop.isShowing())
                        bgVieww.setBackgroundColor(Color.parseColor("#b0000000"));
                    else
                        bgVieww.setBackgroundColor(Color.parseColor("#00000000"));
                }
                break;
            case R.id.filter_three:
                filterThreePop.showPopupWindow(filter_rl);
                if (null != filterThreePop && filterThreePop.isShowing())
                    bgVieww.setBackgroundColor(Color.parseColor("#b0000000"));
                else
                    bgVieww.setBackgroundColor(Color.parseColor("#00000000"));
                break;
            case R.id.tv_right:
                ControllerUtil.go2CompanyAdd();
                break;
        }
    }

    private void updateStatus(int position) {

        for (int i = 0; i < list.size(); i++) {
            if (position == i) {
                list.get(position).setTextColor(Color.parseColor("#ffffff"));
                list.get(position).setTextSize(17);
            } else {
                list.get(i).setTextColor(Color.parseColor("#8bcae7"));
                list.get(i).setTextSize(14);
            }
        }


    }

    public void load() {

        ItemApi.getArchiveslist(this, search, COMMUNITY, CATEGORY,TYPE,flag ,pageIndex + "", pageSize + "", new RequestPageCallback<List<MapiItemResult>>() {
            @Override
            public void success(Integer isNext, List<MapiItemResult> success) {
                swipeLayout.setRefreshing(false);
                ISNEXT = isNext;
                if (success.isEmpty())
                    return;
                itemList.addAll(success);
                mAdapter.notifyDataSetChanged();
            }
        }, new RequestExceptionCallback() {
            @Override
            public void error(String code, String message) {
                swipeLayout.setRefreshing(false);
                MainToast.showLongToast(message);
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
        if (null != itemList) {
            itemList.clear();
            pageIndex = 0;
            mAdapter.notifyDataSetChanged();
            load();
        }
    }

    private CompanyBroadCast companyBroadCast;

    private void initStockReceiver() {
        companyBroadCast = new CompanyBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ReceiverAction.updateCompany_action);
        filter.setPriority(Integer.MAX_VALUE);
        registerReceiver(companyBroadCast, filter);
    }

    /**
     * 企业修改和新增的的广播接受者
     */
    public class CompanyBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ReceiverAction.updateCompany_action)) {//修改
                refreshData();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != companyBroadCast)
            unregisterReceiver(companyBroadCast);
    }

}
