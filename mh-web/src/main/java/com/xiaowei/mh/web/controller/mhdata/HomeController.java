package com.xiaowei.mh.web.controller.mhdata;

import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.res.redis.RedisUser;
import com.xiaowei.mh.service.mhdata.FaultCountService;
import com.xiaowei.mh.service.mhdata.HomeService;
import com.xiaowei.mh.service.mhdata.HomeTwoService;
import com.xiaowei.mh.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * Created by 韩金群
 * CreateTime 2019/1/14
 */
@Slf4j
@RestController
@RequestMapping("/mh")
public class HomeController extends BaseController {
    @Autowired
    private HomeService homeService;
    @Autowired
    private FaultCountService faultCountService;
    @Autowired
    private HomeTwoService homeTwoService;

    @PostMapping(value = "/todayCount/v1")
    public JSONResult todayCount(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeService.todayCount(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，权限点新增失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增权限点失败");
        }
    }
    @PostMapping(value = "/todayCountEc/v1")
    public JSONResult todayCountEc(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoService.todayCountEc(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询电梯今日统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询电梯今日统计失败");
        }
    }
    @PostMapping(value = "/todayCountMh/v1")
    public JSONResult todayCountMh(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoService.todayCountMh(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询电梯今日统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询电梯今日统计失败");
        }
    }
    @PostMapping(value = "/todayCountRw/v1")
    public JSONResult todayCountRw(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoService.todayCountRw(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询电梯今日统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询电梯今日统计失败");
        }
    }
    @PostMapping(value = "/todayCountCw/v1")
    public JSONResult todayCountCw(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoService.todayCountCw(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询电梯今日统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询电梯今日统计失败");
        }
    }

    @PostMapping(value = "/homeTodayEvent/v1")
    public JSONResult homeTodayEvent(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeService.homeTodayEvent(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，权限点新增失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增权限点失败");
        }
    }

    @PostMapping(value = "/faultRanking/v1")
    public JSONResult faultRanking(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return faultCountService.faultRanking(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询故障排行失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询故障排行失败");
        }
    }

    @PostMapping(value = "/sevenTwoFault/v1")
    public JSONResult sevenTwoFault(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeService.sevenTwoFault(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询半月事件失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询半月事件失败");
        }
    }

    /**
     * 最新接入设备
     */
    @PostMapping(value = "/latestDevice/v1")
    public JSONResult latestDevice(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeService.latestDevice(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询最新接入设备失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询最新接入设备失败");
        }
    }

    /**
     * 首页数据：本月监测数据
     */
    @PostMapping(value = "/monthCountLine/v1")
    public JSONResult monthCountLine(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeService.monthCountLine(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询本月监测数据失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询本月监测数据失败");
        }
    }
}
