package com.yigu.jgj.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.util.DPUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class MainAlertDialog extends Dialog {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.left_button)
    TextView mLeftButton;
    @Bind(R.id.right_button)
    TextView mRightButton;


    public MainAlertDialog(Context context) {
        super(context, R.style.dialog_theme);
        initView();
    }

    public MainAlertDialog(Context context, int theme) {
        super(context, theme);
        initView();
    }

    public MainAlertDialog setLeftBtnText(String str) {
        if (str.length() > 0) {
            mLeftButton.setText(str);
            mLeftButton.setVisibility(View.VISIBLE);
        } else {
            mLeftButton.setVisibility(View.GONE);
        }
        return this;
    }

    public MainAlertDialog setRightBtnText(String str) {
        if (str.length() > 0) {
            mRightButton.setText(str);
            mRightButton.setVisibility(View.VISIBLE);
        } else {
            mRightButton.setVisibility(View.GONE);
        }
        return this;
    }


    private void initView() {
        setContentView(R.layout.dialog_alert);
        ButterKnife.bind(this);
    }

    public MainAlertDialog setTitle(String str) {
        mTitle.setText(str);
        return this;
    }

    public MainAlertDialog setLeftClickListener(View.OnClickListener clickListener) {
        mLeftButton.setOnClickListener(clickListener);
        return this;
    }

    public MainAlertDialog setRightClickListener(View.OnClickListener clickListener) {
        mRightButton.setOnClickListener(clickListener);
        return this;
    }

    public void setShopCartTextSize() {
        mLeftButton.setTextSize(DPUtil.dip2px(8));
        mTitle.setTextSize(DPUtil.dip2px(7));
    }
}
