package com.xiaowei.mh.common.res.v2;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/28
 */
public class MhYearRes implements Serializable {
    private String year;
    private Integer num;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
