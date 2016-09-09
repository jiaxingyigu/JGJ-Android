package com.yigu.jgj.commom.result;

import java.io.Serializable;

/**
 * Created by brain on 2016/8/27.
 */
public class MapiSepcialResult  implements Serializable {
    private Integer id;
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
    private String date;
    private String name;
    private String COMMUNITY;
    private String acname;

    public String getAcname() {
        return acname;
    }

    public void setAcname(String acname) {
        this.acname = acname;
    }

    public String getCOMMUNITY() {
        return COMMUNITY;
    }

    public void setCOMMUNITY(String COMMUNITY) {
        this.COMMUNITY = COMMUNITY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCbook() {
        return cbook;
    }

    public void setCbook(Integer cbook) {
        this.cbook = cbook;
    }

    public Integer getChaogu() {
        return chaogu;
    }

    public void setChaogu(Integer chaogu) {
        this.chaogu = chaogu;
    }

    public Integer getContraband() {
        return contraband;
    }

    public void setContraband(Integer contraband) {
        this.contraband = contraband;
    }

    public Integer getCorrection() {
        return correction;
    }

    public void setCorrection(Integer correction) {
        this.correction = correction;
    }

    public Integer getCscope() {
        return cscope;
    }

    public void setCscope(Integer cscope) {
        this.cscope = cscope;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Integer getRbook() {
        return rbook;
    }

    public void setRbook(Integer rbook) {
        this.rbook = rbook;
    }

    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getShop() {
        return shop;
    }

    public void setShop(Integer shop) {
        this.shop = shop;
    }
}
