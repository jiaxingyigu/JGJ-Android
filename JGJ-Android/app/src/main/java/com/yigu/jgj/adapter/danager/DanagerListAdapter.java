package com.yigu.jgj.adapter.danager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yigu.jgj.R;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;

import butterknife.ButterKnife;

/**
 * Created by brain on 2016/7/11.
 */
public class DanagerListAdapter extends RecyclerView.Adapter<DanagerListAdapter.ViewHolder>{

    private LayoutInflater inflater;

    private RecyOnItemClickListener onItemClickListener;

    public void setOnItemClickListener(RecyOnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public DanagerListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_danger_list,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
