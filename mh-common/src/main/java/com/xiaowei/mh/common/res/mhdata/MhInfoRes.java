package com.xiaowei.mh.common.res.mhdata;

import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/12
 */
public class MhInfoRes implements Serializable {
    //设备信息
    private MhEquipmentRes equipment;
    //场景信息
    private MhSenceRes sence;
    //最新信息
    private MhLatestRes latest;

    public MhEquipmentRes getEquipment() {
        return equipment;
    }

    public void setEquipment(MhEquipmentRes equipment) {
        this.equipment = equipment;
    }

    public MhSenceRes getSence() {
        return sence;
    }

    public void setSence(MhSenceRes sence) {
        this.sence = sence;
    }

    public MhLatestRes getLatest() {
        return latest;
    }

    public void setLatest(MhLatestRes latest) {
        this.latest = latest;
    }
}
