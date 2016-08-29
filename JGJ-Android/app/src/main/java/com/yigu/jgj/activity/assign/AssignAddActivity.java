package com.yigu.jgj.activity.assign;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.activity.person.OtherPersonActivity;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.RequestCode;
import com.yigu.jgj.commom.api.DailyApi;
import com.yigu.jgj.commom.api.UserApi;
import com.yigu.jgj.commom.application.AppContext;
import com.yigu.jgj.commom.result.MapiUserResult;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssignAddActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.name_tv)
    TextView nameTv;
    @Bind(R.id.content)
    EditText content;
    ArrayList<MapiUserResult> userList = new ArrayList<>();
    StringBuffer idBuffer = new StringBuffer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_add);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvCenter.setText("新增指令");
        tvRight.setText("确定");
    }

    @OnClick({R.id.back, R.id.tv_right, R.id.person_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.person_ll:
                Intent intent = new Intent(AppContext.getInstance(), OtherPersonActivity.class);
                intent.putExtra("list", userList);
                intent.putExtra("type","1");
                startActivityForResult(intent, RequestCode.sel_otherPerson);
                break;
            case R.id.tv_right:
                if(TextUtils.isEmpty(content.getText().toString())){
                    MainToast.showShortToast("请输入指令内容");
                    return;
                }
                showLoading();
                DailyApi.addtask(this, userSP.getUserBean().getUSER_ID(), content.getText().toString(), idBuffer.toString()
                        , new RequestCallback() {
                            @Override
                            public void success(Object success) {
                                hideLoading();
                                MainToast.showShortToast("新增成功");
                                finish();
                            }
                        }, new RequestExceptionCallback() {
                            @Override
                            public void error(String code, String message) {
                                hideLoading();
                            }
                        });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCode.sel_otherPerson:
                    if (null != data.getExtras()) {
                        userList = (ArrayList<MapiUserResult>) data.getExtras().getSerializable("list");
                        if (idBuffer.length() > 0)
                            idBuffer.delete(0, idBuffer.length());
                        StringBuffer nameBuffer = new StringBuffer();
                        for (MapiUserResult userResult : userList) {
                            if (!TextUtils.isEmpty(idBuffer.toString())) {
                                idBuffer.append(",");
                                nameBuffer.append(",");
                            }
                            idBuffer.append(userResult.getUSER_ID());
                            nameBuffer.append(userResult.getNAME());
                        }
                        DebugLog.i("userIds==>" + idBuffer.toString());
                        nameTv.setText(nameBuffer.toString());
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
