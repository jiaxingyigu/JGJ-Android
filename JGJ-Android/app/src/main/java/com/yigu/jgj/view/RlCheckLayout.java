package com.yigu.jgj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.widget.MainToast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yzb on 2016/6/21.
 */
public class RlCheckLayout extends RelativeLayout {
    View view;
    Context mContext;
    @Bind(R.id.tv_food_sale)
    CheckBox tvFoodSale;
    @Bind(R.id.tv_food_service)
    CheckBox tvFoodService;
    @Bind(R.id.tv_canteen)
    CheckBox tvCanteen;
    @Bind(R.id.tv_license_have)
    RadioButton tvLicenseHave;
    @Bind(R.id.tv_license_null)
    RadioButton tvLicenseNull;
    @Bind(R.id.tv_permit_have)
    RadioButton tvPermitHave;
    @Bind(R.id.tv_permit_null)
    RadioButton tvPermitNull;

    public RlCheckLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public RlCheckLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public RlCheckLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.rl_company_message_check, this);
        ButterKnife.bind(this, view);
    }

    public boolean vorify() {
        if(!tvFoodSale.isChecked()&&!tvFoodService.isChecked()&&!tvCanteen.isChecked()){
            MainToast.showLongToast("请检查许可项目");
            return false;
        }
        if(!tvLicenseHave.isChecked()&&!tvLicenseNull.isChecked()){
            MainToast.showLongToast("请检查营业执照");
            return false;
        }
        if(!tvPermitHave.isChecked()&&!tvPermitNull.isChecked()){
            MainToast.showLongToast("请检查许可证");
            return false;
        }
        return true;
    }

    public int foodSaleCheck(){
        if(tvFoodSale.isChecked())
            return 1;
        else
            return 0;
    }

    public int tvFoodServiceCheck(){
        if(tvFoodService.isChecked())
            return 1;
        else
            return 0;
    }

    public int tvCanteenCheck(){
        if(tvCanteen.isChecked())
            return 1;
        else
            return 0;
    }

    public int tvLicenseHaveCheck(){
        if(tvLicenseHave.isChecked())
            return 1;
        else
            return 0;
    }

    public int tvPermitHaveCheck(){
        if(tvPermitHave.isChecked())
            return 1;
        else
            return 0;
    }

    public void setData(MapiItemResult resourceResult){
        tvFoodSale.setChecked((null!=resourceResult.getFOODSALES()&&resourceResult.getFOODSALES()==1)?true:false);
        tvFoodService.setChecked((null!=resourceResult.getFOODSERVICE()&&resourceResult.getFOODSERVICE()==1)?true:false);
        tvCanteen.setChecked((null!=resourceResult.getCANTEEN()&&resourceResult.getCANTEEN()==1)?true:false);
        if(null!=resourceResult.getLICENSE()){
            if(resourceResult.getLICENSE()==1)
                tvLicenseHave.setChecked(true);
            else
                tvLicenseNull.setChecked(true);
        }

        if(null!=resourceResult.getPEMIT()){
            if(resourceResult.getPEMIT()==1)
                tvPermitHave.setChecked(true);
            else
                tvPermitNull.setChecked(true);
        }

    }

}
