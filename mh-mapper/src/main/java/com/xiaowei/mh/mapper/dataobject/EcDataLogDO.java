package com.xiaowei.mh.mapper.dataobject;

import java.util.Date;

/**
 * The table 电梯日志
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class EcDataLogDO {

    /**
     * id ID.
     */
    private Long id;
    /**
     * cmd 命令.
     */
    private String cmd;
    /**
     * ccid 设备CCID号.
     */
    private String ccid;
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * runSpeed 运行速度.
     */
    private String runSpeed;
    /**
     * faultInfo 故障信息.
     */
    private String faultInfo;
    /**
     * collectDay 采集日.
     */
    private String collectDay;
    /**
     * collectMin 采集分.
     */
    private String collectMin;
    /**
     * deviceName 设备平成.
     */
    private String deviceName;
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
     * networkSignal 网络信号值.
     */
    private String networkSignal;
    /**
     * floor 楼层.
     */
    private Integer floor;
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
     * runSts 运行状态.
     */
    private Integer runSts;
    /**
     * upFlat 上平层.
     */
    private Integer upFlat;
    /**
     * upLimit 上限位.
     */
    private Integer upLimit;
    /**
     * downFlat 下平层.
     */
    private Integer downFlat;
    /**
     * downLimit 下限位.
     */
    private Integer downLimit;
    /**
     * hasPeople 是否有人.
     */
    private Integer hasPeople;
    /**
     * atPowerPct 采集端电量 电量.
     */
    private Integer atPowerPct;
    /**
     * ctPowerPct 通讯端电量 电量.
     */
    private Integer ctPowerPct;
    /**
     * atPowerType 采集端电量 供电类型.
     */
    private Integer atPowerType;
    /**
     * ctPowerType 通讯端电量 供电类型.
     */
    private Integer ctPowerType;
    /**
     * doorLockSts 门锁状态.
     */
    private Integer doorLockSts;
    /**
     * runDirection 运行方向.
     */
    private Integer runDirection;
    /**
     * collectionSts 采集端在线状态 1在线 0离线.
     */
    private Integer collectionSts;
    /**
     * deviceOpenSts 设备开盖状态.
     */
    private Integer deviceOpenSts;
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
     * Set runSpeed 运行速度.
     */
    public void setRunSpeed(String runSpeed){
        this.runSpeed = runSpeed;
    }

    /**
     * Get runSpeed 运行速度.
     *
     * @return the string
     */
    public String getRunSpeed(){
        return runSpeed;
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
     * Set deviceName 设备平成.
     */
    public void setDeviceName(String deviceName){
        this.deviceName = deviceName;
    }

    /**
     * Get deviceName 设备平成.
     *
     * @return the string
     */
    public String getDeviceName(){
        return deviceName;
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
     * Set floor 楼层.
     */
    public void setFloor(Integer floor){
        this.floor = floor;
    }

    /**
     * Get floor 楼层.
     *
     * @return the string
     */
    public Integer getFloor(){
        return floor;
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
     * Set runSts 运行状态.
     */
    public void setRunSts(Integer runSts){
        this.runSts = runSts;
    }

    /**
     * Get runSts 运行状态.
     *
     * @return the string
     */
    public Integer getRunSts(){
        return runSts;
    }

    /**
     * Set upFlat 上平层.
     */
    public void setUpFlat(Integer upFlat){
        this.upFlat = upFlat;
    }

    /**
     * Get upFlat 上平层.
     *
     * @return the string
     */
    public Integer getUpFlat(){
        return upFlat;
    }

    /**
     * Set upLimit 上限位.
     */
    public void setUpLimit(Integer upLimit){
        this.upLimit = upLimit;
    }

    /**
     * Get upLimit 上限位.
     *
     * @return the string
     */
    public Integer getUpLimit(){
        return upLimit;
    }

    /**
     * Set downFlat 下平层.
     */
    public void setDownFlat(Integer downFlat){
        this.downFlat = downFlat;
    }

    /**
     * Get downFlat 下平层.
     *
     * @return the string
     */
    public Integer getDownFlat(){
        return downFlat;
    }

    /**
     * Set downLimit 下限位.
     */
    public void setDownLimit(Integer downLimit){
        this.downLimit = downLimit;
    }

    /**
     * Get downLimit 下限位.
     *
     * @return the string
     */
    public Integer getDownLimit(){
        return downLimit;
    }

    /**
     * Set hasPeople 是否有人.
     */
    public void setHasPeople(Integer hasPeople){
        this.hasPeople = hasPeople;
    }

    /**
     * Get hasPeople 是否有人.
     *
     * @return the string
     */
    public Integer getHasPeople(){
        return hasPeople;
    }

    /**
     * Set atPowerPct 采集端电量 电量.
     */
    public void setAtPowerPct(Integer atPowerPct){
        this.atPowerPct = atPowerPct;
    }

    /**
     * Get atPowerPct 采集端电量 电量.
     *
     * @return the string
     */
    public Integer getAtPowerPct(){
        return atPowerPct;
    }

    /**
     * Set ctPowerPct 通讯端电量 电量.
     */
    public void setCtPowerPct(Integer ctPowerPct){
        this.ctPowerPct = ctPowerPct;
    }

    /**
     * Get ctPowerPct 通讯端电量 电量.
     *
     * @return the string
     */
    public Integer getCtPowerPct(){
        return ctPowerPct;
    }

    /**
     * Set atPowerType 采集端电量 供电类型.
     */
    public void setAtPowerType(Integer atPowerType){
        this.atPowerType = atPowerType;
    }

    /**
     * Get atPowerType 采集端电量 供电类型.
     *
     * @return the string
     */
    public Integer getAtPowerType(){
        return atPowerType;
    }

    /**
     * Set ctPowerType 通讯端电量 供电类型.
     */
    public void setCtPowerType(Integer ctPowerType){
        this.ctPowerType = ctPowerType;
    }

    /**
     * Get ctPowerType 通讯端电量 供电类型.
     *
     * @return the string
     */
    public Integer getCtPowerType(){
        return ctPowerType;
    }

    /**
     * Set doorLockSts 门锁状态.
     */
    public void setDoorLockSts(Integer doorLockSts){
        this.doorLockSts = doorLockSts;
    }

    /**
     * Get doorLockSts 门锁状态.
     *
     * @return the string
     */
    public Integer getDoorLockSts(){
        return doorLockSts;
    }

    /**
     * Set runDirection 运行方向.
     */
    public void setRunDirection(Integer runDirection){
        this.runDirection = runDirection;
    }

    /**
     * Get runDirection 运行方向.
     *
     * @return the string
     */
    public Integer getRunDirection(){
        return runDirection;
    }

    /**
     * Set collectionSts 采集端在线状态 1在线 0离线.
     */
    public void setCollectionSts(Integer collectionSts){
        this.collectionSts = collectionSts;
    }

    /**
     * Get collectionSts 采集端在线状态 1在线 0离线.
     *
     * @return the string
     */
    public Integer getCollectionSts(){
        return collectionSts;
    }

    /**
     * Set deviceOpenSts 设备开盖状态.
     */
    public void setDeviceOpenSts(Integer deviceOpenSts){
        this.deviceOpenSts = deviceOpenSts;
    }

    /**
     * Get deviceOpenSts 设备开盖状态.
     *
     * @return the string
     */
    public Integer getDeviceOpenSts(){
        return deviceOpenSts;
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
