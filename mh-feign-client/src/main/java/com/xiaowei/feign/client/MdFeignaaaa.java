package com.xiaowei.feign.client;

import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by MOMO on 2019/1/7.
 */
@FeignClient(name = "xiaowei-platform", fallback = MdFeignaaaa.MdFeignaaaaaaaaa.class)
public interface MdFeignaaaa {
    @RequestMapping("/platform/insert")
    public JSONResult test();
    @PostMapping("/platform/testDbDAO")
    public JSONResult testDbDAO(@Validated(SysUser.Add.class) @RequestBody SysUser sysUser,
                                @RequestHeader(value = "testHeader", required = false) String testHeader) ;
    @Component
    @Slf4j
    static class MdFeignaaaaaaaaa implements MdFeignaaaa {
        @Override
        public JSONResult testDbDAO(SysUser sysUser, String testHeader) {
            log.error("调用{}异常{}", "test");
            return JSONResult.errorException(-1,"","调用{}异常:platform");
        }
        @Override
        public JSONResult test() {
            log.error("调用{}异常{}", "test");
            return null;
        }
    }
}
