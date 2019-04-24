package com.xiaowei.mh.common.res.power;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/19
 */
public class SituationFaultRes implements Serializable {
    private Integer haveEc=1;
    private Integer haveMh=1;
    private Integer haveRw=1;
    private Integer haveCw=1;
    //电梯故障率
    private String ecRate;
    //井盖故障率
    private String mhRate;
    //地表水故障率
    private String rwRate;
    //饮用水故障率
    private String cwRate;

    public Integer getHaveEc() {
        return haveEc;
    }

    public void setHaveEc(Integer haveEc) {
        this.haveEc = haveEc;
    }

    public Integer getHaveMh() {
        return haveMh;
    }

    public void setHaveMh(Integer haveMh) {
        this.haveMh = haveMh;
    }

    public Integer getHaveRw() {
        return haveRw;
    }

    public void setHaveRw(Integer haveRw) {
        this.haveRw = haveRw;
    }

    public Integer getHaveCw() {
        return haveCw;
    }

    public void setHaveCw(Integer haveCw) {
        this.haveCw = haveCw;
    }

    public String getEcRate() {
        return ecRate;
    }

    public void setEcRate(String ecRate) {
        this.ecRate = ecRate;
    }

    public String getMhRate() {
        return mhRate;
    }

    public void setMhRate(String mhRate) {
        this.mhRate = mhRate;
    }

    public String getRwRate() {
        return rwRate;
    }

    public void setRwRate(String rwRate) {
        this.rwRate = rwRate;
    }

    public String getCwRate() {
        return cwRate;
    }

    public void setCwRate(String cwRate) {
        this.cwRate = cwRate;
    }
}
