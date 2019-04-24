package com.xiaowei.mh.common.res.power;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/18
 */
public class PowerOtherRes implements Serializable {
    private String powerRateMax;
    private String powerRateMin;

    public String getPowerRateMax() {
        return powerRateMax;
    }

    public void setPowerRateMax(String powerRateMax) {
        this.powerRateMax = powerRateMax;
    }

    public String getPowerRateMin() {
        return powerRateMin;
    }

    public void setPowerRateMin(String powerRateMin) {
        this.powerRateMin = powerRateMin;
    }
}
