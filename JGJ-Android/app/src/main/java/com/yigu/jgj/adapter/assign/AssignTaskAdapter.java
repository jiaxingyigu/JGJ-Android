package com.yigu.jgj.adapter.assign;

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
 * Created by brain on 2016/7/3.
 */
public class AssignTaskAdapter extends RecyclerView.Adapter<AssignTaskAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private RecyOnItemClickListener recyOnItemClickListener;
    public AssignTaskAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setRecyOnItemClickListener(RecyOnItemClickListener recyOnItemClickListener){

        this.recyOnItemClickListener = recyOnItemClickListener;

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_assign_task,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item_root.setTag(position);
        holder.item_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=recyOnItemClickListener)
                    recyOnItemClickListener.onItemClick(view,(Integer)view.getTag());
            }
        });
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        @Bind(R.id.item_root)
        RelativeLayout item_root;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
