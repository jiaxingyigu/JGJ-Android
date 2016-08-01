package com.yigu.jgj.commom.api;

import android.app.Activity;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yigu.jgj.commom.result.MapiImageResult;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiTaskResult;
import com.yigu.jgj.commom.result.MapiUserResult;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.MapiUtil;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.util.RequestPageCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brain on 2016/7/23.
 */
public class ItemApi extends BasicApi{

    /**
     * 企业列表
     * @param activity
     * @param NAME
     * @param COMMUNITY
     * @param Illegal
     * @param PAGENO
     * @param callback
     * @param exceptionCallback
     */
    public static void getShoplist(Activity activity, String NAME, String COMMUNITY, String Illegal, String PAGENO,
                                   String SIZE, final RequestPageCallback callback, final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("NAME",NAME);
        params.put("COMMUNITY",COMMUNITY);
        params.put("illegal",Illegal);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,getShoplist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiItemResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("shops").toJSONString(),MapiItemResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                callback.success(ISNEXT,result);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 新增企业
     * @param activity
     * @param FOODSALES
     * @param FOODSERVICE
     * @param CANTEEN
     * @param LICENSE
     * @param PEMIT
     * @param NAME
     * @param ADDRESS
     * @param LPERSON
     * @param CID
     * @param HCATEN
     * @param callback
     * @param exceptionCallback
     */
    public static void addShop(Activity activity, String FOODSALES, String FOODSERVICE,String CANTEEN,String LICENSE,String PEMIT,String NAME,
                               String ADDRESS,String LPERSON,String CID,String HCATEN,String TEL,final RequestCallback callback, final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("FOODSALES",FOODSALES);
        params.put("FOODSERVICE",FOODSERVICE);
        params.put("CANTEEN",CANTEEN);
        params.put("LICENSE",LICENSE);
        params.put("PEMIT",PEMIT);
        params.put("NAME",NAME);
        params.put("ADDRESS",ADDRESS);
        params.put("LPERSON",LPERSON);
        params.put("CID",CID);
        params.put("HCATEN",HCATEN);
        params.put("TEL",TEL);
        MapiUtil.getInstance().call(activity,addShop,params,new MapiUtil.MapiSuccessResponse(){
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
     * 修改企业
     * @param activity
     * @param ID
     * @param FOODSALES
     * @param FOODSERVICE
     * @param CANTEEN
     * @param LICENSE
     * @param PEMIT
     * @param NAME
     * @param ADDRESS
     * @param LPERSON
     * @param CID
     * @param HCATEN
     * @param callback
     * @param exceptionCallback
     */
    public static void editShop(Activity activity, String ID,String FOODSALES, String FOODSERVICE,String CANTEEN,String LICENSE,String PEMIT,String NAME,
                               String ADDRESS,String LPERSON,String TEL,String CID,String HCATEN,final RequestCallback callback, final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("ID",ID);
        params.put("FOODSALES",FOODSALES);
        params.put("FOODSERVICE",FOODSERVICE);
        params.put("CANTEEN",CANTEEN);
        params.put("LICENSE",LICENSE);
        params.put("PEMIT",PEMIT);
        params.put("NAME",NAME);
        params.put("ADDRESS",ADDRESS);
        params.put("LPERSON",LPERSON);
        params.put("TEL",TEL);
        params.put("CID",CID);
        params.put("HCATEN",HCATEN);
        MapiUtil.getInstance().call(activity,editShop,params,new MapiUtil.MapiSuccessResponse(){
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
     * 隐患档案-列表
     * @param activity
     * @param COMPANY
     * @param COMMUNITY
     * @param PAGENO
     * @param SIZE
     * @param receiver
     * @param callback
     * @param exceptionCallback
     */
    public static void getDangerList(Activity activity,String COMPANY,String COMMUNITY,String PAGENO,String SIZE,
                                     String receiver,final RequestPageCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("type","3");
        if(!TextUtils.isEmpty(COMPANY))
            params.put("COMPANY",COMPANY);
        if(!TextUtils.isEmpty(COMMUNITY))
            params.put("COMMUNITY",COMMUNITY);
        params.put("receiver",receiver);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,getDailyPatrollist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiItemResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("lists").toJSONString(),MapiItemResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                callback.success(ISNEXT,result);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 日常巡查隐患详情
     * @param activity
     * @param ID
     * @param callback
     * @param exceptionCallback
     */
    public static void dailyPatrolDetails(Activity activity,String ID,final RequestCallback callback, final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("ID",ID);
        MapiUtil.getInstance().call(activity,dailyPatrolDetails,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                MapiItemResult result = JSONObject.parseObject(json.getJSONObject("data").getJSONObject("details").toJSONString(),MapiItemResult.class);
                List<MapiImageResult> images =  JSONArray.parseArray(json.getJSONObject("data").getJSONArray("images").toJSONString(),MapiImageResult.class);
                if(!images.isEmpty())
                    result.setImages(images);
                callback.success(result);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     *任务分配-列表
     * @param activity
     * @param COMPANY
     * @param COMMUNITY
     * @param PAGENO
     * @param SIZE
     * @param ROLE
     * @param callback
     * @param exceptionCallback
     */
    public static void getAssignList(Activity activity,String COMPANY,String COMMUNITY, String ROLE,String PAGENO,String SIZE
                                    ,final RequestPageCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("type","1");
        if(!TextUtils.isEmpty(COMPANY))
            params.put("COMPANY",COMPANY);
        if(!TextUtils.isEmpty(COMMUNITY))
            params.put("COMMUNITY",COMMUNITY);
        params.put("ROLE",ROLE);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,getDailyPatrollist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiTaskResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("lists").toJSONString(),MapiTaskResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                callback.success(ISNEXT,result);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }


    /**
     * 归档列表
     * @param activity
     * @param type
     *                     0=日常巡查（用于归档日常巡查列表）
     *                     4=已归档隐患（用于归档）
     * @param COMPANY
     * @param COMMUNITY
     * @param startime
     * @param endtime
     * @param PAGENO
     * @param SIZE
     * @param callback
     * @param exceptionCallback
     */
    public static void getFileList(Activity activity,String type,String COMPANY,String COMMUNITY,String startime,String endtime,String PAGENO,String SIZE,
                                     final RequestPageCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("type",type);
        if(!TextUtils.isEmpty(COMPANY))
            params.put("COMPANY",COMPANY);
        if(!TextUtils.isEmpty(COMMUNITY))
            params.put("COMMUNITY",COMMUNITY);
        if(!TextUtils.isEmpty(startime))
             params.put("startime",startime);
        if(!TextUtils.isEmpty(endtime))
             params.put("endtime",endtime);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,getDailyPatrollist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiTaskResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("lists").toJSONString(),MapiTaskResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                callback.success(ISNEXT,result);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 我的任务-列表
     * @param activity
     * @param COMPANY
     * @param COMMUNITY
     * @param receiver
     * @param PAGENO
     * @param SIZE
     * @param callback
     * @param exceptionCallback
     */
    public static void getTaskList(Activity activity,String COMPANY,String COMMUNITY, String receiver,String PAGENO,String SIZE
            ,final RequestPageCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("type","2");
        if(!TextUtils.isEmpty(COMPANY))
            params.put("COMPANY",COMPANY);
        if(!TextUtils.isEmpty(COMMUNITY))
            params.put("COMMUNITY",COMMUNITY);
        params.put("receiver",receiver);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,getDailyPatrollist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiTaskResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("lists").toJSONString(),MapiTaskResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                callback.success(ISNEXT,result);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }



}
