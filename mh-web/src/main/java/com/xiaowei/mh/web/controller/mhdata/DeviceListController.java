package com.xiaowei.mh.web.controller.mhdata;

import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.error.BaseReq;
import com.xiaowei.mh.common.req.fegin.AreaToDeviceReq;
import com.xiaowei.mh.common.req.home.DeviceListReq;
import com.xiaowei.mh.service.mhdata.DeviceListService;
import com.xiaowei.mh.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by 韩金群
 * CreateTime 2019/1/16
 */
@Slf4j
@RestController
@RequestMapping("/mh")
public class DeviceListController extends BaseController {
    @Autowired
    private DeviceListService deviceListService;

    @PostMapping(value = "/ecList/v1")
    public JSONResult ecList(@Validated(DeviceListReq.Query.class) @RequestBody DeviceListReq req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return deviceListService.ecList(req, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，电梯列表查询失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("电梯列表查询失败");
        }

    }

    @PostMapping(value = "/mhList/v1")
    public JSONResult mhList(@Validated(DeviceListReq.Query.class) @RequestBody DeviceListReq req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return deviceListService.mhList(req, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，井盖列表查询失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("井盖列表查询失败");
        }
    }

    @PostMapping(value = "/rwList/v1")
    public JSONResult rwList(@Validated(DeviceListReq.Query.class) @RequestBody DeviceListReq req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return deviceListService.rwList(req, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，地表水列表查询失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("地表水列表查询失败");
        }
    }

    @PostMapping(value = "/cwList/v1")
    public JSONResult cwList(@Validated(DeviceListReq.Query.class) @RequestBody DeviceListReq req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return deviceListService.cwList(req, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，饮用水列表查询失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("饮用水列表查询失败");
        }
    }
}
