package com.yigu.jgj.commom.api;

import android.app.Activity;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yigu.jgj.commom.result.MapiImageResult;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiUserResult;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.MapiUtil;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brain on 2016/7/25.
 */
public class DailyApi extends BasicApi{

    /**
     * 日常巡查
     * @param activity
     * @param type   0=日常巡查 • 1=未处理隐患 2=已分配隐患 3=立案查处隐患  4=已归档隐患  必填
     * @param shopid 登录人id
     * @param userid
     * @param ptioners
     * @param hcate
     * @param showlicense
     * @param hygiene
     * @param invoice
     * @param sanitation
     * @param overdue
     * @param fullmark
     * @param train
     * @param disinfection
     * @param poster
     * @param remark
     * @param tasksend
     * @param image
     * @param callback
     * @param exceptionCallback
     */
    public static void dailyPatrol(Activity activity, String type, String shopid, String userid, String ptioners,
                                   String hcate,String showlicense,String hygiene,String invoice,String sanitation,
                                   String overdue,String fullmark,String train,String disinfection,String poster,
                                   String remark,String tasksend,String image,final RequestCallback callback, final RequestExceptionCallback exceptionCallback){

        Map<String,String> params = new HashMap<>();
        params.put("type",type);
        params.put("shopid",shopid);
        params.put("userid",userid);
        params.put("ptioners",TextUtils.isEmpty(ptioners)?"0":ptioners);
        params.put("hcate",TextUtils.isEmpty(hcate)?"0":hcate);
        params.put("showlicense",(TextUtils.isEmpty(showlicense)||"null".equals(showlicense))?"0":showlicense);
        params.put("hygiene",(TextUtils.isEmpty(hygiene)||"null".equals(hygiene))?"0":hygiene);
        params.put("invoice",(TextUtils.isEmpty(invoice)||"null".equals(invoice))?"0":invoice);
        params.put("sanitation",(TextUtils.isEmpty(sanitation)||"null".equals(sanitation))?"0":sanitation);
        params.put("overdue",(TextUtils.isEmpty(overdue)||"null".equals(overdue))?"0":overdue);
        params.put("fullmark",(TextUtils.isEmpty(fullmark)||"null".equals(fullmark))?"0":fullmark);
        params.put("train",(TextUtils.isEmpty(train)||"null".equals(train))?"0":train);
        params.put("disinfection",(TextUtils.isEmpty(disinfection)||"null".equals(disinfection))?"0":disinfection);
        params.put("poster",(TextUtils.isEmpty(poster)||"null".equals(poster))?"0":poster);
        params.put("remark",remark);
        if(!TextUtils.isEmpty(tasksend))
            params.put("tasksend",tasksend);
        params.put("image",image);
        DebugLog.i(params.toString());
        MapiUtil.getInstance().call(activity,dailyPatrol,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                callback.success(json);
            }
        },new MapiUtil.MapiFailResponse(){

            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });

    }

    /**
     * 上传图片
     * @param activity
     * @param file
     * @param callback
     * @param exceptionCallback
     */
    public static void
    uploadImage(Activity activity, File file, final RequestCallback callback, final RequestExceptionCallback exceptionCallback) {
        MapiUtil.getInstance().uploadFile(activity, saveImages, file, new MapiUtil.MapiSuccessResponse() {
            @Override
            public void success(JSONObject json) {
                DebugLog.i(json.toString());
                MapiImageResult imageResult = JSONObject.parseObject(json.getJSONObject("data").toJSONString(),MapiImageResult.class);
                callback.success(imageResult);
            }
        }, new MapiUtil.MapiFailResponse() {
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code, failMessage);
            }
        });
    }

    /**
     * 任务分配
     * @param activity
     * @param taskreceive  选的人的ID
     * @param ID           隐患ID
     * @param callback
     * @param exceptionCallback
     */
    public static void tasksend(Activity activity,String taskreceive,String ID,final RequestCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("taskreceive",taskreceive);
        params.put("ID",ID);
        MapiUtil.getInstance().call(activity,tasksend,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                callback.success(json);
            }
        },new MapiUtil.MapiFailResponse(){

            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 任务分配-转移
     * @param activity
     * @param ROLE      登录人的角色ID
     * @param ID        隐患ID
     * @param callback
     * @param exceptionCallback
     */
    public static void tasktransfer(Activity activity,String ROLE,String ID,final RequestCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("ROLE",ROLE);
        params.put("ID",ID);
        MapiUtil.getInstance().call(activity,tasktransfer,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                callback.success(json);
            }
        },new MapiUtil.MapiFailResponse(){

            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 我的任务-处理 /隐患档案-完成
     * @param activity
     * @param type     归档传4  需立案查处传3  隐患档案完成传4
     * @param ID       隐患ID
     * @param callback
     * @param exceptionCallback
     */
    public static void taskcomplete(Activity activity,String type,String ID,final RequestCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("type",type);
        params.put("ID",ID);
        MapiUtil.getInstance().call(activity,taskcomplete,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                callback.success(json);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 任务指令-无照上报
     * @param activity
     * @param taskreceive  选的人的ID
     * @param ID           隐患ID
     * @param callback
     * @param exceptionCallback
     */
    public static void tasksendNLS(Activity activity,String taskreceive,String ID,final RequestCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("taskreceive",taskreceive);
        params.put("ID",ID);
        MapiUtil.getInstance().call(activity,tasksendNLS,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                callback.success(json);
            }
        },new MapiUtil.MapiFailResponse(){

            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 我的任务-无照-完成
     * @param activity
     * @param ID           隐患ID
     * @param callback
     * @param exceptionCallback
     */
    public static void taskcompleteNLS(Activity activity,String ID,final RequestCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("ID",ID);
        MapiUtil.getInstance().call(activity,taskcompleteNLS,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                callback.success(json);
            }
        },new MapiUtil.MapiFailResponse(){

            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 任务指令-新增
     * @param activity
     * @param userid
     * @param remark
     * @param receives
     * @param callback
     * @param exceptionCallback
     */
    public static void addtask(Activity activity,String userid,String remark,String receives,final RequestCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("userid",userid);
        params.put("remark",remark);
        params.put("receives",receives);
        MapiUtil.getInstance().call(activity,addtask,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                callback.success(json);
            }
        },new MapiUtil.MapiFailResponse(){

            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 我的任务-其他任务-完成
     * @param activity
     * @param ID
     * @param callback
     * @param exceptionCallback
     */
    public static void Othertaskcomplete(Activity activity,String ID,final RequestCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("ID",ID);
        MapiUtil.getInstance().call(activity,Othertaskcomplete,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                callback.success(json);
            }
        },new MapiUtil.MapiFailResponse(){

            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 通知-新增
     * @param activity
     * @param userid
     * @param remark
     * @param receives
     * @param callback
     * @param exceptionCallback
     */
    public static void addMessage(Activity activity,String userid,String remark,String receives,final RequestCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("userid",userid);
        params.put("remark",remark);
        params.put("receives",receives);
        MapiUtil.getInstance().call(activity,addMessage,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                callback.success(json);
            }
        },new MapiUtil.MapiFailResponse(){

            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 预警-新增
     * @param activity
     * @param userid
     * @param remark
     * @param receives
     * @param callback
     * @param exceptionCallback
     */
    public static void addWarning(Activity activity,String userid,String remark,String receives,final RequestCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("userid",userid);
        params.put("remark",remark);
        params.put("receives",receives);
        MapiUtil.getInstance().call(activity,addWarning,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                callback.success(json);
            }
        },new MapiUtil.MapiFailResponse(){

            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 任务指令-退回
     * @param activity
     * @param ID        隐患ID
     * @param callback
     * @param exceptionCallback
     */
    public static void taskback(Activity activity,String ID,final RequestCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("ID",ID);
        MapiUtil.getInstance().call(activity,taskback,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                callback.success(json);
            }
        },new MapiUtil.MapiFailResponse(){

            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 修改密码
     * @param activity
     * @param USERNAME
     * @param PASSWORD
     * @param NEWPASSWORD
     * @param callback
     * @param exceptionCallback
     */
    public static void editPassword(Activity activity,String USER_ID,String USERNAME,String PASSWORD,String NEWPASSWORD,final RequestCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("USER_ID",USER_ID);
        params.put("USERNAME",USERNAME);
        params.put("PASSWORD",PASSWORD);
        params.put("NEWPASSWORD",NEWPASSWORD);
        MapiUtil.getInstance().call(activity,editPassword,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                callback.success(json);
            }
        },new MapiUtil.MapiFailResponse(){

            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }


}
