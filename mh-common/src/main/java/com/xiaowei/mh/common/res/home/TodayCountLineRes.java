package com.xiaowei.mh.common.res.home;

import java.io.Serializable;

/**
 * created by 韩金群 2019/3/6
 */
public class TodayCountLineRes implements Serializable {
    private String name;
    private String rate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
