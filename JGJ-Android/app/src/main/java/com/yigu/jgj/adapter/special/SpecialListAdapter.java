package com.yigu.jgj.adapter.special;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiSepcialResult;
import com.yigu.jgj.commom.result.MapiUserResult;
import com.yigu.jgj.commom.util.StringUtil;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/8/23.
 */
public class SpecialListAdapter extends RecyclerView.Adapter<SpecialListAdapter.ViewHolder> {
    private LayoutInflater inflater;
    List<MapiSepcialResult> mList;
    private RecyOnItemClickListener recyOnItemClickListener;

    public SpecialListAdapter(Context context, List<MapiSepcialResult> list) {
        inflater = LayoutInflater.from(context);
        mList = list;
    }

    public void setOnItemClickListener(RecyOnItemClickListener recyOnItemClickListener) {
        this.recyOnItemClickListener = recyOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_special_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MapiSepcialResult result = mList.get(position);
        holder.item_root.setTag(position);
        holder.item_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != recyOnItemClickListener)
                    recyOnItemClickListener.onItemClick(view, (Integer) view.getTag());
            }
        });
        holder.nameCircle.setText(StringUtil.nameFormat(result.getName()+""));
        holder.tag_addr.setText(result.getCOMMUNITY());
        holder.date_tv.setText(result.getDate());
        holder.nameTv.setText(result.getName()+"");
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
