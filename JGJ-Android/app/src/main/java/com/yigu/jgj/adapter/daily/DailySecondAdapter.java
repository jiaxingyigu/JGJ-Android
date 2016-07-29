package com.yigu.jgj.adapter.daily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yigu.jgj.R;
import com.yigu.jgj.base.Config;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.view.DailyHeadLayout;
import com.yigu.jgj.view.DailyProjectLayout;
import com.yigu.jgj.view.DailySaleLayout;
import com.yigu.jgj.view.DailyServiceLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/22.
 */
public class DailySecondAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater mLayoutInflater;
    MapiItemResult itemResult;
    List<Integer> list;
    public DailySecondAdapter(Context context,List<Integer> list) {
        mLayoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    public void setItemResult(MapiItemResult itemResult){
        this.itemResult = itemResult;
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Config.daily_head:
                return new HeadViewHolder(mLayoutInflater.inflate(R.layout.lay_daily_head, parent, false));
            case Config.daily_project:
                return new ProjectViewHolder(mLayoutInflater.inflate(R.layout.lay_daily_project, parent, false));
            case Config.daily_sale:
                return new SaleViewHolder(mLayoutInflater.inflate(R.layout.lay_daily_sale, parent, false));
            case Config.daily_service_canteen:
                return new ServiceViewHolder(mLayoutInflater.inflate(R.layout.lay_daily_service, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadViewHolder) {
            ((HeadViewHolder)holder).dailyHeadLayout.loadData(itemResult);
        }else if(holder instanceof ProjectViewHolder){
            ((ProjectViewHolder)holder).dailyProjectLayout.loadData(itemResult,true);
        }else if(holder instanceof SaleViewHolder){
            ((SaleViewHolder)holder).dailySaleLayout.loadData(itemResult,true);
        }else if(holder instanceof ServiceViewHolder){
            ((ServiceViewHolder)holder).dailyServiceLayout.loadData(itemResult,true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position);
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.dailyHeadLayout)
        DailyHeadLayout dailyHeadLayout;
        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.dailyProjectLayout)
        DailyProjectLayout dailyProjectLayout;
        public ProjectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class SaleViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.dailySaleLayout)
        DailySaleLayout dailySaleLayout;
        public SaleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.dailyServiceLayout)
        DailyServiceLayout dailyServiceLayout;
        public ServiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
