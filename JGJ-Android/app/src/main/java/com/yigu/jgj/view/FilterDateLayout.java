package com.yigu.jgj.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.yigu.jgj.R;
import com.yigu.jgj.commom.widget.MainToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by brain on 2016/7/20.
 */
public class FilterDateLayout extends RelativeLayout {
    @Bind(R.id.startTime)
    TextView startTime;
    @Bind(R.id.endTime)
    TextView endTime;
    private Context mContext;
    private View view;

    TimePickerView pvTime;
    List<TextView> views = new ArrayList<>();
    private Date startDate;
    private Date endDate;

    public FilterDateLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public FilterDateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public FilterDateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_filter_date, this);
        ButterKnife.bind(this, view);
        //时间选择器
        pvTime = new TimePickerView(mContext, TimePickerView.Type.YEAR_MONTH);
        //控制时间范围
//        Calendar calendar = Calendar.getInstance();
//        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果哦
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                if(position==0) {
                    startDate = date;
                    startTime.setText("开始时间               " + getTime(date));
                }
                else if(position==1) {
                    endDate = date;
                    endTime.setText("结束时间               " + getTime(date));
                }
            }
        });
        views.add(startTime);
        views.add(endTime);
    }

    public void loadData() {

    }

    int position = 0;

    @OnClick({R.id.startTime, R.id.endTime,R.id.confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startTime:
                position = 0;
                updateStatus(position);
                pvTime.show();
                break;
            case R.id.endTime:
                position = 1;
                updateStatus(position);
                pvTime.show();
                break;
            case R.id.confirm:
                if(null==startDate) {
                    MainToast.showShortToast("请选择开始时间");
                    return;
                }
                if(null==endDate) {
                    MainToast.showShortToast("请选择结束时间");
                    return;
                }
                if(startDate.compareTo(endDate)>0){
                    MainToast.showShortToast("开始时间不能大于结束时间");
                    return;
                }
                if(null!=onTiemInterface){
                    if(startDate.compareTo(endDate)==0){
                        onTiemInterface.onTimeSelect(getTime(startDate));
                    }else{
                        onTiemInterface.onTimeSelect(getTime(startDate)+"-"+getTime(endDate));
                    }

                }
                break;
        }
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM");
        return format.format(date);
    }

    private void updateStatus(int position){
        views.get(position).setTextColor(Color.parseColor("#019b79"));
        views.get(position).setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.circle_green_right,0);
        views.get(views.size()-1-position).setTextColor(Color.parseColor("#333333"));
        views.get(views.size()-1-position).setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.circle_white,0);
    }

    private OnTiemInterface onTiemInterface;

    public interface OnTiemInterface{
        void onTimeSelect(String date);
    }

    public void setOnTiemInterface(OnTiemInterface onTiemInterface){
        this.onTiemInterface = onTiemInterface;
    }

}
