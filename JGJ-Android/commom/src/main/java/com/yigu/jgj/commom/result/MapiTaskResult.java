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
    private String LPERSON;

    private Integer type;

    private String name;

    private String date;

    private String acname;

    private Integer people;

    private Integer shop;

    private Integer cbook;
    private Integer rbook;
    private Integer nlshop;
    private Integer cscope;
    private Integer contraband;
    private String other1;

    private Integer correction;
    private Integer register;
    private Integer chaogu;
    private String other2;

    private String remark;

    private String stardate;

    private String enddate;

    private String sponsor;

    private String tel;

    private String cook;

    private String mealtime;
    private String attend;
    private String patient;
    private String utel;
    private String guidance;

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getGuidance() {
        return guidance;
    }

    public void setGuidance(String guidance) {
        this.guidance = guidance;
    }

    public String getMealtime() {
        return mealtime;
    }

    public void setMealtime(String mealtime) {
        this.mealtime = mealtime;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getUtel() {
        return utel;
    }

    public void setUtel(String utel) {
        this.utel = utel;
    }

    public String getCook() {
        return cook;
    }

    public void setCook(String cook) {
        this.cook = cook;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStardate() {
        return stardate;
    }

    public void setStardate(String stardate) {
        this.stardate = stardate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCorrection() {
        return correction;
    }

    public void setCorrection(Integer correction) {
        this.correction = correction;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }

    public Integer getChaogu() {
        return chaogu;
    }

    public void setChaogu(Integer chaogu) {
        this.chaogu = chaogu;
    }

    public Integer getCscope() {
        return cscope;
    }

    public void setCscope(Integer cscope) {
        this.cscope = cscope;
    }

    public Integer getNlshop() {
        return nlshop;
    }

    public void setNlshop(Integer nlshop) {
        this.nlshop = nlshop;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public Integer getContraband() {
        return contraband;
    }

    public void setContraband(Integer contraband) {
        this.contraband = contraband;
    }

    public Integer getRbook() {
        return rbook;
    }

    public void setRbook(Integer rbook) {
        this.rbook = rbook;
    }

    public Integer getCbook() {
        return cbook;
    }

    public void setCbook(Integer cbook) {
        this.cbook = cbook;
    }

    public Integer getShop() {
        return shop;
    }

    public void setShop(Integer shop) {
        this.shop = shop;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public String getAcname() {
        return acname;
    }

    public void setAcname(String acname) {
        this.acname = acname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private String NAME;

    private String ADDRESS;

    private Integer HCATEN;

    /*营业执照*/
    private Integer LICENSE;

    private Integer PEMIT;

    private String taskotime;
    private String TIMES;

    public String getTIMES() {
        return TIMES;
    }

    public void setTIMES(String TIMES) {
        this.TIMES = TIMES;
    }

    public Integer getPEMIT() {
        return PEMIT;
    }

    public void setPEMIT(Integer PEMIT) {
        this.PEMIT = PEMIT;
    }

    public Integer getLICENSE() {
        return LICENSE;
    }

    public void setLICENSE(Integer LICENSE) {
        this.LICENSE = LICENSE;
    }

    public Integer getHCATEN() {
        return HCATEN;
    }

    public void setHCATEN(Integer HCATEN) {
        this.HCATEN = HCATEN;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

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
