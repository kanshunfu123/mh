package com.xiaowei.mh.service.protocol;

import com.xiaowei.mh.common.util.hex.BytesHexStrTranslate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by WingsChan on 2018/9/2.
 */
public class XiaoweiEcDataReq implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(XiaoweiProtocolV1.class);

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
     * 楼层
     */
    private Integer floor;

    /**
     * 运行状态
     */
    private Integer runSts;

    /**
     * 运行方向
     */
    private Integer runDirection;

    /**
     * 运行速度
     */
    private String runSpeed;

    /**
     * 是否有人
     */
    private Integer hasPeople;

    /**
     * 上平层
     */
    private Integer upFlat;

    /**
     * 下平层
     */
    private Integer downFlat;

    /**
     * 上限位
     */
    private Integer upLimit;

    /**
     * 下限位
     */
    private Integer downLimit;

    /**
     * 门锁状态
     */
    private Integer doorLockSts;

    /**
     * 采集端电量 供电类型
     */
    private Integer atPowerType;

    /**
     * 采集端电量 电量
     */
    private Integer atPowerPct;

    /**
     * 通讯端电量 供电类型
     */
    private Integer ctPowerType;

    /**
     * 通讯端电量 电量
     */
    private Integer ctPowerPct;

    /**
     * 网络信号值
     */
    private String networkSignal;

    /**
     * 设备开盖状态
     */
    private Integer deviceOpenSts;

    /**
     * 采集端在线状态
     */
    private Integer collectionSts;

    public static XiaoweiEcDataReq buildByDataMap(HashMap<String, Object> params) {
        XiaoweiEcDataReq req = new XiaoweiEcDataReq();
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
        //楼层
        if (params.get("floor") != null) {
            req.setFloor(Integer.valueOf(params.get("floor") + "", 16));
        }
        //运行数据
        if (params.get("runningData") != null) {
            byte[] runningData = BytesHexStrTranslate.toBytes(params.get("runningData") + "");
            req.setRunSts(getHeight4(runningData[0]));
            req.setRunDirection(getLow4(runningData[0]));
        }
        //运行速度
        if (params.get("runningSpeed") != null) {
            Integer speed = Integer.valueOf(params.get("runningSpeed") + "", 16);
            req.setRunSpeed((speed * 0.1F) + "");
        }
        //是否有人
        if (params.get("hasPeople") != null) {
            req.setHasPeople(Integer.valueOf(params.get("hasPeople") + "", 16));
        }
        //平层状态
        if (params.get("flatState") != null) {
            byte[] flatState = BytesHexStrTranslate.toBytes(params.get("flatState") + "");
            req.setUpFlat(getHeight4(flatState[0]));
            req.setDownFlat(getLow4(flatState[0]));
        }
        //限位状态
        if (params.get("limitState") != null) {
            byte[] limitState = BytesHexStrTranslate.toBytes(params.get("limitState") + "");
            req.setUpLimit(getHeight4(limitState[0]));
            req.setDownLimit(getLow4(limitState[0]));
        }
        //门锁状态
        if (params.get("doorLockState") != null) {
            req.setDoorLockSts(Integer.valueOf(params.get("doorLockState") + "", 16));
        }
        //采集端电量
        if (params.get("atPower") != null) {
            byte[] atPower = BytesHexStrTranslate.toBytes(params.get("atPower") + "");
            String atPowerStr = byteToBit(atPower[0]);
            String atPowerTypeStr = atPowerStr.substring(0, 1);
            req.setAtPowerType(Integer.valueOf(atPowerTypeStr));
            String atPowerPctaStr = atPowerStr.substring(1);
            req.setAtPowerPct(Integer.valueOf(atPowerPctaStr, 2));
        }
        //通讯端电量
        if (params.get("ctPower") != null) {
            byte[] ctPower = BytesHexStrTranslate.toBytes(params.get("ctPower") + "");
            String ctPowerStr = byteToBit(ctPower[0]);
            String ctPowerTypeStr = ctPowerStr.substring(0, 1);
            req.setCtPowerType(Integer.valueOf(ctPowerTypeStr));
            String ctPowerPctaStr = ctPowerStr.substring(1);
            req.setCtPowerPct(Integer.valueOf(ctPowerPctaStr, 2));
        }
        //网络信号值
        if (params.get("networkSignal") != null) {
            req.setNetworkSignal("-" + Integer.valueOf(params.get("networkSignal") + "", 16));
        }
        //设备开盖状态
        if (params.get("deviceOpenSts") != null) {
            req.setDeviceOpenSts(Integer.valueOf(params.get("deviceOpenSts") + "", 16));
        }
        //采集端在线状态
        if (params.get("collectionSts") != null) {
            req.setCollectionSts(Integer.valueOf(params.get("collectionSts") + "", 16));
        }
        return req;
    }

    public String getCcid() {
        return ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getFaultInfo() {
        return faultInfo;
    }

    public void setFaultInfo(String faultInfo) {
        this.faultInfo = faultInfo;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getRunSts() {
        return runSts;
    }

    public void setRunSts(Integer runSts) {
        this.runSts = runSts;
    }

    public Integer getRunDirection() {
        return runDirection;
    }

    public void setRunDirection(Integer runDirection) {
        this.runDirection = runDirection;
    }

    public String getRunSpeed() {
        return runSpeed;
    }

    public void setRunSpeed(String runSpeed) {
        this.runSpeed = runSpeed;
    }

    public Integer getHasPeople() {
        return hasPeople;
    }

    public void setHasPeople(Integer hasPeople) {
        this.hasPeople = hasPeople;
    }

    public Integer getUpFlat() {
        return upFlat;
    }

    public void setUpFlat(Integer upFlat) {
        this.upFlat = upFlat;
    }

    public Integer getDownFlat() {
        return downFlat;
    }

    public void setDownFlat(Integer downFlat) {
        this.downFlat = downFlat;
    }

    public Integer getUpLimit() {
        return upLimit;
    }

    public void setUpLimit(Integer upLimit) {
        this.upLimit = upLimit;
    }

    public Integer getDownLimit() {
        return downLimit;
    }

    public void setDownLimit(Integer downLimit) {
        this.downLimit = downLimit;
    }

    public Integer getDoorLockSts() {
        return doorLockSts;
    }

    public void setDoorLockSts(Integer doorLockSts) {
        this.doorLockSts = doorLockSts;
    }

    public Integer getAtPowerType() {
        return atPowerType;
    }

    public void setAtPowerType(Integer atPowerType) {
        this.atPowerType = atPowerType;
    }

    public Integer getAtPowerPct() {
        return atPowerPct;
    }

    public void setAtPowerPct(Integer atPowerPct) {
        this.atPowerPct = atPowerPct;
    }

    public Integer getCtPowerType() {
        return ctPowerType;
    }

    public void setCtPowerType(Integer ctPowerType) {
        this.ctPowerType = ctPowerType;
    }

    public Integer getCtPowerPct() {
        return ctPowerPct;
    }

    public void setCtPowerPct(Integer ctPowerPct) {
        this.ctPowerPct = ctPowerPct;
    }

    public String getNetworkSignal() {
        return networkSignal;
    }

    public void setNetworkSignal(String networkSignal) {
        this.networkSignal = networkSignal;
    }

    public Integer getDeviceOpenSts() {
        return deviceOpenSts;
    }

    public void setDeviceOpenSts(Integer deviceOpenSts) {
        this.deviceOpenSts = deviceOpenSts;
    }

    public Integer getCollectionSts() {
        return collectionSts;
    }

    public void setCollectionSts(Integer collectionSts) {
        this.collectionSts = collectionSts;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
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

    @Override
    public String toString() {
        return "XiaoweiEcDataReq{" +
                "deviceNo='" + deviceNo + '\'' +
                ", ccid='" + ccid + '\'' +
                ", timestamp=" + timestamp +
                ", faultInfo='" + faultInfo + '\'' +
                ", floor=" + floor +
                ", runSts=" + runSts +
                ", runDirection=" + runDirection +
                ", runSpeed='" + runSpeed + '\'' +
                ", hasPeople=" + hasPeople +
                ", upFlat=" + upFlat +
                ", downFlat=" + downFlat +
                ", upLimit=" + upLimit +
                ", downLimit=" + downLimit +
                ", doorLockSts=" + doorLockSts +
                ", atPowerType=" + atPowerType +
                ", atPowerPct=" + atPowerPct +
                ", ctPowerType=" + ctPowerType +
                ", ctPowerPct=" + ctPowerPct +
                ", networkSignal='" + networkSignal + '\'' +
                ", deviceOpenSts=" + deviceOpenSts +
                ", collectionSts=" + collectionSts +
                '}';
    }
}
