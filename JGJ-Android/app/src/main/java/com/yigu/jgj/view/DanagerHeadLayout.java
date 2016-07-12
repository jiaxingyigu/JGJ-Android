package com.yigu.jgj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.yigu.jgj.R;

import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/26.
 */
public class DanagerHeadLayout extends RelativeLayout{
    private Context mContext;
    private View view;
    public DanagerHeadLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public DanagerHeadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public DanagerHeadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView(){
        if(isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_danager_head,this);
        ButterKnife.bind(this,view);
    }

    public void loadData(){

    }
}
