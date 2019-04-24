package com.xiaowei.mh.service.protocol.manhole;

import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2018/12/25
 */
public class ThreeDataSuccess implements Serializable {
    private String devType;
    private String deviceNo;
    private String devID;
    private String ccid;
    private Integer lac;
    private Integer cell_ID;
    private Float signalRSRP;
    private Float signalRSSI;
    private Float signalSnr;
    private Integer dataType;
    private String faultInfo;
    private Integer temperature;
    private Integer powerMh;
    private Integer wellOpenSts;
    private Integer gasH2S;
    private Integer humidity;

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    /**
     * 水位 0正常  1 一级报警 2 二级报警 3 水位开关故障
     */
    private Integer waterLine;

    public static String getParamNameByKey(String key) {
        return ThreeDataSuccessEnum.getParamNameByKey(key);
    }

    enum ThreeDataSuccessEnum {

        /**
         * 设备id
         */
        DEV_ID("devID", "设备IMEI号，BCD码"),
        /**
         * ccid
         */
        CCID("ccid", "ccid"),
        /**
         * 电池电量
         */
        POWER_MH("powerMh", "电池电量"),
        /**
         * IMEI号
         */
        DEVICE_NO("deviceNo", "设备编号"),
        /**
         * 基站LAC号
         */
        LAC("lac", "基站LAC号"),
        /**
         * 基站CELL ID号
         */
        CELL_ID("cell_ID", "基站CELL ID号"),
        /**
         * 信号接收功率
         */
        SIGNAL_RSRP("signalRSRP", "信号接收功率"),
        /**
         * 接收的信号强度指示
         */
        SIGNAL_RSSI("signalRSSI", "接收的信号强度指示"),
        /**
         * 信噪比
         */
        SIGNAL_SNR("signalSnr", "信噪比"),
        /**
         * 数据类型
         */
        DATA_TYPE("dataType", "数据类型"),
        /**
         * 硫化氢浓度
         */
        GAS_H2S("gasH2S", "硫化氢浓度"),
        /**
         * 故障信息
         */
        FAULT_INFO("faultInfo", "故障信息"),
        /**
         * 井盖的开盖状态
         */
        WELL_OPEN_STR("wellOpenSts", "开盖状态"),
        /**
         * 温度
         */
        TEMPERATURE("temperature", "温度"),;

        private String paramKey;

        private String paramName;

        public static String getParamNameByKey(String key) {
            ThreeDataSuccessEnum[] paramEnums = values();
            for (ThreeDataSuccessEnum paramEcEnum : paramEnums) {
                if (paramEcEnum.getParamKey().equalsIgnoreCase(key)) {
                    return paramEcEnum.getParamName();
                }
            }
            return null;
        }

        ThreeDataSuccessEnum(String paramKey, String paramName) {
            this.paramKey = paramKey;
            this.paramName = paramName;
        }

        public String getParamKey() {
            return paramKey;
        }

        public ThreeDataSuccessEnum setParamKey(String paramKey) {
            this.paramKey = paramKey;
            return this;
        }

        public String getParamName() {
            return paramName;
        }

        public ThreeDataSuccessEnum setParamName(String paramName) {
            this.paramName = paramName;
            return this;
        }
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public String getDevID() {
        return devID;
    }

    public void setDevID(String devID) {
        this.devID = devID;
    }

    public String getCcid() {
        return ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    public Integer getLac() {
        return lac;
    }

    public void setLac(Integer lac) {
        this.lac = lac;
    }

    public Integer getCell_ID() {
        return cell_ID;
    }

    public void setCell_ID(Integer cell_ID) {
        this.cell_ID = cell_ID;
    }

    public Float getSignalRSRP() {
        return signalRSRP;
    }

    public void setSignalRSRP(Float signalRSRP) {
        this.signalRSRP = signalRSRP;
    }

    public Float getSignalRSSI() {
        return signalRSSI;
    }

    public void setSignalRSSI(Float signalRSSI) {
        this.signalRSSI = signalRSSI;
    }

    public Float getSignalSnr() {
        return signalSnr;
    }

    public void setSignalSnr(Float signalSnr) {
        this.signalSnr = signalSnr;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getFaultInfo() {
        return faultInfo;
    }

    public void setFaultInfo(String faultInfo) {
        this.faultInfo = faultInfo;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getPowerMh() {
        return powerMh;
    }

    public void setPowerMh(Integer powerMh) {
        this.powerMh = powerMh;
    }

    public Integer getWellOpenSts() {
        return wellOpenSts;
    }

    public void setWellOpenSts(Integer wellOpenSts) {
        this.wellOpenSts = wellOpenSts;
    }

    public Integer getGasH2S() {
        return gasH2S;
    }

    public void setGasH2S(Integer gasH2S) {
        this.gasH2S = gasH2S;
    }

    public Integer getWaterLine() {
        return waterLine;
    }

    public void setWaterLine(Integer waterLine) {
        this.waterLine = waterLine;
    }

    @Override
    public String toString() {
        return "ThreeDataSuccess{" +
                "devType='" + devType + '\'' +
                ", deviceNo='" + deviceNo + '\'' +
                ", devID='" + devID + '\'' +
                ", ccid='" + ccid + '\'' +
                ", lac=" + lac +
                ", cell_ID=" + cell_ID +
                ", signalRSRP=" + signalRSRP +
                ", signalRSSI=" + signalRSSI +
                ", signalSnr=" + signalSnr +
                ", dataType=" + dataType +
                ", faultInfo='" + faultInfo + '\'' +
                ", temperature=" + temperature +
                ", powerMh=" + powerMh +
                ", wellOpenSts=" + wellOpenSts +
                ", gasH2S=" + gasH2S +
                ", waterLine=" + waterLine +
                '}';
    }
}
