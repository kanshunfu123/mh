package com.xiaowei.mh.common.res.home;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/27
 */
public class SafeCountRes implements Serializable {
    /**
     * 月份
     * */
    private String month;
    /**
     * 数量
     * */
    private Integer number;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
