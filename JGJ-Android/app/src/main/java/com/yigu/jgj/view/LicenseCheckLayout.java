package com.yigu.jgj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.widget.MainToast;
import com.yigu.jgj.util.JGJDataSource;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yzb on 2016/6/21.
 */
public class LicenseCheckLayout extends RelativeLayout {
    View view;
    Context mContext;
    @Bind(R.id.tv_food_sale)
    CheckBox tvFoodSale;
    @Bind(R.id.tv_food_service)
    CheckBox tvFoodService;
    @Bind(R.id.tv_canteen)
    CheckBox tvCanteen;
    @Bind(R.id.tv_reportperson)
    TextView tvReportperson;
    @Bind(R.id.rg_reportperson)
    RadioGroup rgReportperson;
    @Bind(R.id.tv_person)
    TextView tvPerson;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_license_have)
    RadioButton tvLicenseHave;
    @Bind(R.id.tv_license_null)
    RadioButton tvLicenseNull;
    @Bind(R.id.tv_permit_have)
    RadioButton tvPermitHave;
    @Bind(R.id.tv_permit_null)
    RadioButton tvPermitNull;
    @Bind(R.id.tv_sz)
    RadioButton tvSz;
    @Bind(R.id.tv_zz)
    RadioButton tvZz;

    public LicenseCheckLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public LicenseCheckLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public LicenseCheckLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.rl_license_check, this);
        ButterKnife.bind(this, view);
    }

    public void hiddenPerson() {
        tvPerson.setVisibility(View.GONE);
        tvName.setVisibility(View.GONE);

    }

    public void hiddenReport() {
        tvReportperson.setVisibility(View.GONE);
        rgReportperson.setVisibility(View.GONE);
    }

    public boolean vorify() {
        if (!tvFoodSale.isChecked() && !tvFoodService.isChecked() && !tvCanteen.isChecked()) {
            MainToast.showShortToast("请检查许可项目");
            return false;
        }
        if (!tvLicenseHave.isChecked() && !tvLicenseNull.isChecked()) {
            MainToast.showShortToast("请检查营业执照");
            return false;
        }
        if (!tvPermitHave.isChecked() && !tvPermitNull.isChecked()) {
            MainToast.showShortToast("请检查许可证");
            return false;
        }
        if (tvLicenseHave.isChecked() && tvPermitHave.isChecked()) {
            MainToast.showShortToast("您选择的不符合无照的条件");
            return false;
        }
        if (!tvSz.isChecked() && !tvZz.isChecked()) {
            MainToast.showShortToast("请选择上报人");
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

    public int tvLicenseHaveCheck() {
        if (tvLicenseHave.isChecked())
            return 1;
        else
            return 0;
    }

    public int tvPermitHaveCheck() {
        if (tvPermitHave.isChecked())
            return 1;
        else
            return 0;
    }

    public String getROLE() {
        if (tvSz.isChecked())
            return JGJDataSource.root_one_roleid;
        else
            return JGJDataSource.root_two_roleid;
    }

    public void setData(MapiItemResult resourceResult) {
        tvFoodSale.setChecked((null != resourceResult.getFOODSALES() && resourceResult.getFOODSALES() == 1) ? true : false);
        tvFoodService.setChecked((null != resourceResult.getFOODSERVICE() && resourceResult.getFOODSERVICE() == 1) ? true : false);
        tvCanteen.setChecked((null != resourceResult.getCANTEEN() && resourceResult.getCANTEEN() == 1) ? true : false);
        if (null != resourceResult.getLICENSE()) {
            if (resourceResult.getLICENSE() == 1)
                tvLicenseHave.setChecked(true);
            else
                tvLicenseNull.setChecked(true);
        }

        if (null != resourceResult.getPEMIT()) {
            if (resourceResult.getPEMIT() == 1)
                tvPermitHave.setChecked(true);
            else
                tvPermitNull.setChecked(true);
        }
        if (resourceResult.getSenduser().equals("所长"))
            tvSz.setChecked(true);
        else if (resourceResult.getSenduser().equals("联络员"))
            tvZz.setChecked(true);
        tvName.setText(resourceResult.getUser());
    }

    public void setNoEdit() {
        tvFoodSale.setEnabled(false);
        tvFoodService.setEnabled(false);
        tvCanteen.setEnabled(false);
        tvLicenseHave.setEnabled(false);
        tvLicenseNull.setEnabled(false);
        tvPermitHave.setEnabled(false);
        tvPermitNull.setEnabled(false);
        tvSz.setEnabled(false);
        tvZz.setEnabled(false);
    }

}
