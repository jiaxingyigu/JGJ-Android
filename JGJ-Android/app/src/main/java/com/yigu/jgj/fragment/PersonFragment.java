package com.yigu.jgj.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseFrag;
import com.yigu.jgj.commom.application.AppContext;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.result.MapiUserResult;
import com.yigu.jgj.commom.util.StringUtil;
import com.yigu.jgj.util.ControllerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by brain on 2016/7/30.
 */
public class PersonFragment extends BaseFrag {

    @Bind(R.id.name_circle)
    TextView nameCircle;
    @Bind(R.id.name_tv)
    TextView nameTv;
    @Bind(R.id.company)
    TextView company;
    @Bind(R.id.company_ll)
    LinearLayout companyLl;
    @Bind(R.id.community)
    TextView community;
    @Bind(R.id.community_ll)
    LinearLayout communityLl;
    @Bind(R.id.tel)
    TextView tel;
    @Bind(R.id.tel_ll)
    LinearLayout telLl;
    @Bind(R.id.guide)
    TextView guide;
    @Bind(R.id.exit)
    TextView exit;

    List<MapiResourceResult> companyList = new ArrayList<>();
    List<MapiResourceResult> communityList = new ArrayList<>();
    MapiUserResult userResult;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initView() {
       userResult = userSP.getUserBean();
        if(null!=userResult){
            nameCircle.setText(StringUtil.nameFormat(userResult.getNAME()));
            nameTv.setText(userResult.getNAME());
            if(TextUtils.isEmpty(userResult.getCOMPANY())){
                companyLl.setVisibility(View.GONE);
            }else{
                companyLl.setVisibility(View.VISIBLE);

            }

            if(TextUtils.isEmpty(userResult.getCOMMUNITY())){
                communityLl.setVisibility(View.GONE);
            }else{
                communityLl.setVisibility(View.VISIBLE);

            }

            tel.setText(userResult.getPHONE());

        }
    }

    private void initData(){
        if(null!=userSP.getResource()){
            JSONObject jsonObject = JSONObject.parseObject(userSP.getResource());
            Map<String, ArrayList<MapiResourceResult>> userBean = JSON.parseObject(jsonObject
                            .getJSONObject("data").toJSONString(),
                    new TypeReference<Map<String, ArrayList<MapiResourceResult>>>() {
                    });
            companyList.clear();
            if(!userBean.get("DW").isEmpty())
                companyList.addAll(userBean.get("DW"));
            if(!userBean.get("SQ").isEmpty())
                communityList.addAll(userBean.get("SQ"));
            for(MapiResourceResult resourceResult:companyList){
                if(resourceResult.getZD_ID().equals(userResult.getCOMPANY())){
                    company.setText(resourceResult.getNAME());
                   break;
                }
            }

            for(MapiResourceResult resourceResult:communityList){
                if(resourceResult.getZD_ID().equals(userResult.getCOMMUNITY())){
                    community.setText(resourceResult.getNAME());
                    break;
                }
            }

        }
    }

    @OnClick({R.id.guide, R.id.exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.guide:
                ControllerUtil.go2Guide();
                break;
            case R.id.exit:
                userSP.clearUserData();
                JPushInterface.stopPush(AppContext.getInstance());
                ControllerUtil.go2Login();
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
