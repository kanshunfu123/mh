package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mh.mapper.dataobject.ExcessRwDO;

/**
 * The table 地表水超标（五类及超五类）
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class ExcessRwDO{

    /**
     * id ID.
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
     * ph PH.
     */
    private String ph;
    /**
     * rdo RDO.
     */
    private String rdo;
    /**
     * tss TSS.
     */
    private String tss;
    /**
     * areaName AREA_NAME.
     */
    private String areaName;
    /**
     * cityName CITY_NAME.
     */
    private String cityName;
    /**
     * deviceNo DEVICE_NO.
     */
    private String deviceNo;
    /**
     * conductivity CONDUCTIVITY.
     */
    private String conductivity;
    /**
     * provinceName PROVINCE_NAME.
     */
    private String provinceName;
    /**
     * createTime CREATE_TIME.
     */
    private Date createTime;
    /**
     * collectTime COLLECT_TIME.
     */
    private Date collectTime;

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
     * Set ph PH.
     */
    public void setPh(String ph){
        this.ph = ph;
    }

    /**
     * Get ph PH.
     *
     * @return the string
     */
    public String getPh(){
        return ph;
    }

    /**
     * Set rdo RDO.
     */
    public void setRdo(String rdo){
        this.rdo = rdo;
    }

    /**
     * Get rdo RDO.
     *
     * @return the string
     */
    public String getRdo(){
        return rdo;
    }

    /**
     * Set tss TSS.
     */
    public void setTss(String tss){
        this.tss = tss;
    }

    /**
     * Get tss TSS.
     *
     * @return the string
     */
    public String getTss(){
        return tss;
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
     * Set deviceNo DEVICE_NO.
     */
    public void setDeviceNo(String deviceNo){
        this.deviceNo = deviceNo;
    }

    /**
     * Get deviceNo DEVICE_NO.
     *
     * @return the string
     */
    public String getDeviceNo(){
        return deviceNo;
    }

    /**
     * Set conductivity CONDUCTIVITY.
     */
    public void setConductivity(String conductivity){
        this.conductivity = conductivity;
    }

    /**
     * Get conductivity CONDUCTIVITY.
     *
     * @return the string
     */
    public String getConductivity(){
        return conductivity;
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
     * Set createTime CREATE_TIME.
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
     * Get createTime CREATE_TIME.
     *
     * @return the string
     */
    public Date getCreateTime(){
        return createTime;
    }

    /**
     * Set collectTime COLLECT_TIME.
     */
    public void setCollectTime(Date collectTime){
        this.collectTime = collectTime;
    }

    /**
     * Get collectTime COLLECT_TIME.
     *
     * @return the string
     */
    public Date getCollectTime(){
        return collectTime;
    }
}
