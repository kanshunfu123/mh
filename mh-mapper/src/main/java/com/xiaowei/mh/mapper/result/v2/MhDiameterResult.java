package com.xiaowei.mh.mapper.result.v2;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/28
 */
public class MhDiameterResult implements Serializable {
    private String diameter;
    private Integer num;

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
