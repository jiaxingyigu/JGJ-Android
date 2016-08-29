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
public class SpecialFindLayout extends RelativeLayout {
    @Bind(R.id.license_num)
    EditText licenseNum;
    @Bind(R.id.manage_num)
    EditText manageNum;
    @Bind(R.id.illegal_num)
    EditText illegalNum;
    @Bind(R.id.other_num)
    EditText otherNum;
    private Context mContext;
    private View view;

    public SpecialFindLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public SpecialFindLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public SpecialFindLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_special_find, this);
        ButterKnife.bind(this, view);
    }

    public boolean vorify() {
        if (TextUtils.isEmpty(licenseNum.getText().toString())) {
            MainToast.showLongToast("请输入无证无照");
            return false;
        }
        if (TextUtils.isEmpty(manageNum.getText().toString())) {
            MainToast.showLongToast("请输入改变经营事项");
            return false;
        }
        if (TextUtils.isEmpty(illegalNum.getText().toString())) {
            MainToast.showLongToast("请输入发现违法物品");
            return false;
        }
        if (TextUtils.isEmpty(otherNum.getText().toString())) {
            MainToast.showLongToast("请输入其他问题");
            return false;
        }
        return true;
    }

    public String getLicenseNum(){return licenseNum.getText().toString();}
    public String getManageNum(){return manageNum.getText().toString();}
    public String getIllegalNum(){return illegalNum.getText().toString();}
    public String getOtherNum(){return otherNum.getText().toString();}

    public void loadData(MapiSepcialResult itemResult, boolean enable) {
        if(null!=itemResult){
            licenseNum.setText(itemResult.getNlshop()+"");
            manageNum.setText(itemResult.getCscope()+"");
            illegalNum.setText(itemResult.getContraband()+"");
            otherNum.setText(itemResult.getOther1());

            licenseNum.setEnabled(enable);
            manageNum.setEnabled(enable);
            illegalNum.setEnabled(enable);
            otherNum.setEnabled(enable);
        }
    }

}
