package com.yigu.jgj.adapter.task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiOtherResult;
import com.yigu.jgj.commom.result.MapiTaskResult;
import com.yigu.jgj.commom.util.DateUtil;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/8/23.
 */
public class OtherTaskAdapter extends RecyclerView.Adapter<OtherTaskAdapter.ViewHolder>{
    private LayoutInflater inflater;
    List<MapiOtherResult> mList;
    private RecyOnItemClickListener recyOnItemClickListener;

    public OtherTaskAdapter(Context context, List<MapiOtherResult> list) {
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
        return new ViewHolder(inflater.inflate(R.layout.item_task_other, parent, false));
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
        MapiOtherResult otherResult = mList.get(position);
        String dateStr = DateUtil.getInstance().YMDHMS2YMD(otherResult.getTimes());
        holder.user.setText("指派人："+otherResult.getName());
        holder.date_tv.setText(dateStr);
        holder.content_tv.setText(TextUtils.isEmpty(otherResult.getRemark())?"":otherResult.getRemark());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_root)
        RelativeLayout item_root;
        @Bind(R.id.user)
        TextView user;
        @Bind(R.id.content_tv)
        TextView content_tv;
        @Bind(R.id.date_tv)
        TextView date_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
