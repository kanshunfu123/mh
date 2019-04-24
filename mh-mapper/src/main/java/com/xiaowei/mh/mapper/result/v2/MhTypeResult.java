package com.xiaowei.mh.mapper.result.v2;

import java.io.Serializable;

/**
 * created by 韩金群 2019/3/1
 */
public class MhTypeResult implements Serializable {
    private String type;
    private Integer num;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
