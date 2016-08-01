package com.yigu.jgj.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.yigu.jgj.R;
import com.yigu.jgj.adapter.ShowImageAdapter;
import com.yigu.jgj.commom.result.MapiImageResult;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.util.DPUtil;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.widget.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/7/30.
 */
public class DailyImageLayout extends RelativeLayout {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private Context mContext;
    private View view;
    List<MapiImageResult> mList;
    ShowImageAdapter mAdapter;
    public DailyImageLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public DailyImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public DailyImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_daily_image, this);
        ButterKnife.bind(this, view);
        mList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext, DPUtil.dip2px(8), Color.parseColor("#ffffff")));//分割线
        mAdapter = new ShowImageAdapter(mContext,mList);
        recyclerView.setAdapter(mAdapter);
        initListener();
    }

    public void loadData(MapiItemResult itemResult) {
        if(null!=itemResult){
            if(!itemResult.getImages().isEmpty()){
                mList.clear();
                mList.addAll(itemResult.getImages());
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    private void initListener(){
        mAdapter.setOnItemClickListener(new RecyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

}
