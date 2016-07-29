package com.yigu.jgj.commom.result;

import java.io.Serializable;

/**
 * Created by brain on 2016/7/26.
 */
public class MapiImageResult implements Serializable{
    private String ID;
    private String PATH;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPATH() {
        return PATH;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }
}
