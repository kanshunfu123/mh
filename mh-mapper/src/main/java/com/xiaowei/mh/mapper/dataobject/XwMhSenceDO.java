package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mh.mapper.dataobject.XwMhSenceDO;

/**
 * The table 井盖场景信息表
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class XwMhSenceDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * areaId 区id.
     */
    private Long areaId;
    /**
     * cityId 市id.
     */
    private Long cityId;
    /**
     * provinceId 省id.
     */
    private Long provinceId;
    /**
     * mhUse 用途.
     */
    private String mhUse;
    /**
     * mhCode 井盖位置码.
     */
    private String mhCode;
    /**
     * mhLoad 载荷.
     */
    private String mhLoad;
    /**
     * mhSize 井盖尺寸.
     */
    private String mhSize;
    /**
     * mhType 井盖使用类型.
     */
    private String mhType;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * senceUuid 井盖场景信息uuid.
     */
    private String senceUuid;
    /**
     * mhDiameter 井盖直径.
     */
    private String mhDiameter;
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
    private Date mhEndTime;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;
    /**
     * senceInstallTime 场景安装时间.
     */
    private Date senceInstallTime;

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
     * Set areaId 区id.
     */
    public void setAreaId(Long areaId){
        this.areaId = areaId;
    }

    /**
     * Get areaId 区id.
     *
     * @return the string
     */
    public Long getAreaId(){
        return areaId;
    }

    /**
     * Set cityId 市id.
     */
    public void setCityId(Long cityId){
        this.cityId = cityId;
    }

    /**
     * Get cityId 市id.
     *
     * @return the string
     */
    public Long getCityId(){
        return cityId;
    }

    /**
     * Set provinceId 省id.
     */
    public void setProvinceId(Long provinceId){
        this.provinceId = provinceId;
    }

    /**
     * Get provinceId 省id.
     *
     * @return the string
     */
    public Long getProvinceId(){
        return provinceId;
    }

    /**
     * Set mhUse 用途.
     */
    public void setMhUse(String mhUse){
        this.mhUse = mhUse;
    }

    /**
     * Get mhUse 用途.
     *
     * @return the string
     */
    public String getMhUse(){
        return mhUse;
    }

    /**
     * Set mhCode 井盖位置码.
     */
    public void setMhCode(String mhCode){
        this.mhCode = mhCode;
    }

    /**
     * Get mhCode 井盖位置码.
     *
     * @return the string
     */
    public String getMhCode(){
        return mhCode;
    }

    /**
     * Set mhLoad 载荷.
     */
    public void setMhLoad(String mhLoad){
        this.mhLoad = mhLoad;
    }

    /**
     * Get mhLoad 载荷.
     *
     * @return the string
     */
    public String getMhLoad(){
        return mhLoad;
    }

    /**
     * Set mhSize 井盖尺寸.
     */
    public void setMhSize(String mhSize){
        this.mhSize = mhSize;
    }

    /**
     * Get mhSize 井盖尺寸.
     *
     * @return the string
     */
    public String getMhSize(){
        return mhSize;
    }

    /**
     * Set mhType 井盖使用类型.
     */
    public void setMhType(String mhType){
        this.mhType = mhType;
    }

    /**
     * Get mhType 井盖使用类型.
     *
     * @return the string
     */
    public String getMhType(){
        return mhType;
    }

    /**
     * Set delFlag 删除状态(0-正常，1-删除).
     */
    public void setDelFlag(String delFlag){
        this.delFlag = delFlag;
    }

    /**
     * Get delFlag 删除状态(0-正常，1-删除).
     *
     * @return the string
     */
    public String getDelFlag(){
        return delFlag;
    }

    /**
     * Set createBy 创建人.
     */
    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }

    /**
     * Get createBy 创建人.
     *
     * @return the string
     */
    public String getCreateBy(){
        return createBy;
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
     * Set updateBy 修改人.
     */
    public void setUpdateBy(String updateBy){
        this.updateBy = updateBy;
    }

    /**
     * Get updateBy 修改人.
     *
     * @return the string
     */
    public String getUpdateBy(){
        return updateBy;
    }

    /**
     * Set senceUuid 井盖场景信息uuid.
     */
    public void setSenceUuid(String senceUuid){
        this.senceUuid = senceUuid;
    }

    /**
     * Get senceUuid 井盖场景信息uuid.
     *
     * @return the string
     */
    public String getSenceUuid(){
        return senceUuid;
    }

    /**
     * Set mhDiameter 井盖直径.
     */
    public void setMhDiameter(String mhDiameter){
        this.mhDiameter = mhDiameter;
    }

    /**
     * Get mhDiameter 井盖直径.
     *
     * @return the string
     */
    public String getMhDiameter(){
        return mhDiameter;
    }

    /**
     * Set mhMaterial 井盖材质.
     */
    public void setMhMaterial(String mhMaterial){
        this.mhMaterial = mhMaterial;
    }

    /**
     * Get mhMaterial 井盖材质.
     *
     * @return the string
     */
    public String getMhMaterial(){
        return mhMaterial;
    }

    /**
     * Set trafficFlow 车流量.
     */
    public void setTrafficFlow(String trafficFlow){
        this.trafficFlow = trafficFlow;
    }

    /**
     * Get trafficFlow 车流量.
     *
     * @return the string
     */
    public String getTrafficFlow(){
        return trafficFlow;
    }

    /**
     * Set principalPhone 负责人联系方式.
     */
    public void setPrincipalPhone(String principalPhone){
        this.principalPhone = principalPhone;
    }

    /**
     * Get principalPhone 负责人联系方式.
     *
     * @return the string
     */
    public String getPrincipalPhone(){
        return principalPhone;
    }

    /**
     * Set sencePrincipal 负责人.
     */
    public void setSencePrincipal(String sencePrincipal){
        this.sencePrincipal = sencePrincipal;
    }

    /**
     * Get sencePrincipal 负责人.
     *
     * @return the string
     */
    public String getSencePrincipal(){
        return sencePrincipal;
    }

    /**
     * Set mhYears 使用年限.
     */
    public void setMhYears(Integer mhYears){
        this.mhYears = mhYears;
    }

    /**
     * Get mhYears 使用年限.
     *
     * @return the string
     */
    public Integer getMhYears(){
        return mhYears;
    }

    /**
     * Set mhEndTime 更换时间.
     */
    public void setMhEndTime(Date mhEndTime){
        this.mhEndTime = mhEndTime;
    }

    /**
     * Get mhEndTime 更换时间.
     *
     * @return the string
     */
    public Date getMhEndTime(){
        return mhEndTime;
    }

    /**
     * Set createTime 创建时间.
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
     * Get createTime 创建时间.
     *
     * @return the string
     */
    public Date getCreateTime(){
        return createTime;
    }

    /**
     * Set updateTime 修改时间.
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    /**
     * Get updateTime 修改时间.
     *
     * @return the string
     */
    public Date getUpdateTime(){
        return updateTime;
    }

    /**
     * Set senceInstallTime 场景安装时间.
     */
    public void setSenceInstallTime(Date senceInstallTime){
        this.senceInstallTime = senceInstallTime;
    }

    /**
     * Get senceInstallTime 场景安装时间.
     *
     * @return the string
     */
    public Date getSenceInstallTime(){
        return senceInstallTime;
    }
}
