package com.yigu.jgj.commom.application;

import android.app.Application;

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

    }
}
