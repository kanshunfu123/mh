package com.xiaowei.mh.common.res.home;

import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/21
 */
public class MonthCountLineRes implements Serializable {
    private String online;
    private String offline;
    private String faultline;

    public String getFaultline() {
        return faultline;
    }

    public void setFaultline(String faultline) {
        this.faultline = faultline;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getOffline() {
        return offline;
    }

    public void setOffline(String offline) {
        this.offline = offline;
    }
}
