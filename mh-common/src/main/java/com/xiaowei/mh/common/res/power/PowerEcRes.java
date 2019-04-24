package com.xiaowei.mh.common.res.power;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/15
 */
public class PowerEcRes implements Serializable {
    private String atPowerRateMax;
    private String atPowerRateMin;
    private String ctPowerRateMax;
    private String ctPowerRateMin;

    public String getAtPowerRateMax() {
        return atPowerRateMax;
    }

    public void setAtPowerRateMax(String atPowerRateMax) {
        this.atPowerRateMax = atPowerRateMax;
    }

    public String getAtPowerRateMin() {
        return atPowerRateMin;
    }

    public void setAtPowerRateMin(String atPowerRateMin) {
        this.atPowerRateMin = atPowerRateMin;
    }

    public String getCtPowerRateMax() {
        return ctPowerRateMax;
    }

    public void setCtPowerRateMax(String ctPowerRateMax) {
        this.ctPowerRateMax = ctPowerRateMax;
    }

    public String getCtPowerRateMin() {
        return ctPowerRateMin;
    }

    public void setCtPowerRateMin(String ctPowerRateMin) {
        this.ctPowerRateMin = ctPowerRateMin;
    }
}
