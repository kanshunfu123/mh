package com.xiaowei.mh.common.res.v2;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/28
 */
public class MhDiameterRes implements Serializable {
    private String diameter;
    private String rate;

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
