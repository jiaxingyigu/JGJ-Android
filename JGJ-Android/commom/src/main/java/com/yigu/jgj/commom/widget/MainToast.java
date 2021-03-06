package com.yigu.jgj.commom.widget;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yigu.jgj.commom.application.AppContext;

/**
 * Created by Administrator on 2016/2/17.
 */
public class MainToast {
    private static Toast toast;

    public static void showShortToast(String message) {
        toast = Toast.makeText(AppContext.getInstance(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showLongToast(String message) {
        toast = Toast.makeText(AppContext.getInstance(), message, Toast.LENGTH_LONG);
        toast.show();
    }

}
