package com.yigu.jgj.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiItemResult;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/7/30.
 */
public class DailyRemarkLayout extends RelativeLayout {
    @Bind(R.id.name_tv)
    TextView nameTv;
    @Bind(R.id.remark)
    TextView remark;
    private Context mContext;
    private View view;

    public DailyRemarkLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public DailyRemarkLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public DailyRemarkLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_daily_remark, this);
        ButterKnife.bind(this, view);
    }

    public void loadData(MapiItemResult itemResult) {
        remark.setText(TextUtils.isEmpty(itemResult.getRemark()) ? "" : itemResult.getRemark());
    }

}
