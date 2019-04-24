package com.xiaowei.mh.service.protocol.rw;

/**
 * @author WingsChan
 * @date 2018/9/1
 */
public enum ParamRwEnum {

    /**
     * 设备编号
     */
    DEVICE_NO("deviceNo", "01", "设备IMEI号，BCD码", 8),
    /**
     * ccid
     */
    CCID("ccid", "02", "SIM卡的CCID号", 10),
    /**
     * 数据处理状态
     */
    DATA_OPT_STS("dataOptSts", "03", "数据处理状态", 1),
    /**
     * UNIX时间戳
     */
    TIME("time", "04", "UNIX时间戳(秒)", 4),
    /**
     * 故障信息
     */
    FAULT_INFO("faultInfo", "05", "故障信息", 2),
    /**
     * 纬度
     */
    LATITUDE("latitude", "22", "纬度", 4),
    /**
     * 经度
     */
    LONGITUDE("longitude", "23", "经度", 4),
    /**
     * 温度 单位℃
     */
    TEMPERATURE("temperature", "24", "温度", 2),
    /**
     * 供电状态
     */
    POWER_STS("powerSts", "25", "供电状态", 1),
    /**
     * 溶解氧
     */
    RDO("rdo", "30", "溶解氧", 2),
    /**
     * 酸碱度
     */
    PH("ph", "32", "酸碱度", 2),
    /**
     * 浊度
     */
    TSS("tss", "33", "浊度", 2),
    /**
     * 电导率
     */
    CONDUCTIVITY("conductivity", "34", "电导率", 2),
    /**
     * 水深
     */
    DEPTH("depth", "38", "水深", 2),;


    private String paramKey;

    private String paramCode;

    private String remark;

    private Integer paramLen;

    public Integer getParamLen() {
        return paramLen;
    }

    public void setParamLen(Integer paramLen) {
        this.paramLen = paramLen;
    }

    public static String getParamKey(String code) {
        ParamRwEnum[] paramEcEnums = values();
        for (ParamRwEnum paramEcEnum : paramEcEnums) {
            if (paramEcEnum.getParamCode().equalsIgnoreCase(code)) {
                return paramEcEnum.getParamKey();
            }
        }
        return null;
    }

    ParamRwEnum(String paramKey, String paramCode, String remark, Integer length) {
        this.paramKey = paramKey;
        this.paramCode = paramCode;
        this.remark = remark;
        this.paramLen = length;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
