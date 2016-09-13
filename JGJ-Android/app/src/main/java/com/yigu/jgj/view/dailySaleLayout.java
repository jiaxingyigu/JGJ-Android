package com.yigu.jgj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.widget.MainToast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/23.
 */
public class DailySaleLayout extends RelativeLayout {
    @Bind(R.id.sanitation)
    RadioButton sanitation;
    @Bind(R.id.sanitationNull)
    RadioButton sanitationNull;
    @Bind(R.id.overdue)
    RadioButton overdue;
    @Bind(R.id.overdueNull)
    RadioButton overdueNull;
    @Bind(R.id.fullmark)
    RadioButton fullmark;
    @Bind(R.id.fullmarkNull)
    RadioButton fullmarkNull;
    //    @Bind(R.id.recyclerView)
//    RecyclerView recyclerView;
    private Context mContext;
    private View view;

    //    private DailyItemAdapter mAdapter;
    public DailySaleLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public DailySaleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public DailySaleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_daily_sale, this);
        ButterKnife.bind(this, view);
//        LinearLayoutManager manager = new LinearLayoutManager(mContext);
//        recyclerView.setLayoutManager(manager);
//        mAdapter = new DailyItemAdapter(mContext);
//        recyclerView.setAdapter(mAdapter);
    }

    public void loadData(MapiItemResult itemResult,boolean enable) {
        if(null!=itemResult) {
            if(itemResult.getSanitation()!=null){
                if(itemResult.getSanitation()==1)
                    sanitation.setChecked(true);
                else
                    sanitationNull.setChecked(true);
            }

            if(itemResult.getOverdue()!=null){
                if(itemResult.getOverdue()==1)
                    overdue.setChecked(true);
                else
                    overdueNull.setChecked(true);
            }


            if(itemResult.getFullmark()!=null){
                if(itemResult.getFullmark()==1)
                    fullmark.setChecked(true);
                else
                    fullmarkNull.setChecked(true);
            }

            sanitation.setEnabled(enable);
            sanitationNull.setEnabled(enable);
            overdue.setEnabled(enable);
            overdueNull.setEnabled(enable);
            fullmark.setEnabled(enable);
            fullmarkNull.setEnabled(enable);

        }
    }

    public boolean vorify(){
        if(!sanitation.isChecked()&&!sanitationNull.isChecked()){
            MainToast.showShortToast("请检查店内环境卫生");
            return false;
        }
        if(!overdue.isChecked()&&!overdueNull.isChecked()){
            MainToast.showShortToast("请检查过期变质");
            return false;
        }
        if(!fullmark.isChecked()&&!fullmarkNull.isChecked()){
            MainToast.showShortToast("请检查标签标识");
            return false;
        }
        return true;
    }

    public int sanitationCheck(){
        if(sanitation.isChecked())
            return 1;
        else
            return 0;
    }

    public int overdueCheck(){
        if(overdue.isChecked())
            return 1;
        else
            return 0;
    }

    public int fullmarkCheck(){
        if(fullmark.isChecked())
            return 1;
        else
            return 0;
    }

}
