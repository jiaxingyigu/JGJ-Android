package com.yigu.jgj.activity.special;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.util.DateUtil;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.view.SpecialBasicLayout;
import com.yigu.jgj.view.SpecialFindLayout;
import com.yigu.jgj.view.SpecialRemarkLayout;
import com.yigu.jgj.view.SpecialResultLayout;

import java.util.Date;

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

    TimePickerView pvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_add);
        ButterKnife.bind(this);
        initView();
        initListener();
    }

    private void initView() {
        tvCenter.setText("专项行动");
        tvRight.setText("确定");
        dateTv.setText(DateUtil.getInstance().date2YMD_H(new Date()));
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

    @OnClick({R.id.back, R.id.tv_right,R.id.date_rl})
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
        }
    }

    private void add(){
        showLoading();
        String userId = userSP.getUserBean().getUSER_ID();
        String CID = userSP.getUserBean().getCOMMUNITY();
        ItemApi.addSpecialaction(this,userId,CID,dateTv.getText().toString(),specialBasicLayout.getTitle(),specialBasicLayout.getPeppelNum(),specialBasicLayout.getShopNum(),specialBasicLayout.getPledgeNum(),
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

}
