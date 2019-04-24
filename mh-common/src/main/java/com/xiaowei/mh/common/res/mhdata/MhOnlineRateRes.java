package com.xiaowei.mh.common.res.mhdata;

import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/14
 */
public class MhOnlineRateRes implements Serializable {
    //在线率
    private String online;
    //离线率
    private String offline;

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
