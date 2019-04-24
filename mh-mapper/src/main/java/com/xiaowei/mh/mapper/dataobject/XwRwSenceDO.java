package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mh.mapper.dataobject.XwRwSenceDO;

/**
 * The table 河道水场景
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class XwRwSenceDO{

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
     * rwCode 地表水位置码.
     */
    private String rwCode;
    /**
     * rwName 河道名称.
     */
    private String rwName;
    /**
     * rwType 河道使用类型.
     */
    private String rwType;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * rwDepth 水深.
     */
    private String rwDepth;
    /**
     * rwLevee 岸堤情况.
     */
    private String rwLevee;
    /**
     * rwRange 河道范围.
     */
    private String rwRange;
    /**
     * rwWidth 河宽.
     */
    private String rwWidth;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * rwLength 河长.
     */
    private String rwLength;
    /**
     * rwRemark 水源信息.
     */
    private String rwRemark;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * senceUuid 河道水场景uuid.
     */
    private String senceUuid;
    /**
     * principalPhone 负责人联系方式.
     */
    private String principalPhone;
    /**
     * sencePrincipal 河道负责人.
     */
    private String sencePrincipal;
    /**
     * rwPolluteSource 周围污染源.
     */
    private String rwPolluteSource;
    /**
     * rwGrade 河道等级(1.一类 2.二类 3.三类 4. 四类 5.五类).
     */
    private Integer rwGrade;
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
     * Set rwCode 地表水位置码.
     */
    public void setRwCode(String rwCode){
        this.rwCode = rwCode;
    }

    /**
     * Get rwCode 地表水位置码.
     *
     * @return the string
     */
    public String getRwCode(){
        return rwCode;
    }

    /**
     * Set rwName 河道名称.
     */
    public void setRwName(String rwName){
        this.rwName = rwName;
    }

    /**
     * Get rwName 河道名称.
     *
     * @return the string
     */
    public String getRwName(){
        return rwName;
    }

    /**
     * Set rwType 河道使用类型.
     */
    public void setRwType(String rwType){
        this.rwType = rwType;
    }

    /**
     * Get rwType 河道使用类型.
     *
     * @return the string
     */
    public String getRwType(){
        return rwType;
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
     * Set rwDepth 水深.
     */
    public void setRwDepth(String rwDepth){
        this.rwDepth = rwDepth;
    }

    /**
     * Get rwDepth 水深.
     *
     * @return the string
     */
    public String getRwDepth(){
        return rwDepth;
    }

    /**
     * Set rwLevee 岸堤情况.
     */
    public void setRwLevee(String rwLevee){
        this.rwLevee = rwLevee;
    }

    /**
     * Get rwLevee 岸堤情况.
     *
     * @return the string
     */
    public String getRwLevee(){
        return rwLevee;
    }

    /**
     * Set rwRange 河道范围.
     */
    public void setRwRange(String rwRange){
        this.rwRange = rwRange;
    }

    /**
     * Get rwRange 河道范围.
     *
     * @return the string
     */
    public String getRwRange(){
        return rwRange;
    }

    /**
     * Set rwWidth 河宽.
     */
    public void setRwWidth(String rwWidth){
        this.rwWidth = rwWidth;
    }

    /**
     * Get rwWidth 河宽.
     *
     * @return the string
     */
    public String getRwWidth(){
        return rwWidth;
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
     * Set rwLength 河长.
     */
    public void setRwLength(String rwLength){
        this.rwLength = rwLength;
    }

    /**
     * Get rwLength 河长.
     *
     * @return the string
     */
    public String getRwLength(){
        return rwLength;
    }

    /**
     * Set rwRemark 水源信息.
     */
    public void setRwRemark(String rwRemark){
        this.rwRemark = rwRemark;
    }

    /**
     * Get rwRemark 水源信息.
     *
     * @return the string
     */
    public String getRwRemark(){
        return rwRemark;
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
     * Set senceUuid 河道水场景uuid.
     */
    public void setSenceUuid(String senceUuid){
        this.senceUuid = senceUuid;
    }

    /**
     * Get senceUuid 河道水场景uuid.
     *
     * @return the string
     */
    public String getSenceUuid(){
        return senceUuid;
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
     * Set sencePrincipal 河道负责人.
     */
    public void setSencePrincipal(String sencePrincipal){
        this.sencePrincipal = sencePrincipal;
    }

    /**
     * Get sencePrincipal 河道负责人.
     *
     * @return the string
     */
    public String getSencePrincipal(){
        return sencePrincipal;
    }

    /**
     * Set rwPolluteSource 周围污染源.
     */
    public void setRwPolluteSource(String rwPolluteSource){
        this.rwPolluteSource = rwPolluteSource;
    }

    /**
     * Get rwPolluteSource 周围污染源.
     *
     * @return the string
     */
    public String getRwPolluteSource(){
        return rwPolluteSource;
    }

    /**
     * Set rwGrade 河道等级(1.一类 2.二类 3.三类 4. 四类 5.五类).
     */
    public void setRwGrade(Integer rwGrade){
        this.rwGrade = rwGrade;
    }

    /**
     * Get rwGrade 河道等级(1.一类 2.二类 3.三类 4. 四类 5.五类).
     *
     * @return the string
     */
    public Integer getRwGrade(){
        return rwGrade;
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
