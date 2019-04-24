package com.xiaowei.mh.common.res.home;

import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/16
 */
public class HomeTotalRes implements Serializable {
    /**
     * 总台数
     */
    private Integer count;
    /**
     * 运行时长（毫秒）
     */
    private Long runTime;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }
}
