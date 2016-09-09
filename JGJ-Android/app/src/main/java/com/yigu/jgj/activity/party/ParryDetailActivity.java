package com.yigu.jgj.activity.party;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.result.MapiPartyResult;
import com.yigu.jgj.commom.result.MapiSepcialResult;
import com.yigu.jgj.view.PartyBasicLayout;
import com.yigu.jgj.view.PartyInfoLayout;
import com.yigu.jgj.view.SpecialBasicLayout;
import com.yigu.jgj.view.SpecialFindLayout;
import com.yigu.jgj.view.SpecialRemarkLayout;
import com.yigu.jgj.view.SpecialResultLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParryDetailActivity extends BaseActivity {

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
    MapiPartyResult itemResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_add);
        ButterKnife.bind(this);
        if (null != getIntent().getExtras()) {
            itemResult = (MapiPartyResult) getIntent().getSerializableExtra("item");
        }
        if (null != itemResult) {
            initView();
        }

    }

    private void initView() {
        tvCenter.setText("专项行动");
        startDate.setText(itemResult.getStardate());
        endDate.setText(itemResult.getEnddate());
        partyBasicLayout.loadData(itemResult,false);
        partyInfoLayout.loadData(itemResult,false);
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
