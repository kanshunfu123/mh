package com.xiaowei.mh.mapper.result;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 韩金群
 * CreateTime 2019/1/16
 */
public class SevenFaultResult implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 采集时间
     */
    private Date collectTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
}
