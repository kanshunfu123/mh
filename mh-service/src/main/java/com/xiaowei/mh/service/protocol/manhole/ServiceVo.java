package com.xiaowei.mh.service.protocol.manhole;

/**
 * Created by 韩金群
 * CreateTime 2018/12/25
 */
public class ServiceVo {
    //服务 ID。
    private String serviceId;

    //服务的类型。
    private String serviceType;

    //事件发生时间，时间格式yyyymmddThhmmssZ，例如20151212T121212Z。
    private String eventTime;

    private DataVo data;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public DataVo getData() {
        return data;
    }

    public void setData(DataVo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ServiceVo{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", eventTime='" + eventTime + '\'' +
                ", data=" + data +
                '}';
    }
}
