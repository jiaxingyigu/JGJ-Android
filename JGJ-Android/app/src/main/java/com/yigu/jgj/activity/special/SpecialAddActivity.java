package com.yigu.jgj.activity.special;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bigkoo.pickerview.TimePickerView;
import com.yigu.jgj.R;
import com.yigu.jgj.activity.SelCommunityActivity;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.RequestCode;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.application.AppContext;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.util.DateUtil;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.view.SpecialBasicLayout;
import com.yigu.jgj.view.SpecialFindLayout;
import com.yigu.jgj.view.SpecialRemarkLayout;
import com.yigu.jgj.view.SpecialResultLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpecialAddActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.specialBasicLayout)
    SpecialBasicLayout specialBasicLayout;
    @Bind(R.id.specialFindLayout)
    SpecialFindLayout specialFindLayout;
    @Bind(R.id.specialResultLayout)
    SpecialResultLayout specialResultLayout;
    @Bind(R.id.specialRemarkLayout)
    SpecialRemarkLayout specialRemarkLayout;
    @Bind(R.id.date)
    TextView dateTv;
    @Bind(R.id.date_rl)
    RelativeLayout dateRl;
    @Bind(R.id.cid)
    TextView cid;

    TimePickerView pvTime;

    ArrayList<MapiResourceResult> mList = new ArrayList<>();
    String cid_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_add);
        ButterKnife.bind(this);
        initView();
        initListener();
        load();
    }

    private void initView() {
        tvCenter.setText("专项行动");
        tvRight.setText("确定");
        dateTv.setText(DateUtil.getInstance().date2YMD_H(new Date()));
    }

    private void load() {

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
                        cid.setText(resourceResult.getNAME());
                        cid_id = resourceResult.getZD_ID();
                        break;
                    }
                }
            }
        }

    }

    private void initListener() {
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
                dateTv.setText(DateUtil.getInstance().date2YMD_H(date));
            }
        });
    }

    @OnClick({R.id.back, R.id.tv_right,R.id.date_rl, R.id.cid})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                if(!specialBasicLayout.vorify())//||!specialFindLayout.vorify()||!specialResultLayout.vorify()||!specialRemarkLayout.vorify()
                    return;
                add();
                break;
            case R.id.date_rl:
                pvTime.show();
                break;
            case R.id.cid:
                if (TextUtils.isEmpty(userSP.getUserBean().getCOMMUNITY())) {
                    //startActivityForResult 在标准模式下有效
                    Intent intent = new Intent(AppContext.getInstance(), SelCommunityActivity.class);
                    intent.putExtra("list", mList);
                    startActivityForResult(intent, RequestCode.sel_community);
                }
                break;
        }
    }

    private void add(){
        showLoading();
        String userId = userSP.getUserBean().getUSER_ID();
//        String CID = userSP.getUserBean().getCOMMUNITY();
        ItemApi.addSpecialaction(this,userId,cid_id,dateTv.getText().toString(),specialBasicLayout.getTitle(),specialBasicLayout.getPeppelNum(),specialBasicLayout.getShopNum(),specialBasicLayout.getPledgeNum(),
                specialBasicLayout.getResponsibilityNum(),specialFindLayout.getLicenseNum(),specialFindLayout.getManageNum(),specialFindLayout.getIllegalNum(),specialFindLayout.getOtherNum(),
                specialResultLayout.getChangeNum(),specialResultLayout.getInvestigationNum(),specialResultLayout.getMoveNum(),specialResultLayout.getOtherResultNum(),
                specialRemarkLayout.getRemark(),new RequestCallback() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCode.sel_community:
                    if (null != data.getExtras()) {
                        MapiResourceResult mapiResourceResult = (MapiResourceResult) data.getSerializableExtra("cid");
                        if (null != mapiResourceResult) {
                            cid.setText(mapiResourceResult.getNAME());
                            cid_id = mapiResourceResult.getZD_ID();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
