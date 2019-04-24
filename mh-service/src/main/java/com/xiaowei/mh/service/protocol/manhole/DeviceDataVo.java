package com.xiaowei.mh.service.protocol.manhole;

/**
 * Created by 韩金群
 * CreateTime 2018/12/25
 */
public class DeviceDataVo {
    //通知类型
    private String notifyType;

    //设备 ID，用于唯一标识一个设备。
    private String deviceId;

    //网关 ID，与设备的 deviceId 一致
    private String gatewayId;

    private ServiceVo service;

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

    public ServiceVo getService() {
        return service;
    }

    public void setService(ServiceVo service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "DeviceDataVo{" +
                "notifyType='" + notifyType + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", gatewayId='" + gatewayId + '\'' +
                ", service=" + service +
                '}';
    }
}
