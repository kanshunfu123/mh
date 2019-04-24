package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mh.mapper.dataobject.MhDataLogDO;

/**
 * The table 井盖日志
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class MhDataLogDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * signalSnr 信噪比,，单位：10dB，例：-33=-3.3dB.
     */
    private Float signalSnr;
    /**
     * signalRsrp 信号接收功率,单位：10dbm，例：-919=-91.9dbm.
     */
    private Float signalRsrp;
    /**
     * signalRssi 接收的信号强度指示,单位：10dbm，例：-804=-80.4dbm.
     */
    private Float signalRssi;
    /**
     * cmd 命令.
     */
    private String cmd;
    /**
     * ccid 设备CCID号.
     */
    private String ccid;
    /**
     * imei IMEI号.
     */
    private String imei;
    /**
     * devId 设备id.
     */
    private String devId;
    /**
     * level 水质等级.
     */
    private String level;
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * faultInfo 故障信息.
     */
    private String faultInfo;
    /**
     * levelShow 水质等级显示.
     */
    private String levelShow;
    /**
     * fireGasCon 可燃气浓度 单位%LEL，保留1位小数.
     */
    private String fireGasCon;
    /**
     * networkSignal 网络信号值.
     */
    private String networkSignal;
    /**
     * acquisitionUuid 采集uuid.
     */
    private String acquisitionUuid;
    /**
     * lac 基站LAC号.
     */
    private Integer lac;
    /**
     * cellId 基站CELL ID号 配合LAC号可查询当前连接的基站设置.
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
     * gasH2s 硫化氢浓度，单位：ppm.
     */
    private Integer gasH2s;
    /**
     * powerMh 电池电量.
     */
    private Integer powerMh;
    /**
     * dataType 数据类型	1：数据上报 2：报警数据.
     */
    private Integer dataType;
    /**
     * humidity 湿度.
     */
    private Integer humidity;
    /**
     * waterLine 水位.
     */
    private Integer waterLine;
    /**
     * temperature 温度 单位℃.
     */
    private Integer temperature;
    /**
     * wellOpenSts 设备开盖状态.
     */
    private Integer wellOpenSts;
    /**
     * switchWaterLine 开关水位 0：正常 1：一级报警，2:二级报警.
     */
    private Integer switchWaterLine;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
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
     * Set signalSnr 信噪比,，单位：10dB，例：-33=-3.3dB.
     */
    public void setSignalSnr(Float signalSnr){
        this.signalSnr = signalSnr;
    }

    /**
     * Get signalSnr 信噪比,，单位：10dB，例：-33=-3.3dB.
     *
     * @return the string
     */
    public Float getSignalSnr(){
        return signalSnr;
    }

    /**
     * Set signalRsrp 信号接收功率,单位：10dbm，例：-919=-91.9dbm.
     */
    public void setSignalRsrp(Float signalRsrp){
        this.signalRsrp = signalRsrp;
    }

    /**
     * Get signalRsrp 信号接收功率,单位：10dbm，例：-919=-91.9dbm.
     *
     * @return the string
     */
    public Float getSignalRsrp(){
        return signalRsrp;
    }

    /**
     * Set signalRssi 接收的信号强度指示,单位：10dbm，例：-804=-80.4dbm.
     */
    public void setSignalRssi(Float signalRssi){
        this.signalRssi = signalRssi;
    }

    /**
     * Get signalRssi 接收的信号强度指示,单位：10dbm，例：-804=-80.4dbm.
     *
     * @return the string
     */
    public Float getSignalRssi(){
        return signalRssi;
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
     * Set imei IMEI号.
     */
    public void setImei(String imei){
        this.imei = imei;
    }

    /**
     * Get imei IMEI号.
     *
     * @return the string
     */
    public String getImei(){
        return imei;
    }

    /**
     * Set devId 设备id.
     */
    public void setDevId(String devId){
        this.devId = devId;
    }

    /**
     * Get devId 设备id.
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
     * Set faultInfo 故障信息.
     */
    public void setFaultInfo(String faultInfo){
        this.faultInfo = faultInfo;
    }

    /**
     * Get faultInfo 故障信息.
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
     * Set fireGasCon 可燃气浓度 单位%LEL，保留1位小数.
     */
    public void setFireGasCon(String fireGasCon){
        this.fireGasCon = fireGasCon;
    }

    /**
     * Get fireGasCon 可燃气浓度 单位%LEL，保留1位小数.
     *
     * @return the string
     */
    public String getFireGasCon(){
        return fireGasCon;
    }

    /**
     * Set networkSignal 网络信号值.
     */
    public void setNetworkSignal(String networkSignal){
        this.networkSignal = networkSignal;
    }

    /**
     * Get networkSignal 网络信号值.
     *
     * @return the string
     */
    public String getNetworkSignal(){
        return networkSignal;
    }

    /**
     * Set acquisitionUuid 采集uuid.
     */
    public void setAcquisitionUuid(String acquisitionUuid){
        this.acquisitionUuid = acquisitionUuid;
    }

    /**
     * Get acquisitionUuid 采集uuid.
     *
     * @return the string
     */
    public String getAcquisitionUuid(){
        return acquisitionUuid;
    }

    /**
     * Set lac 基站LAC号.
     */
    public void setLac(Integer lac){
        this.lac = lac;
    }

    /**
     * Get lac 基站LAC号.
     *
     * @return the string
     */
    public Integer getLac(){
        return lac;
    }

    /**
     * Set cellId 基站CELL ID号 配合LAC号可查询当前连接的基站设置.
     */
    public void setCellId(Integer cellId){
        this.cellId = cellId;
    }

    /**
     * Get cellId 基站CELL ID号 配合LAC号可查询当前连接的基站设置.
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
     * Set gasH2s 硫化氢浓度，单位：ppm.
     */
    public void setGasH2s(Integer gasH2s){
        this.gasH2s = gasH2s;
    }

    /**
     * Get gasH2s 硫化氢浓度，单位：ppm.
     *
     * @return the string
     */
    public Integer getGasH2s(){
        return gasH2s;
    }

    /**
     * Set powerMh 电池电量.
     */
    public void setPowerMh(Integer powerMh){
        this.powerMh = powerMh;
    }

    /**
     * Get powerMh 电池电量.
     *
     * @return the string
     */
    public Integer getPowerMh(){
        return powerMh;
    }

    /**
     * Set dataType 数据类型	1：数据上报 2：报警数据.
     */
    public void setDataType(Integer dataType){
        this.dataType = dataType;
    }

    /**
     * Get dataType 数据类型	1：数据上报 2：报警数据.
     *
     * @return the string
     */
    public Integer getDataType(){
        return dataType;
    }

    /**
     * Set humidity 湿度.
     */
    public void setHumidity(Integer humidity){
        this.humidity = humidity;
    }

    /**
     * Get humidity 湿度.
     *
     * @return the string
     */
    public Integer getHumidity(){
        return humidity;
    }

    /**
     * Set waterLine 水位.
     */
    public void setWaterLine(Integer waterLine){
        this.waterLine = waterLine;
    }

    /**
     * Get waterLine 水位.
     *
     * @return the string
     */
    public Integer getWaterLine(){
        return waterLine;
    }

    /**
     * Set temperature 温度 单位℃.
     */
    public void setTemperature(Integer temperature){
        this.temperature = temperature;
    }

    /**
     * Get temperature 温度 单位℃.
     *
     * @return the string
     */
    public Integer getTemperature(){
        return temperature;
    }

    /**
     * Set wellOpenSts 设备开盖状态.
     */
    public void setWellOpenSts(Integer wellOpenSts){
        this.wellOpenSts = wellOpenSts;
    }

    /**
     * Get wellOpenSts 设备开盖状态.
     *
     * @return the string
     */
    public Integer getWellOpenSts(){
        return wellOpenSts;
    }

    /**
     * Set switchWaterLine 开关水位 0：正常 1：一级报警，2:二级报警.
     */
    public void setSwitchWaterLine(Integer switchWaterLine){
        this.switchWaterLine = switchWaterLine;
    }

    /**
     * Get switchWaterLine 开关水位 0：正常 1：一级报警，2:二级报警.
     *
     * @return the string
     */
    public Integer getSwitchWaterLine(){
        return switchWaterLine;
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
}
