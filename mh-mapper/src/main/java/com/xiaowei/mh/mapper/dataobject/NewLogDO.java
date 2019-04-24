package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mh.mapper.dataobject.NewLogDO;

/**
 * The table 设备接收数据新日志表
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class NewLogDO{

    /**
     * id ID.
     */
    private Integer id;
    /**
     * imei IMEI号.
     */
    private String imei;
    /**
     * recData 接收数据.
     */
    private String recData;
    /**
     * deviceId 设备 ID，用于唯一标识一个设备。.
     */
    private String deviceId;
    /**
     * eventTime 事件发生时间.
     */
    private String eventTime;
    /**
     * gatewayId 网关 ID，与设备的 deviceId 一致.
     */
    private String gatewayId;
    /**
     * deviceType 设备类型 1：电梯 2：饮用水 3：地表水 4：井盖.
     */
    private String deviceType;
    /**
     * notifyType 通知类型.
     */
    private String notifyType;
    /**
     * recTime 接收时间.
     */
    private Date recTime;

    /**
     * Set id ID.
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * Get id ID.
     *
     * @return the string
     */
    public Integer getId(){
        return id;
    }

    /**
     * Set imei IMEI号.
     */
    public void setImei(String imei){
        this.imei = imei;
    }

    /**
     * Get imei IMEI号.
     *
     * @return the string
     */
    public String getImei(){
        return imei;
    }

    /**
     * Set recData 接收数据.
     */
    public void setRecData(String recData){
        this.recData = recData;
    }

    /**
     * Get recData 接收数据.
     *
     * @return the string
     */
    public String getRecData(){
        return recData;
    }

    /**
     * Set deviceId 设备 ID，用于唯一标识一个设备。.
     */
    public void setDeviceId(String deviceId){
        this.deviceId = deviceId;
    }

    /**
     * Get deviceId 设备 ID，用于唯一标识一个设备。.
     *
     * @return the string
     */
    public String getDeviceId(){
        return deviceId;
    }

    /**
     * Set eventTime 事件发生时间.
     */
    public void setEventTime(String eventTime){
        this.eventTime = eventTime;
    }

    /**
     * Get eventTime 事件发生时间.
     *
     * @return the string
     */
    public String getEventTime(){
        return eventTime;
    }

    /**
     * Set gatewayId 网关 ID，与设备的 deviceId 一致.
     */
    public void setGatewayId(String gatewayId){
        this.gatewayId = gatewayId;
    }

    /**
     * Get gatewayId 网关 ID，与设备的 deviceId 一致.
     *
     * @return the string
     */
    public String getGatewayId(){
        return gatewayId;
    }

    /**
     * Set deviceType 设备类型 1：电梯 2：饮用水 3：地表水 4：井盖.
     */
    public void setDeviceType(String deviceType){
        this.deviceType = deviceType;
    }

    /**
     * Get deviceType 设备类型 1：电梯 2：饮用水 3：地表水 4：井盖.
     *
     * @return the string
     */
    public String getDeviceType(){
        return deviceType;
    }

    /**
     * Set notifyType 通知类型.
     */
    public void setNotifyType(String notifyType){
        this.notifyType = notifyType;
    }

    /**
     * Get notifyType 通知类型.
     *
     * @return the string
     */
    public String getNotifyType(){
        return notifyType;
    }

    /**
     * Set recTime 接收时间.
     */
    public void setRecTime(Date recTime){
        this.recTime = recTime;
    }

    /**
     * Get recTime 接收时间.
     *
     * @return the string
     */
    public Date getRecTime(){
        return recTime;
    }
}
