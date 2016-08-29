package com.yigu.jgj.activity.special;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiSepcialResult;
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

public class SpecialDetailActivity extends BaseActivity {

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
    MapiSepcialResult itemResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_add);
        ButterKnife.bind(this);
        if (null != getIntent().getExtras()) {
            itemResult = (MapiSepcialResult) getIntent().getSerializableExtra("item");
        }
        if (null != itemResult) {
            initView();
        }

    }

    private void initView() {
        tvCenter.setText("专项行动");
        dateTv.setText(itemResult.getDate());
        specialBasicLayout.loadData(itemResult,false);
        specialFindLayout.loadData(itemResult,false);
        specialResultLayout.loadData(itemResult,false);
        specialRemarkLayout.loadData(itemResult,false);
    }

    @OnClick({R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

}
