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
    private String senduser;
    private String taskstime;
    private String receiver;
    private String taskotime;
    private String LPERSON;

    public String getLPERSON() {
        return LPERSON;
    }

    public void setLPERSON(String LPERSON) {
        this.LPERSON = LPERSON;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSenduser() {
        return senduser;
    }

    public void setSenduser(String senduser) {
        this.senduser = senduser;
    }

    public String getTaskotime() {
        return taskotime;
    }

    public void setTaskotime(String taskotime) {
        this.taskotime = taskotime;
    }

    public String getTaskstime() {
        return taskstime;
    }

    public void setTaskstime(String taskstime) {
        this.taskstime = taskstime;
    }

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
