package com.xiaowei.mh.common.res.home;

import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/19
 */
public class FaultRankingRes implements Serializable {
    //设备编号
    private String deviceNo;
    //安装地址
    private String address;
    //设备类型
    private String type;
    //故障次数
    private Integer number;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
