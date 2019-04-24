package com.xiaowei.mh.common.res.power;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/28
 */
public class StandardRateRes implements Serializable {
    private String yesRate;
    private String noRate;

    public String getYesRate() {
        return yesRate;
    }

    public void setYesRate(String yesRate) {
        this.yesRate = yesRate;
    }

    public String getNoRate() {
        return noRate;
    }

    public void setNoRate(String noRate) {
        this.noRate = noRate;
    }
}
