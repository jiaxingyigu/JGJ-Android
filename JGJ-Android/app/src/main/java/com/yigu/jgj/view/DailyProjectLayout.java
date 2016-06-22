package com.yigu.jgj.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.daily.DailyItemAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/23.
 */
public class DailyProjectLayout extends RelativeLayout {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private Context mContext;
    private View view;
    private DailyItemAdapter mAdapter;
    public DailyProjectLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public DailyProjectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public DailyProjectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_daily_project, this);
        ButterKnife.bind(this, view);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);
        mAdapter = new DailyItemAdapter(mContext);
        recyclerView.setAdapter(mAdapter);
    }

    public void loadData() {

    }
}
