package com.xiaowei.feign.client.home;

import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.fegin.AccessDeviceReq;
import com.xiaowei.mh.common.req.fegin.HomeOneReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by 韩金群
 * CreateTime 2019/1/16
 */
@FeignClient(name = "xiaowei-platform", fallback = HomeFeign.HomeFeignA.class)
public interface HomeFeign {
    @PostMapping("/platform/home/homeTotal/v1")
    public JSONResult homeTotal();

    @PostMapping("/platform/home/homeTotalOne/v1")
    public JSONResult homeTotalOne(@RequestBody HomeOneReq req);

    @PostMapping("/platform/home/latestDevice/v1")
    public JSONResult latestDevice();

    @PostMapping("/platform/home/deviceNoListEc/v1")
    public JSONResult deviceNoListEc();

    @PostMapping("/platform/home/deviceNoListMh/v1")
    public JSONResult deviceNoListMh();

    @PostMapping("/platform/home/deviceNoListRw/v1")
    public JSONResult deviceNoListRw();

    @PostMapping("/platform/home/deviceNoListCw/v1")
    public JSONResult deviceNoListCw();

    @PostMapping("/platform/home/totalSize/v1")
    public JSONResult totalSize();

    @PostMapping("/platform/home/accessDevice/v1")
    public JSONResult accessDevice(@RequestBody List<AccessDeviceReq> list);

    @Component
    @Slf4j
    static class HomeFeignA implements HomeFeign {
        @Override
        public JSONResult homeTotal() {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }

        @Override
        public JSONResult homeTotalOne(HomeOneReq req) {
            return null;
        }

        @Override
        public JSONResult latestDevice() {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }

        @Override
        public JSONResult deviceNoListEc() {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }

        @Override
        public JSONResult deviceNoListMh() {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }

        @Override
        public JSONResult deviceNoListRw() {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }

        @Override
        public JSONResult deviceNoListCw() {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }

        @Override
        public JSONResult totalSize() {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }

        @Override
        public JSONResult accessDevice(List<AccessDeviceReq> list) {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1, "", "调用{}异常:platform");
        }
    }
}
