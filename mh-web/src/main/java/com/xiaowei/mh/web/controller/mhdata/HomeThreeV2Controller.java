package com.xiaowei.mh.web.controller.mhdata;

import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.v2.AreaV2Req;
import com.xiaowei.mh.service.mhdata.HomeThreeV2Service;
import com.xiaowei.mh.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * created by 韩金群 2019/2/27
 */
@Slf4j
@RestController
@RequestMapping("/mh")
public class HomeThreeV2Controller extends BaseController {
    @Autowired
    private HomeThreeV2Service homeThreeV2Service;

    /**
     * 电梯年检月份统计（用）
     */
    @PostMapping(value = "/safeCountEc/v2")
    public JSONResult safeCountEc(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.safeCountEc(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，电梯年检月份统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("电梯年检月份统计失败");
        }
    }

    /**
     * 饮用水达标数据值占比（用）
     */
    @PostMapping(value = "/standardCw/v2")
    public JSONResult standardCw(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.standardCw(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，饮用水达标数据值占比:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("饮用水达标数据值占比失败");
        }
    }

    /**
     * 电梯故障类型占比图（用）
     */
    @PostMapping(value = "/ecFaultType/v2")
    public JSONResult ecFaultType(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.ecFaultType(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，电梯故障类型占比图:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("电梯故障类型占比图失败");
        }
    }
    /**
     * 井盖故障类型占比图（用）
     */
    @PostMapping(value = "/mhFaultType/v2")
    public JSONResult mhFaultType(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.mhFaultType(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，井盖故障类型占比图:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("井盖故障类型占比图失败");
        }
    }
    /**
     * 地表水故障类型占比图（用）
     */
    @PostMapping(value = "/rwFaultType/v2")
    public JSONResult rwFaultType(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.rwFaultType(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，地表水故障类型占比图:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("地表水故障类型占比图失败");
        }
    }
    /**
     * 饮用水故障类型占比图（用）
     */
    @PostMapping(value = "/cwFaultType/v2")
    public JSONResult cwFaultType(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.cwFaultType(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，饮用水故障类型占比图:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("饮用水故障类型占比图失败");
        }
    }
    /**
     * 电梯场景品牌统计图（用）
     */
    @PostMapping(value = "/ecBrandCount/v2")
    public JSONResult ecBrandCount(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.ecBrandCount(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，电梯场景品牌统计图:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("电梯场景品牌统计图失败");
        }
    }
    /**
     * 电梯场景使用年限统计图（用）
     */
    @PostMapping(value = "/ecYearCount/v2")
    public JSONResult ecYearCount(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.ecYearCount(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，电梯场景使用年限统计图:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("电梯场景使用年限统计图失败");
        }
    }
    /**
     * 井盖场景使用年限统计图（用）
     */
    @PostMapping(value = "/mhYearCount/v2")
    public JSONResult mhYearCount(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.mhYearCount(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，井盖场景使用年限统计图:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("井盖场景使用年限统计图失败");
        }
    }
    /**
     * 井盖场景材质数量统计图（用）
     */
    @PostMapping(value = "/mhMaterialCount/v2")
    public JSONResult mhMaterialCount(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.mhMaterialCount(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，井盖场景材质数量统计图:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("井盖场景材质数量统计图失败");
        }
    }
    /**
     * 井盖场景直径占比统计图（用）
     */
    @PostMapping(value = "/mhDiameterCount/v2")
    public JSONResult mhDiameterCount(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.mhDiameterCount(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，井盖场景直径占比统计图:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("井盖场景直径占比统计图失败");
        }
    }
    /**
     * 井盖场景适用类型占比统计图（用）
     */
    @PostMapping(value = "/mhTypeCount/v2")
    public JSONResult mhTypeCount(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.mhTypeCount(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，井盖场景适用类型占比统计图:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("井盖场景适用类型占比统计图失败");
        }
    }
    /**
     * 饮用水材质数量占比统计（用）
     */
    @PostMapping(value = "/cwMaterialCount/v2")
    public JSONResult cwMaterialCount(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.cwMaterialCount(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，饮用水材质数量占比统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("饮用水材质数量占比统计失败");
        }
    }
    /**
     * 地表水使用类型占比统计（用）
     */
    @PostMapping(value = "/rwTypeCount/v2")
    public JSONResult rwTypeCount(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.rwTypeCount(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，地表水使用类型占比统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("地表水使用类型占比统计失败");
        }
    }
    /**
     * 地表水水质等级占比统计（用）
     */
    @PostMapping(value = "/rwLevelCount/v2")
    public JSONResult rwLevelCount(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.rwLevelCount(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，地表水水质等级占比统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("地表水水质等级占比统计失败");
        }
    }
    /**
     * 地表水水质参数达标数值占比统计（用）
     */
    @PostMapping(value = "/standardRw/v2")
    public JSONResult standardRw(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.standardRw(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，地表水水质参数达标数值占比统计:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("地表水水质参数达标数值占比统计失败");
        }
    }
    /**
     *水箱用水用量变化图
     * */
    @PostMapping(value = "/cwWaterCount/v2")
    public JSONResult cwWaterCount(@RequestBody AreaV2Req req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return homeThreeV2Service.cwWaterCount(this.redisUser(userInfo), req);
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，水箱用水用量变化图:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("水箱用水用量变化图失败");
        }
    }
}
