package com.xiaowei.mh.service.protocol;

/**
 * @author WingsChan
 * @date 2018/9/1
 */
public enum DeviceTypeEnum {

    /**
     * 电梯设备
     */
    EC("ec", "01", "电梯设备"),
    /**
     * 河道水
     */
    RIVER_WATER("riverWater", "02", "河道水"),
    /**
     * 小区供水
     */
    COMMUNITY_WATER("communityWater", "03", "小区供水"),
    /**
     * 井盖设备
     */
    MANHOLE("manhole", "04", "井盖"),
    MANHOLE2("manhole", "4", "井盖"),
    /**
     * 饮用水设备
     */
    CW("communityWater", "2", "饮用水"),
    /**
     * 未知设备
     */
    UN_KNOWN("unKnown", "00", "未知设备"),
    /**
     * 未知设备
     */
    UN_KNOWN2("unKnown", "FF", "未知设备"),;
    private String deviceType;

    private String deviceTypeHex;

    private String remark;

    DeviceTypeEnum(String deviceType, String deviceTypeHex, String remark) {
        this.deviceType = deviceType;
        this.deviceTypeHex = deviceTypeHex;
        this.remark = remark;
    }

    public static String fetchDeviceTypeByHex(String deviceTypeHex) {
        DeviceTypeEnum[] deviceTypeEnums = values();
        for (DeviceTypeEnum paramEnum : deviceTypeEnums) {
            if (paramEnum.getDeviceTypeHex().equalsIgnoreCase(deviceTypeHex)) {
                return paramEnum.getDeviceType();
            }
        }
        return UN_KNOWN.getDeviceType();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceTypeHex() {
        return deviceTypeHex;
    }

    public void setDeviceTypeHex(String deviceTypeHex) {
        this.deviceTypeHex = deviceTypeHex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
