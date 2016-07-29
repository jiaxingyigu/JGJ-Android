package com.yigu.jgj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiUserResult;
import com.yigu.jgj.commom.util.StringUtil;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/26.
 */
public class PerManageAdapter extends RecyclerView.Adapter<PerManageAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private int requestCode = 0;
    private List<MapiUserResult> mList = new ArrayList<>();
    private RecyOnItemClickListener onItemClickListener;
    private int selPos = -1;
    public void setOnItemClickListener(RecyOnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public PerManageAdapter(Context context,int requestCode,List<MapiUserResult> mList) {
        inflater = LayoutInflater.from(context);
        this.requestCode = requestCode;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_per_manager,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MapiUserResult result = mList.get(position);
        holder.name_tv.setText(result.getNAME());
        holder.tel_tv.setText(result.getPHONE());
        if(TextUtils.isEmpty(result.getCOMMUNITY()))
            holder.tag_addr.setVisibility(View.GONE);
        else{
            holder.tag_addr.setVisibility(View.VISIBLE);
            holder.tag_addr.setText(result.getCOMMUNITY());
        }
        holder.tag_organize.setText(result.getCOMPANY());
        holder.name_circle.setText(StringUtil.nameFormat(result.getNAME()));

        if(requestCode>0){
            holder.status.setVisibility(View.VISIBLE);
            holder.itemRoot.setTag(position);
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (Integer)view.getTag();
                    if(selPos!=position){
                        if(selPos>=0) {
                            mList.get(selPos).setCheck(false);
                            notifyItemChanged(selPos);
                        }
                        selPos = position;
                        mList.get(selPos).setCheck(true);
                        notifyItemChanged(selPos);
                        if(null!=onItemClickListener)
                            onItemClickListener.onItemClick(view,selPos);
                    }

                }
            });
            if(result.isCheck())
                holder.status.setImageResource(R.mipmap.circle_green_right);
            else
                holder.status.setImageResource(R.mipmap.circle_white);
        }else{

        }

    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.item_root)
        RelativeLayout itemRoot;
        @Bind(R.id.status)
        ImageView status;
        @Bind(R.id.tag_addr)
        TextView tag_addr;
        @Bind(R.id.tag_organize)
        TextView tag_organize;
        @Bind(R.id.tel_tv)
        TextView tel_tv;
        @Bind(R.id.name_tv)
        TextView name_tv;
        @Bind(R.id.name_circle)
        TextView name_circle;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
