package com.xiaowei.mh.common.res.home;

import java.util.List;

/**
 * Created by 韩金群
 * CreateTime 2019/1/16
 */
public class AlarmDetailRes {
    /**
     * 场景名称
     */
    private String name;
    /**
     * 日期报警数量集合
     */
    private List<Integer> dataList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getDataList() {
        return dataList;
    }

    public void setDataList(List<Integer> dataList) {
        this.dataList = dataList;
    }
}
