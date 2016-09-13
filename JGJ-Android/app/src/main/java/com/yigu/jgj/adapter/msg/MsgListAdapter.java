package com.yigu.jgj.adapter.msg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.api.BasicApi;
import com.yigu.jgj.commom.result.MapiMsgResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/8/3.
 */
public class MsgListAdapter extends RecyclerView.Adapter<MsgListAdapter.ViewHolder> {

    private LayoutInflater inflater;
    List<MapiMsgResult> mList;

    public MsgListAdapter(Context context, List<MapiMsgResult> list) {
        inflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_msg_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MapiMsgResult msgResult = mList.get(position);
        holder.name.setText("发送人："+msgResult.getName());
        holder.stime.setText(msgResult.getStime());
        holder.remark.setText(msgResult.getRemark());
        holder.url.setText(TextUtils.isEmpty(msgResult.getUrl())?"":"附件下载："+ BasicApi.BASIC_URL+"/"+msgResult.getUrl());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.stime)
        TextView stime;
        @Bind(R.id.remark)
        TextView remark;
        @Bind(R.id.url)
        TextView url;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}