package com.yigu.jgj.adapter.license;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiTaskResult;
import com.yigu.jgj.commom.util.DateUtil;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/7/3.
 */
public class LicenseTaskAdapter extends RecyclerView.Adapter<LicenseTaskAdapter.ViewHolder> {
    private LayoutInflater inflater;
    List<MapiItemResult> mList;
    private RecyOnItemClickListener recyOnItemClickListener;

    public LicenseTaskAdapter(Context context, List<MapiItemResult> list) {
        inflater = LayoutInflater.from(context);
        mList = list;
    }

    public void setOnItemClickListener(RecyOnItemClickListener recyOnItemClickListener) {
        this.recyOnItemClickListener = recyOnItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_assign_task, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item_root.setTag(position);
        holder.item_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != recyOnItemClickListener)
                    recyOnItemClickListener.onItemClick(view, (Integer) view.getTag());
            }
        });
        MapiItemResult taskResult = mList.get(position);
        if(TextUtils.isEmpty(taskResult.getCOMMUNITY())){
            holder.community.setVisibility(View.GONE);
        }else{
            holder.community.setText(taskResult.getCOMMUNITY());
        }
        String dateStr = DateUtil.getInstance().YMDHMS2YMD(taskResult.getTIMES());
        holder.user.setText("上报人："+ (TextUtils.isEmpty(taskResult.getUser())?"":taskResult.getUser())+"    "+dateStr);
        holder.shopname.setText(taskResult.getNAME());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_root)
        RelativeLayout item_root;
        @Bind(R.id.community)
        TextView community;
        @Bind(R.id.user)
        TextView user;
        @Bind(R.id.shopname)
        TextView shopname;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}