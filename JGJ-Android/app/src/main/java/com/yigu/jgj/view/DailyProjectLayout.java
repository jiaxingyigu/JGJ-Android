package com.yigu.jgj.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.widget.MainToast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/23.
 */
public class DailyProjectLayout extends RelativeLayout {
    //    @Bind(R.id.recyclerView)
//    RecyclerView recyclerView;
    @Bind(R.id.oldhcatenk)
    TextView oldhcatenk;
    @Bind(R.id.ptioners)
    EditText ptioners;
    @Bind(R.id.hcate)
    EditText hcate;
    @Bind(R.id.showlicense)
    RadioButton showlicense;
    @Bind(R.id.hygiene)
    RadioButton hygiene;
    @Bind(R.id.invoice)
    RadioButton invoice;
    @Bind(R.id.showlicenseNull)
    RadioButton showlicenseNull;
    @Bind(R.id.hygieneNull)
    RadioButton hygieneNull;
    @Bind(R.id.invoiceNull)
    RadioButton invoiceNull;
    private Context mContext;
    private View view;
//    private DailyItemAdapter mAdapter;

    public DailyProjectLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public DailyProjectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public DailyProjectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_daily_project, this);
        ButterKnife.bind(this, view);
//        LinearLayoutManager manager = new LinearLayoutManager(mContext);
//        recyclerView.setLayoutManager(manager);
//        mAdapter = new DailyItemAdapter(mContext);
//        recyclerView.setAdapter(mAdapter);
    }

    public void loadData(MapiItemResult itemResult,boolean enable) {
        if(null!=itemResult) {
            oldhcatenk.setText(itemResult.getHCATEN() + "");
            ptioners.setText(itemResult.getPtioners()==null?"":itemResult.getPtioners()+"");
            hcate.setText(itemResult.getHcate()==null?"":itemResult.getHcate()+"");
            if(itemResult.getShowlicense()!=null){
                if(itemResult.getShowlicense()==1)
                    showlicense.setChecked(true);
                else
                    showlicenseNull.setChecked(true);
            }

            if(itemResult.getHygiene()!=null){
                if(itemResult.getHygiene()==1)
                    hygiene.setChecked(true);
                else
                    hygieneNull.setChecked(true);
            }

            if(itemResult.getInvoice()!=null){
                if(itemResult.getInvoice()==1)
                    invoice.setChecked(true);
                else
                    invoiceNull.setChecked(true);
            }
            ptioners.setEnabled(enable);
            hcate.setEnabled(enable);
            showlicense.setEnabled(enable);
            showlicenseNull.setEnabled(enable);
            hygiene.setEnabled(enable);
            hygieneNull.setEnabled(enable);
            invoice.setEnabled(enable);
            invoiceNull.setEnabled(enable);
        }
    }

    public boolean vorify() {
        if (TextUtils.isEmpty(ptioners.getText().toString())) {
            MainToast.showShortToast("请输入从业人员数量");
            return false;
        }

        if (TextUtils.isEmpty(hcate.getText().toString())) {
            MainToast.showShortToast("请输入健康证数");
            return false;
        }

        if (!showlicense.isChecked()&&!showlicenseNull.isChecked()) {
            MainToast.showShortToast("请检查是否亮证经营");
            return false;
        }

        if (!hygiene.isChecked()&&!hygieneNull.isChecked()) {
            MainToast.showShortToast("请检查个人卫生情况");
            return false;
        }

        if (!invoice.isChecked()&&!invoiceNull.isChecked()) {
            MainToast.showShortToast("请检查是否建立索证索票");
            return false;
        }
        return true;
    }

    public String getPtioners(){
        return ptioners.getText().toString();
    }

    public String getHcate(){
        return hcate.getText().toString();
    }

    public int showlicenseCheck(){
        if(showlicense.isChecked())
            return 1;
        else
            return 0;
    }

    public int hygieneCheck(){
        if(hygiene.isChecked())
            return 1;
        else
            return 0;
    }

    public int invoiceCheck(){
        if(invoice.isChecked())
            return 1;
        else
            return 0;
    }

}
