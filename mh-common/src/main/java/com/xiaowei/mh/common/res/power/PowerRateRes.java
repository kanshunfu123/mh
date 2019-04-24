package com.xiaowei.mh.common.res.power;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/15
 */
public class PowerRateRes implements Serializable {
    private String powerMax;
    private String powerMin;

    public String getPowerMax() {
        return powerMax;
    }

    public void setPowerMax(String powerMax) {
        this.powerMax = powerMax;
    }

    public String getPowerMin() {
        return powerMin;
    }

    public void setPowerMin(String powerMin) {
        this.powerMin = powerMin;
    }
}
