package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mh.mapper.dataobject.FaultLogDO;

/**
 * The table 错误日志表（定时）
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class FaultLogDO{

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
     * provinceId 省id.
     */
    private Long provinceId;
    /**
     * address 地点.
     */
    private String address;
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
     * faultInfo 错误值.
     */
    private String faultInfo;
    /**
     * deviceType 0 地表水 1井盖 2生活用水 3电梯.
     */
    private String deviceType;
    /**
     * provinceName PROVINCE_NAME.
     */
    private String provinceName;
    /**
     * fault1 FAULT_1.
     */
    private Integer fault1;
    /**
     * fault2 FAULT_2.
     */
    private Integer fault2;
    /**
     * fault3 FAULT_3.
     */
    private Integer fault3;
    /**
     * fault4 FAULT_4.
     */
    private Integer fault4;
    /**
     * fault5 FAULT_5.
     */
    private Integer fault5;
    /**
     * fault6 FAULT_6.
     */
    private Integer fault6;
    /**
     * fault7 FAULT_7.
     */
    private Integer fault7;
    /**
     * fault8 FAULT_8.
     */
    private Integer fault8;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * collectTime 错误采集时间.
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
     * Set address 地点.
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * Get address 地点.
     *
     * @return the string
     */
    public String getAddress(){
        return address;
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
     * Set faultInfo 错误值.
     */
    public void setFaultInfo(String faultInfo){
        this.faultInfo = faultInfo;
    }

    /**
     * Get faultInfo 错误值.
     *
     * @return the string
     */
    public String getFaultInfo(){
        return faultInfo;
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
     * Set fault1 FAULT_1.
     */
    public void setFault1(Integer fault1){
        this.fault1 = fault1;
    }

    /**
     * Get fault1 FAULT_1.
     *
     * @return the string
     */
    public Integer getFault1(){
        return fault1;
    }

    /**
     * Set fault2 FAULT_2.
     */
    public void setFault2(Integer fault2){
        this.fault2 = fault2;
    }

    /**
     * Get fault2 FAULT_2.
     *
     * @return the string
     */
    public Integer getFault2(){
        return fault2;
    }

    /**
     * Set fault3 FAULT_3.
     */
    public void setFault3(Integer fault3){
        this.fault3 = fault3;
    }

    /**
     * Get fault3 FAULT_3.
     *
     * @return the string
     */
    public Integer getFault3(){
        return fault3;
    }

    /**
     * Set fault4 FAULT_4.
     */
    public void setFault4(Integer fault4){
        this.fault4 = fault4;
    }

    /**
     * Get fault4 FAULT_4.
     *
     * @return the string
     */
    public Integer getFault4(){
        return fault4;
    }

    /**
     * Set fault5 FAULT_5.
     */
    public void setFault5(Integer fault5){
        this.fault5 = fault5;
    }

    /**
     * Get fault5 FAULT_5.
     *
     * @return the string
     */
    public Integer getFault5(){
        return fault5;
    }

    /**
     * Set fault6 FAULT_6.
     */
    public void setFault6(Integer fault6){
        this.fault6 = fault6;
    }

    /**
     * Get fault6 FAULT_6.
     *
     * @return the string
     */
    public Integer getFault6(){
        return fault6;
    }

    /**
     * Set fault7 FAULT_7.
     */
    public void setFault7(Integer fault7){
        this.fault7 = fault7;
    }

    /**
     * Get fault7 FAULT_7.
     *
     * @return the string
     */
    public Integer getFault7(){
        return fault7;
    }

    /**
     * Set fault8 FAULT_8.
     */
    public void setFault8(Integer fault8){
        this.fault8 = fault8;
    }

    /**
     * Get fault8 FAULT_8.
     *
     * @return the string
     */
    public Integer getFault8(){
        return fault8;
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
     * Set collectTime 错误采集时间.
     */
    public void setCollectTime(Date collectTime){
        this.collectTime = collectTime;
    }

    /**
     * Get collectTime 错误采集时间.
     *
     * @return the string
     */
    public Date getCollectTime(){
        return collectTime;
    }
}
