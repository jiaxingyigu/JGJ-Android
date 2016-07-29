package com.yigu.jgj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/22.
 */
public class DailyHeadLayout extends RelativeLayout {
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.lperson)
    TextView lperson;
    @Bind(R.id.tel)
    TextView tel;
    @Bind(R.id.project)
    TextView project;
    private Context mContext;
    private View view;

    public DailyHeadLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public DailyHeadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public DailyHeadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_daily_head, this);
        ButterKnife.bind(this, view);
    }

    public void loadData(MapiItemResult itemResult) {
        if(null!=itemResult){
            List<String> projectList = new ArrayList<>();
            if (null!=itemResult.getFOODSALES()&&itemResult.getFOODSALES() == 1)
                projectList.add("食品销售");
            if (null!=itemResult.getFOODSERVICE()&&itemResult.getFOODSERVICE() == 1)
                projectList.add("餐饮服务");
            if (null!=itemResult.getCANTEEN()&&itemResult.getCANTEEN() == 1)
                projectList.add("单位食堂");
            name.setText(itemResult.getNAME());
            lperson.setText(itemResult.getLPERSON());
            tel.setText(itemResult.getTEL());
            project.setText(StringUtil.listToString(projectList,"  "));
        }

    }

}
