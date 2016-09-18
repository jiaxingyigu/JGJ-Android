package com.yigu.jgj.activity.license;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.activity.SelCommunityActivity;
import com.yigu.jgj.activity.person.PerManageActivity;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.RequestCode;
import com.yigu.jgj.commom.application.AppContext;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.view.LicenseCheckLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LicenseDetailActivity extends BaseActivity {
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
    String cid_id = "";
    ArrayList<MapiResourceResult> mList = new ArrayList<>();
    MapiItemResult itemResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license_detail);
        ButterKnife.bind(this);
        if (null != getIntent().getExtras()) {
            itemResult = (MapiItemResult) getIntent().getSerializableExtra("item");
        }
        if (null != itemResult) {
            initView();
//            load();
        }
    }

    private void initView() {
        tvCenter.setText("无照上报信息");
        tvRight.setText("分派");
        rlCheckLayout.hiddenReport();
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
    }

//    private void load() {
//
//        if (null != userSP.getResource()) {
//            JSONObject jsonObject = JSONObject.parseObject(userSP.getResource());
//            Map<String, ArrayList<MapiResourceResult>> userBean = JSON.parseObject(jsonObject
//                            .getJSONObject("data").toJSONString(),
//                    new TypeReference<Map<String, ArrayList<MapiResourceResult>>>() {
//                    });
//            mList.clear();
//            if (!userBean.get("SQ").isEmpty())
//                mList.addAll(userBean.get("SQ"));
//            if (!TextUtils.isEmpty(userSP.getUserBean().getCOMMUNITY())) {
//                for (MapiResourceResult resourceResult : mList) {
//                    if (resourceResult.getZD_ID().equals(userSP.getUserBean().getCOMMUNITY())) {
//                        cid.setText(resourceResult.getNAME());
//                        cid_id = resourceResult.getZD_ID();
//                        break;
//                    }
//                }
//            }
//        }
//
//    }

    @OnClick({R.id.back, R.id.tv_right, R.id.cid})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                Intent intent = new Intent(this, PerManageActivity.class);
                intent.putExtra("requestCode", RequestCode.assign_license_person);
                intent.putExtra("ID", itemResult.getID());
                startActivity(intent);
                break;
            case R.id.cid:
                if (TextUtils.isEmpty(userSP.getUserBean().getCOMMUNITY())) {
                    //startActivityForResult 在标准模式下有效
                    Intent intent1 = new Intent(AppContext.getInstance(), SelCommunityActivity.class);
                    intent1.putExtra("list", mList);
                    startActivityForResult(intent1, RequestCode.sel_community);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCode.sel_community:
                    if (null != data.getExtras()) {
                        MapiResourceResult mapiResourceResult = (MapiResourceResult) data.getSerializableExtra("cid");
                        if (null != mapiResourceResult) {
                            cid.setText(mapiResourceResult.getNAME());
                            cid_id = mapiResourceResult.getZD_ID();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
