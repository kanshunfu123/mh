package com.xiaowei.mh.common.res.home;

import java.io.Serializable;

/**
 * created by 韩金群 2019/3/1
 */
public class CwWaterRes implements Serializable {
    private String time;
    private Integer size;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
