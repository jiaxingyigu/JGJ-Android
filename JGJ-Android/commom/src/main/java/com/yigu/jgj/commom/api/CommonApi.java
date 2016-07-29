package com.yigu.jgj.commom.api;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yigu.jgj.commom.result.MapiResourceResult;
import com.yigu.jgj.commom.util.DebugLog;
import com.yigu.jgj.commom.util.MapiUtil;
import com.yigu.jgj.commom.util.RequestCallback;
import com.yigu.jgj.commom.util.RequestExceptionCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brain on 2016/7/22.
 */
public class CommonApi extends BasicApi{

    //导入资源
    public static void loadResources(final Activity act, final RequestCallback callback,
                                     final RequestExceptionCallback exceptionCallback) {
        Map<String, String> params = new HashMap<>();
        MapiUtil.getInstance().call(act,getMain, params,
                new MapiUtil.MapiSuccessResponse() {
                    @Override
                    public void success(JSONObject json) {
                        DebugLog.i("resourcejson"+json);
                       /* Map<String, ArrayList<MapiResourceResult>> userBean = JSON.parseObject(json
                                        .getJSONObject("data").toJSONString(),
                                new TypeReference<Map<String, ArrayList<MapiResourceResult>>>() {
                                });*/
                        callback.success(json.toJSONString());
                    }
                }, new MapiUtil.MapiFailResponse() {
                    @Override
                    public void fail(String code,String message) {
                        exceptionCallback.error(code,message);
                    }
                });
    }

}
