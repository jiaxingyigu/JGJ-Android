package com.yigu.jgj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yigu.jgj.R;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.util.DateUtil;
import com.yigu.jgj.commom.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by brain on 2016/6/26.
 */
public class DanagerHeadLayout extends RelativeLayout {
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.lperson)
    TextView lperson;
    @Bind(R.id.tel)
    TextView tel;
    @Bind(R.id.project)
    TextView project;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.receiver)
    TextView receiver;
    private Context mContext;
    private View view;

    public DanagerHeadLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public DanagerHeadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public DanagerHeadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        if (isInEditMode())
            return;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_danager_head, this);
        ButterKnife.bind(this, view);
    }

    public void loadData(MapiItemResult itemResult) {
        if (null != itemResult) {
            List<String> projectList = new ArrayList<>();
            if (itemResult.getFOODSALES() == 1)
                projectList.add("食品销售");
            if (itemResult.getFOODSERVICE() == 1)
                projectList.add("餐饮服务");
            if (itemResult.getCANTEEN() == 1)
                projectList.add("单位食堂");
            name.setText(itemResult.getShopname());
            lperson.setText(itemResult.getLPERSON());
            tel.setText(itemResult.getTEL());
            project.setText(StringUtil.listToString(projectList, "  "));
            date.setText(DateUtil.getInstance().YMDHMS2YMD(itemResult.getIdate()));
            receiver.setText("执行人员："+itemResult.getReceiver());
        }

    }
}
