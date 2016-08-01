package com.yigu.jgj.util;

import android.content.Intent;

import com.yigu.jgj.activity.LoginActivity;
import com.yigu.jgj.activity.MainActivity;
import com.yigu.jgj.activity.SelCommunityActivity;
import com.yigu.jgj.activity.SelRootActivity;
import com.yigu.jgj.activity.assign.AssignDetailActivity;
import com.yigu.jgj.activity.assign.AssignTaskActivity;
import com.yigu.jgj.activity.company.CompanyAddActivity;
import com.yigu.jgj.activity.company.CompanyListActivity;
import com.yigu.jgj.activity.company.CompanyMessageActivity;
import com.yigu.jgj.activity.daily.DailyActivity;
import com.yigu.jgj.activity.daily.DailySecondActivity;
import com.yigu.jgj.activity.daily.DailyThirdActivity;
import com.yigu.jgj.activity.danger.DanagerDetailActivity;
import com.yigu.jgj.activity.danger.DangerListActivity;
import com.yigu.jgj.activity.file.FileActivity;
import com.yigu.jgj.activity.file.FileDetailActivity;
import com.yigu.jgj.activity.person.PerManageActivity;
import com.yigu.jgj.activity.task.TaskActivity;
import com.yigu.jgj.activity.task.TaskDetailActivity;
import com.yigu.jgj.commom.application.AppContext;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.result.MapiTaskResult;

import java.util.ArrayList;
import java.util.Map;

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
    public static void go2DailySecond(MapiItemResult itemResult) {
        Intent intent = new Intent(AppContext.getInstance(), DailySecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("item",itemResult);
        AppContext.getInstance().startActivity(intent);
    }

    public static void go2CompanyMessage(String action,MapiItemResult itemResult) {
        Intent intent = new Intent(AppContext.getInstance(), CompanyMessageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("item",itemResult);
        intent.putExtra("action",action);
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
    public static void go2DailyThird(MapiItemResult itemResult) {
        Intent intent = new Intent(AppContext.getInstance(), DailyThirdActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("item",itemResult);
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
    public static void go2TaskDetail(MapiTaskResult taskResult) {
        Intent intent = new Intent(AppContext.getInstance(), TaskDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("item",taskResult);
        AppContext.getInstance().startActivity(intent);
    }
    public static void go2CompanyList() {
        Intent intent = new Intent(AppContext.getInstance(), CompanyListActivity.class);
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
    public static void go2AssignDetail(MapiTaskResult taskResult) {
        Intent intent = new Intent(AppContext.getInstance(), AssignDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("item",taskResult);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 隐患档案-列表
     */
    public static void go2DangerList() {
        Intent intent = new Intent(AppContext.getInstance(), DangerListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 隐患档案-列表
     */
    public static void go2DanagerDetail(MapiItemResult itemResult) {
        Intent intent = new Intent(AppContext.getInstance(), DanagerDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("item",itemResult);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 归档信息-列表
     */
    public static void go2File() {
        Intent intent = new Intent(AppContext.getInstance(), FileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 登录
     */
    public static void go2Login() {
        Intent intent = new Intent(AppContext.getInstance(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 首页
     */
    public static void go2Main() {
        Intent intent = new Intent(AppContext.getInstance(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 汇报上级
     */
    public static void go2SelRoot(MapiItemResult itemResult) {
        Intent intent = new Intent(AppContext.getInstance(), SelRootActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("item",itemResult);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 归档-详情
     */
    public static void go2FileDetail(MapiTaskResult taskResult,String title) {
        Intent intent = new Intent(AppContext.getInstance(), FileDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("item",taskResult);
        intent.putExtra("title",title);
        AppContext.getInstance().startActivity(intent);
    }

}
