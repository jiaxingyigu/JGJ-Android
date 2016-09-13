package com.yigu.jgj.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiSepcialResult;
import com.yigu.jgj.commom.widget.MainToast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/8/23.
 */
public class SpecialBasicLayout extends RelativeLayout {
    @Bind(R.id.people_num)
    EditText peopleNum;
    @Bind(R.id.shop_num)
    EditText shopNum;
    @Bind(R.id.pledge_num)
    EditText pledgeNum;
    @Bind(R.id.responsibility_num)
    EditText responsibilityNum;
    @Bind(R.id.title_et)
    EditText titleEt;
    private Context mContext;
    private View view;

    public SpecialBasicLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public SpecialBasicLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public SpecialBasicLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_special_basic, this);
        ButterKnife.bind(this, view);
        titleEt.requestFocus();
    }

    public boolean vorify() {
        if(TextUtils.isEmpty(titleEt.getText().toString())){
            MainToast.showShortToast("请输入行动名称");
            return false;
        }
        if (TextUtils.isEmpty(peopleNum.getText().toString())) {
            MainToast.showShortToast("请输入出动人数");
            return false;
        }
        if (TextUtils.isEmpty(shopNum.getText().toString())) {
            MainToast.showShortToast("请输入检查经营主体");
            return false;
        }
        if (TextUtils.isEmpty(pledgeNum.getText().toString())) {
            MainToast.showShortToast("请输入签订承诺书");
            return false;
        }
        if (TextUtils.isEmpty(responsibilityNum.getText().toString())) {
            MainToast.showShortToast("请输入责任书");
            return false;
        }
        return true;
    }

    public String getTitle(){
        return titleEt.getText().toString();
    }

    public String getPeppelNum() {
        return peopleNum.getText().toString();
    }

    public String getShopNum() {
        return shopNum.getText().toString();
    }

    public String getPledgeNum() {
        return pledgeNum.getText().toString();
    }

    public String getResponsibilityNum() {
        return responsibilityNum.getText().toString();
    }

    public void loadData(MapiSepcialResult itemResult, boolean enable) {
        if (null != itemResult) {
            titleEt.setText(itemResult.getAcname());
            peopleNum.setText(itemResult.getPeople() + "");
            shopNum.setText(itemResult.getShop() + "");
            pledgeNum.setText(itemResult.getCbook() + "");
            responsibilityNum.setText(itemResult.getRbook() + "");

            titleEt.setEnabled(enable);
            peopleNum.setEnabled(enable);
            shopNum.setEnabled(enable);
            pledgeNum.setEnabled(enable);
            responsibilityNum.setEnabled(enable);
        }
    }

}
