package com.yigu.jgj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/20.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private List<Integer> mList;
    private LayoutInflater inflater;

    private RecyOnItemClickListener onItemClickListener;

    public void setOnItemClickListener(RecyOnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public MainAdapter(Context context,  List<Integer> list) {
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
        holder.image.setImageResource(mList.get(position));
        holder.image.setTag(position);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null!=onItemClickListener)
                    onItemClickListener.onItemClick(view,(Integer)view.getTag());
            }
        });
        String text = "";
        switch (position){
            case 0:
                text = "日常巡查";
                break;
            case 1:
                text = "企业管理";
                break;
            case 2:
                text = "我的任务";
                break;
            case 3:
                text = "隐患档案";
                break;
            case 4:
                text = "人员管理";
                break;
            case 5:
                text = "归档信息";
                break;
        }
        holder.title.setText(text);

    }
   public class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.title)
        TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }



}
