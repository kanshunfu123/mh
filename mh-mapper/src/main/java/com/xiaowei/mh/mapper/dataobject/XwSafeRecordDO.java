package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mh.mapper.dataobject.XwSafeRecordDO;

/**
 * The table 维保记录表
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class XwSafeRecordDO{

    /**
     * id 主键.
     */
    private Long id;
    /**
     * areaId AREA_ID.
     */
    private Long areaId;
    /**
     * cityId CITY_ID.
     */
    private Long cityId;
    /**
     * provinceId PROVINCE_ID.
     */
    private Long provinceId;
    /**
     * delFlag 0正常 1删除.
     */
    private String delFlag;
    /**
     * safeMan 维保人.
     */
    private String safeMan;
    /**
     * areaName AREA_NAME.
     */
    private String areaName;
    /**
     * cityName CITY_NAME.
     */
    private String cityName;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * safePhone 维保人手机号.
     */
    private String safePhone;
    /**
     * deviceType 0 地表水 1井盖 2生活用水 3电梯.
     */
    private String deviceType;
    /**
     * safeCompany 维保公司.
     */
    private String safeCompany;
    /**
     * provinceName PROVINCE_NAME.
     */
    private String provinceName;
    /**
     * safeRecordUuid 维保记录uuid.
     */
    private String safeRecordUuid;
    /**
     * safeYear 维保年份.
     */
    private Integer safeYear;
    /**
     * safeMonth 维保月份.
     */
    private Integer safeMonth;
    /**
     * safeTime 维保时间.
     */
    private Date safeTime;
    /**
     * createTime 创建时间.
     */
    private Date createTime;

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
     * Set areaId AREA_ID.
     */
    public void setAreaId(Long areaId){
        this.areaId = areaId;
    }

    /**
     * Get areaId AREA_ID.
     *
     * @return the string
     */
    public Long getAreaId(){
        return areaId;
    }

    /**
     * Set cityId CITY_ID.
     */
    public void setCityId(Long cityId){
        this.cityId = cityId;
    }

    /**
     * Get cityId CITY_ID.
     *
     * @return the string
     */
    public Long getCityId(){
        return cityId;
    }

    /**
     * Set provinceId PROVINCE_ID.
     */
    public void setProvinceId(Long provinceId){
        this.provinceId = provinceId;
    }

    /**
     * Get provinceId PROVINCE_ID.
     *
     * @return the string
     */
    public Long getProvinceId(){
        return provinceId;
    }

    /**
     * Set delFlag 0正常 1删除.
     */
    public void setDelFlag(String delFlag){
        this.delFlag = delFlag;
    }

    /**
     * Get delFlag 0正常 1删除.
     *
     * @return the string
     */
    public String getDelFlag(){
        return delFlag;
    }

    /**
     * Set safeMan 维保人.
     */
    public void setSafeMan(String safeMan){
        this.safeMan = safeMan;
    }

    /**
     * Get safeMan 维保人.
     *
     * @return the string
     */
    public String getSafeMan(){
        return safeMan;
    }

    /**
     * Set areaName AREA_NAME.
     */
    public void setAreaName(String areaName){
        this.areaName = areaName;
    }

    /**
     * Get areaName AREA_NAME.
     *
     * @return the string
     */
    public String getAreaName(){
        return areaName;
    }

    /**
     * Set cityName CITY_NAME.
     */
    public void setCityName(String cityName){
        this.cityName = cityName;
    }

    /**
     * Get cityName CITY_NAME.
     *
     * @return the string
     */
    public String getCityName(){
        return cityName;
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
     * Set safePhone 维保人手机号.
     */
    public void setSafePhone(String safePhone){
        this.safePhone = safePhone;
    }

    /**
     * Get safePhone 维保人手机号.
     *
     * @return the string
     */
    public String getSafePhone(){
        return safePhone;
    }

    /**
     * Set deviceType 0 地表水 1井盖 2生活用水 3电梯.
     */
    public void setDeviceType(String deviceType){
        this.deviceType = deviceType;
    }

    /**
     * Get deviceType 0 地表水 1井盖 2生活用水 3电梯.
     *
     * @return the string
     */
    public String getDeviceType(){
        return deviceType;
    }

    /**
     * Set safeCompany 维保公司.
     */
    public void setSafeCompany(String safeCompany){
        this.safeCompany = safeCompany;
    }

    /**
     * Get safeCompany 维保公司.
     *
     * @return the string
     */
    public String getSafeCompany(){
        return safeCompany;
    }

    /**
     * Set provinceName PROVINCE_NAME.
     */
    public void setProvinceName(String provinceName){
        this.provinceName = provinceName;
    }

    /**
     * Get provinceName PROVINCE_NAME.
     *
     * @return the string
     */
    public String getProvinceName(){
        return provinceName;
    }

    /**
     * Set safeRecordUuid 维保记录uuid.
     */
    public void setSafeRecordUuid(String safeRecordUuid){
        this.safeRecordUuid = safeRecordUuid;
    }

    /**
     * Get safeRecordUuid 维保记录uuid.
     *
     * @return the string
     */
    public String getSafeRecordUuid(){
        return safeRecordUuid;
    }

    /**
     * Set safeYear 维保年份.
     */
    public void setSafeYear(Integer safeYear){
        this.safeYear = safeYear;
    }

    /**
     * Get safeYear 维保年份.
     *
     * @return the string
     */
    public Integer getSafeYear(){
        return safeYear;
    }

    /**
     * Set safeMonth 维保月份.
     */
    public void setSafeMonth(Integer safeMonth){
        this.safeMonth = safeMonth;
    }

    /**
     * Get safeMonth 维保月份.
     *
     * @return the string
     */
    public Integer getSafeMonth(){
        return safeMonth;
    }

    /**
     * Set safeTime 维保时间.
     */
    public void setSafeTime(Date safeTime){
        this.safeTime = safeTime;
    }

    /**
     * Get safeTime 维保时间.
     *
     * @return the string
     */
    public Date getSafeTime(){
        return safeTime;
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
}
