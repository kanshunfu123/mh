package com.xiaowei.mh.service.protocol.manhole;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by WingsChan on 2018/9/2.
 */
public class XiaoweiMhDataReq implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(XiaoweiMhDataReq.class);

    /**
     * level 水质等级.
     */
    private String level;
    /**
     * levelShow 水质等级显示.
     */
    private String levelShow;
    /**
     * networkSignal 网络信号值.
     */
    private String networkSignal;
    /**
     * acquisitionUuid 采集uuid.
     */
    private String acquisitionUuid;
    /**
     * powerMh 电池电量.
     */
    private Integer powerMh;
    /**
     * wellOpenSts 设备开盖状态.
     */
    private Integer wellOpenSts;
    /**
     * 设备编号
     */
    private String deviceNo;

    /**
     * 设备CCID号
     */
    private String ccid;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 故障信息
     */
    private String faultInfo;

    /**
     * 水位 单位cm
     */
    private Integer waterLine;

    /**
     * 开关水位
     */
    private Integer switchWaterLine;

    /**
     * 井盖状态0 ：正常 1：开盖
     */
    private Integer wellCoverSts;
    /**
     * 可燃气浓度 单位%LEL，保留1位小数
     */
        private String fireGasConcentration;

    /**
     * 温度 单位℃
     */
    private Integer temperature;

    public static String getParamNameByKey(String key) {
        return XiaoweiMhDataReqEnum.getParamNameByKey(key);
    }

    enum XiaoweiMhDataReqEnum {

        /**
         * 设备编号
         */
        DEVICE_NO("deviceNo","设备IMEI号，BCD码"),
        /**
         * ccid
         */
        CCID("ccid", "ccid"),
        /**
         * UNIX时间戳
         */
        TIME("timestamp", "时间戳"),
        /**
         * 故障信息
         */
        FAULT_INFO("faultInfo", "故障信息"),
        /**
         * 水位 单位cm
         */
        WATER_LINE("waterLine", "水位"),
        /**
         * 开关水位 0 正常 1 一级报警 2二级报警
         */
        SWITCH_WATER_LINE("switchWaterLine", "开关水位"),
        /**
         * 井盖状态
         */
        WELL_COVER_STS("wellCoverSts", "井盖状态"),
        /**
         * 温度 单位℃
         */
        TEMPERATURE("temperature", "温度"),
        /**
         * 可燃气浓度
         */
        FIRE_GAS_CONCENTRATION("fireGasConcentration", "可燃气浓度"),

        NETWORK_SIGNAL("networkSignal",  "网络信号值"),

        POWER_MH("powerMh","电池电量"),
        ;

        private String paramKey;

        private String paramName;

        public static String getParamNameByKey(String key) {
            XiaoweiMhDataReqEnum[] paramEnums = values();
            for (XiaoweiMhDataReqEnum paramEcEnum : paramEnums) {
                if (paramEcEnum.getParamKey().equalsIgnoreCase(key)) {
                    return paramEcEnum.getParamName();
                }
            }
            return null;
        }

        XiaoweiMhDataReqEnum(String paramKey, String paramName) {
            this.paramKey = paramKey;
            this.paramName = paramName;
        }

        public String getParamKey() {
            return paramKey;
        }

        public XiaoweiMhDataReqEnum setParamKey(String paramKey) {
            this.paramKey = paramKey;
            return this;
        }

        public String getParamName() {
            return paramName;
        }

        public XiaoweiMhDataReqEnum setParamName(String paramName) {
            this.paramName = paramName;
            return this;
        }
    }

    /**
     * 采集故障数据
     */
    private static final String ERROR_COLLECT_DATA_65536 = "FFFF";
    private static final String ERROR_COLLECT_DATA_256 = "FF";

    public static XiaoweiMhDataReq buildByDataMap(HashMap<String, Object> params) {
        XiaoweiMhDataReq req = new XiaoweiMhDataReq();
        req.setDeviceNo(params.get("deviceNo") + "");
        req.setCcid(params.get("ccid") + "");
        //时间戳
        if (params.get("time") != null) {
            req.setTimestamp(Long.valueOf(params.get("time") + "", 16));
        }
        //故障信息
        if (params.get("faultInfo") != null) {
            req.setFaultInfo(Integer.valueOf(params.get("faultInfo") + "", 16) + "");
        }
        // 水位状态
        if (params.get("waterLine") != null && !ERROR_COLLECT_DATA_65536.equals(params.get("waterLine"))) {
            req.setWaterLine(Integer.valueOf(params.get("waterLine") + "", 16));
        } else {
            req.setWaterLine(null);
        }
        if (params.get("switchWaterLine") != null && !ERROR_COLLECT_DATA_256.equals(params.get("switchWaterLine"))) {
            req.setSwitchWaterLine(Integer.valueOf(params.get("switchWaterLine") + "", 16));
        } else {
            req.setWaterLine(null);
        }
        //井盖状态0 ：正常 1：开盖
        if (params.get("wellCoverSts") != null && !ERROR_COLLECT_DATA_256.equals(params.get("wellCoverSts"))) {
            req.setWellCoverSts(Integer.valueOf(params.get("wellCoverSts") + "", 16));
        } else {
            req.setWellCoverSts(null);
        }
        //温度
        if (params.get("temperature") != null && !ERROR_COLLECT_DATA_256.equals(params.get("temperature"))) {
            req.setTemperature(Integer.valueOf(params.get("temperature") + "", 16));
        } else {
            req.setTemperature(null);
        }
        //网络信号值
        if (params.get("networkSignal") != null) {
            req.setNetworkSignal("-" + Integer.valueOf(params.get("networkSignal") + "", 16));
        }
        //电池电量
        if (params.get("powerMh") != null) {
            req.setPowerMh(Integer.valueOf(params.get("powerMh") + "", 16));
        }

        //可燃气浓度 单位%LEL，保留1位小数
        if (params.get("fireGasConcentration") != null && !ERROR_COLLECT_DATA_65536.equals(params.get("fireGasConcentration"))) {
            String fireGasConcentrationStr = params.get("fireGasConcentration") + "";
            Integer fireGasConcentration = Integer.valueOf(fireGasConcentrationStr, 16);
            String fireGasConcentrationData = saveOneBitTwoRound(fireGasConcentration / 10D, 1) + "";
            req.setFireGasConcentration(fireGasConcentrationData);
        } else {
            req.setFireGasConcentration(null);
        }
        return req;
    }



    public String getDeviceNo() {
        return deviceNo;
    }

    public XiaoweiMhDataReq setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
        return this;
    }

    public String getCcid() {
        return ccid;
    }

    public XiaoweiMhDataReq setCcid(String ccid) {
        this.ccid = ccid;
        return this;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public XiaoweiMhDataReq setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getFaultInfo() {
        return faultInfo;
    }

    public XiaoweiMhDataReq setFaultInfo(String faultInfo) {
        this.faultInfo = faultInfo;
        return this;
    }

    public Integer getWaterLine() {
        return waterLine;
    }

    public XiaoweiMhDataReq setWaterLine(Integer waterLine) {
        this.waterLine = waterLine;
        return this;
    }

    public static Logger getLog() {
        return log;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelShow() {
        return levelShow;
    }

    public void setLevelShow(String levelShow) {
        this.levelShow = levelShow;
    }

    public String getNetworkSignal() {
        return networkSignal;
    }

    public void setNetworkSignal(String networkSignal) {
        this.networkSignal = networkSignal;
    }

    public String getAcquisitionUuid() {
        return acquisitionUuid;
    }

    public void setAcquisitionUuid(String acquisitionUuid) {
        this.acquisitionUuid = acquisitionUuid;
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

    public Integer getWellCoverSts() {
        return wellCoverSts;
    }

    public XiaoweiMhDataReq setWellCoverSts(Integer wellCoverSts) {
        this.wellCoverSts = wellCoverSts;
        return this;
    }

    public String getFireGasConcentration() {
        return fireGasConcentration;
    }

    public XiaoweiMhDataReq setFireGasConcentration(String fireGasConcentration) {
        this.fireGasConcentration = fireGasConcentration;
        return this;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public XiaoweiMhDataReq setTemperature(Integer temperature) {
        this.temperature = temperature;
        return this;
    }

    public Integer getSwitchWaterLine() {
        return switchWaterLine;
    }

    public void setSwitchWaterLine(Integer switchWaterLine) {
        this.switchWaterLine = switchWaterLine;
    }

    @Override
    public String toString() {
        return "XiaoweiMhDataReq{" +
                "level='" + level + '\'' +
                ", levelShow='" + levelShow + '\'' +
                ", networkSignal='" + networkSignal + '\'' +
                ", acquisitionUuid='" + acquisitionUuid + '\'' +
                ", powerMh=" + powerMh +
                ", wellOpenSts=" + wellOpenSts +
                ", deviceNo='" + deviceNo + '\'' +
                ", ccid='" + ccid + '\'' +
                ", timestamp=" + timestamp +
                ", faultInfo='" + faultInfo + '\'' +
                ", waterLine=" + waterLine +
                ", switchWaterLine=" + switchWaterLine +
                ", wellCoverSts=" + wellCoverSts +
                ", fireGasConcentration='" + fireGasConcentration + '\'' +
                ", temperature=" + temperature +
                '}';
    }

    /**
     * 保留两位小数,进行四舍五入
     *
     * @param d
     * @return
     */
    public static Double saveOneBitTwoRound(Double d, int scale) {
        BigDecimal bd = new BigDecimal(d);
        Double tem = bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return tem;
    }


    private static String byteToBit(byte[] bytes) {
        String str = "";
        for (byte b : bytes) {
            str += byteToBit(b);
        }
        return str;
    }

    /**
     * 把byte转为字符串的bit
     */
    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }


    public static int getHeight4(byte data) {//获取高四位
        int height;
        height = ((data & 0xf0) >> 4);
        return height;
    }

    public static int getLow4(byte data) {//获取低四位
        int low;
        low = (data & 0x0f);
        return low;
    }


}
