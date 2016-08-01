package com.yigu.jgj.commom.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import org.xutils.BuildConfig;
import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by brain on 2016/6/14.
 */
public class AppContext extends Application {

    private static AppContext appContext;
    public static AppContext getInstance(){
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = (AppContext) getApplicationContext();
        Fresco.initialize(this);
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }
}
