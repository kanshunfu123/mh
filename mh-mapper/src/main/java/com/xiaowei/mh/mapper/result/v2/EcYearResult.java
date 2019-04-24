package com.xiaowei.mh.mapper.result.v2;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/28
 */
public class EcYearResult implements Serializable {
    private Integer year;
    private Integer num;
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
