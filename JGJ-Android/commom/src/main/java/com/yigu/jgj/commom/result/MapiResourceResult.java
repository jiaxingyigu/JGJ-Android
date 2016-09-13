package com.yigu.jgj.commom.result;

import java.io.Serializable;

/**
 * Created by brain on 2016/7/22.
 */
public class MapiResourceResult implements Serializable {
    private String NAME;
    private String ZD_ID;
    private int id;

    public MapiResourceResult(){

    }
    public MapiResourceResult(int id,String NAME) {
        this.id = id;
        this.NAME = NAME;
    }

    public MapiResourceResult(String ZD_ID,String NAME) {
        this.ZD_ID = ZD_ID;
        this.NAME = NAME;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private boolean isCheck = false;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getZD_ID() {
        return ZD_ID;
    }

    public void setZD_ID(String ZD_ID) {
        this.ZD_ID = ZD_ID;
    }
}