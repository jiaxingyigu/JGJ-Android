package com.yigu.jgj.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.api.UserApi;
import com.yigu.jgj.commom.result.MapiUserResult;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.util.ControllerUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.user)
    EditText user;
    @Bind(R.id.psd_icon)
    ImageView psd_icon;
    @Bind(R.id.psd)
    EditText psd;
    @Bind(R.id.user_icon)
    ImageView userIcon;
    @Bind(R.id.user_line)
    View userLine;
    @Bind(R.id.psd_line)
    View psdLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initListener();
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
                userSP.saveUserBean(success);
                ControllerUtil.go2Main();
                finish();
            }
        }, new RequestExceptionCallback() {
            @Override
            public void error(String code, String message) {
                hideLoading();
                MainToast.showShortToast(message);
            }
        });

    }

    private void initListener() {
        user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){

                    userIcon.setImageResource(R.mipmap.user_edit_icon);
                    userLine.setBackgroundColor(Color.parseColor("#019b79"));
                }else{
                    userIcon.setImageResource(R.mipmap.user_icon);
                    userLine.setBackgroundColor(Color.parseColor("#eeeeee"));
                }

            }
        });
        psd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    psd_icon.setImageResource(R.mipmap.psd_edit_icon);
                    psdLine.setBackgroundColor(Color.parseColor("#019b79"));
                }else{
                    psd_icon.setImageResource(R.mipmap.psd_icon);
                    psdLine.setBackgroundColor(Color.parseColor("#eeeeee"));
                }
            }
        });
    }

}
