package com.yigu.jgj.activity.daily;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.view.DailyHeadLayout;
import com.yigu.jgj.view.DailyProjectLayout;
import com.yigu.jgj.view.DailySaleLayout;
import com.yigu.jgj.view.DailyServiceLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DailySecondTwoActivity extends BaseActivity {

    @Bind(R.id.dailyHeadLayout)
    DailyHeadLayout dailyHeadLayout;
    @Bind(R.id.dailyProjectLayout)
    DailyProjectLayout dailyProjectLayout;
    @Bind(R.id.dailySaleLayout)
    DailySaleLayout dailySaleLayout;
    @Bind(R.id.dailyServiceLayout)
    DailyServiceLayout dailyServiceLayout;

    MapiItemResult itemResult;

    MapiItemResult item;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getIntent().getExtras())
            itemResult = (MapiItemResult) getIntent().getSerializableExtra("item");
        if (null != itemResult) {
            setContentView(R.layout.activity_daily_second_two);
            ButterKnife.bind(this);
            initView();
            load();
        }

    }

    private void initView() {
        tvCenter.setText("日常巡查");
        tvRight.setText("下一步");

        if(itemResult.getFOODSALES()==1) {
            dailySaleLayout.setVisibility(View.VISIBLE);

        }else
            dailySaleLayout.setVisibility(View.GONE);

        if(itemResult.getFOODSERVICE()==1||itemResult.getCANTEEN()==1){
            dailyServiceLayout.setVisibility(View.VISIBLE);
        }else
            dailyServiceLayout.setVisibility(View.GONE);

    }

    private void load(){
        dailyHeadLayout.loadData(itemResult);
        dailyProjectLayout.loadData(itemResult,true);
        dailySaleLayout.loadData(itemResult,true);
        dailyServiceLayout.loadData(itemResult,true);

    }

    @OnClick({R.id.back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                initInfo();
                if(null!=item)
                    ControllerUtil.go2DailyThird(item);
                break;
        }
    }

    private void initInfo(){
        item = new MapiItemResult();
        item.setID(itemResult.getID());
        if(dailyProjectLayout.vorify()){
            item.setPtioners(TextUtils.isEmpty(dailyProjectLayout.getPtioners())?0:Integer.valueOf(dailyProjectLayout.getPtioners()));
            item.setHCATEN(TextUtils.isEmpty(dailyProjectLayout.getHcate())?0:Integer.valueOf(dailyProjectLayout.getHcate()));
            item.setShowlicense(dailyProjectLayout.showlicenseCheck());
            item.setHygiene(dailyProjectLayout.hygieneCheck());
            item.setInvoice(dailyProjectLayout.invoiceCheck());
            DebugLog.i(dailyProjectLayout.getPtioners());
            DebugLog.i(dailyProjectLayout.getHcate());
        }else {
            item = null;
            return;
        }


        if(dailySaleLayout.getVisibility()==View.VISIBLE){
            if(dailySaleLayout.vorify()){
                item.setSanitation(dailySaleLayout.sanitationCheck());
                item.setOverdue(dailySaleLayout.overdueCheck());
                item.setFullmark(dailySaleLayout.fullmarkCheck());
            }else {
                item = null;
                return;
            }
        }

        if(dailyServiceLayout.getVisibility()==View.VISIBLE){
            if(dailyServiceLayout.vorify()){
                item.setTrain(dailyServiceLayout.trainCheck());
                item.setDisinfection(dailyServiceLayout.disinfectionCheck());
                item.setPoster(dailyServiceLayout.posterCheck());
            }else {
                item = null;
                return;
            }
        }
    }

}
