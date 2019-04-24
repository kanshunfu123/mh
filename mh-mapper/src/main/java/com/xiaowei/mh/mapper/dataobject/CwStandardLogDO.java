package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mh.mapper.dataobject.CwStandardLogDO;

/**
 * The table 饮用水的参数统计
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class CwStandardLogDO{

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
     * ph ph.
     */
    private Float ph;
    /**
     * oxygen 溶解氧.
     */
    private Float oxygen;
    /**
     * chlorine 余氯.
     */
    private Float chlorine;
    /**
     * turbidity 浊度.
     */
    private Float turbidity;
    /**
     * delFlag 0正常  1删除.
     */
    private String delFlag;
    /**
     * areaName AREA_NAME.
     */
    private String areaName;
    /**
     * cityName CITY_NAME.
     */
    private String cityName;
    /**
     * deviceNo 设备编号.
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
     * Set ph ph.
     */
    public void setPh(Float ph){
        this.ph = ph;
    }

    /**
     * Get ph ph.
     *
     * @return the string
     */
    public Float getPh(){
        return ph;
    }

    /**
     * Set oxygen 溶解氧.
     */
    public void setOxygen(Float oxygen){
        this.oxygen = oxygen;
    }

    /**
     * Get oxygen 溶解氧.
     *
     * @return the string
     */
    public Float getOxygen(){
        return oxygen;
    }

    /**
     * Set chlorine 余氯.
     */
    public void setChlorine(Float chlorine){
        this.chlorine = chlorine;
    }

    /**
     * Get chlorine 余氯.
     *
     * @return the string
     */
    public Float getChlorine(){
        return chlorine;
    }

    /**
     * Set turbidity 浊度.
     */
    public void setTurbidity(Float turbidity){
        this.turbidity = turbidity;
    }

    /**
     * Get turbidity 浊度.
     *
     * @return the string
     */
    public Float getTurbidity(){
        return turbidity;
    }

    /**
     * Set delFlag 0正常  1删除.
     */
    public void setDelFlag(String delFlag){
        this.delFlag = delFlag;
    }

    /**
     * Get delFlag 0正常  1删除.
     *
     * @return the string
     */
    public String getDelFlag(){
        return delFlag;
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
