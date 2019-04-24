package com.xiaowei.mh.service.protocol.manhole;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 韩金群
 * CreateTime 2018/12/26
 */
public class NewLogVo implements Serializable {
    //通知类型
    private String notifyType;
    //设备 ID
    private String deviceId;
    //网关 ID
    private String gatewayId;
    //imei号
    private String imei;
    //设备类型
    private String deviceType;
    //接收时间
    private Date recTime;
    //接收数据
    private String recData;
    //事件发生时间
    private String eventTime;

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Date getRecTime() {
        return recTime;
    }

    public void setRecTime(Date recTime) {
        this.recTime = recTime;
    }

    public String getRecData() {
        return recData;
    }

    public void setRecData(String recData) {
        this.recData = recData;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}
