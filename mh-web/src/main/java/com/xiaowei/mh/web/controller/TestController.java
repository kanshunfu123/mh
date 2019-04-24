package com.xiaowei.mh.web.controller;

import com.github.pagehelper.PageInfo;
import com.xiaowei.feign.client.MdFeignaaaa;
import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.SysUser;
import com.xiaowei.mh.common.util.RedisUtil;
import com.xiaowei.mh.mapper.dataobject.TestDbDO;
import com.xiaowei.mh.mapper.mapper.UserMapper;
import com.xiaowei.mh.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by MOMO on 2018/12/5.
 */
@RestController
@Slf4j
@RequestMapping("/mh")
public class TestController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TestService testService;
    @Autowired
    private MdFeignaaaa mdFeignaaaa;

    @PostMapping("/testDbDAO")
    public JSONResult testDbDAO(@Validated(SysUser.Add.class) @RequestBody SysUser sysUser,
                                @RequestHeader(value = "testHeader", required = false) String testHeader) {
        PageInfo<TestDbDO> pageInfo = testService.testDbDAO();
        JSONResult jsonResult = mdFeignaaaa.testDbDAO(sysUser, "");
        return JSONResult.ok(pageInfo);
    }

    @PostMapping("insert")
    public JSONResult insert(@Validated(SysUser.Add.class) @RequestBody SysUser sysUser,
                             @RequestHeader(value = "testHeader", required = false) String testHeader) {
        testService.insert();
        return JSONResult.ok(1);
    }

}
