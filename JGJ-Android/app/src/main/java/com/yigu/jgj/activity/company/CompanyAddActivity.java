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
import com.yigu.jgj.broadcast.ReceiverAction;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.application.AppContext;
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

public class CompanyAddActivity extends BaseActivity {
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
    RlCheckLayout rlCheckLayout;
    String cid_id = "";
    ArrayList<MapiResourceResult> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_add);
        ButterKnife.bind(this);
        initView();
        load();
    }

    private void initView() {
        tvCenter.setText("新增企业");
        tvRight.setText("确定");
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
            if (!TextUtils.isEmpty(userSP.getUserBean().getCOMMUNITY())) {
                for (MapiResourceResult resourceResult : mList) {
                    if (resourceResult.getZD_ID().equals(userSP.getUserBean().getCOMMUNITY())) {
                        cid.setText(resourceResult.getNAME());
                        cid_id = resourceResult.getZD_ID();
                        break;
                    }
                }
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
                    MainToast.showShortToast("请输入企业名称");
                    return;
                }
                if (TextUtils.isEmpty(addressStr)) {
                    MainToast.showShortToast("请输入地址");
                    return;
                }
                if (TextUtils.isEmpty(lpersonStr)) {
                    MainToast.showShortToast("请输入法人");
                    return;
                }
                if (TextUtils.isEmpty(telStr)) {
                    MainToast.showShortToast("请输入电话");
                    return;
                }
                if (TextUtils.isEmpty(cid_id)) {
                    MainToast.showShortToast("请选择社区");
                    return;
                }
                if (TextUtils.isEmpty(hcatenStr)) {
                    MainToast.showShortToast("请输入健康证数");
                    return;
                }
                if (!rlCheckLayout.vorify())
                    return;
                add(nameStr, addressStr, lpersonStr, cid_id, hcatenStr, rlCheckLayout.foodSaleCheck() + "", rlCheckLayout.tvFoodServiceCheck() + "", rlCheckLayout.tvCanteenCheck() + "",
                        "1", "1", telStr,rlCheckLayout.getCATEGORY(),rlCheckLayout.getTYPE());
                break;
            case R.id.cid:
                if (TextUtils.isEmpty(userSP.getUserBean().getCOMMUNITY())) {
                    //startActivityForResult 在标准模式下有效
                    Intent intent = new Intent(AppContext.getInstance(), SelCommunityActivity.class);
                    intent.putExtra("list", mList);
                    startActivityForResult(intent, RequestCode.sel_community);
                }
                break;
        }
    }

    private void add(String name, String address, String lperson, String cid_id, String hcaten, String FOODSALES, String FOODSERVICE, String CANTEEN, final String LICENSE, final String PEMIT, String TEL
                     ,String CATEGORY,String TYPE) {
        showLoading();
        ItemApi.addShop(this, FOODSALES, FOODSERVICE, CANTEEN, LICENSE, PEMIT, name, address, lperson, cid_id, hcaten, TEL,CATEGORY,TYPE,new RequestCallback() {
            @Override
            public void success(Object success) {
                hideLoading();
                MainToast.showLongToast("新增成功");
                String action = ReceiverAction.updateCompany_action;
                sendBroadcast(new Intent(action));//发送广播
                finish();
            }
        }, new RequestExceptionCallback() {
            @Override
            public void error(String code, String message) {
                hideLoading();
                MainToast.showLongToast(message);
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
