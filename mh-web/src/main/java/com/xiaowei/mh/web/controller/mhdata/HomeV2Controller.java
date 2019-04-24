package com.xiaowei.mh.web.controller.mhdata;

import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.v2.AreaV2Req;
import com.xiaowei.mh.service.mhdata.HomeV2Service;
import com.xiaowei.mh.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * created by 韩金群 2019/2/21
 */
@Slf4j
@RestController
@RequestMapping("/mh")
public class HomeV2Controller extends BaseController {
    @Autowired
    private HomeV2Service homeV2Service;

    /**
     * 首页今日统计数
     */
    @PostMapping(value = "/todayCountV2/v2")
    public JSONResult todayCountV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeV2Service.todayCountV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，首页今日统计数失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("首页今日统计数失败");
        }
    }

    /**
     * 当月监测数据占比
     */
    @PostMapping(value = "/monthCountLineV2/v2")
    public JSONResult monthCountLineV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeV2Service.monthCountLineV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，当月监测数据占比失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("当月监测数据占比失败");
        }
    }

    /**
     * 场景故障图
     */
    @PostMapping(value = "/situationFaultV2/v2")
    public JSONResult situationFaultV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeV2Service.situationFaultV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，当月监测数据占比失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("当月监测数据占比失败");
        }
    }

    /**
     * 首页场景设备数量及占比
     */
    @PostMapping(value = "/deviceRateV2/v2")
    public JSONResult deviceRateV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeV2Service.deviceRateV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，首页场景设备数量及占比失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("当首页场景设备数量及占比失败");
        }
    }

    /**
     * 半月事件趋势图
     */
    @PostMapping(value = "/sevenTwoFaultV2/v2")
    public JSONResult sevenTwoFaultV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeV2Service.sevenTwoFaultV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，半月事件趋势图失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("半月事件趋势图失败");
        }
    }

    /**
     * 本年接入设备趋势图
     */
    @PostMapping(value = "/accessDeviceV2/v2")
    public JSONResult accessDeviceV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeV2Service.accessDeviceV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，本年接入设备趋势图失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("本年接入设备趋势图失败");
        }
    }

    /**
     * 今日最新事件
     */
    @PostMapping(value = "/homeTodayEventV2/v2")
    public JSONResult homeTodayEventV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeV2Service.homeTodayEventV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，今日最新事件失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("今日最新事件失败");
        }
    }
}
