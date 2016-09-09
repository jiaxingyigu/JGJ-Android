package com.yigu.jgj.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.api.DailyApi;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyPsdActivity extends BaseActivity {

    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.oldPsd_et)
    EditText oldPsdEt;
    @Bind(R.id.newPsd_et)
    EditText newPsdEt;
    @Bind(R.id.nextPsd_et)
    EditText nextPsdEt;
    @Bind(R.id.tv_center)
    TextView tvCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psd);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvCenter.setText("修改密码");
        tvRight.setText("确定");
    }

    @OnClick({R.id.back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                if(TextUtils.isEmpty(oldPsdEt.getText())){
                    MainToast.showShortToast("请输入原密码");
                    return;
                }
                if(TextUtils.isEmpty(newPsdEt.getText())){
                    MainToast.showShortToast("请输入新密码");
                    return;
                }
                if(TextUtils.isEmpty(nextPsdEt.getText())){
                    MainToast.showShortToast("请再输入一次新密码");
                    return;
                }

                if(!newPsdEt.getText().toString().equals(nextPsdEt.getText().toString())){
                    MainToast.showShortToast("两次新密码输入不一致");
                    return;
                }

                modify();
                break;
        }
    }

    private void modify(){
        DailyApi.editPassword(this, userSP.getUserBean().getUSER_ID(),userSP.getUserBean().getUSERNAME(), oldPsdEt.getText().toString(), nextPsdEt.getText().toString(), new RequestCallback() {
            @Override
            public void success(Object success) {
                MainToast.showShortToast("密码修改成功");
                finish();
            }
        }, new RequestExceptionCallback() {
            @Override
            public void error(String code, String message) {
                MainToast.showShortToast(message);
            }
        });
    }

}
