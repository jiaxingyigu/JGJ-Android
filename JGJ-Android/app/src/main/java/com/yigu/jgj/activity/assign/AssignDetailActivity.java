package com.yigu.jgj.activity.assign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.activity.person.PerManageActivity;
import com.yigu.jgj.adapter.assign.AssignDetailAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.Config;
import com.yigu.jgj.base.RequestCode;
import com.yigu.jgj.broadcast.ReceiverAction;
import com.yigu.jgj.commom.api.DailyApi;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.result.MapiTaskResult;
import com.yigu.jgj.commom.util.DPUtil;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.widget.TopPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssignDetailActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.iv_right)
    ImageView tvRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.bg_color)
    View bgColor;

    AssignDetailAdapter mAdapter;

    MapiTaskResult taskResult;
    List<Integer> list = new ArrayList();
    List<MapiResourceResult> sList = new ArrayList<>();
    TopPopWindow topPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_detail);
        ButterKnife.bind(this);
        if(null!=getIntent().getExtras())
            taskResult = (MapiTaskResult) getIntent().getSerializableExtra("item");
        if(null!=taskResult){
            initView();
            initListener();
            load();
        }
    }

    private void initView() {
        tvCenter.setText("任务分派");
        tvRight.setImageResource(R.mipmap.menu_icon);
        list.add(Config.daily_head);
        list.add(Config.daily_project);
        if(taskResult.getFOODSALES()==1)
            list.add(Config.daily_sale);
        if(taskResult.getFOODSERVICE()==1||taskResult.getCANTEEN()==1)
            list.add(Config.daily_service_canteen);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new AssignDetailAdapter(this,list);
        recyclerView.setAdapter(mAdapter);
        sList.add(new MapiResourceResult("0","分派"));
        sList.add(new MapiResourceResult("1","转移"));
        sList.add(new MapiResourceResult("2","退回"));
        topPopWindow = new TopPopWindow(this, DPUtil.dip2px(149), sList, R.style.PopupWindowAnimation);
    }

    private void initListener(){
        topPopWindow.setOnPopItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                if(null!=view){
                    if(postion>=0){
                        if("0".equals(sList.get(postion).getZD_ID())){
                            Intent intent = new Intent(AssignDetailActivity.this, PerManageActivity.class);
                            intent.putExtra("requestCode", RequestCode.assign_person);
                            intent.putExtra("ID", taskResult.getID());
                            startActivity(intent);
                        }else if("1".equals(sList.get(postion).getZD_ID())){
                            transfer();
                        }else if("2".equals(sList.get(postion).getZD_ID())){
                            reBack();
                        }
                    }

                }
                bgColor.setVisibility(View.GONE);
            }
        });
    }

    @OnClick({R.id.back, R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.iv_right:
                topPopWindow.showPopupWindow(view);
                bgColor.setVisibility(View.VISIBLE);

                break;
        }
    }

    private void load(){
        DebugLog.i("taskResult.getID()"+taskResult.getID());
        ItemApi.dailyPatrolDetails(this, taskResult.getID(), new RequestCallback<MapiItemResult>() {
            @Override
            public void success(MapiItemResult success) {
                if(null!=success){
                    if(!TextUtils.isEmpty(success.getRemark()))
                        list.add(Config.daily_remark);
                    if(null!=success.getImages()&&!success.getImages().isEmpty())
                        list.add(Config.daily_image);
                    mAdapter.setItemResult(success);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }, new RequestExceptionCallback() {
            @Override
            public void error(String code, String message) {

            }
        });
    }

    private void transfer(){
        showLoading();
        DailyApi.tasktransfer(this, userSP.getUserBean().getROLE_ID(), taskResult.getID(), new RequestCallback() {
            @Override
            public void success(Object success) {
                hideLoading();
                MainToast.showShortToast("转移完成");
                sendBroadcast(new Intent(ReceiverAction.assignDanager_action));//发送广播
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

    private void reBack(){
        showLoading();
        DailyApi.taskback(this, taskResult.getID(), new RequestCallback() {
            @Override
            public void success(Object success) {
                hideLoading();
                MainToast.showShortToast("成功退回");
                sendBroadcast(new Intent(ReceiverAction.assignDanager_action));//发送广播
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
