package com.yigu.jgj.adapter.task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiTaskResult;
import com.yigu.jgj.commom.util.DateUtil;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/26.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    List<MapiTaskResult> mList;
    private RecyOnItemClickListener onItemClickListener;
    public TaskAdapter(Context context, List<MapiTaskResult> list) {
        mLayoutInflater = LayoutInflater.from(context);
        mList = list;
    }

    public void setOnItemClickListener(RecyOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.task_item.setTag(position);
        holder.task_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onItemClickListener)
                    onItemClickListener.onItemClick(view, (Integer) view.getTag());
            }
        });
        MapiTaskResult taskResult = mList.get(position);
        holder.taskstime.setText(DateUtil.getInstance().YMDHMS2YMD(taskResult.getTaskstime()));
        holder.senduser.setText("指派人："+taskResult.getSenduser());
        holder.shopname.setText("检查店名："+taskResult.getShopname());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.task_item)
        RelativeLayout task_item;
        @Bind(R.id.taskstime)
        TextView taskstime;
        @Bind(R.id.senduser)
        TextView senduser;
        @Bind(R.id.shopname)
        TextView shopname;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
