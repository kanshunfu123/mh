package com.xiaowei.mh.mapper.result.v2;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/28
 */
public class EcBrandResult implements Serializable {
    private String brand;
    private Integer num;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
