package com.xiaowei.mh.service.protocol.rw;

import com.xiaowei.mh.common.util.hex.BytesHexStrTranslate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by WingsChan on 2018/9/2.
 */
public class XiaoweiRwDataReq implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(XiaoweiRwDataReq.class);

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
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 外部温度
     */
    private Integer outTemperature;
    /**
     * 内部温度
     */
    private Integer inTemperature;

    /**
     * 供电类型
     */
    private Integer powerType;

    /**
     * 电量
     */
    private Integer powerPct;

    /**
     * 溶解氧 单位 mg/L
     */
    private String rdo;

    /**
     * 酸碱度
     */
    private String ph;

    /**
     * 浊度 单位NTU
     */
    private String tss;

    /**
     * 电导率 电导率 μS/cm
     */
    private Integer conductivity;

    /**
     * 水深 单位cm
     */
    private Integer depth;

    /**
     * @param key
     * @return
     */
    public static String getParamNameByKey(String key) {
        return XiaoweiRwDataReqEnum.getParamNameByKey(key);
    }

    enum XiaoweiRwDataReqEnum {
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
         * 纬度
         */
        LATITUDE("latitude", "纬度"),
        /**
         * 经度
         */
        LONGITUDE("longitude", "经度"),
        /**
         * 温度 单位℃
         */
        IN_TEMPERATURE("inTemperature", "内部温度"),
        OUT_TEMPERATURE("outTemperature", "外部温度"),
        /**
         * 供电状态
         */
        POWER_PCT("powerPct", "电池电量"),
        POWER_TYPE("powerType", "供电类型"),
        /**
         * 溶解氧
         */
        RDO("rdo", "溶解氧"),
        /**
         * 酸碱度
         */
        PH("ph", "酸碱度"),
        /**
         * 浊度
         */
        TSS("tss", "浊度"),
        /**
         * 电导率
         */
        CONDUCTIVITY("conductivity", "电导率"),
        /**
         * 水深
         */
        DEPTH("depth", "水深"),;

        private String paramKey;

        private String paramName;

        public static String getParamNameByKey(String key) {
            XiaoweiRwDataReqEnum[] paramEnums = values();
            for (XiaoweiRwDataReqEnum paramEcEnum : paramEnums) {
                if (paramEcEnum.getParamKey().equalsIgnoreCase(key)) {
                    return paramEcEnum.getParamName();
                }
            }
            return null;
        }

        XiaoweiRwDataReqEnum(String paramKey, String paramName) {
            this.paramKey = paramKey;
            this.paramName = paramName;
        }

        public String getParamKey() {
            return paramKey;
        }

        public XiaoweiRwDataReqEnum setParamKey(String paramKey) {
            this.paramKey = paramKey;
            return this;
        }

        public String getParamName() {
            return paramName;
        }

        public XiaoweiRwDataReqEnum setParamName(String paramName) {
            this.paramName = paramName;
            return this;
        }
    }

    private static final String ERROR_LATITUDE = "00000000";
    private static final String ERROR_LONGITUDE = "00000000";

    /**
     * 采集故障数据
     */
    private static final String ERROR_COLLECT_DATA = "FFFF";
    private static final String ERROR_COLLECT_DATA_1 = "FF";

    public static XiaoweiRwDataReq buildByDataMap(HashMap<String, Object> params) {
        XiaoweiRwDataReq req = new XiaoweiRwDataReq();
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
        //纬度
        if (params.get("latitude") != null && !ERROR_LATITUDE.equals(params.get("latitude"))) {
            byte[] latitudeByteArray = BytesHexStrTranslate.toBytes(params.get("latitude") + "");
            String latitudeStr = byteToBit(latitudeByteArray);
            String latitudeTypeStr = latitudeStr.substring(0, 1);
            String latitudeType = latitudeTypeStr.equals("0") ? "" : "-";
            String latitudeDataStr = latitudeStr.substring(1);
            Integer latitudeData = Integer.valueOf(latitudeDataStr, 2);
            latitudeDataStr = latitudeType + (latitudeData / 1000000F);
            req.setLatitude(latitudeDataStr);
        } else {
            req.setLatitude(null);
        }
        //经度
        if (params.get("longitude") != null && !ERROR_LONGITUDE.equals(params.get("longitude"))) {
            byte[] longitudeByteArray = BytesHexStrTranslate.toBytes(params.get("longitude") + "");
            String longitudeStr = byteToBit(longitudeByteArray);
            String longitudeTypeStr = longitudeStr.substring(0, 1);
            String longitudeType = longitudeTypeStr.equals("0") ? "" : "-";
            String longitudeDataStr = longitudeStr.substring(1);
            Integer longitudeData = Integer.valueOf(longitudeDataStr, 2);
            longitudeDataStr = longitudeType + (longitudeData / 1000000F);
            req.setLongitude(longitudeDataStr);
        } else {
            req.setLongitude(null);
        }
        //温度
        if (params.get("temperature") != null && !ERROR_COLLECT_DATA.equals(params.get("temperature"))) {
            String temperatureStr = params.get("temperature") + "";
            //外部温度
            Integer outTemperature = Integer.valueOf(temperatureStr.substring(0, 2), 16);
            req.setOutTemperature(outTemperature);
            //内部温度
            Integer inTemperature = Integer.valueOf(temperatureStr.substring(2, 4), 16);
            req.setInTemperature(inTemperature);
        } else {
            req.setOutTemperature(null);
            req.setInTemperature(null);
        }

        //电量状态
        if (params.get("powerSts") != null) {
            byte[] atPower = BytesHexStrTranslate.toBytes(params.get("powerSts") + "");
            String atPowerStr = byteToBit(atPower[0]);
            String atPowerTypeStr = atPowerStr.substring(0, 1);
            req.setPowerType(Integer.valueOf(atPowerTypeStr));
            String atPowerPctaStr = atPowerStr.substring(1);
            req.setPowerPct(Integer.valueOf(atPowerPctaStr, 2));
        }
        //溶解氧
        if (params.get("rdo") != null && !ERROR_COLLECT_DATA.equals(params.get("rdo"))) {
            String rdoStr = params.get("rdo") + "";
            Integer rdo = Integer.valueOf(rdoStr, 16);
            String rdoData = saveOneBitTwoRound(rdo / 100D, 2) + "";
            req.setRdo(rdoData);
        } else {
            req.setRdo(null);
        }
        //酸碱度
        if (params.get("ph") != null && !ERROR_COLLECT_DATA.equals(params.get("ph"))) {
            String phStr = params.get("ph") + "";
            Integer ph = Integer.valueOf(phStr, 16);
            String phData = saveOneBitTwoRound(ph / 100D, 2) + "";
            req.setPh(phData);
        } else {
            req.setPh(null);
        }
        //浊度
        if (params.get("tss") != null && !ERROR_COLLECT_DATA.equals(params.get("tss"))) {
            String tssStr = params.get("tss") + "";
            Integer tss = Integer.valueOf(tssStr, 16);
            String tssData = saveOneBitTwoRound(tss / 10D, 1) + "";
            req.setTss(tssData);
        } else {
            req.setTss(null);
        }
        //电导率
        if (params.get("conductivity") != null && !ERROR_COLLECT_DATA.equals(params.get("conductivity"))) {
            req.setConductivity(Integer.valueOf(params.get("conductivity") + "", 16));
        } else {
            req.setConductivity(null);
        }
        //水深
        if (params.get("depth") != null && !ERROR_COLLECT_DATA_1.equals(params.get("depth"))) {
            req.setDepth(Integer.valueOf(params.get("depth") + "", 16));
        } else {
            req.setDepth(null);
        }
        return req;
    }


    public String getDeviceNo() {
        return deviceNo;
    }

    public XiaoweiRwDataReq setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
        return this;
    }

    public String getCcid() {
        return ccid;
    }

    public XiaoweiRwDataReq setCcid(String ccid) {
        this.ccid = ccid;
        return this;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public XiaoweiRwDataReq setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getFaultInfo() {
        return faultInfo;
    }

    public XiaoweiRwDataReq setFaultInfo(String faultInfo) {
        this.faultInfo = faultInfo;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public XiaoweiRwDataReq setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public XiaoweiRwDataReq setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public Integer getOutTemperature() {
        return outTemperature;
    }

    public XiaoweiRwDataReq setOutTemperature(Integer outTemperature) {
        this.outTemperature = outTemperature;
        return this;
    }

    public Integer getInTemperature() {
        return inTemperature;
    }

    public XiaoweiRwDataReq setInTemperature(Integer inTemperature) {
        this.inTemperature = inTemperature;
        return this;
    }

    public Integer getPowerType() {
        return powerType;
    }

    public XiaoweiRwDataReq setPowerType(Integer powerType) {
        this.powerType = powerType;
        return this;
    }

    public Integer getPowerPct() {
        return powerPct;
    }

    public XiaoweiRwDataReq setPowerPct(Integer powerPct) {
        this.powerPct = powerPct;
        return this;
    }

    public String getRdo() {
        return rdo;
    }

    public XiaoweiRwDataReq setRdo(String rdo) {
        this.rdo = rdo;
        return this;
    }

    public String getPh() {
        return ph;
    }

    public XiaoweiRwDataReq setPh(String ph) {
        this.ph = ph;
        return this;
    }

    public String getTss() {
        return tss;
    }

    public XiaoweiRwDataReq setTss(String tss) {
        this.tss = tss;
        return this;
    }

    public Integer getConductivity() {
        return conductivity;
    }

    public XiaoweiRwDataReq setConductivity(Integer conductivity) {
        this.conductivity = conductivity;
        return this;
    }

    public Integer getDepth() {
        return depth;
    }

    public XiaoweiRwDataReq setDepth(Integer depth) {
        this.depth = depth;
        return this;
    }

    @Override
    public String toString() {
        return "XiaoweiRwDataReq{" +
                "deviceNo='" + deviceNo + '\'' +
                ", ccid='" + ccid + '\'' +
                ", timestamp=" + timestamp +
                ", faultInfo='" + faultInfo + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", outTemperature=" + outTemperature +
                ", inTemperature=" + inTemperature +
                ", powerType=" + powerType +
                ", powerPct=" + powerPct +
                ", rdo='" + rdo + '\'' +
                ", ph='" + ph + '\'' +
                ", tss='" + tss + '\'' +
                ", conductivity=" + conductivity +
                ", depth=" + depth +
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
