package com.xiaowei.feign.client.home;

import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.fegin.AreaToDeviceReq;
import com.xiaowei.mh.common.req.fegin.AreaToDeviceReq_v1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by 韩金群
 * CreateTime 2019/1/18
 */
@FeignClient(name = "xiaowei-platform", fallback = DeviceListFeign.DeviceListFeignA.class)
public interface DeviceListFeign {
    //电梯
    @PostMapping("/platform/selectDevice/ec")
    public JSONResult ec(@Validated(AreaToDeviceReq.Query.class) @RequestBody AreaToDeviceReq areaToDeviceReq);

    //井盖
    @PostMapping("/platform/selectDevice/mh")
    public JSONResult mh(@Validated(AreaToDeviceReq.Query.class) @RequestBody AreaToDeviceReq areaToDeviceReq);

    //河道水
    @PostMapping("/platform/selectDevice/rw")
    public JSONResult rw(@Validated(AreaToDeviceReq.Query.class) @RequestBody AreaToDeviceReq areaToDeviceReq);

    //饮用水
    @PostMapping("/platform/selectDevice/cw")
    public JSONResult cw(@Validated(AreaToDeviceReq.Query.class) @RequestBody AreaToDeviceReq areaToDeviceReq);

    /**
     * 地表水
     *
     * @param areaToDeviceReq
     * @return
     */
    @PostMapping("/platform/selectDevice/rw/v1")
    public JSONResult rw_v1(@Validated(AreaToDeviceReq_v1.Query.class) @RequestBody AreaToDeviceReq_v1 areaToDeviceReq);


    /**
     * 井盖
     *
     * @param areaToDeviceReq
     * @return
     */
    @PostMapping("/platform/selectDevice/mh/v1")
    public JSONResult mh_v1(@Validated(AreaToDeviceReq_v1.Query.class) @RequestBody AreaToDeviceReq_v1 areaToDeviceReq);

    /**
     * 电梯
     *
     * @param areaToDeviceReq
     * @return
     */
    @PostMapping("/platform/selectDevice/ec/v1")
    public JSONResult ec_v1(@Validated(AreaToDeviceReq_v1.Query.class) @RequestBody AreaToDeviceReq_v1 areaToDeviceReq);

    /**
     * 电梯
     *
     * @param areaToDeviceReq
     * @return
     */
    @PostMapping("/platform/selectDevice/cw/v1")
    public JSONResult cw_v1(@Validated(AreaToDeviceReq_v1.Query.class) @RequestBody AreaToDeviceReq_v1 areaToDeviceReq);

    @Component
    @Slf4j
    static class DeviceListFeignA implements DeviceListFeign {
        @Override
        public JSONResult ec(AreaToDeviceReq areaToDeviceReq) {
            log.error("调用电梯设备集合异常{}", "ec");
            return JSONResult.errorException(-1, "", "调用电梯设备集合异常:platform");
        }

        @Override
        public JSONResult mh(AreaToDeviceReq areaToDeviceReq) {
            log.error("调用井盖设备集合异常{}", "ec");
            return JSONResult.errorException(-1, "", "调用井盖设备集合异常:platform");
        }

        @Override
        public JSONResult rw(AreaToDeviceReq areaToDeviceReq) {
            log.error("调用地表水设备集合异常{}", "ec");
            return JSONResult.errorException(-1, "", "调用地表水设备集合异常:platform");
        }

        @Override
        public JSONResult cw(AreaToDeviceReq areaToDeviceReq) {
            log.error("调用饮用水设备集合异常{}", "ec");
            return JSONResult.errorException(-1, "", "调用饮用水设备集合异常:platform");
        }

        @Override
        public JSONResult rw_v1(AreaToDeviceReq_v1 areaToDeviceReq) {
            log.error("调用地表水2设备集合异常{}", "ec");
            return JSONResult.errorException(-1, "", "调用地表水设备集合异常:platform");
        }

        @Override
        public JSONResult mh_v1(AreaToDeviceReq_v1 areaToDeviceReq) {
            log.error("调用井盖设备集合异常{}", "ec");
            return JSONResult.errorException(-1, "", "调用井盖设备集合异常:platform");
        }

        @Override
        public JSONResult ec_v1(AreaToDeviceReq_v1 areaToDeviceReq) {
            log.error("调用电梯2设备集合异常{}", "ec");
            return JSONResult.errorException(-1, "", "调用电梯设备集合异常:platform");
        }

        @Override
        public JSONResult cw_v1(AreaToDeviceReq_v1 areaToDeviceReq) {
            log.error("调用饮用水2设备集合异常{}", "ec");
            return JSONResult.errorException(-1, "", "调用饮用水设备集合异常:platform");
        }
    }
}
