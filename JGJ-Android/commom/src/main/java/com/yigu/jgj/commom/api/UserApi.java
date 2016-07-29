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
                MapiUserResult result = JSONObject.parseObject(json.getJSONObject("data").toJSONString(),MapiUserResult.class);
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
     * @param callback
     * @param exceptionCallback
     */
    public static void getUserList(Activity activity, String USERNAME, String COMPANY, String COMMUNITY, String PAGENO, String SIZE, final RequestPageCallback callback, final RequestExceptionCallback exceptionCallback){

        Map<String,String> params = new HashMap<>();
        params.put("USERNAME",USERNAME);
        if(!TextUtils.isEmpty(COMPANY))
            params.put("COMPANY",COMPANY);
        if(!TextUtils.isEmpty(COMMUNITY))
            params.put("COMMUNITY",COMMUNITY);
        params.put("PAGENO",PAGENO);
        params.put("SIZE",SIZE);
        MapiUtil.getInstance().call(activity,getUserlist,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                List<MapiUserResult> result = JSONArray.parseArray(json.getJSONObject("data").getJSONArray("users").toJSONString(),MapiUserResult.class);
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
