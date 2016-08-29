package com.yigu.jgj.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.util.JGJDataSource;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity {

    @Bind(R.id.viewpager)
    ViewPager viewPager;
    List<Integer> mList = new ArrayList<>();
    ViewPagerAdapter mAdapter;
    @Bind(R.id.header_layout)
    RelativeLayout headerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initView();
        load();
    }

    private void initView() {
        headerLayout.setBackgroundColor(Color.parseColor("#0D000000"));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.HORIZONTAL);
        mAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(mAdapter);
    }

    private void load() {
        String role_id = userSP.getUserBean().getROLE_ID();
        mList.clear();
        if (!TextUtils.isEmpty(role_id)) {
            if (role_id.equals(JGJDataSource.root_one_roleid) || role_id.equals(JGJDataSource.root_two_roleid)) {
                mList.add(R.mipmap.guide_assign);
            } else if (role_id.equals(JGJDataSource.manage_roleid)) {
                mList.add(R.mipmap.guide_task);
            } else
                mList.add(R.mipmap.guide_daily);
            mList.add(R.mipmap.guide_file);
            mAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View channelView = null;
            try {
                channelView = LayoutInflater.from(GuideActivity.this).inflate(
                        R.layout.item_guide, container, false);
                ImageView image = (ImageView) channelView.findViewById(R.id.image);
                image.setImageResource(mList.get(position));
                container.addView(channelView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return channelView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

}
