package com.xiaowei.mh.common.res.home;

import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/21
 */
public class LatestDeviceRes implements Serializable {
    private String deviceNo;
    private String address;
    private String setupTime;
    private String type;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(String setupTime) {
        this.setupTime = setupTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
