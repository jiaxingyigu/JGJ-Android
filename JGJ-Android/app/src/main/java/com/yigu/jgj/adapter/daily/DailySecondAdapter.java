package com.yigu.jgj.adapter.daily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/22.
 */
public class DailySecondAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final static int daily_head = 0;
    private final static int daily_project = 1;
    private final static int daily_sale = 2;
    private LayoutInflater mLayoutInflater;
    public DailySecondAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return daily_head;
            case 1:
                return daily_project;
            case 2:
                return daily_sale;
            default:
                return daily_head;
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{
        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder{
        public ProjectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class SaleViewHolder extends RecyclerView.ViewHolder{
        public SaleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
