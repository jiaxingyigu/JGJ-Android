package com.yigu.jgj.commom.api;

/**
 * Created by brain on 2016/6/14.
 */
public class BasicApi {

    public static String BASIC_URL = "http://115.159.118.182/MASU";
    public static String BASIC_IMAGE = "http://115.159.118.182/MASU/uploadFiles/uploadImgs/";

    /**user*/
    protected static String loginUrl = "/appuser/getLogin";
    /**员工列表*/
    protected static String getUserlist = "/appuser/getUserlist";
    /**资源*/
    protected static String getMain = "/appuser/getmain";
    /**企业列表*/
    protected static String getShoplist = "/appuser/getShoplist";
    /**新增企业*/
    protected static String addShop = "/appuser/addShop";
    /**修改企业*/
    protected static String editShop = "/appuser/editShop";
    /**日常巡查*/
    protected static String dailyPatrol = "/appuser/dailyPatrol";
    /**上传文件*/
    protected static String saveImages  = "/appuser/saveImages";
    /**日常隐患通用列表接口*/
    protected static String getDailyPatrollist  = "/appuser/getDailyPatrollist";
    /**日常巡查隐患详情*/
    protected static String dailyPatrolDetails = "/appuser/dailyPatrolDetails";
    /**任务分配*/
    protected static String tasksend = "/appuser/tasksend";
}
