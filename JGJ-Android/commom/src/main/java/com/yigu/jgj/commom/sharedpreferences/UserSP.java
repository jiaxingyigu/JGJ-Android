package com.yigu.jgj.commom.sharedpreferences;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.yigu.jgj.commom.result.MapiUserResult;

/**
 * Created by brain on 2016/6/14.
 */
public class UserSP extends AbstractSP {

    private final static String KEY_SP_USER = "fx.user";
    private final static String KEY_SP_USER_GUIDE = "user_guide";

    public UserSP(Context context) {
        super(context);
    }

    public void saveUserBean(MapiUserResult userbean) {
        sharedPreferences.edit().putString(KEY_SP_USER, JSONObject.toJSONString(userbean)).commit();
    }

    public MapiUserResult getUserBean() {
        String userJsonStr = sharedPreferences.getString(KEY_SP_USER, null);
        if (TextUtils.isEmpty(userJsonStr)) {
            return null;
        }
        return JSONObject.parseObject(userJsonStr, MapiUserResult.class);
    }

    public boolean checkLogin() {
        return getUserBean() != null && !TextUtils.isEmpty(getUserBean().getSession());
    }

    public void clearUserData() {
        sharedPreferences.edit().remove(KEY_SP_USER).commit();
    }

    /**
     * 保存版本
     *
     * @param value
     */
    public void saveUserGuide(String value) {
        sharedPreferences.edit().putString(KEY_SP_USER_GUIDE, value).commit();
    }

    /**
     * 获取版本
     *
     * @return
     */
    public String getUserGuide() {
        String code = sharedPreferences.getString(KEY_SP_USER_GUIDE, null);
        if (TextUtils.isEmpty(code)) {
            return null;
        }
        return code;
    }

}
