package com.yigu.jgj.commom.api;

/**
 * Created by brain on 2016/6/14.
 */
public class BasicApi {

    public static String BASIC_URL = "http://115.159.118.182:8081/MASU";//http://182.254.152.46/MASU
    public static String BASIC_IMAGE = "http://182.254.152.46/MASU/uploadFiles/uploadImgs/";

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
    /**我的任务-处理/隐患档案-完成*/
    protected static String taskcomplete = "/appuser/taskcomplete";
    /**任务分配-转移*/
    protected static String tasktransfer = "/appuser/tasktransfer";
    /**消息列表*/
    protected static String  getMessagelist = "/appuser/getMessagelist";
    /**档案管理列表*/
    protected static String getArchiveslist = "/appuser/getArchiveslist";
    /**无照上报列表*/
    protected static String getNlicenselist  = "/appuser/getNlicenselist";
    /**新增无照上报*/
    protected static String addNLShop = "/appuser/addNLShop";
    /**任务指令-无照*/
    protected static String tasksendNLS = "/appuser/tasksendNLS";
    /**我的任务-无照-完成*/
    protected static String taskcompleteNLS = "/appuser/taskcompleteNLS";
    /**专项行动-列表*/
    protected static String specialactionlist = "/appuser/specialactionlist";
    /**专项行动-新增*/
    protected static String addSpecialaction = "/appuser/addSpecialaction";
    /**人员下拉选择接口*/
    protected static String getdropdownlist = "/appuser/getdropdownlist";
    /**任务指令-新增*/
    protected static String addtask = "/appuser/addtask";
    /**我的任务-其他-列表*/
    protected static String getOthertask = "/appuser/getOthertask";
    /**Othertaskcomplete*/
    protected static String Othertaskcomplete = "/appuser/Othertaskcomplete";
    /**通知-新增*/
    protected static String addMessage = "/appuser/addMessage";
    /**预警列表*/
    protected static String getWarninglist = "/appuser/getWarninglist";
    /**预警-新增*/
    protected static String addWarning = "/appuser/addWarning";
    /**任务指令-退回*/
    protected static String taskback = "/appuser/taskback";
    /**修改密码*/
    protected static String editPassword = "/appuser/editPassword";
    /**聚餐管理-新增*/
    protected static String addDinner = "/appuser/addDinner";
    /**聚餐管理-列表*/
    protected static String Dinnerlist = "/appuser/Dinnerlist";
    /**数据查询*/
    protected static String GetHisData = "/appuser/GetHisData";
}
