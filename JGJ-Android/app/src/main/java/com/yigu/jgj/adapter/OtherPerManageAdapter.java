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
import com.yigu.jgj.activity.person.OtherPersonActivity;
import com.yigu.jgj.commom.result.MapiUserResult;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.StringUtil;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/26.
 */
public class OtherPerManageAdapter extends RecyclerView.Adapter<OtherPerManageAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<MapiUserResult> mList = new ArrayList<>();
    private RecyOnItemClickListener onItemClickListener;
    private ArrayList<MapiUserResult> selList = new ArrayList<>();
    private OtherPersonActivity activity;
    public ArrayList<MapiUserResult> getSelList(){
        return selList;
    }
    public void setSelList(List<MapiUserResult> list){
        selList.clear();
        selList.addAll(list);
    }

    public void setOnItemClickListener(RecyOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OtherPerManageAdapter(Context context, List<MapiUserResult> mList) {
        inflater = LayoutInflater.from(context);
        this.mList = mList;
        activity = (OtherPersonActivity) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_per_manager, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final MapiUserResult result = mList.get(position);
        holder.name_tv.setText(result.getNAME());
        holder.tel_tv.setText(result.getPHONE());
        if (TextUtils.isEmpty(result.getCOMMUNITY()))
            holder.tag_addr.setVisibility(View.GONE);
        else {
            holder.tag_addr.setVisibility(View.VISIBLE);
            holder.tag_addr.setText(result.getCOMMUNITY());
        }

        if (TextUtils.isEmpty(result.getCOMPANY()))
            holder.tag_organize.setVisibility(View.GONE);
        else {
            holder.tag_organize.setVisibility(View.VISIBLE);
            holder.tag_organize.setText(result.getCOMPANY());
        }

        holder.name_circle.setText(StringUtil.nameFormat(result.getNAME()));

        holder.status.setVisibility(View.VISIBLE);
        holder.itemRoot.setTag(position);
        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                if(!mList.get(position).isCheck()){
                    mList.get(position).setCheck(true);
                }else{
                    mList.get(position).setCheck(false);
                }
                notifyItemChanged(position);
            }
        });
        if (result.isCheck()) {
            holder.status.setImageResource(R.mipmap.circle_blue_right);
            if(!selList.contains(result)){
                selList.add(result);
                DebugLog.i("selList.size()"+selList.size()+"mList.size()"+mList.size());
                if(selList.size()>0&&selList.size()==mList.size())
                    activity.setAll(true);
            }
        }else {
            holder.status.setImageResource(R.mipmap.circle_white);
            if (selList.contains(result)) {
                selList.remove(result);
                activity.setAll(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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
            ButterKnife.bind(this, itemView);
        }
    }
}
