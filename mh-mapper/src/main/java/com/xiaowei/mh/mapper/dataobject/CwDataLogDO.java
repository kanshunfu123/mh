package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;

/**
 * The table 饮用水日志表
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class CwDataLogDO {

    /**
     * id 主键.
     */
    private Long id;
    /**
     * ph ph值 数值除以100，保留2位小数，-1代表无效.
     */
    private Float ph;
    /**
     * oxygen 溶解氧 数值除以100，保留2位小数，-1代表无效.
     */
    private Float oxygen;
    /**
     * chlorine 余氯数值除以100，保留2位小数，-1代表无效.
     */
    private Float chlorine;
    /**
     * signalSnr SIGNAL_SNR.
     */
    private Float signalSnr;
    /**
     * turbidity 浊度 数值除以100，保留2位小数，-1代表无效.
     */
    private Float turbidity;
    /**
     * signalRsrp 信号RSRP.
     */
    private Float signalRsrp;
    /**
     * signalRssi 信号RSSI.
     */
    private Float signalRssi;
    /**
     * temperature 数值除以100，保留2位小数，-100代表无效.
     */
    private Float temperature;
    /**
     * ccid sim卡CCID号.
     */
    private String ccid;
    /**
     * imei 模块IMEI号.
     */
    private String imei;
    /**
     * devId 设备ID.
     */
    private String devId;
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * faultInfo 数据报警 0正常.
     */
    private String faultInfo;
    /**
     * lac 基站lac.
     */
    private Integer lac;
    /**
     * cellId sim卡CCID号.
     */
    private Integer cellId;
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
     * devType 2饮用水.
     */
    private Integer devType;
    /**
     * dataType 数据类型	1：上报.
     */
    private Integer dataType;
    /**
     * collectionSts 采集端在线状态 0离线 1在线.
     */
    private Integer collectionSts;
    /**
     * createTime 创建时间.
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
     * Set ph ph值 数值除以100，保留2位小数，-1代表无效.
     */
    public void setPh(Float ph){
        this.ph = ph;
    }

    /**
     * Get ph ph值 数值除以100，保留2位小数，-1代表无效.
     *
     * @return the string
     */
    public Float getPh(){
        return ph;
    }

    /**
     * Set oxygen 溶解氧 数值除以100，保留2位小数，-1代表无效.
     */
    public void setOxygen(Float oxygen){
        this.oxygen = oxygen;
    }

    /**
     * Get oxygen 溶解氧 数值除以100，保留2位小数，-1代表无效.
     *
     * @return the string
     */
    public Float getOxygen(){
        return oxygen;
    }

    /**
     * Set chlorine 余氯数值除以100，保留2位小数，-1代表无效.
     */
    public void setChlorine(Float chlorine){
        this.chlorine = chlorine;
    }

    /**
     * Get chlorine 余氯数值除以100，保留2位小数，-1代表无效.
     *
     * @return the string
     */
    public Float getChlorine(){
        return chlorine;
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
     * Set turbidity 浊度 数值除以100，保留2位小数，-1代表无效.
     */
    public void setTurbidity(Float turbidity){
        this.turbidity = turbidity;
    }

    /**
     * Get turbidity 浊度 数值除以100，保留2位小数，-1代表无效.
     *
     * @return the string
     */
    public Float getTurbidity(){
        return turbidity;
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
     * Set temperature 数值除以100，保留2位小数，-100代表无效.
     */
    public void setTemperature(Float temperature){
        this.temperature = temperature;
    }

    /**
     * Get temperature 数值除以100，保留2位小数，-100代表无效.
     *
     * @return the string
     */
    public Float getTemperature(){
        return temperature;
    }

    /**
     * Set ccid sim卡CCID号.
     */
    public void setCcid(String ccid){
        this.ccid = ccid;
    }

    /**
     * Get ccid sim卡CCID号.
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
     * Set faultInfo 数据报警 0正常.
     */
    public void setFaultInfo(String faultInfo){
        this.faultInfo = faultInfo;
    }

    /**
     * Get faultInfo 数据报警 0正常.
     *
     * @return the string
     */
    public String getFaultInfo(){
        return faultInfo;
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
     * Set devType 2饮用水.
     */
    public void setDevType(Integer devType){
        this.devType = devType;
    }

    /**
     * Get devType 2饮用水.
     *
     * @return the string
     */
    public Integer getDevType(){
        return devType;
    }

    /**
     * Set dataType 数据类型	1：上报.
     */
    public void setDataType(Integer dataType){
        this.dataType = dataType;
    }

    /**
     * Get dataType 数据类型	1：上报.
     *
     * @return the string
     */
    public Integer getDataType(){
        return dataType;
    }

    /**
     * Set collectionSts 采集端在线状态 0离线 1在线.
     */
    public void setCollectionSts(Integer collectionSts){
        this.collectionSts = collectionSts;
    }

    /**
     * Get collectionSts 采集端在线状态 0离线 1在线.
     *
     * @return the string
     */
    public Integer getCollectionSts(){
        return collectionSts;
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
