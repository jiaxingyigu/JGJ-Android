package com.yigu.jgj.commom.api;

import android.app.Activity;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yigu.jgj.commom.result.MapiImageResult;
import com.yigu.jgj.commom.result.MapiItemResult;
import com.yigu.jgj.commom.result.MapiMsgResult;
import com.yigu.jgj.commom.result.MapiOtherResult;
import com.yigu.jgj.commom.result.MapiPartyResult;
import com.yigu.jgj.commom.result.MapiSepcialResult;
import com.yigu.jgj.commom.result.MapiTaskResult;
import com.yigu.jgj.commom.result.MapiUserResult;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.MapiUtil;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;
import com.yigu.jgj.commom.util.RequestPageCallback;
import com.yigu.jgj.commom.util.RequestPageTwoCallback;

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
                                   String SIZE, final RequestPageTwoCallback callback, final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("NAME",NAME);
        if(!TextUtils.isEmpty(COMMUNITY))
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
                Integer count = json.getJSONObject("data").getInteger("count");
                callback.success(ISNEXT,count,count,count,result);
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
                               String ADDRESS,String LPERSON,String CID,String HCATEN,String TEL,String CATEGORY,String TYPE,final RequestCallback callback, final RequestExceptionCallback exceptionCallback){
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
        params.put("CATEGORY",CATEGORY);
        params.put("TYPE",TYPE);
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
                               String ADDRESS,String LPERSON,String TEL,String CID,String HCATEN,String CATEGORY,String TYPE,final RequestCallback callback, final RequestExceptionCallback exceptionCallback){
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
        params.put("CATEGORY",CATEGORY);
        params.put("TYPE",TYPE);
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
     * @param PAGENO
     * @param SIZE
     * @param receiver
     * @param callback
     * @param exceptionCallback
     */
    public static void getDangerList(Activity activity,String NAME,String PAGENO,String SIZE,
                                     String receiver,String ROLE,final RequestPageTwoCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("type","3");
        params.put("NAME",NAME);
//        if(!TextUtils.isEmpty(COMPANY))
//            params.put("COMPANY",COMPANY);
//        if(!TextUtils.isEmpty(COMMUNITY))
//            params.put("COMMUNITY",COMMUNITY);
        if(!TextUtils.isEmpty(ROLE)){
            params.put("ROLE",ROLE);
        }else{
            params.put("receiver",receiver);
        }
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,getDailyPatrollist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiItemResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("lists").toJSONString(),MapiItemResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                Integer countld = json.getJSONObject("data").getInteger("count");
                callback.success(ISNEXT,countld,countld,countld,result);
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
     * @param COMMUNITY
     * @param PAGENO
     * @param SIZE
     * @param ROLE
     * @param callback
     * @param exceptionCallback
     */
    public static void getAssignList(Activity activity,String COMMUNITY, String ROLE,String PAGENO,String SIZE
                                    ,final RequestPageTwoCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("type","1");
        if(!TextUtils.isEmpty(COMMUNITY))
            params.put("COMMUNITY",COMMUNITY);
        params.put("ROLE",ROLE);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        DebugLog.i(params.toString());
        MapiUtil.getInstance().call(activity,getDailyPatrollist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiTaskResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("lists").toJSONString(),MapiTaskResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                Integer countld = json.getJSONObject("data").getInteger("count");
                callback.success(ISNEXT,countld,countld,countld,result);
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
     * @param COMMUNITY
     * @param startime
     * @param endtime
     * @param PAGENO
     * @param SIZE
     * @param callback
     * @param exceptionCallback
     */
    public static void getFileList(Activity activity,String type,String COMMUNITY,String startime,String endtime,String PAGENO,String SIZE,
                                     final RequestPageCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("type",type);
//        if(!TextUtils.isEmpty(COMPANY))
//            params.put("COMPANY",COMPANY);
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
     * @param receiver
     * @param PAGENO
     * @param SIZE
     * @param callback
     * @param exceptionCallback
     */
    public static void getTaskList(Activity activity, String receiver,String PAGENO,String SIZE
            ,final RequestPageTwoCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("type","2");
        params.put("receiver",receiver);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,getDailyPatrollist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiTaskResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("lists").toJSONString(),MapiTaskResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                Integer countld = json.getJSONObject("data").getInteger("count");
                callback.success(ISNEXT,countld,countld,countld,result);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 消息列表
     * @param activity
     * @param USER_ID
     * @param PAGENO
     * @param SIZE
     * @param callback
     * @param exceptionCallback
     */
    public static void getMessagelist(Activity activity,String USER_ID,String PAGENO,String SIZE,final RequestPageCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("USER_ID",USER_ID);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,getMessagelist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiMsgResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("messages").toJSONString(),MapiMsgResult.class);
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
     * 档案管理列表
     * @param activity
     * @param NAME
     * @param COMMUNITY
     * @param CATEGORY
     *              分类 1-餐饮 2-流通 3-生产  必填
     * @param TYPE
     *              A/B/C  可选填
     * @param flag
     *              1-完成 0-未完成  可选填
     * @param PAGENO
     * @param SIZE
     * @param callback
     * @param exceptionCallback
     */
    public static void getArchiveslist(Activity activity, String NAME, String COMMUNITY, String CATEGORY, String TYPE, String flag, String PAGENO, String SIZE,
                                       final RequestPageTwoCallback callback, final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("NAME",NAME);
        params.put("CATEGORY",CATEGORY);
        if(!TextUtils.isEmpty(TYPE))
            params.put("TYPE",TYPE);
        if(!TextUtils.isEmpty(COMMUNITY))
            params.put("COMMUNITY",COMMUNITY);
        if(!TextUtils.isEmpty(flag))
            params.put("flag",flag);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,getArchiveslist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiItemResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("shops").toJSONString(),MapiItemResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                Integer count = json.getJSONObject("data").getInteger("count");
                callback.success(ISNEXT,count,count,count,result);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 无照上报列表
     * @param activity
     * @param COMMUNITY
     * @param PAGENO
     * @param SIZE
     * @param callback
     * @param exceptionCallback
     */
    public static void getNlicenselist(Activity activity,String COMMUNITY,String PAGENO,String SIZE, final RequestPageTwoCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        if(!TextUtils.isEmpty(COMMUNITY))
            params.put("COMMUNITY",COMMUNITY);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,getNlicenselist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiItemResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("shops").toJSONString(),MapiItemResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                Integer count = json.getJSONObject("data").getInteger("count");
                callback.success(ISNEXT,count,count,count,result);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 新增无照上报
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
    public static void addNLShop(Activity activity, String FOODSALES, String FOODSERVICE,String CANTEEN,String LICENSE,String PEMIT,String NAME,
                               String ADDRESS,String LPERSON,String CID,String HCATEN,String TEL,String ROLE,String user,final RequestCallback callback, final RequestExceptionCallback exceptionCallback){
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
        params.put("ROLE",ROLE);
        params.put("user",user);
        MapiUtil.getInstance().call(activity,addNLShop,params,new MapiUtil.MapiSuccessResponse(){
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
     * 任务指令列表
     * @param activity
     * @param PAGENO
     * @param SIZE
     * @param callback
     * @param exceptionCallback
     */
    public static void getAssignlist(Activity activity,String ROLE,String receiver,String state,String PAGENO,String SIZE, final RequestPageTwoCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        if(!TextUtils.isEmpty(ROLE))
            params.put("ROLE",ROLE);
        if(!TextUtils.isEmpty(receiver))
            params.put("receiver",receiver);
        params.put("state",state);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,getNlicenselist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiItemResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("shops").toJSONString(),MapiItemResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                Integer countld = json.getJSONObject("data").getInteger("count");
                callback.success(ISNEXT,countld,countld,countld,result);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }


    /**
     * 专项行动-列表
     * @param activity
     * @param COMMUNITY
     * @param PAGENO
     * @param SIZE
     * @param callback
     * @param exceptionCallback
     */
    public static void specialactionlist(Activity activity,String COMMUNITY,String PAGENO,String SIZE, final RequestPageTwoCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        if(!TextUtils.isEmpty(COMMUNITY))
            params.put("COMMUNITY",COMMUNITY);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,specialactionlist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiSepcialResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("specialaction").toJSONString(),MapiSepcialResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                Integer countld = json.getJSONObject("data").getInteger("count");
                callback.success(ISNEXT,countld,countld,countld,result);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 专项行动-新增
     * @param activity
     * @param userid
     * @param CID
     * @param date
     * @param people
     * @param shop
     * @param cbook
     * @param rbook
     * @param nlshop
     * @param cscope
     * @param contraband
     * @param other1
     * @param correction
     * @param register
     * @param chaogu
     * @param other2
     * @param remark
     * @param callback
     * @param exceptionCallback
     */
    public static void addSpecialaction(Activity activity,String userid,String CID,String date,String name,String people,String shop,String cbook,String rbook,
                                        String nlshop,String cscope,String contraband,String other1,String correction,String register,
                                        String chaogu,String other2,String remark,final RequestCallback callback, final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("userid",userid);
        if(!TextUtils.isEmpty(CID))
            params.put("CID",CID);
        params.put("date",date);
        params.put("name",name);
        params.put("people",people);
        params.put("shop",shop);
        params.put("cbook",cbook);
        params.put("rbook",rbook);
        params.put("nlshop",nlshop);
        params.put("cscope",cscope);
        params.put("contraband",contraband);
        params.put("other1",other1);
        params.put("correction",correction);
        params.put("register",register);
        params.put("chaogu",chaogu);
        params.put("other2",other2);
        params.put("remark",remark);

        MapiUtil.getInstance().call(activity,addSpecialaction,params,new MapiUtil.MapiSuccessResponse(){
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
     * 我的任务-其他-列表
     * @param activity
     * @param userid
     * @param PAGENO
     * @param SIZE
     * @param callback
     * @param exceptionCallback
     */
    public static void getOthertask(Activity activity,String userid,String PAGENO,String SIZE, final RequestPageTwoCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("userid",userid);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        DebugLog.i(params.toString()+"");
        MapiUtil.getInstance().call(activity,getOthertask,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiOtherResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("tasks").toJSONString(),MapiOtherResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                Integer countld = json.getJSONObject("data").getInteger("count");
                callback.success(ISNEXT,countld,countld,countld,result);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 预警列表列表
     * @param activity
     * @param USER_ID
     * @param PAGENO
     * @param SIZE
     * @param callback
     * @param exceptionCallback
     */
    public static void getWarninglist(Activity activity,String USER_ID,String PAGENO,String SIZE,final RequestPageCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("USER_ID",USER_ID);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,getWarninglist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiMsgResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("messages").toJSONString(),MapiMsgResult.class);
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
     * 聚餐管理-新增
     * @param activity
     * @param stardate
     * @param enddate
     * @param sponsor
     * @param tel
     * @param cook
     * @param CID
     * @param address
     * @param mealtime
     * @param attend
     * @param patient
     * @param userid
     * @param utel
     * @param guidance
     * @param callback
     * @param exceptionCallback
     */
    public static void addDinner(Activity activity,String stardate,String enddate,String sponsor,String tel,String cook,String CID,
                                 String address,String mealtime,String attend,String patient,String userid,String utel,String guidance,
                                 final RequestCallback callback,final RequestExceptionCallback exceptionCallback ){
        Map<String,String> params = new HashMap<>();
        params.put("stardate",stardate);
        params.put("enddate",enddate);
        params.put("sponsor",sponsor);
        params.put("tel",tel);
        params.put("cook",cook);
        if(!TextUtils.isEmpty(CID))
            params.put("CID",CID);
        params.put("address",address);
        params.put("mealtime",mealtime);
        params.put("attend",attend);
        params.put("patient",patient);
        params.put("userid",userid);
        params.put("utel",utel);
        params.put("guidance",guidance);
        MapiUtil.getInstance().call(activity,addDinner,params,new MapiUtil.MapiSuccessResponse(){
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
     * 聚餐管理-列表
     * @param activity
     * @param COMMUNITY
     * @param PAGENO
     * @param SIZE
     * @param callback
     * @param exceptionCallback
     */
    public static void Dinnerlist(Activity activity,String COMMUNITY,String PAGENO,String SIZE, final RequestPageTwoCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        if(!TextUtils.isEmpty(COMMUNITY))
            params.put("COMMUNITY",COMMUNITY);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,Dinnerlist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiPartyResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("dinner").toJSONString(),MapiPartyResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                Integer countld = json.getJSONObject("data").getInteger("count");
                callback.success(ISNEXT,countld,countld,countld,result);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }


    /**
     * 数据查询
     * @param activity
     * @param type
     *                    0-日常巡查 1-隐患 2-无照上报 3-专项行动 4-聚餐上报    都是归档数据
     * @param COMMUNITY
     * @param startime
     * @param PAGENO
     * @param SIZE
     * @param callback
     * @param exceptionCallback
     */
    public static void GetHisData(Activity activity,String type,String COMMUNITY,String NAME,String startime,String PAGENO,String SIZE,
                                   final RequestPageTwoCallback callback,final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("type",type);
        if(!TextUtils.isEmpty(COMMUNITY))
            params.put("COMMUNITY",COMMUNITY);
        params.put("NAME",NAME);
        if(!TextUtils.isEmpty(startime))
            params.put("startime",startime);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,GetHisData,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiTaskResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("lists").toJSONString(),MapiTaskResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                Integer count = json.getJSONObject("data").getInteger("count");
                callback.success(ISNEXT,count,count,count,result);
            }
        },new MapiUtil.MapiFailResponse(){
            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

}
