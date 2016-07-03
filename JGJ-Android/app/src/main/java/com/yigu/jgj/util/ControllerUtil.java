package com.yigu.jgj.util;

import android.content.Intent;

import com.yigu.jgj.activity.assign.AssignDetailActivity;
import com.yigu.jgj.activity.assign.AssignTaskActivity;
import com.yigu.jgj.activity.company.CompanyAddActivity;
import com.yigu.jgj.activity.company.CompanyListActivity;
import com.yigu.jgj.activity.company.CompanyListActivity2;
import com.yigu.jgj.activity.company.CompanyMessageActivity;
import com.yigu.jgj.activity.daily.DailyActivity;
import com.yigu.jgj.activity.daily.DailySecondActivity;
import com.yigu.jgj.activity.daily.DailyThirdActivity;
import com.yigu.jgj.activity.person.PerManageActivity;
import com.yigu.jgj.activity.task.TaskActivity;
import com.yigu.jgj.activity.task.TaskDetailActivity;
import com.yigu.jgj.commom.application.AppContext;

/**
 * Created by brain on 2016/6/22.
 */
public class ControllerUtil {

    /**
     * 由主页进入日常巡查
     */
    public static void go2Daily() {
        Intent intent = new Intent(AppContext.getInstance(), DailyActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }
    /**
     * 日常巡查第二步
     */
    public static void go2DailySecond() {
        Intent intent = new Intent(AppContext.getInstance(), DailySecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }
    public static void go2CompanyList() {
        Intent intent = new Intent(AppContext.getInstance(), CompanyListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }
    public static void go2CompanyMessage() {
        Intent intent = new Intent(AppContext.getInstance(), CompanyMessageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }
    public static void go2CompanyAdd() {
        Intent intent = new Intent(AppContext.getInstance(), CompanyAddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 日常巡查第三步
     */
    public static void go2DailyThird() {
        Intent intent = new Intent(AppContext.getInstance(), DailyThirdActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 人员管理
     */
    public static void go2PerManage() {
        Intent intent = new Intent(AppContext.getInstance(), PerManageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 我的任务
     */
    public static void go2MyTask() {
        Intent intent = new Intent(AppContext.getInstance(), TaskActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 我的任务-详情
     */
    public static void go2TaskDetail() {
        Intent intent = new Intent(AppContext.getInstance(), TaskDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }
    public static void go2CompanyList2() {
        Intent intent = new Intent(AppContext.getInstance(), CompanyListActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 任务分派
     */
    public static void go2AssignTask() {
        Intent intent = new Intent(AppContext.getInstance(), AssignTaskActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 任务分派-详情
     */
    public static void go2AssignDetail() {
        Intent intent = new Intent(AppContext.getInstance(), AssignDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

}
