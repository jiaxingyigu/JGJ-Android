package com.yigu.jgj.util;

import com.yigu.jgj.commom.result.MapiResourceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brain on 2016/7/29.
 */
public class JGJDataSource {

    public static final String root_one_roleid = "9d0495a5ad044083903707e83b110baa";
    public static final String root_two_roleid = "fa77590189db4a12b6a18491a0d85b36";
    public static final String manage_roleid = "5978c776b7254fa0b0c87fd747695a48";
    public static final int TYPE_DAILY = 0x01;
    public static final int TYPE_COMPANY = 0x02;
    public static final int TYPE_TASK = 0x03;
    public static final int TYPE_ASSIGN = 0x04;
    public static final int TYPE_DANGER = 0x05;
    public static final int TYPE_PERSON = 0x06;
    public static final int TYPE_FILE = 0x07;
    /**
     * 获取所长/站长功能菜单
     * @return
     */
    public static List<MapiResourceResult> getRootResource(){
        List<MapiResourceResult> list = new ArrayList<>();
        list.add(new MapiResourceResult(TYPE_PERSON,"人员管理"));
        list.add(new MapiResourceResult(TYPE_COMPANY,"企业管理"));
        list.add(new MapiResourceResult(TYPE_DANGER,"隐患档案"));
        list.add(new MapiResourceResult(TYPE_ASSIGN,"任务分配"));
        list.add(new MapiResourceResult(TYPE_FILE,"归档信息"));
        return list;
    }
    /**
     * 获取管理员功能菜单
     * @return
     */
    public static List<MapiResourceResult> getManagerResource(){
        List<MapiResourceResult> list = new ArrayList<>();
        list.add(new MapiResourceResult(TYPE_PERSON,"人员管理"));
        list.add(new MapiResourceResult(TYPE_COMPANY,"企业管理"));
        list.add(new MapiResourceResult(TYPE_DANGER,"隐患档案"));
        list.add(new MapiResourceResult(TYPE_TASK,"我的任务"));
        list.add(new MapiResourceResult(TYPE_FILE,"归档信息"));
        return list;
    }

    /**
     * 获取网格员功能菜单
     * @return
     */
    public static List<MapiResourceResult> getOtherResource(){
        List<MapiResourceResult> list = new ArrayList<>();
        list.add(new MapiResourceResult(TYPE_DAILY,"日常巡查"));
        list.add(new MapiResourceResult(TYPE_PERSON,"人员管理"));
        list.add(new MapiResourceResult(TYPE_COMPANY,"企业管理"));
        list.add(new MapiResourceResult(TYPE_FILE,"归档信息"));
        return list;
    }

}
