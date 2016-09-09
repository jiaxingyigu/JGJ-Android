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
public class PartyInfoLayout extends RelativeLayout {

    @Bind(R.id.partyNum)
    EditText partyNum;
    @Bind(R.id.peopleNum)
    EditText peopleNum;
    @Bind(R.id.morbidity)
    EditText morbidity;
    @Bind(R.id.guide)
    TextView guide;
    @Bind(R.id.guideTel)
    EditText guideTel;
    @Bind(R.id.guideNum)
    EditText guideNum;
    private Context mContext;
    private View view;

    public PartyInfoLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public PartyInfoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public PartyInfoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_party_info, this);
        ButterKnife.bind(this, view);
    }

    public void setGuide(String guideStr){
        guide.setText(guideStr);
    }

    public String getGuide() {
        return guide.getText().toString();
    }

    public String getGuideNum() {
        return guideNum.getText().toString();
    }

    public String getGuideTel() {
        return guideTel.getText().toString();
    }

    public String getMorbidity() {
        return morbidity.getText().toString();
    }

    public String getPartyNum() {
        return partyNum.getText().toString();
    }

    public String getPeopleNum() {
        return peopleNum.getText().toString();
    }

    public void loadData(MapiPartyResult itemResult, boolean enable) {
        partyNum.setText(itemResult.getMealtime());
        peopleNum.setText(itemResult.getAttend());
        morbidity.setText(itemResult.getPatient());
        guide.setText(itemResult.getName());
        guideTel.setText(itemResult.getUtel());
        guideNum.setText(itemResult.getGuidance());

        partyNum.setEnabled(enable);
        peopleNum.setEnabled(enable);
        morbidity.setEnabled(enable);
        guide.setEnabled(enable);
        guideTel.setEnabled(enable);
        guideNum.setEnabled(enable);
    }


}
