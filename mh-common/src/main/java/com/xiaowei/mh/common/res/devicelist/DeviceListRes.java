package com.xiaowei.mh.common.res.devicelist;

import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/18
 */
public class DeviceListRes implements Serializable {
    //设备编号
    private String deviceNo;
    //上部展示
    private String topShow;
    //下部展示
    private String lowerShow;
    //设备状态 1在线 2离线 3故障
    private Integer status;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getTopShow() {
        return topShow;
    }

    public void setTopShow(String topShow) {
        this.topShow = topShow;
    }

    public String getLowerShow() {
        return lowerShow;
    }

    public void setLowerShow(String lowerShow) {
        this.lowerShow = lowerShow;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
