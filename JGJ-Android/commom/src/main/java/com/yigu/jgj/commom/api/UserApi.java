package com.yigu.jgj.commom.api;

import android.app.Activity;
import android.text.TextUtils;
import android.util.DebugUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
 * Created by brain on 2016/6/16.
 */
public class UserApi extends BasicApi{

    public static void login(Activity activity, String name, String psd, final RequestCallback callback, final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("USERNAME",name);
        params.put("PASSWORD",psd);
        MapiUtil.getInstance().call(activity,loginUrl,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                MapiUserResult result = null;
                try{
                    result = JSONObject.parseObject(json.getJSONObject("data").toJSONString(),MapiUserResult.class);
                }catch (Exception e){

                }finally {
                    callback.success(result);
                }

            }
        },new MapiUtil.MapiFailResponse(){

            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

    /**
     * 员工列表
     * @param activity
     * @param USERNAME　　
     *
     * @param COMPANY
     *      　        单位ID  可选填
     * @param COMMUNITY
     *                社区ID 可选填
     * @param PAGENO
     * 　　　　　　　页码 必填
     * @param ROLE_ID 任务分配时用
     * @param callback
     * @param exceptionCallback
     */
    public static void getUserList(Activity activity, String USERNAME, String COMPANY, String COMMUNITY, String ROLE_ID, String PAGENO, String SIZE, final RequestPageTwoCallback callback, final RequestExceptionCallback exceptionCallback){

        Map<String,String> params = new HashMap<>();
        params.put("USERNAME",USERNAME);
        if(!TextUtils.isEmpty(COMPANY))
            params.put("COMPANY",COMPANY);
        if(!TextUtils.isEmpty(COMMUNITY))
            params.put("COMMUNITY",COMMUNITY);
        if(!TextUtils.isEmpty(ROLE_ID))
            params.put("ROLE_ID",ROLE_ID);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,getUserlist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiUserResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("users").toJSONString(),MapiUserResult.class);
                Integer ISNEXT = json.getJSONObject("data").getInteger("ISNEXT");
                Integer countld = json.getJSONObject("data").getInteger("countld");
                Integer countone = json.getJSONObject("data").getInteger("countone");
                Integer counttwo = json.getJSONObject("data").getInteger("counttwo");
                callback.success(ISNEXT,countld,countone,counttwo,result);
            }
        },new MapiUtil.MapiFailResponse(){

            @Override
            public void fail(String code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });

    }

    /**
     * 人员下拉选择接口
     * @param activity
     * @param type
     *          0=全部  1=本单位管理层+网格员 需要传COMPANY 登录人单位    2=所有网格员
     * @param callback
     * @param exceptionCallback
     */
    public static void getdropdownlist(Activity activity,String type,String COMPANY,final RequestPageCallback callback, final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("type",type);
        if(!TextUtils.isEmpty(COMPANY))
            params.put("COMPANY",COMPANY);
        MapiUtil.getInstance().call(activity,getdropdownlist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiUserResult> result = JSONArray.parseArray(json.getJSONArray("data").toJSONString(),MapiUserResult.class);
                Integer ISNEXT = json.getInteger("ISNEXT");
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
