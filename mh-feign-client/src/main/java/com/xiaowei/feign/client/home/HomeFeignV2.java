package com.xiaowei.feign.client.home;

import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.fegin.AccessDeviceReq;
import com.xiaowei.mh.common.req.fegin.AccessReq;
import com.xiaowei.mh.common.req.fegin.DeviceUserRoleV2Req;
import com.xiaowei.mh.common.req.fegin.HomeOneReq;
import com.xiaowei.mh.common.v2.AreaV2Req;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * created by 韩金群 2019/2/20
 */
@FeignClient(name = "xiaowei-platform", fallback = HomeFeignV2.HomeFeignV2A.class)
public interface HomeFeignV2 {
    @PostMapping("/platform/device/deviceRoleByUserIdV2/v2")
    public JSONResult deviceRoleByUserIdServiceV2(@Validated(DeviceUserRoleV2Req.Query.class) @RequestBody DeviceUserRoleV2Req deviceUserRoleV2Req);

    /**
     * 统计总台数和运行总时长
     */
    @PostMapping("/platform/home/homeTotalV2/v2")
    public JSONResult homeTotalV2(@RequestBody AreaV2Req req);

    /**
     * 统计单个场景总台数和运行总时长
     */
    @PostMapping("/platform/home/homeTotalOneV2/v2")
    public JSONResult homeTotalOneV2(@RequestBody HomeOneReq req);
    /**
     * 统计单个场景设备列表
     */
    @PostMapping("/platform/home/homeDeviceOneV2/v2")
    public JSONResult homeDeviceOneV2(@RequestBody HomeOneReq req);

    /**
     * 统计各个场景的设备数量
     */
    @PostMapping("/platform/home/totalSizeV2/v2")
    public JSONResult totalSizeV2(@RequestBody AreaV2Req req);

    /**
     * 本年接入设备趋势图
     */
    @PostMapping("/platform/home/accessDeviceV2/v2")
    public JSONResult accessDeviceV2(@RequestBody AccessReq accessReq);

    @Component
    @Slf4j
    public class HomeFeignV2A implements HomeFeignV2 {
        @Override
        public JSONResult deviceRoleByUserIdServiceV2(DeviceUserRoleV2Req deviceUserRoleV2Req) {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }

        @Override
        public JSONResult homeTotalV2(AreaV2Req req) {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }

        @Override
        public JSONResult homeTotalOneV2(HomeOneReq req) {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }

        @Override
        public JSONResult totalSizeV2(AreaV2Req req) {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }

        @Override
        public JSONResult accessDeviceV2(AccessReq accessReq) {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }

        @Override
        public JSONResult homeDeviceOneV2(HomeOneReq req) {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }
    }
}
