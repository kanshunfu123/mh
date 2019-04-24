package com.xiaowei.mh.common.res.v2;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/28
 */
public class FaultTypeRes implements Serializable {
    private String faultName;
    private String faultRate;

    public String getFaultName() {
        return faultName;
    }

    public void setFaultName(String faultName) {
        this.faultName = faultName;
    }

    public String getFaultRate() {
        return faultRate;
    }

    public void setFaultRate(String faultRate) {
        this.faultRate = faultRate;
    }
}
