package com.yigu.jgj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiPartyResult;
import com.yigu.jgj.commom.result.MapiSepcialResult;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/9/6.
 */
public class PartyBasicLayout extends RelativeLayout {
    @Bind(R.id.holdPeople)
    EditText holdPeople;
    @Bind(R.id.tel)
    EditText tel;
    @Bind(R.id.chef)
    EditText chef;
    @Bind(R.id.community)
    TextView community;
    @Bind(R.id.addr)
    EditText addr;
    private Context mContext;
    private View view;

    public PartyBasicLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public PartyBasicLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public PartyBasicLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_party_basic, this);
        ButterKnife.bind(this, view);
    }

    public void setCommunity(String communityStr) {
        community.setText(communityStr);
    }

    public String getAddr() {
        return addr.getText().toString();
    }

    public String getChef() {
        return chef.getText().toString();
    }

    public String getCommunity() {
        return community.getText().toString();
    }

    public String getHoldPeople() {
        return holdPeople.getText().toString();
    }

    public String getTel() {
        return tel.getText().toString();
    }

    public void loadData(MapiPartyResult itemResult, boolean enable) {
        if(null!=itemResult){
            holdPeople.setText(itemResult.getSponsor());
            tel.setText(itemResult.getTel());
            chef.setText(itemResult.getCook());
            community.setText(itemResult.getCOMMUNITY());
            addr.setText(itemResult.getAddress());

            holdPeople.setEnabled(enable);
            tel.setEnabled(enable);
            chef.setEnabled(enable);
            community.setEnabled(enable);
            addr.setEnabled(enable);
        }
    }


}
