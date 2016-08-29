package com.yigu.jgj.util;

import android.content.Intent;

import com.yigu.jgj.activity.GuideActivity;
import com.yigu.jgj.activity.LoginActivity;
import com.yigu.jgj.activity.LoginTwoActivity;
import com.yigu.jgj.activity.MainActivity;
import com.yigu.jgj.activity.PersonInfoActivity;
import com.yigu.jgj.activity.SelCommunityActivity;
import com.yigu.jgj.activity.SelRootActivity;
import com.yigu.jgj.activity.ShowBigPicActivity;
import com.yigu.jgj.activity.assign.AssignAddActivity;
import com.yigu.jgj.activity.assign.AssignDetailActivity;
import com.yigu.jgj.activity.assign.AssignTaskActivity;
import com.yigu.jgj.activity.assign.AssignTaskTwoActivity;
import com.yigu.jgj.activity.company.CompanyAddActivity;
import com.yigu.jgj.activity.company.CompanyListActivity;
import com.yigu.jgj.activity.company.CompanyListTwoActivity;
import com.yigu.jgj.activity.company.CompanyMessageActivity;
import com.yigu.jgj.activity.daily.DailyActivity;
import com.yigu.jgj.activity.daily.DailySecondActivity;
import com.yigu.jgj.activity.daily.DailyThirdActivity;
import com.yigu.jgj.activity.danger.DanagerDetailActivity;
import com.yigu.jgj.activity.danger.DangerListActivity;
import com.yigu.jgj.activity.file.FileActivity;
import com.yigu.jgj.activity.file.FileDetailActivity;
import com.yigu.jgj.activity.license.LicenseAddActivity;
import com.yigu.jgj.activity.license.LicenseDetailActivity;
import com.yigu.jgj.activity.license.LicenseMessageActivity;
import com.yigu.jgj.activity.license.WithoutLicenseActivity;
import com.yigu.jgj.activity.msg.MsgListActivity;
import com.yigu.jgj.activity.notify.NotifyAddActivity;
import com.yigu.jgj.activity.notify.NotifyListActivity;
import com.yigu.jgj.activity.person.PerManageActivity;
import com.yigu.jgj.activity.special.SpecialAddActivity;
import com.yigu.jgj.activity.special.SpecialDetailActivity;
import com.yigu.jgj.activity.special.SpecialListActivity;
import com.yigu.jgj.activity.task.TaskActivity;
import com.yigu.jgj.activity.task.TaskDetailActivity;
import com.yigu.jgj.activity.task.TaskLicDetailActivity;
import com.yigu.jgj.activity.task.TaskTwoActivity;
import com.yigu.jgj.commom.application.AppContext;
import com.yigu.jgj.commom.result.MapiImageResult;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.result.MapiSepcialResult;
import com.yigu.jgj.commom.result.MapiTaskResult;

import java.util.ArrayList;
import java.util.List;
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

    public static void go2CompanyMessage(MapiItemResult itemResult) {
        Intent intent = new Intent(AppContext.getInstance(), CompanyMessageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("item",itemResult);
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
        Intent intent = new Intent(AppContext.getInstance(), TaskTwoActivity.class);
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
        Intent intent = new Intent(AppContext.getInstance(), CompanyListTwoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 任务分派
     */
    public static void go2AssignTask() {
        Intent intent = new Intent(AppContext.getInstance(), AssignTaskTwoActivity.class);
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
        Intent intent = new Intent(AppContext.getInstance(), LoginTwoActivity.class);
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

    /**
     * 大图展示
     * @param list
     * @param position
     */
    public static void go2ShowBigPic(ArrayList<MapiImageResult> list, int position) {
        Intent intent = new Intent(AppContext.getInstance(), ShowBigPicActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("list",list);
        intent.putExtra("position",position);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 消息-列表
     */
    public static void go2MsgList(){
        Intent intent = new Intent(AppContext.getInstance(), MsgListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 新手指导
     */
    public static void go2Guide(){
        Intent intent = new Intent(AppContext.getInstance(), GuideActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 无照列表
     */
    public static void go2WithoutLicense() {
        Intent intent = new Intent(AppContext.getInstance(), WithoutLicenseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 无照新增
     */
    public static void go2LicenseAdd() {
        Intent intent = new Intent(AppContext.getInstance(), LicenseAddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 无照上报-详情
     */
    public static void go2LicenseDetail(MapiItemResult taskResult) {
        Intent intent = new Intent(AppContext.getInstance(), LicenseDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("item",taskResult);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 任务指令-新增
     */
    public static void go2AssignAdd() {
        Intent intent = new Intent(AppContext.getInstance(), AssignAddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 专项行动
     */
    public static void go2SpecialList() {
        Intent intent = new Intent(AppContext.getInstance(), SpecialListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 专项行动-新增
     */
    public static void go2SpecialAdd() {
        Intent intent = new Intent(AppContext.getInstance(), SpecialAddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 无照上报-详情
     */
    public static void go2LicenseMessage(MapiItemResult itemResult) {
        Intent intent = new Intent(AppContext.getInstance(), LicenseMessageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("item",itemResult);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 我的任务无照上报-详情
     */
    public static void go2TaskLicDetail(MapiItemResult taskResult) {
        Intent intent = new Intent(AppContext.getInstance(), TaskLicDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("item",taskResult);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 专项行动-详情
     */
    public static void go2SpecialDetail(MapiSepcialResult itemResult) {
        Intent intent = new Intent(AppContext.getInstance(), SpecialDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("item",itemResult);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 个人信息
     */
    public static void go2PersonInfo() {
        Intent intent = new Intent(AppContext.getInstance(),PersonInfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 通知-列表
     */
    public static void go2NotifyList(){
        Intent intent = new Intent(AppContext.getInstance(), NotifyListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

    /**
     * 通知公告-新增
     */
    public static void go2NotifyAdd() {
        Intent intent = new Intent(AppContext.getInstance(), NotifyAddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.getInstance().startActivity(intent);
    }

}
