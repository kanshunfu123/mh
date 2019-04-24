package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mh.mapper.dataobject.PowerLogDO;

/**
 * The table D_POWER_LOG
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class PowerLogDO{

    /**
     * id 主键自动增长.
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
     * delFlag DEL_FLAG.
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
     * deviceType 0 地表水 1井盖 2生活用水 3电梯.
     */
    private String deviceType;
    /**
     * provinceName 省名.
     */
    private String provinceName;
    /**
     * powerLaunch 发射端电量.
     */
    private Integer powerLaunch;
    /**
     * powerCollect 采集端.
     */
    private Integer powerCollect;
    /**
     * createTime CREATE_TIME.
     */
    private Date createTime;
    /**
     * collectTime 采集时间.
     */
    private Date collectTime;

    /**
     * Set id 主键自动增长.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get id 主键自动增长.
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
     * Set delFlag DEL_FLAG.
     */
    public void setDelFlag(String delFlag){
        this.delFlag = delFlag;
    }

    /**
     * Get delFlag DEL_FLAG.
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
     * Set provinceName 省名.
     */
    public void setProvinceName(String provinceName){
        this.provinceName = provinceName;
    }

    /**
     * Get provinceName 省名.
     *
     * @return the string
     */
    public String getProvinceName(){
        return provinceName;
    }

    /**
     * Set powerLaunch 发射端电量.
     */
    public void setPowerLaunch(Integer powerLaunch){
        this.powerLaunch = powerLaunch;
    }

    /**
     * Get powerLaunch 发射端电量.
     *
     * @return the string
     */
    public Integer getPowerLaunch(){
        return powerLaunch;
    }

    /**
     * Set powerCollect 采集端.
     */
    public void setPowerCollect(Integer powerCollect){
        this.powerCollect = powerCollect;
    }

    /**
     * Get powerCollect 采集端.
     *
     * @return the string
     */
    public Integer getPowerCollect(){
        return powerCollect;
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
     * Set collectTime 采集时间.
     */
    public void setCollectTime(Date collectTime){
        this.collectTime = collectTime;
    }

    /**
     * Get collectTime 采集时间.
     *
     * @return the string
     */
    public Date getCollectTime(){
        return collectTime;
    }
}
