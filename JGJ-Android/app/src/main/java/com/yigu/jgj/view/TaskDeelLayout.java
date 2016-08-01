package com.yigu.jgj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.yigu.jgj.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by brain on 2016/6/26.
 */
public class TaskDeelLayout extends RelativeLayout {
    private Context mContext;
    private View view;

    public TaskDeelLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public TaskDeelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public TaskDeelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_task_deel, this);
        ButterKnife.bind(this, view);
    }

    public void loadData() {

    }

    @OnClick({R.id.change, R.id.investigation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change:
                if(null!=l)
                    l.change();
                break;
            case R.id.investigation:
                if(null!=l)
                    l.investigation();
                break;
        }
    }

    private OnDeelClickListener l;

    public interface OnDeelClickListener{
        void change();
        void investigation();
    }

    public void setOnDeelClickListener(OnDeelClickListener l){
        this.l = l;
    }

}
