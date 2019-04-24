package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mh.mapper.dataobject.XwCwSenceDO;

/**
 * The table 饮用水场景表
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class XwCwSenceDO{

    /**
     * id 主键.
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
     * cwCode 饮用水位置码.
     */
    private String cwCode;
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
     * tankSeal 密封情况.
     */
    private String tankSeal;
    /**
     * tankSize 水箱尺寸.
     */
    private String tankSize;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * senceUuid 饮用水场景uuid.
     */
    private String senceUuid;
    /**
     * tankMaterial 水箱材质.
     */
    private String tankMaterial;
    /**
     * principalPhone 负责人联系方式.
     */
    private String principalPhone;
    /**
     * sencePrincipal 负责人.
     */
    private String sencePrincipal;
    /**
     * cleaningFrequency 清洁频率.
     */
    private String cleaningFrequency;
    /**
     * waterSupportStatus 供水厂详情.
     */
    private String waterSupportStatus;
    /**
     * sencePropertyCompany 场景物业公司.
     */
    private String sencePropertyCompany;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;
    /**
     * lastCleaningTime 上次清洗时间.
     */
    private Date lastCleaningTime;
    /**
     * senceInstallTime 场景安装时间.
     */
    private Date senceInstallTime;

    /**
     * Set id 主键.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get id 主键.
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
     * Set cwCode 饮用水位置码.
     */
    public void setCwCode(String cwCode){
        this.cwCode = cwCode;
    }

    /**
     * Get cwCode 饮用水位置码.
     *
     * @return the string
     */
    public String getCwCode(){
        return cwCode;
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
     * Set tankSeal 密封情况.
     */
    public void setTankSeal(String tankSeal){
        this.tankSeal = tankSeal;
    }

    /**
     * Get tankSeal 密封情况.
     *
     * @return the string
     */
    public String getTankSeal(){
        return tankSeal;
    }

    /**
     * Set tankSize 水箱尺寸.
     */
    public void setTankSize(String tankSize){
        this.tankSize = tankSize;
    }

    /**
     * Get tankSize 水箱尺寸.
     *
     * @return the string
     */
    public String getTankSize(){
        return tankSize;
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
     * Set senceUuid 饮用水场景uuid.
     */
    public void setSenceUuid(String senceUuid){
        this.senceUuid = senceUuid;
    }

    /**
     * Get senceUuid 饮用水场景uuid.
     *
     * @return the string
     */
    public String getSenceUuid(){
        return senceUuid;
    }

    /**
     * Set tankMaterial 水箱材质.
     */
    public void setTankMaterial(String tankMaterial){
        this.tankMaterial = tankMaterial;
    }

    /**
     * Get tankMaterial 水箱材质.
     *
     * @return the string
     */
    public String getTankMaterial(){
        return tankMaterial;
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
     * Set cleaningFrequency 清洁频率.
     */
    public void setCleaningFrequency(String cleaningFrequency){
        this.cleaningFrequency = cleaningFrequency;
    }

    /**
     * Get cleaningFrequency 清洁频率.
     *
     * @return the string
     */
    public String getCleaningFrequency(){
        return cleaningFrequency;
    }

    /**
     * Set waterSupportStatus 供水厂详情.
     */
    public void setWaterSupportStatus(String waterSupportStatus){
        this.waterSupportStatus = waterSupportStatus;
    }

    /**
     * Get waterSupportStatus 供水厂详情.
     *
     * @return the string
     */
    public String getWaterSupportStatus(){
        return waterSupportStatus;
    }

    /**
     * Set sencePropertyCompany 场景物业公司.
     */
    public void setSencePropertyCompany(String sencePropertyCompany){
        this.sencePropertyCompany = sencePropertyCompany;
    }

    /**
     * Get sencePropertyCompany 场景物业公司.
     *
     * @return the string
     */
    public String getSencePropertyCompany(){
        return sencePropertyCompany;
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
     * Set lastCleaningTime 上次清洗时间.
     */
    public void setLastCleaningTime(Date lastCleaningTime){
        this.lastCleaningTime = lastCleaningTime;
    }

    /**
     * Get lastCleaningTime 上次清洗时间.
     *
     * @return the string
     */
    public Date getLastCleaningTime(){
        return lastCleaningTime;
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
