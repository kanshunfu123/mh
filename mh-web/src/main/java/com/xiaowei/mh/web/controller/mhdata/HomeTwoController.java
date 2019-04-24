package com.xiaowei.mh.web.controller.mhdata;

import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.service.mhdata.FaultCountService;
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
 * created by 韩金群 2019/2/15
 */
@Slf4j
@RestController
@RequestMapping("/mh")
public class HomeTwoController extends BaseController {
    @Autowired
    private FaultCountService faultCountService;
    @Autowired
    private HomeTwoService homeTwoService;

    /**
     * 电梯监测数据统计
     */
    @PostMapping(value = "/monitorCountEc/v1")
    public JSONResult monitorCountEc(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoService.monitorCountEc(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，电梯监测数据统计失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("电梯监测数据统计失败");
        }
    }

    /**
     * 井盖监测数据统计
     */
    @PostMapping(value = "/monitorCountMh/v1")
    public JSONResult monitorCountMh(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoService.monitorCountMh(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，井盖监测数据统计失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("井盖监测数据统计失败");
        }
    }

    /**
     * 地表水监测数据统计
     */
    @PostMapping(value = "/monitorCountRw/v1")
    public JSONResult monitorCountRw(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoService.monitorCountRw(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，地表水监测数据统计失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("地表水监测数据统计失败");
        }
    }

    /**
     * 饮用水监测数据统计
     */
    @PostMapping(value = "/monitorCountCw/v1")
    public JSONResult monitorCountCw(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoService.monitorCountCw(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，饮用水监测数据统计失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("饮用水监测数据统计失败");
        }
    }

    /**
     * 电梯电池电量监测数据统计
     */
    @PostMapping(value = "/powerEc/v1")
    public JSONResult powerEc(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoService.powerEc(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，电梯电池电量监测数据统计失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("电梯电池电量监测数据统计失败");
        }
    }

    /**
     * 井盖电池电量监测数据统计
     */
    @PostMapping(value = "/powerMh/v1")
    public JSONResult powerMh(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoService.powerMh(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，井盖电池电量监测数据统计失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("井盖电池电量监测数据统计失败");
        }
    }

    /**
     * 地表水电池电量监测数据统计
     */
    @PostMapping(value = "/powerRw/v1")
    public JSONResult powerRw(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoService.powerRw(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，地表水电池电量监测数据统计失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("地表水电池电量监测数据统计失败");
        }
    }

    /**
     * 饮用水电池电量监测数据统计
     */
    @PostMapping(value = "/powerCw/v1")
    public JSONResult powerCw(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoService.powerCw(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，饮用水电池电量监测数据统计失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("饮用水电池电量监测数据统计失败");
        }
    }

    /**
     * 查询首页场景故障图
     */
    @PostMapping(value = "/situationFault/v1")
    public JSONResult situationFault(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoService.situationFault(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询首页场景故障图失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询首页场景故障图失败");
        }
    }


    /**
     * 查询首页本年接入设备趋势图
     */
    @PostMapping(value = "/accessDevice/v1")
    public JSONResult accessDevice(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeTwoService.accessDevice(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询首页本年接入设备趋势图失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询首页本年接入设备趋势图失败");
        }
    }
}
