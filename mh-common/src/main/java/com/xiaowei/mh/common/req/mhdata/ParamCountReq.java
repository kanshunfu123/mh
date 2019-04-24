package com.xiaowei.mh.common.req.mhdata;

import com.xiaowei.mh.common.error.BaseReq;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/14
 */
public class ParamCountReq extends BaseReq implements Serializable {
    /**
     * 设备编号
     */
    @NotBlank(message = "deviceNo不能为空", groups = {Query.class})
    private String deviceNo;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
