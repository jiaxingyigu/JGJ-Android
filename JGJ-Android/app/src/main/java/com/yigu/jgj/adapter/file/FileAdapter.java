package com.yigu.jgj.adapter.file;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiPartyResult;
import com.yigu.jgj.commom.result.MapiSepcialResult;
import com.yigu.jgj.commom.result.MapiTaskResult;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.StringUtil;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/22.
 */
public class FileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater inflater;
    List<MapiTaskResult> mList;
    private RecyOnItemClickListener onItemClickListener;

    public void setOnItemClickListener(RecyOnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public FileAdapter(Context context, List<MapiTaskResult> list) {
        inflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new OneViewHolder(inflater.inflate(R.layout.item_daily,parent,false));
            case 2:
                return new TwoViewHolder(inflater.inflate(R.layout.item_company, parent, false));
            case 3:
                return new ThreeViewHolder(inflater.inflate(R.layout.item_special_list, parent, false));
            case 4:
                return new FourViewHolder(inflater.inflate(R.layout.item_party_list, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof OneViewHolder) {
            setOneData((OneViewHolder) holder,position);
        }else if(holder instanceof TwoViewHolder){
            setTwoData((TwoViewHolder) holder,position);
        }else if(holder instanceof ThreeViewHolder){
            setThreeData((ThreeViewHolder) holder,position);
        }else if(holder instanceof FourViewHolder){
            setFourData((FourViewHolder) holder,position);
        }
    }



    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    private void setOneData(OneViewHolder holder,int position){
        holder.item_root.setTag(position);
        holder.item_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=onItemClickListener)
                    onItemClickListener.onItemClick(view,(Integer)view.getTag());
            }
        });

        MapiTaskResult itemResult = mList.get(position);
        holder.name.setText(itemResult.getShopname());
        holder.lperson.setText(itemResult.getLPERSON());
        holder.tel.setText(itemResult.getTel());
        holder.address.setText(itemResult.getAddress());
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

    private void setThreeData(ThreeViewHolder holder,int position){
        MapiTaskResult result = mList.get(position);
        holder.item_root.setTag(position);
        holder.item_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=onItemClickListener)
                    onItemClickListener.onItemClick(view,(Integer)view.getTag());
            }
        });
        holder.nameCircle.setText(StringUtil.nameFormat(result.getName()+""));
        if(TextUtils.isEmpty(result.getCOMMUNITY())){
            holder.tag_addr.setVisibility(View.GONE);
        }else{
            holder.tag_addr.setText(result.getCOMMUNITY());
        }

        holder.date_tv.setText(result.getDate());
        holder.nameTv.setText(result.getName()+"");

    }

    private void setFourData(FourViewHolder holder,int position){
        MapiTaskResult result = mList.get(position);
        holder.item_root.setTag(position);
        holder.item_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=onItemClickListener)
                    onItemClickListener.onItemClick(view,(Integer)view.getTag());
            }
        });
        if(TextUtils.isEmpty(result.getCOMMUNITY())){
            holder.tag_addr.setVisibility(View.GONE);
        }else{
            holder.tag_addr.setText(result.getCOMMUNITY());
        }

        holder.date_tv.setText(result.getStardate());
        holder.nameTv.setText(result.getName()+"");
    }

    private void setTwoData(TwoViewHolder holder,int position){
        holder.item_daily.setTag(position);
        holder.item_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onItemClickListener)
                    onItemClickListener.onItemClick(view, (Integer) view.getTag());
            }
        });
        MapiTaskResult itemResult = mList.get(position);
        DebugLog.i(itemResult.getName());
        holder.name.setText(itemResult.getName());

        holder.lperson.setText(itemResult.getLPERSON());
        holder.tel.setText(itemResult.getTel());
        holder.address.setText(itemResult.getAddress());
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


    class OneViewHolder extends RecyclerView.ViewHolder{
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
        public OneViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class TwoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_daily)
        RelativeLayout item_daily;
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

        public TwoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ThreeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_root)
        RelativeLayout item_root;
        @Bind(R.id.tag_addr)
        TextView tag_addr;
        @Bind(R.id.date_tv)
        TextView date_tv;
        @Bind(R.id.name_circle)
        TextView nameCircle;
        @Bind(R.id.name_tv)
        TextView nameTv;
        public ThreeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class FourViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_root)
        RelativeLayout item_root;
        @Bind(R.id.tag_addr)
        TextView tag_addr;
        @Bind(R.id.date_tv)
        TextView date_tv;
        @Bind(R.id.name_tv)
        TextView nameTv;
        public FourViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



}
