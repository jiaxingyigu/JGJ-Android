package com.yigu.jgj.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.facebook.drawee.view.SimpleDraweeView;
import com.yigu.jgj.R;
import com.yigu.jgj.base.BaseActivity;
import com.yigu.jgj.commom.result.IndexData;
import com.yigu.jgj.util.JGJDataSource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity {

    @Bind(R.id.viewpager)
    ViewPager viewPager;
    ViewPagerAdapter mAdapter;
    @Bind(R.id.header_layout)
    RelativeLayout headerLayout;
    List<Integer> resList1 = new ArrayList<>();
    List<Integer> resList2 = new ArrayList<>();
    List<Integer> resList3 = new ArrayList<>();
    List<Integer> resList4 = new ArrayList<>();
    List<IndexData> mList = new ArrayList<>();
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
        /*if (!TextUtils.isEmpty(role_id)) {
            if (role_id.equals(JGJDataSource.root_one_roleid) || role_id.equals(JGJDataSource.root_two_roleid)) {
                mList.add(R.mipmap.guide_assign);
            } else if (role_id.equals(JGJDataSource.manage_roleid)) {
                mList.add(R.mipmap.guide_task);
            } else
                mList.add(R.mipmap.guide_daily);
            mList.add(R.mipmap.guide_file);
            mAdapter.notifyDataSetChanged();
        }*/
        resList1.clear();
        resList1.add(R.mipmap.guide_daily);
        resList1.add(R.mipmap.guide_daily_two);
        mList.add(new IndexData(0,"0",resList1));
        resList2.clear();
        resList2.add(R.mipmap.guide_license);
        resList2.add(R.mipmap.guide_license_two);
        mList.add(new IndexData(1,"1",resList2));
        resList3.clear();
        resList3.add(R.mipmap.guide_special);
        mList.add(new IndexData(2,"2",resList3));
        resList4.clear();
        resList4.add(R.mipmap.guide_notify);
        mList.add(new IndexData(3,"3",resList4));
        mAdapter.notifyDataSetChanged();
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
                List<Integer> resList = (List<Integer>) mList.get(position).getData();
                ImageView image = (ImageView) channelView.findViewById(R.id.image);
                ImageView imageTwo = (ImageView) channelView.findViewById(R.id.image_two);
                Bitmap bitmap = readBitMap(GuideActivity.this,resList.get(0));
                image.setImageBitmap(bitmap);
                if(null!=resList&&resList.size()>1){
                    Bitmap bitmapTwo = readBitMap(GuideActivity.this,resList.get(1));
                    imageTwo.setImageBitmap(bitmapTwo);
                }else{
                    imageTwo.setVisibility(View.GONE);
                }
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

    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
// 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

}
