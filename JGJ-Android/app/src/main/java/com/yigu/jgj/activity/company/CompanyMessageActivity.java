package com.yigu.jgj.activity.company;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yigu.jgj.R;
import com.yigu.jgj.activity.SelCommunityActivity;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.RequestCode;
import com.yigu.jgj.broadCast.ReceiverAction;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.application.AppContext;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.view.RlCheckLayout;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompanyMessageActivity extends BaseActivity {
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
    @Bind(R.id.rlCheckLayout)
    RlCheckLayout rlCheckLayout;
    @Bind(R.id.tel)
    EditText tel;

    MapiItemResult itemResult;
    ArrayList<MapiResourceResult> mList = new ArrayList<>();
    int selPos = -1;
    String cid_id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_add);
        ButterKnife.bind(this);
        if (null != getIntent().getExtras()) {
            itemResult = (MapiItemResult) getIntent().getSerializableExtra("item");
        }
        if (null != itemResult) {
            initView();
            load();
        }
    }

    private void initView() {
        tvCenter.setText("修改企业信息");
        tvRight.setText("修改");

        name.setText(itemResult.getNAME());
        address.setText(itemResult.getADDRESS());
        lperson.setText(itemResult.getLPERSON());
        hcaten.setText(itemResult.getHCATEN() + "");
        tel.setText(itemResult.getTEL());
        rlCheckLayout.setData(itemResult);
    }

    private void load() {
        if (null != userSP.getResource()) {
            JSONObject jsonObject = JSONObject.parseObject(userSP.getResource());
            Map<String, ArrayList<MapiResourceResult>> userBean = JSON.parseObject(jsonObject
                            .getJSONObject("data").toJSONString(),
                    new TypeReference<Map<String, ArrayList<MapiResourceResult>>>() {
                    });
            mList.clear();
            if (!userBean.get("SQ").isEmpty())
                mList.addAll(userBean.get("SQ"));
        }

        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getNAME().equals(itemResult.getCOMMUNITY())) {
                selPos = i;
                cid_id = mList.get(i).getZD_ID();
                cid.setText(mList.get(i).getNAME());
                break;
            }
        }
    }

    @OnClick({R.id.back, R.id.tv_right, R.id.cid})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_right:
                String nameStr = name.getText().toString();
                String addressStr = address.getText().toString();
                String lpersonStr = lperson.getText().toString();
                String hcatenStr = hcaten.getText().toString();
                String telStr = tel.getText().toString();
                if (TextUtils.isEmpty(nameStr)) {
                    MainToast.showLongToast("请输入企业名称");
                    return;
                }
                if (TextUtils.isEmpty(addressStr)) {
                    MainToast.showLongToast("请输入地址");
                    return;
                }
                if (TextUtils.isEmpty(lpersonStr)) {
                    MainToast.showLongToast("请输入法人");
                    return;
                }
                if(TextUtils.isEmpty(telStr)){
                    MainToast.showLongToast("请输入电话");
                    return;
                }
                if (TextUtils.isEmpty(cid_id)) {
                    MainToast.showLongToast("请选择社区");
                    return;
                }
                if (TextUtils.isEmpty(hcatenStr)) {
                    MainToast.showLongToast("请输入有效健康证明人数");
                    return;
                }
                if (!rlCheckLayout.vorify())
                    return;
                edit(nameStr, addressStr, lpersonStr, telStr,cid_id, hcatenStr, rlCheckLayout.foodSaleCheck() + "", rlCheckLayout.tvFoodServiceCheck() + "", rlCheckLayout.tvCanteenCheck() + "",
                       "1", "1",rlCheckLayout.getCATEGORY(),rlCheckLayout.getTYPE());
                break;
            case R.id.cid:
                if (TextUtils.isEmpty(userSP.getUserBean().getCOMMUNITY())) {
                    //startActivityForResult 在标准模式下有效
                    Intent intent = new Intent(AppContext.getInstance(), SelCommunityActivity.class);
                    intent.putExtra("list", mList);
                    intent.putExtra("pos", selPos);
                    startActivityForResult(intent, RequestCode.sel_community);
                }
                break;
        }
    }

    private void edit(String name, String address, String lperson, String tel,String cid_id, String hcaten, String FOODSALES, String FOODSERVICE, String CANTEEN, String LICENSE, String PEMIT
    ,String CATEGORY,String TYPE) {
        showLoading();
        ItemApi.editShop(this, itemResult.getID(), FOODSALES, FOODSERVICE, CANTEEN, LICENSE, PEMIT, name, address, lperson,tel, cid_id, hcaten, CATEGORY,TYPE,new RequestCallback() {
            @Override
            public void success(Object success) {
                hideLoading();
                MainToast.showLongToast("修改成功");
                sendBroadcast(new Intent(ReceiverAction.updateCompany_action));//发送广播
                finish();

            }
        }, new RequestExceptionCallback() {
            @Override
            public void error(String code, String message) {
                hideLoading();
            }
        });
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
