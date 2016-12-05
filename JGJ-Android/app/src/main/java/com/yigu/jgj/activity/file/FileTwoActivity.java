package com.yigu.jgj.activity.file;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bigkoo.pickerview.TimePickerView;
import com.yigu.jgj.R;
import com.yigu.jgj.activity.task.TaskDetailActivity;
import com.yigu.jgj.adapter.company.CompanyFragmentAdapter;
import com.yigu.jgj.adapter.file.FileAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.RequestCode;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.application.AppContext;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiPartyResult;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.result.MapiSepcialResult;
import com.yigu.jgj.commom.result.MapiTaskResult;
import com.yigu.jgj.commom.util.DateUtil;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.util.RequestPageCallback;
import com.yigu.jgj.commom.util.RequestPageTwoCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.view.FilterDateLayout;
import com.yigu.jgj.widget.BestSwipeRefreshLayout;
import com.yigu.jgj.widget.pop.FilterAddsPop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileTwoActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
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
    @Bind(R.id.search_et)
    EditText searchEt;
    @Bind(R.id.clear_iv)
    ImageView clear_iv;
    @Bind(R.id.pepConutTV)
    TextView pepConutTV;

    private List<String> list_title = new ArrayList<>();

    String type = "";
    String COMMUNITY = "";
    String search = "";
    private Integer pageIndex=0;
    private Integer pageSize = 8;
    private Integer ISNEXT = 1;
    private String startime = "";
    private String endtime = "";
    private boolean isClick = true;

    FileAdapter mAdapter;
    FilterAddsPop filterAddsPop;
    List<MapiResourceResult> mList = new ArrayList<>();
    List<MapiTaskResult> itemList = new ArrayList<>();

    TimePickerView pvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_two);
        ButterKnife.bind(this);
        initView();
        initListener();
        load();
    }

    private void initView() {
        back.setImageResource(R.mipmap.back);
        tvCenter.setText("痕迹管理");

        list_title.add("日常巡查");
        list_title.add("隐患");
        list_title.add("无照上报");
        list_title.add("专项行动");
        list_title.add("聚餐上报");
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tablayout.addTab(tablayout.newTab().setText(list_title.get(0)));
        tablayout.addTab(tablayout.newTab().setText(list_title.get(1)));
        tablayout.addTab(tablayout.newTab().setText(list_title.get(2)));
        tablayout.addTab(tablayout.newTab().setText(list_title.get(3)));
        tablayout.addTab(tablayout.newTab().setText(list_title.get(4)));

        type = "0";

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

    //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH);
        //控制时间范围
//        Calendar calendar = Calendar.getInstance();
//        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果哦
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                clear_iv.setVisibility(View.VISIBLE);
                filterDate.setText(getTime(date));
                startime = getTime(date);

               refreshData();
            }

        });


    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    @OnClick({ R.id.back, R.id.filter_adds, R.id.filter_date,R.id.clear_iv})
    public void onClick(View view) {
        switch (view.getId()) {
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
        /*if(filterDateLayout.getVisibility()==View.GONE)
            filterDateLayout.setVisibility(View.VISIBLE);
        else
            filterDateLayout.setVisibility(View.GONE);*/

            pvTime.show();

        break;
            case R.id.clear_iv:
                filterDate.setText("请选择时间");
                clear_iv.setVisibility(View.GONE);
                startime = "";

                refreshData();
                break;
        }
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if("0".equals(type))
                    ControllerUtil.go2FileDetail(itemList.get(position),"日常巡查");
                else if("1".equals(type)){
                    Intent intent = new Intent(AppContext.getInstance(), TaskDetailActivity.class);
                    intent.putExtra("item",itemList.get(position));
                    intent.putExtra("title","隐患");
                    intent.putExtra("dell",false);
                    startActivity(intent);
                }else if("2".equals(type)){

                    MapiTaskResult taskResult = itemList.get(position);

                    MapiItemResult itemResult = new MapiItemResult();
                    itemResult.setNAME(taskResult.getName());
                    itemResult.setADDRESS(taskResult.getAddress());
                    itemResult.setLPERSON(taskResult.getLPERSON());
                    itemResult.setHCATEN(taskResult.getHCATEN());
                    itemResult.setTEL(taskResult.getTel());
                    itemResult.setCOMMUNITY(taskResult.getCOMMUNITY());
                    itemResult.setFOODSALES(taskResult.getFOODSALES());
                    itemResult.setFOODSERVICE(taskResult.getFOODSERVICE());
                    itemResult.setCANTEEN(taskResult.getCANTEEN());
                    itemResult.setLICENSE(taskResult.getLICENSE());
                    itemResult.setPEMIT(taskResult.getPEMIT());
                    itemResult.setSenduser(taskResult.getSenduser());
                    itemResult.setUser(taskResult.getUser());
                    itemResult.setIdate(taskResult.getIdate());
                    itemResult.setTaskotime(taskResult.getTaskotime());
                    itemResult.setTaskstime(taskResult.getTaskstime());

                    ControllerUtil.go2FileLicense(itemResult);
                }else if("3".equals(type)){
                    MapiTaskResult taskResult = itemList.get(position);
                    MapiSepcialResult mapiSepcialResult = new MapiSepcialResult();
                    mapiSepcialResult.setDate(taskResult.getDate());
                    mapiSepcialResult.setAcname(taskResult.getAcname());
                    mapiSepcialResult.setPeople(taskResult.getPeople());
                    mapiSepcialResult.setShop(taskResult.getShop());
                    mapiSepcialResult.setCbook(taskResult.getCbook());
                    mapiSepcialResult.setRbook(taskResult.getRbook());
                    mapiSepcialResult.setCOMMUNITY(taskResult.getCOMMUNITY());

                    mapiSepcialResult.setNlshop(taskResult.getNlshop());
                    mapiSepcialResult.setCscope(taskResult.getCscope());
                    mapiSepcialResult.setContraband(taskResult.getContraband());
                    mapiSepcialResult.setOther1(taskResult.getOther1());

                    mapiSepcialResult.setCorrection(taskResult.getCorrection());
                    mapiSepcialResult.setRegister(taskResult.getRegister());
                    mapiSepcialResult.setChaogu(taskResult.getChaogu());
                    mapiSepcialResult.setOther2(taskResult.getOther2());

                    mapiSepcialResult.setRemark(taskResult.getRemark());

                    ControllerUtil.go2SpecialDetail(mapiSepcialResult);
                }else if("4".equals(type)){
                    MapiTaskResult taskResult = itemList.get(position);
                    MapiPartyResult mapiPartyResult = new MapiPartyResult();

                    mapiPartyResult.setStardate(taskResult.getStardate());
                    mapiPartyResult.setEnddate(taskResult.getEnddate());
                    mapiPartyResult.setSponsor(taskResult.getSponsor());
                    mapiPartyResult.setTel(taskResult.getTel());
                    mapiPartyResult.setCook(taskResult.getCook());
                    mapiPartyResult.setCOMMUNITY(taskResult.getCOMMUNITY());
                    mapiPartyResult.setAddress(taskResult.getAddress());

                    mapiPartyResult.setMealtime(taskResult.getMealtime());
                    mapiPartyResult.setAttend(taskResult.getAttend());
                    mapiPartyResult.setPatient(taskResult.getPatient());
                    mapiPartyResult.setName(taskResult.getName());
                    mapiPartyResult.setUtel(taskResult.getUtel());
                    mapiPartyResult.setGuidance(taskResult.getGuidance());

                    ControllerUtil.go2ParryDetail(mapiPartyResult);
                }

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
                    if(postion!=-1) {
                        filterAdds.setText(mList.get(postion).getNAME());
                        COMMUNITY = mList.get(postion).getZD_ID();
                    }else{
                        filterAdds.setText("社区");
                        COMMUNITY = "";
                    }
                    refreshData();
                }
            }
        });

       /* filterDateLayout.setOnTiemInterface(new FilterDateLayout.OnTiemInterface() {
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
        });*/

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

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               switch (tab.getPosition()){
                   case 0:
                       type = "0";
                       break;
                   case 1:
                       type = "1";
                       break;
                   case 2:
                       type = "2";
                       break;
                   case 3:
                       type = "3";
                       break;
                   case 4:
                       type = "4";
                       break;
               }
                refreshData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void load(){
        ItemApi.GetHisData(this, type,COMMUNITY,search,startime,pageIndex+"",pageSize+"", new RequestPageTwoCallback<List<MapiTaskResult>>() {
            @Override
            public void success(Integer isNext,Integer countld,Integer countone,Integer counttwo,List<MapiTaskResult> success) {
                swipeLayout.setRefreshing(false);
                ISNEXT = isNext;
                pepConutTV.setText("当前共有："+(null!=countld?countld:0)+"条记录");
                if(success.isEmpty())
                    return;
                itemList.addAll(success);
                for(MapiTaskResult mapiTaskResult : itemList){
                    int state = 1;
                    if(type.equals("3")){
                        state = 3;
                    }else if(type.equals("4")){
                        state = 4;
                    }else if(type.equals("2")){
                        state = 2;
                    }
                    else{
                        state = 1;
                    }
                    mapiTaskResult.setType(state);
                }
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
