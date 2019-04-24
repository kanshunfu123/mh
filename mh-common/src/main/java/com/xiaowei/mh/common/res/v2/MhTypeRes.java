package com.xiaowei.mh.common.res.v2;

import java.io.Serializable;

/**
 * created by 韩金群 2019/3/1
 */
public class MhTypeRes implements Serializable {
    private String type;
    private String rate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
