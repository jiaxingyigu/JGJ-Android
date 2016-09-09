package com.yigu.jgj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;
import com.yigu.jgj.util.JGJDataSource;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/20.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private List<MapiResourceResult> mList;
    private LayoutInflater inflater;

    private RecyOnItemClickListener onItemClickListener;

    public void setOnItemClickListener(RecyOnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public MainAdapter(Context context,  List<MapiResourceResult> list) {
        this.mList = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mList.size()==0?0:mList.size();
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_main,parent,false));
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        int resId = R.mipmap.main_one;
        switch (mList.get(position).getId()){
            case JGJDataSource.TYPE_TEL:
                resId = R.mipmap.main_one;
                break;
            case JGJDataSource.TYPE_COMPANY:
                resId = R.mipmap.main_two;
                break;
            case JGJDataSource.TYPE_DAILY:
                resId = R.mipmap.main_three;
                break;
            case JGJDataSource.TYPE_LICENSE:
                resId = R.mipmap.main_four;
                break;
            case JGJDataSource.TYPE_PARTY:
                resId = R.mipmap.main_five;
                break;
            case JGJDataSource.TYPE_SPECIAL:
                resId = R.mipmap.main_six;
                break;
            case JGJDataSource.TYPE_ASSIGN:
                resId = R.mipmap.main_seven;
                break;
            case JGJDataSource.TYPE_TASK:
                resId = R.mipmap.main_eight;
                break;
            case JGJDataSource.TYPE_DANGER:
                resId = R.mipmap.main_nine;
                break;
            case JGJDataSource.TYPE_NOTIFY:
                resId = R.mipmap.main_ten;
                break;
            case JGJDataSource.TYPE_WARNING:
                resId = R.mipmap.main_ele;
                break;
            case JGJDataSource.TYPE_PERSON:
                resId = R.mipmap.main_twl;
                break;

        }
        holder.image.setImageResource(resId);
        holder.root_view.setTag(position);
        holder.root_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=onItemClickListener)
                    onItemClickListener.onItemClick(view,(Integer)view.getTag());
            }
        });

        holder.title.setText(mList.get(position).getNAME());

    }
   public class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.title)
        TextView title;
       @Bind(R.id.root_view)
       RelativeLayout root_view;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }



}
