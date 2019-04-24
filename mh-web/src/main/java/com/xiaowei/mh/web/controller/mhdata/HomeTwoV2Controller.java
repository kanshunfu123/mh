package com.xiaowei.mh.web.controller.mhdata;

import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.v2.AreaV2Req;
import com.xiaowei.mh.service.mhdata.HomeTwoV2Service;
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
public class HomeTwoV2Controller extends BaseController {
    @Autowired
    private HomeTwoV2Service homeTwoV2Service;

    @PostMapping(value = "/todayCountEcV2/v2")
    public JSONResult todayCountEcV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.todayCountEcV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询电梯今日统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询电梯今日统计失败");
        }
    }

    @PostMapping(value = "/todayCountMhV2/v2")
    public JSONResult todayCountMhV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.todayCountMhV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询电梯今日统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询电梯今日统计失败");
        }
    }

    @PostMapping(value = "/todayCountRwV2/v2")
    public JSONResult todayCountRwV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.todayCountRwV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询电梯今日统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询电梯今日统计失败");
        }
    }

    @PostMapping(value = "/todayCountCwV2/v2")
    public JSONResult todayCountCwV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.todayCountCwV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询电梯今日统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询电梯今日统计失败");
        }
    }

    /**
     * 电梯数据监测占比
     */
    @PostMapping(value = "/monitorCountEcV2/v2")
    public JSONResult monitorCountEcV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.monitorCountEcV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，电梯数据监测占比:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("电梯数据监测占比失败");
        }
    }

    /**
     * 井盖数据监测占比
     */
    @PostMapping(value = "/monitorCountMhV2/v2")
    public JSONResult monitorCountMhV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.monitorCountMhV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，井盖数据监测占比:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("井盖数据监测占比失败");
        }
    }

    /**
     * 地表水数据监测占比
     */
    @PostMapping(value = "/monitorCountRwV2/v2")
    public JSONResult monitorCountRwV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.monitorCountRwV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，地表水数据监测占比:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("地表水数据监测占比失败");
        }
    }

    /**
     * 饮用水数据监测占比
     */
    @PostMapping(value = "/monitorCountCwV2/v2")
    public JSONResult monitorCountCwV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.monitorCountCwV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，饮用水数据监测占比:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("饮用水数据监测占比失败");
        }
    }

    /**
     * 电梯最新事件数
     */
    @PostMapping(value = "/homeTodayEventEcV2/v2")
    public JSONResult homeTodayEventEcV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.homeTodayEventEcV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，电梯最新事件数:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("电梯最新事件数失败");
        }
    }

    /**
     * 井盖最新事件数
     */
    @PostMapping(value = "/homeTodayEventMhV2/v2")
    public JSONResult homeTodayEventMhV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.homeTodayEventMhV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，井盖最新事件数:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("井盖最新事件数失败");
        }
    }

    /**
     * 地表水最新事件数
     */
    @PostMapping(value = "/homeTodayEventRwV2/v2")
    public JSONResult homeTodayEventRwV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.homeTodayEventRwV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，地表水最新事件数:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("地表水最新事件数失败");
        }
    }

    /**
     * 饮用水最新事件数
     */
    @PostMapping(value = "/homeTodayEventCwV2/v2")
    public JSONResult homeTodayEventCwV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.homeTodayEventCwV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，饮用水最新事件数:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("饮用水最新事件数失败");
        }
    }

    /**
     * 电梯今日事件占比
     */
    @PostMapping(value = "/todayEventRateEcV2/v2")
    public JSONResult todayEventRateEcV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.todayEventRateEcV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，电梯今日事件占比:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("电梯今日事件占比失败");
        }
    }

    /**
     * 井盖今日事件占比
     */
    @PostMapping(value = "/todayEventRateMhV2/v2")
    public JSONResult todayEventRateMhV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.todayEventRateMhV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，井盖今日事件占比:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("井盖今日事件占比失败");
        }
    }

    /**
     * 地表水今日事件占比
     */
    @PostMapping(value = "/todayEventRateRwV2/v2")
    public JSONResult todayEventRateRwV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.todayEventRateRwV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，地表水今日事件占比:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("地表水今日事件占比失败");
        }
    }

    /**
     * 饮用水今日事件占比
     */
    @PostMapping(value = "/todayEventRateCwV2/v2")
    public JSONResult todayEventRateCwV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.todayEventRateCwV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，饮用水今日事件占比:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("饮用水今日事件占比失败");
        }
    }

    /**
     * 电梯电池电量统计
     */
    @PostMapping(value = "/powerEcV2/v2")
    public JSONResult powerEcV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.powerEcV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，电梯电池电量统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("电梯电池电量统计失败");
        }
    }

    /**
     * 井盖电池电量统计
     */
    @PostMapping(value = "/powerMhV2/v2")
    public JSONResult powerMhV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.powerMhV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，井盖电池电量统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("井盖电池电量统计失败");
        }
    }

    /**
     * 地表水电池电量统计
     */
    @PostMapping(value = "/powerRwV2/v2")
    public JSONResult powerRwV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.powerRwV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，地表水电池电量统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("地表水电池电量统计失败");
        }
    }

    /**
     * 饮用水电池电量统计
     */
    @PostMapping(value = "/powerCwV2/v2")
    public JSONResult powerCwV2(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoV2Service.powerCwV2(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，饮用水电池电量统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("饮用水电池电量统计失败");
        }
    }
}
