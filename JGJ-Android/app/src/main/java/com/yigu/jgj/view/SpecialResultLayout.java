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
public class SpecialResultLayout extends RelativeLayout {
    @Bind(R.id.change_num)
    EditText changeNum;
    @Bind(R.id.investigation_num)
    EditText investigationNum;
    @Bind(R.id.move_num)
    EditText moveNum;
    @Bind(R.id.other_result_num)
    EditText otherResultNum;
    private Context mContext;
    private View view;

    public SpecialResultLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public SpecialResultLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public SpecialResultLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_special_result, this);
        ButterKnife.bind(this, view);
    }

    public boolean vorify() {
        if (TextUtils.isEmpty(changeNum.getText().toString())) {
            MainToast.showLongToast("请输入责令改正");
            return false;
        }
        if (TextUtils.isEmpty(investigationNum.getText().toString())) {
            MainToast.showLongToast("请输入立案查处");
            return false;
        }
        if (TextUtils.isEmpty(moveNum.getText().toString())) {
            MainToast.showLongToast("请输入抄告、移送");
            return false;
        }
        if (TextUtils.isEmpty(otherResultNum.getText().toString())) {
            MainToast.showLongToast("请输入其他结果");
            return false;
        }
        return true;
    }

    public String getChangeNum() {
        return changeNum.getText().toString();
    }

    public String getInvestigationNum() {
        return investigationNum.getText().toString();
    }

    public String getMoveNum() {
        return moveNum.getText().toString();
    }

    public String getOtherResultNum() {
        return otherResultNum.getText().toString();
    }

    public void loadData(MapiSepcialResult itemResult, boolean enable) {
        if (null != itemResult) {
            changeNum.setText(itemResult.getCorrection() + "");
            investigationNum.setText(itemResult.getRegister() + "");
            moveNum.setText(itemResult.getChaogu() + "");
            otherResultNum.setText(itemResult.getOther2());

            changeNum.setEnabled(enable);
            investigationNum.setEnabled(enable);
            moveNum.setEnabled(enable);
            otherResultNum.setEnabled(enable);
        }
    }

}
