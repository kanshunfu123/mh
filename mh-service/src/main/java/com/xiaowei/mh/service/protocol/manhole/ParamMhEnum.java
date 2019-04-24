package com.xiaowei.mh.service.protocol.manhole;

/**
 * @author WingsChan
 * @date 2018/9/1
 */
public enum ParamMhEnum {

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
     * 水位 单位cm
     */
    WATER_LINE("waterLine", "14", "水位状态", 2),

    /**
     * 开关水位 0 正常 1 一级报警 2二级报警
     */
    SWITCH_WATER_LINE("switchWaterLine", "15", "开关水位", 1),
    /**
     * 井盖状态0 ：正常 1：开盖
     */
    WELL_COVER_STS("wellCoverSts", "22", "井盖状态", 1),
    /**
     * 可燃气浓度 单位%LEL，保留1位小数
     */
    FIRE_GAS_CONCENTRATION("fireGasConcentration", "23", "可燃气浓度", 2),
    /**
     * 温度 单位℃
     */
    TEMPERATURE("temperature", "24", "温度", 1),
    NETWORK_SIGNAL("networkSignal", "30", "网络信号值", 1),

    POWER_MH("powerMh", "31", "电池电量", 1),
    ;


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
        ParamMhEnum[] paramEcEnums = values();
        for (ParamMhEnum paramEcEnum : paramEcEnums) {
            if (paramEcEnum.getParamCode().equalsIgnoreCase(code)) {
                return paramEcEnum.getParamKey();
            }
        }
        return null;
    }

    ParamMhEnum(String paramKey, String paramCode, String remark, Integer length) {
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
