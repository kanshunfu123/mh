package com.xiaowei.mh.service.protocol;

/**
 * Created by WingsChan on 2018/9/1.
 */
public enum ParamEcEnum {

    DEVICE_NO("deviceNo", "01", "设备IMEI号，BCD码",8),
    CCID("ccid", "02", "SIM卡的CCID号",10),
    DATA_OPT_STS("dataOptSts", "03", "数据处理状态",1),
    TIME("time", "04", "UNIX时间戳(秒)",4),
    FAULT_INFO("faultInfo", "05", "故障信息",2),
    FLOOR("floor", "06", "楼层",1),
    RUNNING_DATA("runningData", "07", "运行数据",1),
    RUNNING_SPEED("runningSpeed", "08", "运行速度,单位0.1m/s",1),

    HAS_PEOPLE("hasPeople", "09", "人体状态 0:无人 1:有人",1),
    FLAT_STATE("flatState", "0A", "平层状态,高4bit代表上平层；" + "低4bit代表下平层；" + "0:非平层位置 1:平层位置",1),
    LIMIT_STATE("limitState", "0B", "高4bit代表上限位；" + "低4bit代表下限位；" + "0:非限位位置 1:限位",1),
    DOOR_LOCK_STATE("doorLockState", "0C", "门锁状态,0:门关闭 1:门打开",1),
    AT_POWER("atPower", "0D", "采集端电量",1),
    CT_POWER("ctPower", "0E", "通讯端电量",1),
    NETWORK_SIGNAL("networkSignal", "0F", "网络信号值,单位：dbm",1),
    DEVICE_OPEN_STS("deviceOpenSts", "10", "设备开盖状态 0:闭盖" + "1:开盖",1),
    COLLECTION_STS("collectionSts", "11", "采集端在线状态,0:离线" + "1:在线",1),
    FLOOR_MEAN_HEIGHT_SET_VAL("floorMeanHeightSetVal", "30", "楼层平均高度设定值,精度 0.1米",1),
    OVER_SPEED_SET_VAL("over_speedSettingVal", "31", "超速设定值,精度：0.1m/s",1),
    UPLOAD_TIME_SET_VAL("uploadTimeSetVal", "32", "设备上传时间间隔设定值,精度：秒",1),;


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
        ParamEcEnum[] paramEnums = values();
        for (ParamEcEnum paramEnum : paramEnums) {
            if (paramEnum.getParamCode().equalsIgnoreCase(code)) {
                return paramEnum.getParamKey();
            }
        }
        return null;
    }

    ParamEcEnum(String paramKey, String paramCode, String remark, Integer length) {
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
