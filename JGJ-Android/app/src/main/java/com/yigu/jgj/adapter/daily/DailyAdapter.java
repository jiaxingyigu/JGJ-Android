package com.yigu.jgj.adapter.daily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/22.
 */
public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder>{
    private LayoutInflater inflater;
    List<MapiItemResult> mList;
    private RecyOnItemClickListener onItemClickListener;

    public void setOnItemClickListener(RecyOnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public DailyAdapter(Context context, List<MapiItemResult> list) {
        inflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public DailyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_daily,parent,false));
    }

    @Override
    public void onBindViewHolder(DailyAdapter.ViewHolder holder, int position) {
        holder.item_root.setTag(position);
        holder.item_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=onItemClickListener)
                    onItemClickListener.onItemClick(view,(Integer)view.getTag());
            }
        });

        MapiItemResult itemResult = mList.get(position);
        holder.name.setText(itemResult.getNAME());
        holder.lperson.setText(itemResult.getLPERSON());
        holder.tel.setText(itemResult.getTEL());
        holder.address.setText(itemResult.getADDRESS());
        if(itemResult.getFOODSALES()==1){
            holder.image.setImageResource(R.mipmap.sales);
            return;
        }

        if(itemResult.getFOODSERVICE()==1){
            holder.image.setImageResource(R.mipmap.service);
            return;
        }

        if(itemResult.getCANTEEN()==1){
            holder.image.setImageResource(R.mipmap.canteen);
            return;
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.item_root)
        RelativeLayout item_root;
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.lperson)
        TextView lperson;
        @Bind(R.id.tel)
        TextView tel;
        @Bind(R.id.address)
        TextView address;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
