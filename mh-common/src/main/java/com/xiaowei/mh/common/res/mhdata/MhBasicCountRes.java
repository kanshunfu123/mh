package com.xiaowei.mh.common.res.mhdata;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 韩金群
 * CreateTime 2019/1/17
 */
public class MhBasicCountRes implements Serializable {
    //日期集合
    private List<String> dateList;
    //参数数据
    private List<MhParamRes> paramList;

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public List<MhParamRes> getParamList() {
        return paramList;
    }

    public void setParamList(List<MhParamRes> paramList) {
        this.paramList = paramList;
    }
}
