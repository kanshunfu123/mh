package com.xiaowei.mh.common.res.power;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/19
 */
public class AccessDeviceRes implements Serializable {
    private String month;
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
