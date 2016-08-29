package com.yigu.jgj.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yigu.jgj.R;
import com.yigu.jgj.base.Config;
import com.yigu.jgj.commom.api.BasicApi;
import com.yigu.jgj.commom.result.MapiImageResult;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/7/26.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{
    private LayoutInflater inflater;
    List<MapiImageResult> mList;
    private RecyOnItemClickListener onItemClickListener;

    public void setOnItemClickListener(RecyOnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public ImageAdapter(Context context, List<MapiImageResult> list) {
        inflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public int getItemCount() {
        return mList==null?1:mList.size()+1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_daily_image,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(getItemCount()>= Config.MAX_VALUE+1&&position==getItemCount()-1){
            holder.simpleDraweeView.setVisibility(View.GONE);
        }else{
            holder.simpleDraweeView.setVisibility(View.VISIBLE);
            if(position==getItemCount()-1){
                holder.simpleDraweeView.setImageResource(R.mipmap.add);//ImageURI(Uri.parse("android.resource://com.yigu.jgj/mipmap/add.png"));
            }else{
                holder.simpleDraweeView.setImageURI(Uri.parse(BasicApi.BASIC_IMAGE+mList.get(position).getPATH()));
            }
        }

        holder.simpleDraweeView.setTag(position);
        holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=onItemClickListener)
                    onItemClickListener.onItemClick(view,(Integer)view.getTag());
            }
        });

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.image)
        SimpleDraweeView simpleDraweeView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
