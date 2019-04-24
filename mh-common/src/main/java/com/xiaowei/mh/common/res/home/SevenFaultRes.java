package com.xiaowei.mh.common.res.home;

import java.util.List;

/**
 * Created by 韩金群
 * CreateTime 2019/1/16
 */
public class SevenFaultRes {
    /**
     * 七天日期集合
     */
    private List<String>dateList;
    /**
     * 每个场景的数据
     * */
    private List<AlarmDetailRes>alarmList;

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public List<AlarmDetailRes> getAlarmList() {
        return alarmList;
    }

    public void setAlarmList(List<AlarmDetailRes> alarmList) {
        this.alarmList = alarmList;
    }
}
