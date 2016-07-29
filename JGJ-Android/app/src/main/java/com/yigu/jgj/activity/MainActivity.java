package com.yigu.jgj.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.MainAdapter;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.api.CommonApi;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.ControllerUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.startLayout)
    LinearLayout startLayout;
    MainAdapter mAdapter;
    List<Integer> mList = new ArrayList<>();


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
        back.setVisibility(View.INVISIBLE);
        tvRight.setVisibility(View.GONE);
        tvCenter.setText(getResources().getString(R.string.main_title));
    }

    private void initData() {
        mList.add(R.mipmap.main_one);
        mList.add(R.mipmap.main_two);
        mList.add(R.mipmap.main_three);
        mList.add(R.mipmap.main_four);
        mList.add(R.mipmap.main_five);
        mList.add(R.mipmap.main_six);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        mAdapter = new MainAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);
        if (TextUtils.isEmpty(userSP.getResource()))
            load();
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        ControllerUtil.go2Daily();
                        break;
                    case 1:
                        ControllerUtil.go2CompanyList();
                        break;
                    case 2:
//                        ControllerUtil.go2MyTask();
                        ControllerUtil.go2AssignTask();
                        break;
                    case 3://隐患档案
                        ControllerUtil.go2DangerList();
                        break;
                    case 4:
                        ControllerUtil.go2PerManage();
                        break;
                    case 5:
                        ControllerUtil.go2File();
                        break;
                }
            }
        });
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

}
