package com.yigu.jgj.widget.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.yigu.jgj.R;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by brain on 2016/7/19.
 */
public class FilterDatePop extends PopupWindow implements PopupWindow.OnDismissListener {
    @Bind(R.id.startTime)
    TextView startTime;
    @Bind(R.id.endTime)
    TextView endTime;
    private LayoutInflater inflater = null;
    private View contentView = null;
    private Context mContext;
    TimePickerView pvTime;

    public FilterDatePop(Activity context) {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        initTopLayout();
    }

    private void initTopLayout() {
        contentView = inflater.inflate(R.layout.pop_filter_date, null);
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
            }
        });
    }

    @Override
    public void onDismiss() {

    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {

        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 0);
        } else {
            this.dismiss();
        }
    }

    @OnClick({R.id.startTime, R.id.endTime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startTime:
                pvTime.show();
                break;
            case R.id.endTime:
                pvTime.show();
                break;
        }
    }
}
