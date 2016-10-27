package com.yigu.jgj.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yigu.jgj.R;
import com.yigu.jgj.adapter.MainAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.broadcast.ReceiverAction;
import com.yigu.jgj.commom.api.CommonApi;
import com.yigu.jgj.commom.application.AppContext;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.util.DPUtil;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.fragment.PersonFragment;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.util.JGJDataSource;
import com.yigu.jgj.util.JpushUtil;
import com.yigu.jgj.widget.DividerGridItemDecoration;
import com.yigu.shop.update.UpdateFunGo;
import com.yigu.shop.update.config.DownloadKey;
import com.yigu.shop.update.config.UpdateKey;
import com.yigu.shop.update.module.MapiUpdateVersionResult;
import com.yigu.shop.update.utils.GetAppInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    //    @Bind(R.id.startLayout)
//    LinearLayout startLayout;
    MainAdapter mAdapter;
    List<MapiResourceResult> mList = new ArrayList<>();
    //    @Bind(R.id.fragment_content)
//    FrameLayout fragmentContent;
//    @Bind(R.id.drawerLayout)
//    DrawerLayout drawerLayout;
    @Bind(R.id.iv_right)
    ImageView ivRight;
//    @Bind(R.id.ll_drawerlayout)
//    LinearLayout llDrawerlayout;

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
            if (JPushInterface.isPushStopped(AppContext.getInstance())) {
                JPushInterface.resumePush(AppContext.getInstance());
            }
            if (!userSP.getAlias()) {
                JpushUtil.getInstance().setAlias(userSP.getUserBean().getUSER_ID());
            }
            registerMessageReceiver();  // used for receive msg
//            startLayout.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                    startLayout.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            setLogoHideAnimation(R.anim.start_alpha_fade_1000);
//                        }
//                    }, 2000);
//
//
//                }
//            }, 500);
        }
        if (null != getIntent()) {
            int type = getIntent().getIntExtra("type", 0);
            if (type == 1) {
                ControllerUtil.go2NotifyList();
            } else if (type == 2) {
                ControllerUtil.go2WarningList();
            }
        }

    }

    private void initView() {
//        back.setImageResource(R.mipmap.person_icon);
        back.setVisibility(View.INVISIBLE);
        tvCenter.setText(getResources().getString(R.string.main_title));
//        ivRight.setImageResource(R.mipmap.msg_have_icon);
//        fragmentManager = getSupportFragmentManager();
//        try {
//            personFragment = new PersonFragment();
//            replaceFragment(personFragment);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    /**
     * 检查版本，若不是最新版本则显示弹框
     *
     * @param result
     */
    private void checkVersion(MapiResourceResult result) {
        if (!GetAppInfo.getAppVersionCode(this).equals(result.getVersion())) {
            DownloadKey.version = result.getVersion();
            DownloadKey.changeLog = result.getRemark();
            DownloadKey.apkUrl = result.getUrl();
            //如果你想通过Dialog来进行下载，可以如下设置
            UpdateKey.DialogOrNotification = UpdateKey.WITH_DIALOG;
            DownloadKey.ToShowDownloadView = DownloadKey.showUpdateView;
            UpdateFunGo.init(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateFunGo.onResume(this);//现在只能弹框下载
    }

    @Override
    protected void onStop() {
        super.onStop();
        UpdateFunGo.onStop();
    }


    /*private void replaceFragment(PersonFragment newFragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();// 创建事物操作对象
        if (!newFragment.isAdded()) {// 如果没有将fragment添加到
            transaction
                    .add(R.id.fragment_content, newFragment)
                    .commitAllowingStateLoss();

        } else {
            transaction.show(newFragment)
                    .commitAllowingStateLoss();
        }

    }*/

    private void initData() {
        mList.clear();
        mList.addAll(JGJDataSource.getAllResource());
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this, DPUtil.dip2px(0.5f), getResources().getColor(R.color.divider_line)));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
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
                    case JGJDataSource.TYPE_DANGER://隐患跟踪
                        ControllerUtil.go2DangerList();
                        break;
                    case JGJDataSource.TYPE_PERSON:
                        ControllerUtil.go2PersonInfo();
                        break;
                    case JGJDataSource.TYPE_FILE://数据查询
                        ControllerUtil.go2File();
                        break;
                    case JGJDataSource.TYPE_TEL:
                        ControllerUtil.go2PerManage();
                        break;
                    case JGJDataSource.TYPE_LICENSE:
                        ControllerUtil.go2WithoutLicense();
                        break;
                    case JGJDataSource.TYPE_SPECIAL:
                        ControllerUtil.go2SpecialList();
                        break;
                    case JGJDataSource.TYPE_NOTIFY:
                        ControllerUtil.go2NotifyList();
                        break;
                    case JGJDataSource.TYPE_WARNING:
                        ControllerUtil.go2WarningList();
                        break;
                    case JGJDataSource.TYPE_PARTY:
                        ControllerUtil.go2Party();
                        break;
                }
            }
        });
    }

//    @OnClick(R.id.iv_right)
//    void right(){
//        ControllerUtil.go2MsgList();
//    }

//    @OnClick(R.id.back)
//    void person() {
//        if (null != drawerLayout && drawerLayout.isDrawerOpen(llDrawerlayout))
//            drawerLayout.closeDrawer(Gravity.LEFT);
//        else
//            drawerLayout.openDrawer(Gravity.LEFT);
//    }

    private void load() {
        showLoading();
        CommonApi.loadResources(this, new RequestCallback<String>() {
            @Override
            public void success(String success) {
                hideLoading();
                userSP.saveResource(success);
                if (null != userSP.getResource()) {
                    JSONObject jsonObject = JSONObject.parseObject(userSP.getResource());
                    Map<String, ArrayList<MapiResourceResult>> userBean = JSON.parseObject(jsonObject
                                    .getJSONObject("data").toJSONString(),
                            new TypeReference<Map<String, ArrayList<MapiResourceResult>>>() {
                            });
                    if (null != userBean) {
                        List<MapiResourceResult> list = new ArrayList<MapiResourceResult>();
                        list.clear();
                        if (!userBean.get("version").isEmpty())
                            list.addAll(userBean.get("version"));
                        if (null != list && list.size() > 0)
                            checkVersion(list.get(0));
                    }

                }
            }
        }, new RequestExceptionCallback() {
            @Override
            public void error(String code, String message) {
                hideLoading();
                MainToast.showShortToast(message);
            }
        });
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出智慧市场监管", Toast.LENGTH_SHORT).show();
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
   /* private void setLogoHideAnimation(int id) {
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
    }*/

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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int type = intent.getIntExtra("type", 0);
        if (type == 1) {
            ControllerUtil.go2NotifyList();
        } else if (type == 2) {
            ControllerUtil.go2WarningList();
        } else if (type == 3) {
            ControllerUtil.go2Login();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        if (null != mMessageReceiver)
            unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }
}