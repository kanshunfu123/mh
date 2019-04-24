package com.xiaowei.mh.common.req.fegin;

import com.xiaowei.mh.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Created by 韩金群
 * CreateTime 2019/1/14
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceUserRoleReq extends BaseReq {
    @NotNull(message = "用户id 必填 userId", groups = {Query.class})
    private Long userId;
}
