package com.yigu.jgj.activity.party;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bigkoo.pickerview.TimePickerView;
import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.util.DateUtil;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.view.PartyBasicLayout;
import com.yigu.jgj.view.PartyInfoLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartyAddActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.startDate)
    TextView startDate;
    @Bind(R.id.endDate)
    TextView endDate;
    @Bind(R.id.partyBasicLayout)
    PartyBasicLayout partyBasicLayout;
    @Bind(R.id.partyInfoLayout)
    PartyInfoLayout partyInfoLayout;
    TimePickerView pvTime;
    private Date sDate;
    private Date eDate;
    private int position = 0;
    ArrayList<MapiResourceResult> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_add);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvCenter.setText("聚餐管理");
        tvRight.setText("完成");

        if (null != userSP.getResource()) {
            JSONObject jsonObject = JSONObject.parseObject(userSP.getResource());
            Map<String, ArrayList<MapiResourceResult>> userBean = JSON.parseObject(jsonObject
                            .getJSONObject("data").toJSONString(),
                    new TypeReference<Map<String, ArrayList<MapiResourceResult>>>() {
                    });
            mList.clear();
            if (!userBean.get("SQ").isEmpty())
                mList.addAll(userBean.get("SQ"));
            if (!TextUtils.isEmpty(userSP.getUserBean().getCOMMUNITY())) {
                for (MapiResourceResult resourceResult : mList) {
                    if (resourceResult.getZD_ID().equals(userSP.getUserBean().getCOMMUNITY())) {
                        partyBasicLayout.setCommunity(resourceResult.getNAME());
                        break;
                    }
                }
            }
        }
        partyInfoLayout.setGuide(userSP.getUserBean().getNAME());
        //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
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
                if(position==0) {
                    sDate = date;
                    startDate.setText(DateUtil.getInstance().date2YMD_H(date));
                }
                else if(position==1) {
                    eDate = date;
                    endDate.setText(DateUtil.getInstance().date2YMD_H(date));
                }
            }
        });
    }


    @OnClick({R.id.back, R.id.tv_right, R.id.startDate, R.id.endDate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                if(TextUtils.isEmpty(startDate.getText().toString())){
                    MainToast.showShortToast("请选择开始时间");
                    return;
                }
                if(TextUtils.isEmpty(endDate.getText().toString())){
                    MainToast.showShortToast("请选择结束时间");
                    return;
                }
                if(sDate.compareTo(eDate)>0){
                    MainToast.showLongToast("开始时间不能大于结束时间");
                    return;
                }
                add();
                break;
            case R.id.startDate:
                position = 0;
                pvTime.show();
                break;
            case R.id.endDate:
                position = 1;
                pvTime.show();
                break;
        }
    }

    private void add(){
        showLoading();
        ItemApi.addDinner(this,startDate.getText().toString(),endDate.getText().toString(),partyBasicLayout.getHoldPeople(),partyBasicLayout.getTel(),
                partyBasicLayout.getChef(),userSP.getUserBean().getCOMMUNITY(),partyBasicLayout.getAddr(),partyInfoLayout.getPartyNum(),
                partyInfoLayout.getPeopleNum(),partyInfoLayout.getMorbidity(),userSP.getUserBean().getUSER_ID(),partyInfoLayout.getGuideTel(),
                partyInfoLayout.getGuideNum(),new RequestCallback() {
                    @Override
                    public void success(Object success) {
                        hideLoading();
                        MainToast.showLongToast("新增成功");
                        setResult(RESULT_OK);
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
