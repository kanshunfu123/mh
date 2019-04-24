package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mh.mapper.dataobject.XwEquipmentEcDO;

/**
 * The table 设备表（四种设备）
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class XwEquipmentEcDO{

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
     * streetId STREET_ID.
     */
    private Long streetId;
    /**
     * villageId 小区id.
     */
    private Long villageId;
    /**
     * provinceId 省id.
     */
    private Long provinceId;
    /**
     * latitude 纬度.
     */
    private Float latitude;
    /**
     * longitude 经度.
     */
    private Float longitude;
    /**
     * area 区.
     */
    private String area;
    /**
     * city 市.
     */
    private String city;
    /**
     * street 街道（与小区表保持一致）.
     */
    private String street;
    /**
     * vendor 设备生产厂商名称.
     */
    private String vendor;
    /**
     * address 详细地址.
     */
    private String address;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * safeMan 维保人员.
     */
    private String safeMan;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * province 省.
     */
    private String province;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * safePhone 联系电话.
     */
    private String safePhone;
    /**
     * deviceCode 编号（比如1#左）.
     */
    private String deviceCode;
    /**
     * deviceDesc 设备描述.
     */
    private String deviceDesc;
    /**
     * deviceName 设备名称.
     */
    private String deviceName;
    /**
     * installMan 安装人.
     */
    private String installMan;
    /**
     * safeCompany 维保公司.
     */
    private String safeCompany;
    /**
     * villageName 小区名.
     */
    private String villageName;
    /**
     * equipmentUuid uuid.
     */
    private String equipmentUuid;
    /**
     * floorNum 楼层数量.
     */
    private Integer floorNum;
    /**
     * floorDownnum 下楼层数量.
     */
    private Integer floorDownnum;
    /**
     * setupTime 设备安装时间.
     */
    private Date setupTime;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;
    /**
     * productTime 生产时间.
     */
    private Date productTime;

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
     * Set streetId STREET_ID.
     */
    public void setStreetId(Long streetId){
        this.streetId = streetId;
    }

    /**
     * Get streetId STREET_ID.
     *
     * @return the string
     */
    public Long getStreetId(){
        return streetId;
    }

    /**
     * Set villageId 小区id.
     */
    public void setVillageId(Long villageId){
        this.villageId = villageId;
    }

    /**
     * Get villageId 小区id.
     *
     * @return the string
     */
    public Long getVillageId(){
        return villageId;
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
     * Set latitude 纬度.
     */
    public void setLatitude(Float latitude){
        this.latitude = latitude;
    }

    /**
     * Get latitude 纬度.
     *
     * @return the string
     */
    public Float getLatitude(){
        return latitude;
    }

    /**
     * Set longitude 经度.
     */
    public void setLongitude(Float longitude){
        this.longitude = longitude;
    }

    /**
     * Get longitude 经度.
     *
     * @return the string
     */
    public Float getLongitude(){
        return longitude;
    }

    /**
     * Set area 区.
     */
    public void setArea(String area){
        this.area = area;
    }

    /**
     * Get area 区.
     *
     * @return the string
     */
    public String getArea(){
        return area;
    }

    /**
     * Set city 市.
     */
    public void setCity(String city){
        this.city = city;
    }

    /**
     * Get city 市.
     *
     * @return the string
     */
    public String getCity(){
        return city;
    }

    /**
     * Set street 街道（与小区表保持一致）.
     */
    public void setStreet(String street){
        this.street = street;
    }

    /**
     * Get street 街道（与小区表保持一致）.
     *
     * @return the string
     */
    public String getStreet(){
        return street;
    }

    /**
     * Set vendor 设备生产厂商名称.
     */
    public void setVendor(String vendor){
        this.vendor = vendor;
    }

    /**
     * Get vendor 设备生产厂商名称.
     *
     * @return the string
     */
    public String getVendor(){
        return vendor;
    }

    /**
     * Set address 详细地址.
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * Get address 详细地址.
     *
     * @return the string
     */
    public String getAddress(){
        return address;
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
     * Set safeMan 维保人员.
     */
    public void setSafeMan(String safeMan){
        this.safeMan = safeMan;
    }

    /**
     * Get safeMan 维保人员.
     *
     * @return the string
     */
    public String getSafeMan(){
        return safeMan;
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
     * Set province 省.
     */
    public void setProvince(String province){
        this.province = province;
    }

    /**
     * Get province 省.
     *
     * @return the string
     */
    public String getProvince(){
        return province;
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
     * Set safePhone 联系电话.
     */
    public void setSafePhone(String safePhone){
        this.safePhone = safePhone;
    }

    /**
     * Get safePhone 联系电话.
     *
     * @return the string
     */
    public String getSafePhone(){
        return safePhone;
    }

    /**
     * Set deviceCode 编号（比如1#左）.
     */
    public void setDeviceCode(String deviceCode){
        this.deviceCode = deviceCode;
    }

    /**
     * Get deviceCode 编号（比如1#左）.
     *
     * @return the string
     */
    public String getDeviceCode(){
        return deviceCode;
    }

    /**
     * Set deviceDesc 设备描述.
     */
    public void setDeviceDesc(String deviceDesc){
        this.deviceDesc = deviceDesc;
    }

    /**
     * Get deviceDesc 设备描述.
     *
     * @return the string
     */
    public String getDeviceDesc(){
        return deviceDesc;
    }

    /**
     * Set deviceName 设备名称.
     */
    public void setDeviceName(String deviceName){
        this.deviceName = deviceName;
    }

    /**
     * Get deviceName 设备名称.
     *
     * @return the string
     */
    public String getDeviceName(){
        return deviceName;
    }

    /**
     * Set installMan 安装人.
     */
    public void setInstallMan(String installMan){
        this.installMan = installMan;
    }

    /**
     * Get installMan 安装人.
     *
     * @return the string
     */
    public String getInstallMan(){
        return installMan;
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
     * Set villageName 小区名.
     */
    public void setVillageName(String villageName){
        this.villageName = villageName;
    }

    /**
     * Get villageName 小区名.
     *
     * @return the string
     */
    public String getVillageName(){
        return villageName;
    }

    /**
     * Set equipmentUuid uuid.
     */
    public void setEquipmentUuid(String equipmentUuid){
        this.equipmentUuid = equipmentUuid;
    }

    /**
     * Get equipmentUuid uuid.
     *
     * @return the string
     */
    public String getEquipmentUuid(){
        return equipmentUuid;
    }

    /**
     * Set floorNum 楼层数量.
     */
    public void setFloorNum(Integer floorNum){
        this.floorNum = floorNum;
    }

    /**
     * Get floorNum 楼层数量.
     *
     * @return the string
     */
    public Integer getFloorNum(){
        return floorNum;
    }

    /**
     * Set floorDownnum 下楼层数量.
     */
    public void setFloorDownnum(Integer floorDownnum){
        this.floorDownnum = floorDownnum;
    }

    /**
     * Get floorDownnum 下楼层数量.
     *
     * @return the string
     */
    public Integer getFloorDownnum(){
        return floorDownnum;
    }

    /**
     * Set setupTime 设备安装时间.
     */
    public void setSetupTime(Date setupTime){
        this.setupTime = setupTime;
    }

    /**
     * Get setupTime 设备安装时间.
     *
     * @return the string
     */
    public Date getSetupTime(){
        return setupTime;
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
     * Set productTime 生产时间.
     */
    public void setProductTime(Date productTime){
        this.productTime = productTime;
    }

    /**
     * Get productTime 生产时间.
     *
     * @return the string
     */
    public Date getProductTime(){
        return productTime;
    }
}
