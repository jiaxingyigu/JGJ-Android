package com.yigu.jgj.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiSepcialResult;
import com.yigu.jgj.commom.widget.MainToast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/8/24.
 */
public class SpecialRemarkLayout extends RelativeLayout {
    @Bind(R.id.remark)
    EditText remark;
    private Context mContext;
    private View view;

    public SpecialRemarkLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public SpecialRemarkLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public SpecialRemarkLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_special_remark, this);
        ButterKnife.bind(this, view);
    }

    public boolean vorify() {
        /*if (TextUtils.isEmpty(remark.getText().toString())) {
            MainToast.showLongToast("请输入备注");
            return false;
        }*/
        return true;
    }

    public String getRemark() {
        return remark.getText().toString();
    }

    public void loadData(MapiSepcialResult itemResult, boolean enable) {
        if (null != itemResult) {
            remark.setText(itemResult.getRemark());
            remark.setEnabled(enable);
        }
    }

}
