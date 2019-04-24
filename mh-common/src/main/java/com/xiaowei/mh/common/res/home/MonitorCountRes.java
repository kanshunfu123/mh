package com.xiaowei.mh.common.res.home;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/15
 */
public class MonitorCountRes implements Serializable {
    private String month;
    private String online;
    private String offline;
    private String faultline;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public String getFaultline() {
        return faultline;
    }

    public void setFaultline(String faultline) {
        this.faultline = faultline;
    }
}
