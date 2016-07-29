package com.yigu.jgj.adapter.task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yigu.jgj.R;
import com.yigu.jgj.view.DailyHeadLayout;
import com.yigu.jgj.view.DailyProjectLayout;
import com.yigu.jgj.view.DailySaleLayout;
import com.yigu.jgj.view.TaskDeelLayout;
import com.yigu.jgj.view.TaskHeadLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/22.
 */
public class TaskDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final static int daily_head = 0;
    private final static int daily_project = 1;
    private final static int daily_sale = 2;
    private final static int daily_deel = 3;
    private LayoutInflater mLayoutInflater;
    public TaskDetailAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case daily_head:
                return new HeadViewHolder(mLayoutInflater.inflate(R.layout.lay_task_head, parent, false));
            case daily_project:
                return new ProjectViewHolder(mLayoutInflater.inflate(R.layout.lay_daily_project, parent, false));
            case daily_sale:
                return new SaleViewHolder(mLayoutInflater.inflate(R.layout.lay_daily_sale, parent, false));
            case daily_deel:
                return new DeelViewHolder(mLayoutInflater.inflate(R.layout.lay_task_deel, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadViewHolder) {
            ((HeadViewHolder)holder).taskHeadLayout.loadData();
        }else if(holder instanceof ProjectViewHolder){
            ((ProjectViewHolder)holder).dailyProjectLayout.loadData(null,false);
        }else if(holder instanceof SaleViewHolder){
            ((SaleViewHolder)holder).dailySaleLayout.loadData(null,false);
        }else if(holder instanceof DeelViewHolder){
            ((DeelViewHolder)holder).taskDeelLayout.loadData();
        }
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
            case 3:
                return daily_deel;
            default:
                return daily_head;
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.taskHeadLayout)
        TaskHeadLayout taskHeadLayout;
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
    class DeelViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.taskDeelLayout)
        TaskDeelLayout taskDeelLayout;
        public DeelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
