package com.xiaowei.mh.common.v2;

import com.xiaowei.mh.common.error.BaseReq;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * created by 韩金群 2019/2/20
 */
public class AreaV2Req extends BaseReq implements Serializable {
    @NotNull(message = "level不能为空", groups = {Query.class})
    private Integer level;
    @NotNull(message = "parentId不能为空", groups = {Query.class})
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
}
