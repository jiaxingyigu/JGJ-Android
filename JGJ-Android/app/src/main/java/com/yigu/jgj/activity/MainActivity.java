package com.yigu.jgj.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.MainAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.broadCast.ReceiverAction;
import com.yigu.jgj.commom.api.CommonApi;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.fragment.PersonFragment;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.util.JGJDataSource;
import com.yigu.jgj.util.JpushUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.startLayout)
    LinearLayout startLayout;
    MainAdapter mAdapter;
    List<MapiResourceResult> mList = new ArrayList<>();
    @Bind(R.id.fragment_content)
    FrameLayout fragmentContent;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.ll_drawerlayout)
    LinearLayout llDrawerlayout;

    private FragmentManager fragmentManager;
    private PersonFragment personFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!userSP.checkLogin()) {
            ControllerUtil.go2Login();
            finish();
        } else {
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
            initView();
            initData();
            initListener();
            JpushUtil.getInstance().verifyInit(this);
            if(!JpushUtil.getInstance().pref.getBoolean("isAlias", false)){
                JpushUtil.getInstance().setAlias("1111");
            }
            registerMessageReceiver();  // used for receive msg
            startLayout.postDelayed(new Runnable() {
                @Override
                public void run() {

                    startLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setLogoHideAnimation(R.anim.start_alpha_fade_1000);
                        }
                    }, 2000);


                }
            }, 500);
        }
    }

    private void initView() {
        back.setImageResource(R.mipmap.person_icon);
        tvRight.setVisibility(View.GONE);
        tvCenter.setText(getResources().getString(R.string.main_title));
        fragmentManager = getSupportFragmentManager();
        try {
            personFragment = new PersonFragment();
            replaceFragment(personFragment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void replaceFragment(PersonFragment newFragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();// 创建事物操作对象
        if (!newFragment.isAdded()) {// 如果没有将fragment添加到
            transaction
                    .add(R.id.fragment_content, newFragment)
                    .commitAllowingStateLoss();

        } else {
            transaction.show(newFragment)
                    .commitAllowingStateLoss();
        }

    }

    private void initData() {
        String role_id = userSP.getUserBean().getROLE_ID();
        mList.clear();
        if (!TextUtils.isEmpty(role_id)) {
            if (role_id.equals(JGJDataSource.root_one_roleid) || role_id.equals(JGJDataSource.root_two_roleid))
                mList.addAll(JGJDataSource.getRootResource());
            else if (role_id.equals(JGJDataSource.manage_roleid))
                mList.addAll(JGJDataSource.getManagerResource());
            else
                mList.addAll(JGJDataSource.getOtherResource());
        }

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        mAdapter = new MainAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);
//        if (TextUtils.isEmpty(userSP.getResource()))
        load();
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (mList.get(position).getId()) {
                    case JGJDataSource.TYPE_DAILY:
                        ControllerUtil.go2Daily();
                        break;
                    case JGJDataSource.TYPE_COMPANY:
                        ControllerUtil.go2CompanyList();
                        break;
                    case JGJDataSource.TYPE_TASK:
                        ControllerUtil.go2MyTask();
                        break;
                    case JGJDataSource.TYPE_ASSIGN:
                        ControllerUtil.go2AssignTask();
                        break;
                    case JGJDataSource.TYPE_DANGER://隐患档案
                        ControllerUtil.go2DangerList();
                        break;
                    case JGJDataSource.TYPE_PERSON:
                        ControllerUtil.go2PerManage();
                        break;
                    case JGJDataSource.TYPE_FILE:
                        ControllerUtil.go2File();
                        break;
                }
            }
        });
    }

    @OnClick(R.id.back)
    void person() {
        if (null != drawerLayout && drawerLayout.isDrawerOpen(llDrawerlayout))
            drawerLayout.closeDrawer(Gravity.LEFT);
        else
            drawerLayout.openDrawer(Gravity.LEFT);
    }

    private void load() {
        CommonApi.loadResources(this, new RequestCallback<String>() {
            @Override
            public void success(String success) {
                userSP.saveResource(success);
            }
        }, new RequestExceptionCallback() {
            @Override
            public void error(String code, String message) {
                MainToast.showShortToast(message);
            }
        });
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出食品监管", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 隐藏启动页
     */
    private void setLogoHideAnimation(int id) {
        Animation animation = AnimationUtils.loadAnimation(this, id);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                DebugLog.i("onAnimationEnd");
                startLayout.clearAnimation();
                startLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startLayout.startAnimation(animation);
    }

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(ReceiverAction.MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (ReceiverAction.MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(JpushUtil.KEY_MESSAGE);
                String extras = intent.getStringExtra(JpushUtil.KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(JpushUtil.KEY_MESSAGE + " : " + messge + "\n");
                if (!JpushUtil.getInstance().isEmpty(extras)) {
                    showMsg.append(JpushUtil.KEY_EXTRAS + " : " + extras + "\n");
                }
                MainToast.showLongToast(showMsg.toString());
            }
        }
    }

    @Override
    protected void onDestroy() {
        if(null!=mMessageReceiver)
            unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

}
