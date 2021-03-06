package com.yigu.jgj.commom.util;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yigu.jgj.commom.api.BasicApi;
import com.yigu.jgj.commom.application.AppContext;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 *  Created by brain on 2016/6/14.
 */
public class MapiUtil {
    private volatile static MapiUtil mapiUtil;
    private  Map<String, String> head = null;
    private RequestQueue requestQueue;

    public static  MapiUtil getInstance() {
        if (mapiUtil == null) {
            synchronized (MapiUtil.class) {
                mapiUtil = new MapiUtil();
            }
        }
        return mapiUtil;
    }

    private MapiUtil() {
        requestQueue = Volley.newRequestQueue(AppContext.getInstance());
    }


    /**
     * volley get方式
     *
     * @param activity
     * @param url
     * @param response
     * @param fail
     */
    public void getCall(Activity activity, String url, final MapiSuccessResponse response, final MapiFailResponse fail) {
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        DebugLog.i("mapi response" + s);
                        JSONObject jsonObject = JSONObject.parseObject(s);
                        response.success(jsonObject);
                        Integer code = jsonObject.getInteger("errcode");
                        if (code != null) {
                            fail.fail(code, jsonObject.getString("errmsg"));
                        }


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DebugLog.e("error=" + error);
            }
        }){
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                return super.parseNetworkResponse(response);
                String str = null;
                try {
                    str = new String(response.data, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Response.success(str,
                        HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        requestQueue.add(stringRequest);
    }

    /**
     * 网络通信volley
     *
     * @param act      类名，广播
     * @param url      接口地址
     * @param params   传递的参数
     * @param response 成功返回数据的接口
     * @param fail     返回异常的接口
     */
    public void call(final Activity act, final String url, final Map<String, String> params,
                     final MapiSuccessResponse response, final MapiFailResponse fail) {
        if (params != null)
//            DebugLog.i("params=" + params.toString());
        DebugLog.i("url=" + BasicApi.BASIC_URL + url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BasicApi.BASIC_URL + url,
                new Response.Listener<String>() {
                    public void onResponse(String s) {
                        DebugLog.i("mapi response" + s);
                        JSONObject jsonObject = JSONObject.parseObject(s);
                        if (jsonObject.getInteger("code") == 0) {
                            response.success(jsonObject);
                        }
                        Integer code = jsonObject.getInteger("code");
                        if (code == 9998) {
                            //打开登录UI
                            if (act == null) {
                                return;
                            }
                            Intent intent = new Intent();
                            intent.setAction("com.ypn.mobile.login");
                            act.sendBroadcast(intent);
                            return;
                        }
                        if (fail != null && code != 0) {
                            fail.fail(code, jsonObject.getString("message"));//参数不满足条件
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                DebugLog.e("volleyError=" + volleyError);
                if (volleyError != null) {
                    if (volleyError instanceof TimeoutError || volleyError instanceof NoConnectionError) {
                        fail.fail(9999, "oops！网络异常请重新连接");
                    } else {
                        fail.fail(9999, volleyError.getMessage());
                    }
                }
            }
        }) /*{
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> heads = initHead();

                return heads;
            }
        }*/;
        requestQueue.add(stringRequest);
//        requestQueue.start();//初始化的时候就已经调用
    }

   /* public Map<String, String> initHead() {
        if (head == null) {
            head = new HashMap<>();
            //参考 http://wiki.fredzhu.com/bin/view/Main/HTTP%E4%BA%A4%E4%BA%92%E8%AF%B4%E6%98%8E 说明设置值
            head.put(Constant.APPKEY, Constant.APPKEY_VALUE);
            head.put(Constant.PLATFORM, Constant.PLATFORM_VALUE);
            head.put(Constant.VERSION, Constant.VERSION_VALUE);
            head.put(Constant.NETWORK_TYPE_KEY, Constant.NETWORK_TYPE);
        }
        UserSP sp = new UserSP(AppContext.getInstance());
        MapiUserResult user = sp.getUserBean();
        if (user != null) {
            head.put(Constant.USER_ID, user.getId().toString());
            DebugLog.i("session=" + user.getSession() + "userId=" + user.getId());
            head.put(Constant.USER_SESSION, user.getSession());
        } else {
            head.put(Constant.USER_ID, "");
            head.put(Constant.USER_SESSION, "");
        }
        return head;
    }*/

    public interface MapiSuccessResponse {

        void success(JSONObject json);

    }

    public interface MapiFailResponse {

        void fail(Integer code, String failMessage);

    }
}
