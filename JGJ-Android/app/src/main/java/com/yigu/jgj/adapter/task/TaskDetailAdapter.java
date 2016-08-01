package com.yigu.jgj.adapter.task;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yigu.jgj.R;
import com.yigu.jgj.activity.task.TaskDetailActivity;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.base.Config;
import com.yigu.jgj.commom.api.DailyApi;
import com.yigu.jgj.commom.api.ItemApi;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.util.ControllerUtil;
import com.yigu.jgj.view.DailyHeadLayout;
import com.yigu.jgj.view.DailyImageLayout;
import com.yigu.jgj.view.DailyProjectLayout;
import com.yigu.jgj.view.DailyRemarkLayout;
import com.yigu.jgj.view.DailySaleLayout;
import com.yigu.jgj.view.DailyServiceLayout;
import com.yigu.jgj.view.TaskDeelLayout;
import com.yigu.jgj.view.TaskHeadLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/22.
 */
public class TaskDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private LayoutInflater mLayoutInflater;
    MapiItemResult itemResult;
    List<Integer> list;
    String ID = "";
    TaskDetailActivity context;
    public TaskDetailAdapter(Context context,List<Integer> list) {
        mLayoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = (TaskDetailActivity)context;
    }

    public void setItemResult(MapiItemResult itemResult){
        this.itemResult = itemResult;
    }

    public void setItemID(String ID){
        this.ID = ID;
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Config.daily_head:
                return new HeadViewHolder(mLayoutInflater.inflate(R.layout.lay_task_head, parent, false));
            case Config.daily_project:
                return new ProjectViewHolder(mLayoutInflater.inflate(R.layout.lay_daily_project, parent, false));
            case Config.daily_sale:
                return new SaleViewHolder(mLayoutInflater.inflate(R.layout.lay_daily_sale, parent, false));
            case Config.daily_service_canteen:
                return new ServiceViewHolder(mLayoutInflater.inflate(R.layout.lay_daily_service, parent, false));
            case Config.daily_remark:
                return new RemarkViewHolder(mLayoutInflater.inflate(R.layout.lay_daily_remark, parent, false));
            case Config.daily_image:
                return new ImageViewHolder(mLayoutInflater.inflate(R.layout.lay_daily_image, parent, false));
            case Config.daily_deel:
                return new DeelViewHolder(mLayoutInflater.inflate(R.layout.lay_task_deel, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadViewHolder) {
            ((HeadViewHolder)holder).taskHeadLayout.loadData(itemResult);
        }else if(holder instanceof ProjectViewHolder){
            ((ProjectViewHolder)holder).dailyProjectLayout.loadData(itemResult,false);
        }else if(holder instanceof SaleViewHolder){
            ((SaleViewHolder)holder).dailySaleLayout.loadData(itemResult,false);
        }else if(holder instanceof ServiceViewHolder){
            ((ServiceViewHolder)holder).dailyServiceLayout.loadData(itemResult,false);
        }else if(holder instanceof RemarkViewHolder){
            ((RemarkViewHolder)holder).dailyRemarkLayout.loadData(itemResult);
        }else if(holder instanceof ImageViewHolder){
            ((ImageViewHolder)holder).dailyImageLayout.loadData(itemResult);
        } else if(holder instanceof DeelViewHolder){
            ((DeelViewHolder)holder).taskDeelLayout.loadData();
            ((DeelViewHolder)holder).taskDeelLayout.setOnDeelClickListener(new TaskDeelLayout.OnDeelClickListener() {
                @Override
                public void change() {
                    if(!TextUtils.isEmpty(ID)){
                        context.showLoading();
                        DailyApi.taskcomplete(context, "4", ID, new RequestCallback() {
                            @Override
                            public void success(Object success) {
                               context.hideLoading();
                                MainToast.showShortToast("完成");
                                context.setResult(Activity.RESULT_OK);
                                context.finish();
                            }
                        }, new RequestExceptionCallback() {
                            @Override
                            public void error(String code, String message) {
                                context.hideLoading();
                            }
                        });

                    }
                }

                @Override
                public void investigation() {
                    context.showLoading();
                    if(!TextUtils.isEmpty(ID)){
                        context.showLoading();
                        DailyApi.taskcomplete(context, "3", ID, new RequestCallback() {
                            @Override
                            public void success(Object success) {
                               context.hideLoading();
                                MainToast.showShortToast("完成");
                                context.setResult(Activity.RESULT_OK);
                                context.finish();
                            }
                        }, new RequestExceptionCallback() {
                            @Override
                            public void error(String code, String message) {
                                context.hideLoading();
                            }
                        });

                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position);
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.taskHeadLayout)
        TaskHeadLayout taskHeadLayout;
        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.dailyProjectLayout)
        DailyProjectLayout dailyProjectLayout;
        public ProjectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class SaleViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.dailySaleLayout)
        DailySaleLayout dailySaleLayout;
        public SaleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.dailyServiceLayout)
        DailyServiceLayout dailyServiceLayout;
        public ServiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class RemarkViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.dailyRemarkLayout)
        DailyRemarkLayout dailyRemarkLayout;
        public RemarkViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.dailyImageLayout)
        DailyImageLayout dailyImageLayout;
        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class DeelViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.taskDeelLayout)
        TaskDeelLayout taskDeelLayout;
        public DeelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
