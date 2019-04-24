package com.xiaowei.mh.mapper.result.v2;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/28
 */
public class MhMaterialResult implements Serializable {
    private String material;
    private Integer num;

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
