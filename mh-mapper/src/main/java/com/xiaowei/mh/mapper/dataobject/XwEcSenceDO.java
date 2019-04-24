package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mh.mapper.dataobject.XwEcSenceDO;

/**
 * The table 电梯场景表
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class XwEcSenceDO{

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
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * ecBrand 电梯品牌.
     */
    private String ecBrand;
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
     * senceUuid 电梯场景uuid.
     */
    private String senceUuid;
    /**
     * elevatorUse 电梯用途.
     */
    private String elevatorUse;
    /**
     * ecSupervisor 监管人员.
     */
    private String ecSupervisor;
    /**
     * elevatorCode 电梯位置码.
     */
    private String elevatorCode;
    /**
     * senceSafeMan 场景维保人员.
     */
    private String senceSafeMan;
    /**
     * serialNumber 出厂编号.
     */
    private String serialNumber;
    /**
     * regulatoryUnit 监管单位.
     */
    private String regulatoryUnit;
    /**
     * senceSafePhone 场景维保人联系方式.
     */
    private String senceSafePhone;
    /**
     * supervisorPhone 监管人联系方式.
     */
    private String supervisorPhone;
    /**
     * senceSafeCompany 场景维保公司.
     */
    private String senceSafeCompany;
    /**
     * ecCrew 乘员.
     */
    private Integer ecCrew;
    /**
     * ecLoad 载重.
     */
    private Integer ecLoad;
    /**
     * yearsOfUser 使用年限.
     */
    private Integer yearsOfUser;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;
    /**
     * productTime 生产日期.
     */
    private Date productTime;
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
     * Set ecBrand 电梯品牌.
     */
    public void setEcBrand(String ecBrand){
        this.ecBrand = ecBrand;
    }

    /**
     * Get ecBrand 电梯品牌.
     *
     * @return the string
     */
    public String getEcBrand(){
        return ecBrand;
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
     * Set senceUuid 电梯场景uuid.
     */
    public void setSenceUuid(String senceUuid){
        this.senceUuid = senceUuid;
    }

    /**
     * Get senceUuid 电梯场景uuid.
     *
     * @return the string
     */
    public String getSenceUuid(){
        return senceUuid;
    }

    /**
     * Set elevatorUse 电梯用途.
     */
    public void setElevatorUse(String elevatorUse){
        this.elevatorUse = elevatorUse;
    }

    /**
     * Get elevatorUse 电梯用途.
     *
     * @return the string
     */
    public String getElevatorUse(){
        return elevatorUse;
    }

    /**
     * Set ecSupervisor 监管人员.
     */
    public void setEcSupervisor(String ecSupervisor){
        this.ecSupervisor = ecSupervisor;
    }

    /**
     * Get ecSupervisor 监管人员.
     *
     * @return the string
     */
    public String getEcSupervisor(){
        return ecSupervisor;
    }

    /**
     * Set elevatorCode 电梯位置码.
     */
    public void setElevatorCode(String elevatorCode){
        this.elevatorCode = elevatorCode;
    }

    /**
     * Get elevatorCode 电梯位置码.
     *
     * @return the string
     */
    public String getElevatorCode(){
        return elevatorCode;
    }

    /**
     * Set senceSafeMan 场景维保人员.
     */
    public void setSenceSafeMan(String senceSafeMan){
        this.senceSafeMan = senceSafeMan;
    }

    /**
     * Get senceSafeMan 场景维保人员.
     *
     * @return the string
     */
    public String getSenceSafeMan(){
        return senceSafeMan;
    }

    /**
     * Set serialNumber 出厂编号.
     */
    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;
    }

    /**
     * Get serialNumber 出厂编号.
     *
     * @return the string
     */
    public String getSerialNumber(){
        return serialNumber;
    }

    /**
     * Set regulatoryUnit 监管单位.
     */
    public void setRegulatoryUnit(String regulatoryUnit){
        this.regulatoryUnit = regulatoryUnit;
    }

    /**
     * Get regulatoryUnit 监管单位.
     *
     * @return the string
     */
    public String getRegulatoryUnit(){
        return regulatoryUnit;
    }

    /**
     * Set senceSafePhone 场景维保人联系方式.
     */
    public void setSenceSafePhone(String senceSafePhone){
        this.senceSafePhone = senceSafePhone;
    }

    /**
     * Get senceSafePhone 场景维保人联系方式.
     *
     * @return the string
     */
    public String getSenceSafePhone(){
        return senceSafePhone;
    }

    /**
     * Set supervisorPhone 监管人联系方式.
     */
    public void setSupervisorPhone(String supervisorPhone){
        this.supervisorPhone = supervisorPhone;
    }

    /**
     * Get supervisorPhone 监管人联系方式.
     *
     * @return the string
     */
    public String getSupervisorPhone(){
        return supervisorPhone;
    }

    /**
     * Set senceSafeCompany 场景维保公司.
     */
    public void setSenceSafeCompany(String senceSafeCompany){
        this.senceSafeCompany = senceSafeCompany;
    }

    /**
     * Get senceSafeCompany 场景维保公司.
     *
     * @return the string
     */
    public String getSenceSafeCompany(){
        return senceSafeCompany;
    }

    /**
     * Set ecCrew 乘员.
     */
    public void setEcCrew(Integer ecCrew){
        this.ecCrew = ecCrew;
    }

    /**
     * Get ecCrew 乘员.
     *
     * @return the string
     */
    public Integer getEcCrew(){
        return ecCrew;
    }

    /**
     * Set ecLoad 载重.
     */
    public void setEcLoad(Integer ecLoad){
        this.ecLoad = ecLoad;
    }

    /**
     * Get ecLoad 载重.
     *
     * @return the string
     */
    public Integer getEcLoad(){
        return ecLoad;
    }

    /**
     * Set yearsOfUser 使用年限.
     */
    public void setYearsOfUser(Integer yearsOfUser){
        this.yearsOfUser = yearsOfUser;
    }

    /**
     * Get yearsOfUser 使用年限.
     *
     * @return the string
     */
    public Integer getYearsOfUser(){
        return yearsOfUser;
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
     * Set productTime 生产日期.
     */
    public void setProductTime(Date productTime){
        this.productTime = productTime;
    }

    /**
     * Get productTime 生产日期.
     *
     * @return the string
     */
    public Date getProductTime(){
        return productTime;
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
