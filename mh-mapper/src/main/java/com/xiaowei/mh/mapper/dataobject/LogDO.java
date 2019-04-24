package com.xiaowei.mh.mapper.dataobject;

import com.xiaowei.mh.mapper.dataobject.LogDO;

/**
 * The table 设备接收数据日志
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class LogDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * recData 接收数据.
     */
    private String recData;
    /**
     * recTime 接收时间.
     */
    private String recTime;
    /**
     * dataFlag 日志处理标记 1 已处理.
     */
    private String dataFlag;
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * deviceType 设备类型.
     */
    private String deviceType;

    /**
     * Set id ID.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get id ID.
     *
     * @return the string
     */
    public Long getId(){
        return id;
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
     * Set recTime 接收时间.
     */
    public void setRecTime(String recTime){
        this.recTime = recTime;
    }

    /**
     * Get recTime 接收时间.
     *
     * @return the string
     */
    public String getRecTime(){
        return recTime;
    }

    /**
     * Set dataFlag 日志处理标记 1 已处理.
     */
    public void setDataFlag(String dataFlag){
        this.dataFlag = dataFlag;
    }

    /**
     * Get dataFlag 日志处理标记 1 已处理.
     *
     * @return the string
     */
    public String getDataFlag(){
        return dataFlag;
    }

    /**
     * Set deviceNo 设备编号.
     */
    public void setDeviceNo(String deviceNo){
        this.deviceNo = deviceNo;
    }

    /**
     * Get deviceNo 设备编号.
     *
     * @return the string
     */
    public String getDeviceNo(){
        return deviceNo;
    }

    /**
     * Set deviceType 设备类型.
     */
    public void setDeviceType(String deviceType){
        this.deviceType = deviceType;
    }

    /**
     * Get deviceType 设备类型.
     *
     * @return the string
     */
    public String getDeviceType(){
        return deviceType;
    }
}
