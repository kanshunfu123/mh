package com.xiaowei.mh.mapper.result;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 韩金群
 * CreateTime 2019/1/16
 */
public class TodayFaultResult implements Serializable {
    /**
     * 故障编号
     */
    private String deviceNo;
    /**
     * 故障时间
     */
    private Date collectTime;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
}
