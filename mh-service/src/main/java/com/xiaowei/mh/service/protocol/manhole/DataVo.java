package com.xiaowei.mh.service.protocol.manhole;

/**
 * Created by 韩金群
 * CreateTime 2018/12/25
 */
public class DataVo {
    //设备类型 1：电梯 2：饮用水 3：地表水 4：井盖
    private Integer devType;

    //设备ID 6个字节
    private String devID;

    private String devIdStr;

    //设备ID 6个字节
    private String imei;

    private String imeiStr;

    //SIM卡号 10个字节
    private String ccid;

    private String ccidStr;

    //基站LAC号
    private Integer lac;

    //基站CELL ID号		配合LAC号可查询当前连接的基站位置
    private Integer cell_ID;

    //RSRP（参考信号接收功率），单位：10dbm，例：-919=-91.9dbm
    private Integer signalRSRP;
    private Float signalRSRPFlo;
    private String signalRSRPStr;

    //RSSI（接收的信号强度指示），单位：10dbm，例：-804=-80.4dbm
    private Integer signalRSSI;
    private Float signalRSSIFlo;
    private String signalRSSIStr;


    //SNR（信噪比），单位：10dB，例：-33=-3.3dB
    private Integer signalSnr;
    private Float signalSnrFlo;
    private String signalSnrStr;

    //数据类型	1：数据上报		2：报警数据
    private Integer dataType;

    /**
     * 状态	按bit位处理，
     * 0：正常
     * 1：异常，
     * bit0：设备状态 bit1：井盖状态报警	bit5：硫化氢报警
     */
    private Integer status;
    private String statusResult;

    //温度，单位：摄氏度
    private Integer temperature;

    //电池电量百分比
    private Integer bat;

    //井盖状态：0：闭合	1：开启
    private Integer cover;

    //硫化氢浓度，单位：ppm
    private Integer gasH2S;
    //水位
// 0 正常
// 1 一级报警
// 2 二级报警
// 3 水位开关故障
    private Integer waterSwitch;
    //湿度
    private Integer humi;

    public Integer getHumi() {
        return humi;
    }

    public void setHumi(Integer humi) {
        this.humi = humi;
    }

    public Integer getWaterSwitch() {
        return waterSwitch;
    }

    public void setWaterSwitch(Integer waterSwitch) {
        this.waterSwitch = waterSwitch;
    }

    public Integer getDevType() {
        return devType;
    }

    public void setDevType(Integer devType) {
        this.devType = devType;
    }

    public String getDevID() {
        return devID;
    }

    public void setDevID(String devID) {
        this.devID = devID;
    }

    public String getDevIdStr() {
        return devIdStr;
    }

    public void setDevIdStr(String devIdStr) {
        this.devIdStr = devIdStr;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImeiStr() {
        return imeiStr;
    }

    public void setImeiStr(String imeiStr) {
        this.imeiStr = imeiStr;
    }

    public String getCcid() {
        return ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    public String getCcidStr() {
        return ccidStr;
    }

    public void setCcidStr(String ccidStr) {
        this.ccidStr = ccidStr;
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

    public Integer getSignalRSRP() {
        return signalRSRP;
    }

    public void setSignalRSRP(Integer signalRSRP) {
        this.signalRSRP = signalRSRP;
    }

    public Float getSignalRSRPFlo() {
        return signalRSRPFlo;
    }

    public void setSignalRSRPFlo(Float signalRSRPFlo) {
        this.signalRSRPFlo = signalRSRPFlo;
    }

    public String getSignalRSRPStr() {
        return signalRSRPStr;
    }

    public void setSignalRSRPStr(String signalRSRPStr) {
        this.signalRSRPStr = signalRSRPStr;
    }

    public Integer getSignalRSSI() {
        return signalRSSI;
    }

    public void setSignalRSSI(Integer signalRSSI) {
        this.signalRSSI = signalRSSI;
    }

    public Float getSignalRSSIFlo() {
        return signalRSSIFlo;
    }

    public void setSignalRSSIFlo(Float signalRSSIFlo) {
        this.signalRSSIFlo = signalRSSIFlo;
    }

    public String getSignalRSSIStr() {
        return signalRSSIStr;
    }

    public void setSignalRSSIStr(String signalRSSIStr) {
        this.signalRSSIStr = signalRSSIStr;
    }

    public Integer getSignalSnr() {
        return signalSnr;
    }

    public void setSignalSnr(Integer signalSnr) {
        this.signalSnr = signalSnr;
    }

    public Float getSignalSnrFlo() {
        return signalSnrFlo;
    }

    public void setSignalSnrFlo(Float signalSnrFlo) {
        this.signalSnrFlo = signalSnrFlo;
    }

    public String getSignalSnrStr() {
        return signalSnrStr;
    }

    public void setSignalSnrStr(String signalSnrStr) {
        this.signalSnrStr = signalSnrStr;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusResult() {
        return statusResult;
    }

    public void setStatusResult(String statusResult) {
        this.statusResult = statusResult;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getBat() {
        return bat;
    }

    public void setBat(Integer bat) {
        this.bat = bat;
    }

    public Integer getCover() {
        return cover;
    }

    public void setCover(Integer cover) {
        this.cover = cover;
    }

    public Integer getGasH2S() {
        return gasH2S;
    }

    public void setGasH2S(Integer gasH2S) {
        this.gasH2S = gasH2S;
    }

    @Override
    public String toString() {
        return "DataVo{" +
                "devType=" + devType +
                ", devID='" + devID + '\'' +
                ", devIdStr='" + devIdStr + '\'' +
                ", imei='" + imei + '\'' +
                ", imeiStr='" + imeiStr + '\'' +
                ", ccid='" + ccid + '\'' +
                ", ccidStr='" + ccidStr + '\'' +
                ", lac=" + lac +
                ", cell_ID=" + cell_ID +
                ", signalRSRP=" + signalRSRP +
                ", signalRSRPFlo=" + signalRSRPFlo +
                ", signalRSRPStr='" + signalRSRPStr + '\'' +
                ", signalRSSI=" + signalRSSI +
                ", signalRSSIFlo=" + signalRSSIFlo +
                ", signalRSSIStr='" + signalRSSIStr + '\'' +
                ", signalSnr=" + signalSnr +
                ", signalSnrFlo=" + signalSnrFlo +
                ", signalSnrStr='" + signalSnrStr + '\'' +
                ", dataType=" + dataType +
                ", status=" + status +
                ", statusResult='" + statusResult + '\'' +
                ", temperature=" + temperature +
                ", bat=" + bat +
                ", cover=" + cover +
                ", gasH2S=" + gasH2S +
                '}';
    }
}
