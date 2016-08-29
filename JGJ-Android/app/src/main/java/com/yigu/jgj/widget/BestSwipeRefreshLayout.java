package com.yigu.jgj.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.yigu.jgj.R;


/**
 * Created by brain on 2016/6/23.
 */
public class BestSwipeRefreshLayout extends SwipeRefreshLayout{
    private BestRefreshListener bestRefreshListener;

    public BestSwipeRefreshLayout(Context context) {
        super(context);
        initView();
        initListener();
    }

    public BestSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initListener();
    }

    public void setBestRefreshListener(BestRefreshListener bestRefreshListener) {
        this.bestRefreshListener = bestRefreshListener;
    }

    private void initView() {
        setColorSchemeResources(R.color.jgj_blue);
        setProgressBackgroundColorSchemeResource(R.color.jgj_black);
        setSize(SwipeRefreshLayout.LARGE);
    }

    public void initListener() {
        this.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                bestRefreshListener.onBestRefresh();
            }
        });
    }

    public interface BestRefreshListener {
        void onBestRefresh();
    }

}
