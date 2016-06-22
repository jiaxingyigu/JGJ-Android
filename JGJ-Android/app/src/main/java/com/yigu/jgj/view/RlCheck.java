package com.yigu.jgj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.yigu.jgj.R;

import butterknife.ButterKnife;

/**
 * Created by yzb on 2016/6/21.
 */
public class RlCheck extends RelativeLayout{
    View view;
    Context mContext;
    public RlCheck(Context context) {

        super(context);
        mContext = context;
        initView();
    }

    public RlCheck(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public RlCheck(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView(){
        view = LayoutInflater.from(mContext).inflate(R.layout.rl_company_message_check,this);
        ButterKnife.bind(this, view);
    }

}
