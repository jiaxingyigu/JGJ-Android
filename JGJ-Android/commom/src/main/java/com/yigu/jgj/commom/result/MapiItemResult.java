package com.yigu.jgj.commom.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by brain on 2016/7/23.
 */
public class MapiItemResult implements Serializable {
    private String ID;
    /*食品销售*/
    private Integer FOODSALES;
    /*餐饮服务*/
    private Integer FOODSERVICE;
    /*单位食堂*/
    private Integer CANTEEN;
    /*营业执照*/
    private Integer LICENSE;
    private Integer PEMIT;

    private String NAME;
    private String COMMUNITY;
    private String LPERSON;
    private String TEL;
    private String ADDRESS;
    private Integer HCATEN;

    private Integer ptioners;
    private Integer showlicense;
    private Integer hygiene;
    private Integer invoice;
    private Integer sanitation;
    private Integer overdue;
    private Integer fullmark;
    private Integer train;
    private Integer disinfection;
    private Integer poster;
    private String remark;
    private String shopname;
    private String idate;

    public String getIdate() {
        return idate;
    }

    public void setIdate(String idate) {
        this.idate = idate;
    }

    private List<MapiImageResult> images;

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public List<MapiImageResult> getImages() {
        return images;
    }

    public void setImages(List<MapiImageResult> images) {
        this.images = images;
    }

    public Integer getPtioners() {
        return ptioners;
    }

    public void setPtioners(Integer ptioners) {
        this.ptioners = ptioners;
    }

    public Integer getDisinfection() {
        return disinfection;
    }

    public void setDisinfection(Integer disinfection) {
        this.disinfection = disinfection;
    }

    public Integer getFullmark() {
        return fullmark;
    }

    public void setFullmark(Integer fullmark) {
        this.fullmark = fullmark;
    }

    public Integer getHygiene() {
        return hygiene;
    }

    public void setHygiene(Integer hygiene) {
        this.hygiene = hygiene;
    }

    public Integer getInvoice() {
        return invoice;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public Integer getOverdue() {
        return overdue;
    }

    public void setOverdue(Integer overdue) {
        this.overdue = overdue;
    }

    public Integer getPoster() {
        return poster;
    }

    public void setPoster(Integer poster) {
        this.poster = poster;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSanitation() {
        return sanitation;
    }

    public void setSanitation(Integer sanitation) {
        this.sanitation = sanitation;
    }

    public Integer getShowlicense() {
        return showlicense;
    }

    public void setShowlicense(Integer showlicense) {
        this.showlicense = showlicense;
    }

    public Integer getTrain() {
        return train;
    }

    public void setTrain(Integer train) {
        this.train = train;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getLICENSE() {
        return LICENSE;
    }

    public void setLICENSE(Integer LICENSE) {
        this.LICENSE = LICENSE;
    }

    public Integer getPEMIT() {
        return PEMIT;
    }

    public void setPEMIT(Integer PEMIT) {
        this.PEMIT = PEMIT;
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

    public String getLPERSON() {
        return LPERSON;
    }

    public void setLPERSON(String LPERSON) {
        this.LPERSON = LPERSON;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }
}
