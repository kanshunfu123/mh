package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mh.mapper.dataobject.ExcessCwDO;

/**
 * The table 饮用水超标数据统计
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class ExcessCwDO{

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
    private Float ph;
    /**
     * oxygen OXYGEN.
     */
    private Float oxygen;
    /**
     * chlorine CHLORINE.
     */
    private Float chlorine;
    /**
     * turbidity TURBIDITY.
     */
    private Float turbidity;
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
    public void setPh(Float ph){
        this.ph = ph;
    }

    /**
     * Get ph PH.
     *
     * @return the string
     */
    public Float getPh(){
        return ph;
    }

    /**
     * Set oxygen OXYGEN.
     */
    public void setOxygen(Float oxygen){
        this.oxygen = oxygen;
    }

    /**
     * Get oxygen OXYGEN.
     *
     * @return the string
     */
    public Float getOxygen(){
        return oxygen;
    }

    /**
     * Set chlorine CHLORINE.
     */
    public void setChlorine(Float chlorine){
        this.chlorine = chlorine;
    }

    /**
     * Get chlorine CHLORINE.
     *
     * @return the string
     */
    public Float getChlorine(){
        return chlorine;
    }

    /**
     * Set turbidity TURBIDITY.
     */
    public void setTurbidity(Float turbidity){
        this.turbidity = turbidity;
    }

    /**
     * Get turbidity TURBIDITY.
     *
     * @return the string
     */
    public Float getTurbidity(){
        return turbidity;
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
