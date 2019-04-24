package com.xiaowei.feign.client.home;

import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.fegin.DeviceUserRoleReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by 韩金群
 * CreateTime 2019/1/14
 */
@FeignClient(name = "xiaowei-platform", fallback = UserDeviceFeign.UserDeviceFeignA.class)
public interface UserDeviceFeign {
    @PostMapping("/platform/device/deviceRoleByUserId/v1")
    public JSONResult deviceRoleByUserIdService(@Validated(DeviceUserRoleReq.Query.class) @RequestBody DeviceUserRoleReq deviceUserRoleReq
    );

    @Component
    @Slf4j
    static class UserDeviceFeignA implements UserDeviceFeign {
        @Override
        public JSONResult deviceRoleByUserIdService(DeviceUserRoleReq deviceUserRoleReq) {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }
    }
}
