package com.xiaowei.mh.common.res.mhdata;

import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/12
 */
public class MhSenceRes implements Serializable {
    /**
     * id ID.
     */
    private Long id;
    /**
     * mhUse 用途.
     */
    private String mhUse;
    /**
     * mhLoad 载荷.
     */
    private String mhLoad;
    /**
     * mhSize 井盖尺寸.
     */
    private String mhSize;
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * mhMaterial 井盖材质.
     */
    private String mhMaterial;
    /**
     * trafficFlow 车流量.
     */
    private String trafficFlow;
    /**
     * principalPhone 负责人联系方式.
     */
    private String principalPhone;
    /**
     * sencePrincipal 负责人.
     */
    private String sencePrincipal;
    /**
     * mhYears 使用年限.
     */
    private Integer mhYears;
    /**
     * mhEndTime 更换时间.
     */
    private String mhEndTime;
    /**
     * senceInstallTime 场景安装时间.
     */
    private String senceInstallTime;
    /**
     * 位置
     */
    private String location;
    /**
     * mhCode 井盖编码.
     */
    private String mhCode;

    public String getMhCode() {
        return mhCode;
    }

    public void setMhCode(String mhCode) {
        this.mhCode = mhCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMhUse() {
        return mhUse;
    }

    public void setMhUse(String mhUse) {
        this.mhUse = mhUse;
    }

    public String getMhLoad() {
        return mhLoad;
    }

    public void setMhLoad(String mhLoad) {
        this.mhLoad = mhLoad;
    }

    public String getMhSize() {
        return mhSize;
    }

    public void setMhSize(String mhSize) {
        this.mhSize = mhSize;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getMhMaterial() {
        return mhMaterial;
    }

    public void setMhMaterial(String mhMaterial) {
        this.mhMaterial = mhMaterial;
    }

    public String getTrafficFlow() {
        return trafficFlow;
    }

    public void setTrafficFlow(String trafficFlow) {
        this.trafficFlow = trafficFlow;
    }

    public String getPrincipalPhone() {
        return principalPhone;
    }

    public void setPrincipalPhone(String principalPhone) {
        this.principalPhone = principalPhone;
    }

    public String getSencePrincipal() {
        return sencePrincipal;
    }

    public void setSencePrincipal(String sencePrincipal) {
        this.sencePrincipal = sencePrincipal;
    }

    public Integer getMhYears() {
        return mhYears;
    }

    public void setMhYears(Integer mhYears) {
        this.mhYears = mhYears;
    }

    public String getMhEndTime() {
        return mhEndTime;
    }

    public void setMhEndTime(String mhEndTime) {
        this.mhEndTime = mhEndTime;
    }

    public String getSenceInstallTime() {
        return senceInstallTime;
    }

    public void setSenceInstallTime(String senceInstallTime) {
        this.senceInstallTime = senceInstallTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
