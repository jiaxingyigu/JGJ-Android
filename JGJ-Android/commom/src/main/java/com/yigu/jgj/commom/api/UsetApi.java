package com.yigu.jgj.commom.api;

import android.app.Activity;
import android.util.DebugUtils;

import com.alibaba.fastjson.JSONObject;
import com.yigu.jgj.commom.result.MapiUserResult;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.MapiUtil;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brain on 2016/6/16.
 */
public class UsetApi extends BasicApi{

    public static void login(Activity activity, String name, String psd, final RequestCallback callback, final RequestExceptionCallback exceptionCallback){
        Map<String,String> params = new HashMap<>();
        params.put("username",name);
        params.put("password",psd);
        MapiUtil.getInstance().call(activity,loginUrl,params,new MapiUtil.MapiSuccessResponse(){
            @Override
            public void success(JSONObject json) {
                DebugLog.i("json="+json);
                MapiUserResult result = JSONObject.parseObject(json.getJSONObject("data").toJSONString(),MapiUserResult.class);
                callback.success(result);
            }
        },new MapiUtil.MapiFailResponse(){

            @Override
            public void fail(Integer code, String failMessage) {
                exceptionCallback.error(code,failMessage);
            }
        });
    }

}
