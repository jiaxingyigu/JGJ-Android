package com.yigu.jgj.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.api.UserApi;
import com.yigu.jgj.commom.result.MapiUserResult;
import com.yigu.jgj.commom.util.DateUtil;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.util.ControllerUtil;

import org.xutils.common.util.MD5;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by brain on 2016/8/17.
 */
public class LoginTwoActivity extends BaseActivity{
    @Bind(R.id.user)
    EditText user;
    @Bind(R.id.psd)
    EditText psd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_logintwo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    void Login() {
        if (TextUtils.isEmpty(user.getText().toString())) {
            MainToast.showLongToast("请输入用户名");
            return;
        }

        if (TextUtils.isEmpty(psd.getText().toString())) {
            MainToast.showLongToast("请输入密码");
            return;
        }
        showLoading();
        UserApi.login(this, user.getText().toString(), psd.getText().toString(), new RequestCallback<MapiUserResult>() {
            @Override
            public void success(MapiUserResult success) {
                hideLoading();
                if(null==success){
                    MainToast.showShortToast("用户名或密码错误");
                }else{
                    userSP.saveUserBean(success);
                    ControllerUtil.go2Main();
                    finish();
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

}
