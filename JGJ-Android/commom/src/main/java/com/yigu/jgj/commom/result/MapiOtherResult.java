package com.yigu.jgj.commom.result;

import java.io.Serializable;

/**
 * Created by brain on 2016/8/28.
 */
public class MapiOtherResult implements Serializable{
    private String id;
    private String times;
    private String remark;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}
