package com.xiaowei.mh.common.req.fegin;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/15
 */
public class HomeOneReq implements Serializable {
    //单个设备场景类型
    private String type;
    private Integer level;
    private Long parentId;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
