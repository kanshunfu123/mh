package com.xiaowei.mh.common.req.fegin;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/19
 */
public class AccessDeviceReq implements Serializable {
    private String beginTime;
    private String endTime;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
