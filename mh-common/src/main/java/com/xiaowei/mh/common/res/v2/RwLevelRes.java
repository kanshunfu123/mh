package com.xiaowei.mh.common.res.v2;

import java.io.Serializable;

/**
 * created by 韩金群 2019/3/1
 */
public class RwLevelRes implements Serializable {
    private String level;
    private Integer num;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
