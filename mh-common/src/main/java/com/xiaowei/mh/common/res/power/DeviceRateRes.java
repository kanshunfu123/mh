package com.xiaowei.mh.common.res.power;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/19
 */
public class DeviceRateRes implements Serializable {
    private Integer num;
    private String rate;
    private String name;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
