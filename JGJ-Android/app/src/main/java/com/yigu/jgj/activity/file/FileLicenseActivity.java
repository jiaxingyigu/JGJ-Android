package com.yigu.jgj.activity.file;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.util.DateUtil;
import com.yigu.jgj.view.LicenseCheckLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileLicenseActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.tv_center)
    TextView tvCenter;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.address)
    EditText address;
    @Bind(R.id.lperson)
    EditText lperson;
    @Bind(R.id.cid)
    TextView cid;
    @Bind(R.id.hcaten)
    EditText hcaten;
    @Bind(R.id.tel)
    EditText tel;
    @Bind(R.id.rlCheckLayout)
    LicenseCheckLayout rlCheckLayout;
    @Bind(R.id.taskstime)
    TextView taskstime;
    @Bind(R.id.senduser)
    TextView senduser;
    @Bind(R.id.idate)
    TextView idate;
    @Bind(R.id.user)
    TextView user;

    String cid_id = "";
    ArrayList<MapiResourceResult> mList = new ArrayList<>();
    MapiItemResult itemResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_license);
        ButterKnife.bind(this);
        if (null != getIntent().getExtras()) {
            itemResult = (MapiItemResult) getIntent().getSerializableExtra("item");
        }
        if (null != itemResult) {
            initView();
        }
    }

    private void initView() {
        tvCenter.setText("无照上报信息");
        rlCheckLayout.hiddenPerson();
        name.setText(itemResult.getNAME());
        address.setText(itemResult.getADDRESS());
        lperson.setText(itemResult.getLPERSON());
        hcaten.setText(itemResult.getHCATEN() + "");
        tel.setText(itemResult.getTEL());
        cid.setText(itemResult.getCOMMUNITY());
        name.setEnabled(false);
        address.setEnabled(false);
        lperson.setEnabled(false);
        hcaten.setEnabled(false);
        tel.setEnabled(false);
        cid.setEnabled(false);
        rlCheckLayout.setData(itemResult);
        rlCheckLayout.setNoEdit();

        idate.setText("检查日期："+ DateUtil.getInstance().YMDHMS2YMD(itemResult.getTaskotime()));
        taskstime.setText("指派日期："+DateUtil.getInstance().YMDHMS2YMD(itemResult.getTaskstime()));
        senduser.setText("指派人："+itemResult.getSenduser());
        user.setText("检查人："+itemResult.getUser());

    }

    @OnClick({R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

        }
    }

}
