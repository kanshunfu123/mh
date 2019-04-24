package com.xiaowei.mh.common.req.home;

import com.xiaowei.mh.common.error.BaseReq;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/19
 */
public class DeviceListReq extends BaseReq implements Serializable {
    //下拉框最底部id
    @NotNull(message = "下拉框最底部selectId 必填", groups = {Query.class})
    private Long selectId;
    private Integer level;
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getSelectId() {
        return selectId;
    }

    public void setSelectId(Long selectId) {
        this.selectId = selectId;
    }
}
