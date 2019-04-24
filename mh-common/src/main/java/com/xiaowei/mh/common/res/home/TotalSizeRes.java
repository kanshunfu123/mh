package com.xiaowei.mh.common.res.home;

import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/21
 */
public class TotalSizeRes implements Serializable {
    private Integer ec;
    private Integer mh;
    private Integer rw;
    private Integer cw;

    public Integer getEc() {
        return ec;
    }

    public void setEc(Integer ec) {
        this.ec = ec;
    }

    public Integer getMh() {
        return mh;
    }

    public void setMh(Integer mh) {
        this.mh = mh;
    }

    public Integer getRw() {
        return rw;
    }

    public void setRw(Integer rw) {
        this.rw = rw;
    }

    public Integer getCw() {
        return cw;
    }

    public void setCw(Integer cw) {
        this.cw = cw;
    }
}
