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
    @Bind(R.id.tv_catering)
    RadioButton tvCatering;
    @Bind(R.id.tv_product)
    RadioButton tvProduct;
    @Bind(R.id.tv_currency)
    RadioButton tvCurrency;
    @Bind(R.id.tv_a)
    RadioButton tvA;
    @Bind(R.id.tv_b)
    RadioButton tvB;
    @Bind(R.id.tv_c)
    RadioButton tvC;

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
        if (!tvFoodSale.isChecked() && !tvFoodService.isChecked() && !tvCanteen.isChecked()) {
            MainToast.showShortToast("请检查许可项目");
            return false;
        }
//        if(!tvLicenseHave.isChecked()&&!tvLicenseNull.isChecked()){
//            MainToast.showLongToast("请检查营业执照");
//            return false;
//        }
//        if(!tvPermitHave.isChecked()&&!tvPermitNull.isChecked()){
//            MainToast.showLongToast("请检查许可证");
//            return false;
//        }
        if(!tvCatering.isChecked()&&!tvProduct.isChecked()&&!tvCurrency.isChecked()){
            MainToast.showShortToast("请选择分类");
            return false;
        }
        if(!tvA.isChecked()&&!tvB.isChecked()&&!tvC.isChecked()){
            MainToast.showShortToast("请选择类别");
            return false;
        }
        return true;
    }

    public int foodSaleCheck() {
        if (tvFoodSale.isChecked())
            return 1;
        else
            return 0;
    }

    public int tvFoodServiceCheck() {
        if (tvFoodService.isChecked())
            return 1;
        else
            return 0;
    }

    public int tvCanteenCheck() {
        if (tvCanteen.isChecked())
            return 1;
        else
            return 0;
    }

    public String getCATEGORY(){
        if(tvCatering.isChecked())
            return "1";
        else if(tvProduct.isChecked())
            return "2";
        else if(tvCurrency.isChecked())
            return "3";
        return "";
    }

    public String getTYPE(){
        if(tvA.isChecked())
            return "A";
        else if(tvB.isChecked())
            return "B";
        else if(tvC.isChecked())
            return "C";
        return "";
    }

   /* public int tvLicenseHaveCheck() {
        if(tvLicenseHave.isChecked())
            return 1;
        else
        return 0;
    }

    public int tvPermitHaveCheck() {
        if(tvPermitHave.isChecked())
            return 1;
        else
        return 0;
    }*/

    public void setData(MapiItemResult resourceResult) {
        tvFoodSale.setChecked((null != resourceResult.getFOODSALES() && resourceResult.getFOODSALES() == 1) ? true : false);
        tvFoodService.setChecked((null != resourceResult.getFOODSERVICE() && resourceResult.getFOODSERVICE() == 1) ? true : false);
        tvCanteen.setChecked((null != resourceResult.getCANTEEN() && resourceResult.getCANTEEN() == 1) ? true : false);
        if (null != resourceResult.getLICENSE()) {
//            if(resourceResult.getLICENSE()==1)
//                tvLicenseHave.setChecked(true);
//            else
//                tvLicenseNull.setChecked(true);
        }

        if (null != resourceResult.getPEMIT()) {
//            if(resourceResult.getPEMIT()==1)
//                tvPermitHave.setChecked(true);
//            else
//                tvPermitNull.setChecked(true);
        }

        if (null != resourceResult.getCATEGORY()) {
            if(resourceResult.getCATEGORY().equals("1"))
                tvCatering.setChecked(true);
            if(resourceResult.getCATEGORY().equals("2"))
                tvProduct.setChecked(true);
            if(resourceResult.getCATEGORY().equals("3"))
                tvCurrency.setChecked(true);
        }

        if (null != resourceResult.getTYPE()) {
            if(resourceResult.getTYPE().equals("A"))
                tvA.setChecked(true);
            if(resourceResult.getTYPE().equals("B"))
                tvB.setChecked(true);
            if(resourceResult.getTYPE().equals("C"))
                tvC.setChecked(true);
        }

    }

}
