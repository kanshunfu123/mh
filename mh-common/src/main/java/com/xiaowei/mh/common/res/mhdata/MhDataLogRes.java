package com.xiaowei.mh.common.res.mhdata;

import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/12
 */
public class MhDataLogRes implements Serializable {
    /**
     * id ID.
     */
    private Long id;
    /**
     * cmd 命令.
     */
    private String cmd;
    /**
     * ccid 设备CCID号.
     */
    private String ccid;
    /**
     * imei IMEI号.
     */
    private String imei;
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * faultInfo 故障信息.
     */
    private String faultInfo;
    /**
     * 信号强度
     */
    private String signal;
    /**
     * powerMh 电池电量.
     */
    private String powerPct;
    /**
     * temperature 温度 单位℃.
     */
    private String temperature;
    /**
     * wellOpenSts 设备开盖状态.
     */
    private String wellOpenSts;
    /**
     * collectTime 采集时间.
     */
    private String collectTime;
    /**
     * 采集间隔
     */
    private String mistiming;
    /**
     * 是否有井下液位(0没有 1有)
     */
    private Integer haveWaterLine;
    //水位
    private String waterLine;
    //是否有可燃气(0没有 1有)
    private Integer haveGas;
    //可燃气浓度
    private String fireGasCon;
    //是否有硫化氢(0没有 1有)
    private Integer haveH2s;
    //硫化氢浓度
    private String gasH2s;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getCcid() {
        return ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getFaultInfo() {
        return faultInfo;
    }

    public void setFaultInfo(String faultInfo) {
        this.faultInfo = faultInfo;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public String getPowerPct() {
        return powerPct;
    }

    public void setPowerPct(String powerPct) {
        this.powerPct = powerPct;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWellOpenSts() {
        return wellOpenSts;
    }

    public void setWellOpenSts(String wellOpenSts) {
        this.wellOpenSts = wellOpenSts;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public String getMistiming() {
        return mistiming;
    }

    public void setMistiming(String mistiming) {
        this.mistiming = mistiming;
    }

    public Integer getHaveWaterLine() {
        return haveWaterLine;
    }

    public void setHaveWaterLine(Integer haveWaterLine) {
        this.haveWaterLine = haveWaterLine;
    }

    public String getWaterLine() {
        return waterLine;
    }

    public void setWaterLine(String waterLine) {
        this.waterLine = waterLine;
    }

    public Integer getHaveGas() {
        return haveGas;
    }

    public void setHaveGas(Integer haveGas) {
        this.haveGas = haveGas;
    }

    public String getFireGasCon() {
        return fireGasCon;
    }

    public void setFireGasCon(String fireGasCon) {
        this.fireGasCon = fireGasCon;
    }

    public Integer getHaveH2s() {
        return haveH2s;
    }

    public void setHaveH2s(Integer haveH2s) {
        this.haveH2s = haveH2s;
    }

    public String getGasH2s() {
        return gasH2s;
    }

    public void setGasH2s(String gasH2s) {
        this.gasH2s = gasH2s;
    }
}
