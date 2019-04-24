package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;

/**
 * The table 河道水采集日志
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class RwDataLogDO {

    /**
     * id ID.
     */
    private Long id;
    /**
     * signalSnr SIGNAL_SNR.
     */
    private Float signalSnr;
    /**
     * signalRsrp 信号RSRP.
     */
    private Float signalRsrp;
    /**
     * signalRssi 信号RSSI.
     */
    private Float signalRssi;
    /**
     * ph 酸碱度.
     */
    private String ph;
    /**
     * cmd 命令.
     */
    private String cmd;
    /**
     * rdo 溶解氧 单位 mg/L.
     */
    private String rdo;
    /**
     * tss 浊度 单位NTU.
     */
    private String tss;
    /**
     * ccid 设备CCID号.
     */
    private String ccid;
    /**
     * imei 模块IMEI号.
     */
    private String imei;
    /**
     * nh3N 氨氮.
     */
    private String nh3N;
    /**
     * depth 水深 单位cm.
     */
    private String depth;
    /**
     * devId 设备ID.
     */
    private String devId;
    /**
     * level 水质等级.
     */
    private String level;
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
     * latitude 纬度.
     */
    private String latitude;
    /**
     * powerPct 电量.
     */
    private String powerPct;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * faultInfo 故障信息 2离水报警.
     */
    private String faultInfo;
    /**
     * levelShow 水质等级显示.
     */
    private String levelShow;
    /**
     * longitude 经度.
     */
    private String longitude;
    /**
     * powerType 供电类型0电池 1光伏.
     */
    private String powerType;
    /**
     * collectDay 采集日.
     */
    private String collectDay;
    /**
     * collectMin 采集分.
     */
    private String collectMin;
    /**
     * collectHour 采集时.
     */
    private String collectHour;
    /**
     * collectYear 采集年.
     */
    private String collectYear;
    /**
     * collectMonth 采集月.
     */
    private String collectMonth;
    /**
     * conductivity 电导率 电导率 μS/cm.
     */
    private String conductivity;
    /**
     * inTemperature 内部温度.
     */
    private String inTemperature;
    /**
     * outTemperature 外部温度（共用）.
     */
    private String outTemperature;
    /**
     * lac 基站lac.
     */
    private Integer lac;
    /**
     * cellId sim卡CCID号.
     */
    private Integer cellId;
    /**
     * fault1 故障1.
     */
    private Integer fault1;
    /**
     * fault2 故障2.
     */
    private Integer fault2;
    /**
     * fault3 故障3.
     */
    private Integer fault3;
    /**
     * fault4 故障4.
     */
    private Integer fault4;
    /**
     * fault5 故障5.
     */
    private Integer fault5;
    /**
     * fault6 故障6.
     */
    private Integer fault6;
    /**
     * fault7 故障7.
     */
    private Integer fault7;
    /**
     * fault8 故障8.
     */
    private Integer fault8;
    /**
     * dataType 数据类型	1：上报2报警.
     */
    private Integer dataType;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;
    /**
     * collectTime 采集时间.
     */
    private Date collectTime;
    /**
     * lastCollectTime 上次采集时间.
     */
    private Date lastCollectTime;

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
     * Set signalSnr SIGNAL_SNR.
     */
    public void setSignalSnr(Float signalSnr){
        this.signalSnr = signalSnr;
    }

    /**
     * Get signalSnr SIGNAL_SNR.
     *
     * @return the string
     */
    public Float getSignalSnr(){
        return signalSnr;
    }

    /**
     * Set signalRsrp 信号RSRP.
     */
    public void setSignalRsrp(Float signalRsrp){
        this.signalRsrp = signalRsrp;
    }

    /**
     * Get signalRsrp 信号RSRP.
     *
     * @return the string
     */
    public Float getSignalRsrp(){
        return signalRsrp;
    }

    /**
     * Set signalRssi 信号RSSI.
     */
    public void setSignalRssi(Float signalRssi){
        this.signalRssi = signalRssi;
    }

    /**
     * Get signalRssi 信号RSSI.
     *
     * @return the string
     */
    public Float getSignalRssi(){
        return signalRssi;
    }

    /**
     * Set ph 酸碱度.
     */
    public void setPh(String ph){
        this.ph = ph;
    }

    /**
     * Get ph 酸碱度.
     *
     * @return the string
     */
    public String getPh(){
        return ph;
    }

    /**
     * Set cmd 命令.
     */
    public void setCmd(String cmd){
        this.cmd = cmd;
    }

    /**
     * Get cmd 命令.
     *
     * @return the string
     */
    public String getCmd(){
        return cmd;
    }

    /**
     * Set rdo 溶解氧 单位 mg/L.
     */
    public void setRdo(String rdo){
        this.rdo = rdo;
    }

    /**
     * Get rdo 溶解氧 单位 mg/L.
     *
     * @return the string
     */
    public String getRdo(){
        return rdo;
    }

    /**
     * Set tss 浊度 单位NTU.
     */
    public void setTss(String tss){
        this.tss = tss;
    }

    /**
     * Get tss 浊度 单位NTU.
     *
     * @return the string
     */
    public String getTss(){
        return tss;
    }

    /**
     * Set ccid 设备CCID号.
     */
    public void setCcid(String ccid){
        this.ccid = ccid;
    }

    /**
     * Get ccid 设备CCID号.
     *
     * @return the string
     */
    public String getCcid(){
        return ccid;
    }

    /**
     * Set imei 模块IMEI号.
     */
    public void setImei(String imei){
        this.imei = imei;
    }

    /**
     * Get imei 模块IMEI号.
     *
     * @return the string
     */
    public String getImei(){
        return imei;
    }

    /**
     * Set nh3N 氨氮.
     */
    public void setNh3N(String nh3N){
        this.nh3N = nh3N;
    }

    /**
     * Get nh3N 氨氮.
     *
     * @return the string
     */
    public String getNh3N(){
        return nh3N;
    }

    /**
     * Set depth 水深 单位cm.
     */
    public void setDepth(String depth){
        this.depth = depth;
    }

    /**
     * Get depth 水深 单位cm.
     *
     * @return the string
     */
    public String getDepth(){
        return depth;
    }

    /**
     * Set devId 设备ID.
     */
    public void setDevId(String devId){
        this.devId = devId;
    }

    /**
     * Get devId 设备ID.
     *
     * @return the string
     */
    public String getDevId(){
        return devId;
    }

    /**
     * Set level 水质等级.
     */
    public void setLevel(String level){
        this.level = level;
    }

    /**
     * Get level 水质等级.
     *
     * @return the string
     */
    public String getLevel(){
        return level;
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
     * Set latitude 纬度.
     */
    public void setLatitude(String latitude){
        this.latitude = latitude;
    }

    /**
     * Get latitude 纬度.
     *
     * @return the string
     */
    public String getLatitude(){
        return latitude;
    }

    /**
     * Set powerPct 电量.
     */
    public void setPowerPct(String powerPct){
        this.powerPct = powerPct;
    }

    /**
     * Get powerPct 电量.
     *
     * @return the string
     */
    public String getPowerPct(){
        return powerPct;
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
     * Set faultInfo 故障信息 2离水报警.
     */
    public void setFaultInfo(String faultInfo){
        this.faultInfo = faultInfo;
    }

    /**
     * Get faultInfo 故障信息 2离水报警.
     *
     * @return the string
     */
    public String getFaultInfo(){
        return faultInfo;
    }

    /**
     * Set levelShow 水质等级显示.
     */
    public void setLevelShow(String levelShow){
        this.levelShow = levelShow;
    }

    /**
     * Get levelShow 水质等级显示.
     *
     * @return the string
     */
    public String getLevelShow(){
        return levelShow;
    }

    /**
     * Set longitude 经度.
     */
    public void setLongitude(String longitude){
        this.longitude = longitude;
    }

    /**
     * Get longitude 经度.
     *
     * @return the string
     */
    public String getLongitude(){
        return longitude;
    }

    /**
     * Set powerType 供电类型0电池 1光伏.
     */
    public void setPowerType(String powerType){
        this.powerType = powerType;
    }

    /**
     * Get powerType 供电类型0电池 1光伏.
     *
     * @return the string
     */
    public String getPowerType(){
        return powerType;
    }

    /**
     * Set collectDay 采集日.
     */
    public void setCollectDay(String collectDay){
        this.collectDay = collectDay;
    }

    /**
     * Get collectDay 采集日.
     *
     * @return the string
     */
    public String getCollectDay(){
        return collectDay;
    }

    /**
     * Set collectMin 采集分.
     */
    public void setCollectMin(String collectMin){
        this.collectMin = collectMin;
    }

    /**
     * Get collectMin 采集分.
     *
     * @return the string
     */
    public String getCollectMin(){
        return collectMin;
    }

    /**
     * Set collectHour 采集时.
     */
    public void setCollectHour(String collectHour){
        this.collectHour = collectHour;
    }

    /**
     * Get collectHour 采集时.
     *
     * @return the string
     */
    public String getCollectHour(){
        return collectHour;
    }

    /**
     * Set collectYear 采集年.
     */
    public void setCollectYear(String collectYear){
        this.collectYear = collectYear;
    }

    /**
     * Get collectYear 采集年.
     *
     * @return the string
     */
    public String getCollectYear(){
        return collectYear;
    }

    /**
     * Set collectMonth 采集月.
     */
    public void setCollectMonth(String collectMonth){
        this.collectMonth = collectMonth;
    }

    /**
     * Get collectMonth 采集月.
     *
     * @return the string
     */
    public String getCollectMonth(){
        return collectMonth;
    }

    /**
     * Set conductivity 电导率 电导率 μS/cm.
     */
    public void setConductivity(String conductivity){
        this.conductivity = conductivity;
    }

    /**
     * Get conductivity 电导率 电导率 μS/cm.
     *
     * @return the string
     */
    public String getConductivity(){
        return conductivity;
    }

    /**
     * Set inTemperature 内部温度.
     */
    public void setInTemperature(String inTemperature){
        this.inTemperature = inTemperature;
    }

    /**
     * Get inTemperature 内部温度.
     *
     * @return the string
     */
    public String getInTemperature(){
        return inTemperature;
    }

    /**
     * Set outTemperature 外部温度（共用）.
     */
    public void setOutTemperature(String outTemperature){
        this.outTemperature = outTemperature;
    }

    /**
     * Get outTemperature 外部温度（共用）.
     *
     * @return the string
     */
    public String getOutTemperature(){
        return outTemperature;
    }

    /**
     * Set lac 基站lac.
     */
    public void setLac(Integer lac){
        this.lac = lac;
    }

    /**
     * Get lac 基站lac.
     *
     * @return the string
     */
    public Integer getLac(){
        return lac;
    }

    /**
     * Set cellId sim卡CCID号.
     */
    public void setCellId(Integer cellId){
        this.cellId = cellId;
    }

    /**
     * Get cellId sim卡CCID号.
     *
     * @return the string
     */
    public Integer getCellId(){
        return cellId;
    }

    /**
     * Set fault1 故障1.
     */
    public void setFault1(Integer fault1){
        this.fault1 = fault1;
    }

    /**
     * Get fault1 故障1.
     *
     * @return the string
     */
    public Integer getFault1(){
        return fault1;
    }

    /**
     * Set fault2 故障2.
     */
    public void setFault2(Integer fault2){
        this.fault2 = fault2;
    }

    /**
     * Get fault2 故障2.
     *
     * @return the string
     */
    public Integer getFault2(){
        return fault2;
    }

    /**
     * Set fault3 故障3.
     */
    public void setFault3(Integer fault3){
        this.fault3 = fault3;
    }

    /**
     * Get fault3 故障3.
     *
     * @return the string
     */
    public Integer getFault3(){
        return fault3;
    }

    /**
     * Set fault4 故障4.
     */
    public void setFault4(Integer fault4){
        this.fault4 = fault4;
    }

    /**
     * Get fault4 故障4.
     *
     * @return the string
     */
    public Integer getFault4(){
        return fault4;
    }

    /**
     * Set fault5 故障5.
     */
    public void setFault5(Integer fault5){
        this.fault5 = fault5;
    }

    /**
     * Get fault5 故障5.
     *
     * @return the string
     */
    public Integer getFault5(){
        return fault5;
    }

    /**
     * Set fault6 故障6.
     */
    public void setFault6(Integer fault6){
        this.fault6 = fault6;
    }

    /**
     * Get fault6 故障6.
     *
     * @return the string
     */
    public Integer getFault6(){
        return fault6;
    }

    /**
     * Set fault7 故障7.
     */
    public void setFault7(Integer fault7){
        this.fault7 = fault7;
    }

    /**
     * Get fault7 故障7.
     *
     * @return the string
     */
    public Integer getFault7(){
        return fault7;
    }

    /**
     * Set fault8 故障8.
     */
    public void setFault8(Integer fault8){
        this.fault8 = fault8;
    }

    /**
     * Get fault8 故障8.
     *
     * @return the string
     */
    public Integer getFault8(){
        return fault8;
    }

    /**
     * Set dataType 数据类型	1：上报2报警.
     */
    public void setDataType(Integer dataType){
        this.dataType = dataType;
    }

    /**
     * Get dataType 数据类型	1：上报2报警.
     *
     * @return the string
     */
    public Integer getDataType(){
        return dataType;
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

    /**
     * Set lastCollectTime 上次采集时间.
     */
    public void setLastCollectTime(Date lastCollectTime){
        this.lastCollectTime = lastCollectTime;
    }

    /**
     * Get lastCollectTime 上次采集时间.
     *
     * @return the string
     */
    public Date getLastCollectTime(){
        return lastCollectTime;
    }

    @Override
    public String toString() {
        return "RwDataLogDO{" +
                "id=" + id +
                ", signalSnr=" + signalSnr +
                ", signalRsrp=" + signalRsrp +
                ", signalRssi=" + signalRssi +
                ", ph='" + ph + '\'' +
                ", cmd='" + cmd + '\'' +
                ", rdo='" + rdo + '\'' +
                ", tss='" + tss + '\'' +
                ", ccid='" + ccid + '\'' +
                ", imei='" + imei + '\'' +
                ", nh3N='" + nh3N + '\'' +
                ", depth='" + depth + '\'' +
                ", devId='" + devId + '\'' +
                ", level='" + level + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", createBy='" + createBy + '\'' +
                ", deviceNo='" + deviceNo + '\'' +
                ", latitude='" + latitude + '\'' +
                ", powerPct='" + powerPct + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", faultInfo='" + faultInfo + '\'' +
                ", levelShow='" + levelShow + '\'' +
                ", longitude='" + longitude + '\'' +
                ", powerType='" + powerType + '\'' +
                ", collectDay='" + collectDay + '\'' +
                ", collectMin='" + collectMin + '\'' +
                ", collectHour='" + collectHour + '\'' +
                ", collectYear='" + collectYear + '\'' +
                ", collectMonth='" + collectMonth + '\'' +
                ", conductivity='" + conductivity + '\'' +
                ", inTemperature='" + inTemperature + '\'' +
                ", outTemperature='" + outTemperature + '\'' +
                ", lac=" + lac +
                ", cellId=" + cellId +
                ", fault1=" + fault1 +
                ", fault2=" + fault2 +
                ", fault3=" + fault3 +
                ", fault4=" + fault4 +
                ", fault5=" + fault5 +
                ", fault6=" + fault6 +
                ", fault7=" + fault7 +
                ", fault8=" + fault8 +
                ", dataType=" + dataType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", collectTime=" + collectTime +
                ", lastCollectTime=" + lastCollectTime +
                '}';
    }
}
