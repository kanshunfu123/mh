package com.xiaowei.mh.common.req.mhdata;

import com.xiaowei.mh.common.error.BaseReq;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/12
 */
public class MhHistoryPageReq extends BaseReq implements Serializable {
    /**
     * 设备编号
     */
    @NotBlank(message = "deviceNo不能为空", groups = {Query.class})
    private String deviceNo;

    @Min(value = 1, message = "每页显示记录数不能为空，且只能为整数", groups = {Query.class})
    private Integer limit = 10;//每页显示记录数
    @Min(value = 1, message = "页码不能为空，且只能为整数", groups = {Query.class})
    private Integer currPageNo = 1;//当前页 页码
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(Integer currPageNo) {
        this.currPageNo = currPageNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
