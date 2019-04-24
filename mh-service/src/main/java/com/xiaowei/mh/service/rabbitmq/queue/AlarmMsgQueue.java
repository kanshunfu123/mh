package com.xiaowei.mh.service.rabbitmq.queue;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by 韩金群
 * CreateTime 2019/1/25
 */
public class AlarmMsgQueue implements Serializable {
    private String id;
    private String device_no;
    private String address;
    private String district;
    private String town;
    /**
     * 小区
     */
    private String community;
    /**
     * 1电梯 2水箱
     */
    private String device_type;
    private HashMap<String, String> alarm_info;
    /**
     * 发生时间
     */
    private Long alarm_time;
    /**
     * 其他想附件信息，比如建议
     */
    private String msg;

    public String getId() {
        return id;
    }

    public AlarmMsgQueue setId(String id) {
        this.id = id;
        return this;
    }

    public String getDevice_no() {
        return device_no;
    }

    public AlarmMsgQueue setDevice_no(String device_no) {
        this.device_no = device_no;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AlarmMsgQueue setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public AlarmMsgQueue setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getTown() {
        return town;
    }

    public AlarmMsgQueue setTown(String town) {
        this.town = town;
        return this;
    }

    public String getCommunity() {
        return community;
    }

    public AlarmMsgQueue setCommunity(String community) {
        this.community = community;
        return this;
    }

    public String getDevice_type() {
        return device_type;
    }

    public AlarmMsgQueue setDevice_type(String device_type) {
        this.device_type = device_type;
        return this;
    }

    public HashMap<String, String> getAlarm_info() {
        return alarm_info;
    }

    public AlarmMsgQueue setAlarm_info(HashMap<String, String> alarm_info) {
        this.alarm_info = alarm_info;
        return this;
    }

    public Long getAlarm_time() {
        return alarm_time;
    }

    public AlarmMsgQueue setAlarm_time(Long alarm_time) {
        this.alarm_time = alarm_time;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public AlarmMsgQueue setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
