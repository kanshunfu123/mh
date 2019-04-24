package com.xiaowei.mh.common.res.mhdata;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 韩金群
 * CreateTime 2019/1/17
 */
public class MhParamRes implements Serializable {
    //参数名称
    private String name;
    //单位
    private String unit;
    //对应日期的数据集合
    private List<String> dataList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }
}
