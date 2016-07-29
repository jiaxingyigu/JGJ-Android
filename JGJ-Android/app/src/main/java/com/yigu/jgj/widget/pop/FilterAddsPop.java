package com.yigu.jgj.widget.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.jgjinterface.RecyOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/7/19.
 */
public class FilterAddsPop extends PopupWindow implements PopupWindow.OnDismissListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private LayoutInflater inflater = null;
    private View contentView = null;
    private Context mContext;
    List<MapiResourceResult> list = new ArrayList<>();
    private int pos = -1;
    private PopAdapter mAdapter;
    public FilterAddsPop(Activity context, List<MapiResourceResult> list) {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        this.list = list;
        initTopLayout();
    }

    private void initTopLayout() {
        contentView = inflater.inflate(R.layout.pop_filter_adds, null);
        this.setContentView(contentView);
        ButterKnife.bind(this, contentView);
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 设置允许在外点击消失
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // 设置popupwindow显示和隐藏时的动画
        this.setAnimationStyle(R.style.PopupWindowAnimation_alpha);
        setOnDismissListener(this);
        initView();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new PopAdapter(mContext);
        recyclerView.setAdapter(mAdapter);
    }

    private RecyOnItemClickListener mOnPopItemClickListener;

    /**
     * 设置item的点击监听事件
     */
    public void setOnPopItemClickListener(RecyOnItemClickListener l) {
        mOnPopItemClickListener = l;
    }

    class PopAdapter extends RecyclerView.Adapter<PopAdapter.ViewHolder>{
        private LayoutInflater inflater;
        public PopAdapter(Context context) {
            inflater = inflater.from(context);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflater.inflate(R.layout.item_pop_filter_adds,parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.adds.setText(list.get(position).getNAME());
            holder.item_root.setTag(position);
            holder.item_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(pos!=(Integer)view.getTag()){
                        pos = (Integer)view.getTag();
                        if (null != mOnPopItemClickListener)
                            mOnPopItemClickListener.onItemClick(view, (Integer)view.getTag());
                        notifyDataSetChanged();
                        dismiss();
                    }
                }
            });
            if(pos==position){
                holder.adds.setTextColor(Color.parseColor("#019b79"));
                holder.iv_status.setImageResource(R.mipmap.circle_green_right);
            }else{
                holder.adds.setTextColor(Color.parseColor("#8c8c8c"));
                holder.iv_status.setImageResource(R.mipmap.circle_white);
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            @Bind(R.id.adds)
            TextView adds;
            @Bind(R.id.iv_status)
            ImageView iv_status;
            @Bind(R.id.item_root)
            RelativeLayout item_root;
            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }

    }


    @Override
    public void onDismiss() {
        if (null != mOnPopItemClickListener)
            mOnPopItemClickListener.onItemClick(null,0);
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {

        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent,parent.getLayoutParams().width / 2, 0);
        } else {
            this.dismiss();
        }
    }



}
