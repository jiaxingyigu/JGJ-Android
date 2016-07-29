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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/7/24.
 */
public class SelCommunityAdapter extends RecyclerView.Adapter<SelCommunityAdapter.ViewHolder> {
    private LayoutInflater inflater;
    List<MapiResourceResult> mList;

    private RecyOnItemClickListener onItemClickListener;
    private int selPos = -1;

    public void setOnItemClickListener(RecyOnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void setPos(int pos){
        selPos = pos;
    }

    public SelCommunityAdapter(Context context, List<MapiResourceResult> list) {
        inflater = LayoutInflater.from(context);
        this.mList = list;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_sel_community, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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
        MapiResourceResult mapiResourceResult = mList.get(position);
        holder.name.setText(mapiResourceResult.getNAME());
        if(mapiResourceResult.isCheck())
            holder.image.setImageResource(R.mipmap.circle_green_right);
        else
            holder.image.setImageResource(R.mipmap.circle_white);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.item_root)
        RelativeLayout itemRoot;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
