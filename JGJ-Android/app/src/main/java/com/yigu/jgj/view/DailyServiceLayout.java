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
public class DailyServiceLayout extends RelativeLayout {

    @Bind(R.id.train)
    RadioButton train;
    @Bind(R.id.trainNull)
    RadioButton trainNull;
    @Bind(R.id.disinfection)
    RadioButton disinfection;
    @Bind(R.id.disinfectionNull)
    RadioButton disinfectionNull;
    @Bind(R.id.poster)
    RadioButton poster;
    @Bind(R.id.posterNull)
    RadioButton posterNull;
    //    @Bind(R.id.recyclerView)
//    RecyclerView recyclerView;
    private Context mContext;
    private View view;

    //    private DailyItemAdapter mAdapter;
    public DailyServiceLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public DailyServiceLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public DailyServiceLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_daily_service, this);
        ButterKnife.bind(this, view);
//        LinearLayoutManager manager = new LinearLayoutManager(mContext);
//        recyclerView.setLayoutManager(manager);
//        mAdapter = new DailyItemAdapter(mContext);
//        recyclerView.setAdapter(mAdapter);
    }

    public void loadData(MapiItemResult itemResult,boolean enable) {
        if(null!=itemResult) {
            if(itemResult.getTrain()!=null){
                if(itemResult.getTrain()==1)
                    train.setChecked(true);
                else
                    trainNull.setChecked(true);
            }

            if(itemResult.getDisinfection()!=null){
                if(itemResult.getDisinfection()==1)
                    disinfection.setChecked(true);
                else
                    disinfectionNull.setChecked(true);
            }


            if(itemResult.getPoster()!=null){
                if(itemResult.getPoster()==1)
                    poster.setChecked(true);
                else
                    posterNull.setChecked(true);
            }

            train.setEnabled(enable);
            trainNull.setEnabled(enable);
            disinfection.setEnabled(enable);
            disinfectionNull.setEnabled(enable);
            poster.setEnabled(enable);
            posterNull.setEnabled(enable);

        }
    }

    public boolean vorify() {
        if (!train.isChecked() && !trainNull.isChecked()) {
            MainToast.showLongToast("请检查培训记录");
            return false;
        }
        if (!disinfection.isChecked() && !disinfectionNull.isChecked()) {
            MainToast.showLongToast("请检查消毒记录");
            return false;
        }
        if (!poster.isChecked() && !posterNull.isChecked()) {
            MainToast.showLongToast("请检查桌牌和海报");
            return false;
        }
        return true;
    }

    public int trainCheck() {
        if (train.isChecked())
            return 1;
        else
            return 0;
    }

    public int disinfectionCheck() {
        if (disinfection.isChecked())
            return 1;
        else
            return 0;
    }

    public int posterCheck() {
        if (poster.isChecked())
            return 1;
        else
            return 0;
    }

}
