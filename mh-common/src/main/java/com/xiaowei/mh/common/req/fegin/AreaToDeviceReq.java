package com.xiaowei.mh.common.req.fegin;

import com.xiaowei.mh.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/18
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaToDeviceReq extends BaseReq implements Serializable {
    //下拉框最底部id
    @NotNull(message = "下拉框最底部selectId 必填", groups = {Query.class})
    private Long selectId;

    //用户id
    @NotNull(message = "用户userId 必填", groups = {Query.class})
    private Long userId;
    //第三方组id  小为为1
    @NotNull(message = "三方组id   必填", groups = {Query.class})
    private Long groupId;
}
