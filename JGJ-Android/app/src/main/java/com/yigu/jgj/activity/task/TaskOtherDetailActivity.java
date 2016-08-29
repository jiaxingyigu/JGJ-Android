package com.yigu.jgj.activity.task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.api.DailyApi;
import com.yigu.jgj.commom.result.MapiOtherResult;
import com.yigu.jgj.commom.util.DateUtil;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskOtherDetailActivity extends BaseActivity {

    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.name_tv)
    TextView nameTv;
    @Bind(R.id.date_tv)
    TextView dateTv;
    @Bind(R.id.content)
    EditText content;
    MapiOtherResult otherResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_other_detail);
        ButterKnife.bind(this);
        if(null!=getIntent())
            otherResult = (MapiOtherResult) getIntent().getSerializableExtra("item");
        if(null!=otherResult)
            initView();
    }

    @OnClick({R.id.back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                if(null!=otherResult)
                    complete();
                break;
        }
    }

    private void initView(){
        tvCenter.setText("其他");
        tvRight.setText("完成");
        nameTv.setText(otherResult.getName());
        dateTv.setText(DateUtil.getInstance().YMDHMS2YMD(otherResult.getTimes()));
        content.setText(TextUtils.isEmpty(otherResult.getRemark())?"":otherResult.getRemark());
        content.setEnabled(false);
    }

    private void complete(){
        showLoading();
        DailyApi.Othertaskcomplete(this, otherResult.getId(), new RequestCallback() {
            @Override
            public void success(Object success) {
                hideLoading();
                MainToast.showShortToast("任务完成");
                setResult(RESULT_OK);
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
