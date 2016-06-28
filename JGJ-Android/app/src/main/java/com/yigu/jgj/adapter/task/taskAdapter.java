package com.yigu.jgj.adapter.task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yigu.jgj.R;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/26.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{
    private LayoutInflater mLayoutInflater;
    private RecyOnItemClickListener onItemClickListener;
    public TaskAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }
    public void setOnItemClickListener(RecyOnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_task,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.task_item.setTag(position);
        holder.task_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=onItemClickListener)
                    onItemClickListener.onItemClick(view,(Integer)view.getTag());
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.task_item)
        RelativeLayout task_item;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
