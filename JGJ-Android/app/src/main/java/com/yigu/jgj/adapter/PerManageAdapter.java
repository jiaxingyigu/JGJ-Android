package com.yigu.jgj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yigu.jgj.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/26.
 */
public class PerManageAdapter extends RecyclerView.Adapter<PerManageAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private int requestCode = 0;
    public PerManageAdapter(Context context,int requestCode) {
        inflater = LayoutInflater.from(context);
        this.requestCode = requestCode;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_per_manager,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(requestCode>0){
            holder.status.setVisibility(View.VISIBLE);
        }else{

        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.status)
        ImageView status;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
