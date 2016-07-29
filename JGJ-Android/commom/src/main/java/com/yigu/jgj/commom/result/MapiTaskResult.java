package com.yigu.jgj.commom.result;

import java.io.Serializable;

/**
 * Created by brain on 2016/7/28.
 */
public class MapiTaskResult implements Serializable{
    private String idate;
    private String address;
    private String TEL;
    private String COMMUNITY;
    private Integer FOODSALES;
    private Integer FOODSERVICE;
    private Integer CANTEEN;
    private String ID;
    private String shopname;
    private String user;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCANTEEN() {
        return CANTEEN;
    }

    public void setCANTEEN(Integer CANTEEN) {
        this.CANTEEN = CANTEEN;
    }

    public String getCOMMUNITY() {
        return COMMUNITY;
    }

    public void setCOMMUNITY(String COMMUNITY) {
        this.COMMUNITY = COMMUNITY;
    }

    public Integer getFOODSALES() {
        return FOODSALES;
    }

    public void setFOODSALES(Integer FOODSALES) {
        this.FOODSALES = FOODSALES;
    }

    public Integer getFOODSERVICE() {
        return FOODSERVICE;
    }

    public void setFOODSERVICE(Integer FOODSERVICE) {
        this.FOODSERVICE = FOODSERVICE;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIdate() {
        return idate;
    }

    public void setIdate(String idate) {
        this.idate = idate;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
