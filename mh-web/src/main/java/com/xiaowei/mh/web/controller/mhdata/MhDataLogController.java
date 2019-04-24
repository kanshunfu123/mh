package com.xiaowei.mh.web.controller.mhdata;

import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.mhdata.MhHistoryPageReq;
import com.xiaowei.mh.common.req.mhdata.MhInfoReq;
import com.xiaowei.mh.common.req.mhdata.MhOnlineRateReq;
import com.xiaowei.mh.common.req.mhdata.ParamCountReq;
import com.xiaowei.mh.service.mhdata.MhDataLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 韩金群
 * CreateTime 2019/1/12
 */
@RestController
@Slf4j
@RequestMapping(value = "/mh")
public class MhDataLogController {
    @Autowired
    private MhDataLogService mhDataLogService;

    /**
     * 井盖分页
     */
    @PostMapping(value = "/mhHistoryPage/v1")
    public JSONResult ecHistoryPage(@Validated(MhHistoryPageReq.Query.class) @RequestBody MhHistoryPageReq req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        return mhDataLogService.mhHistoryPage(req);
    }

    /**
     * 井盖详情
     */
    @PostMapping(value = "/mhInfo/v1")
    public JSONResult mhInfo(@Validated(MhInfoReq.Query.class) @RequestBody MhInfoReq req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        return mhDataLogService.mhInfo(req);
    }

    /**
     * 设备在线率
     */
    @PostMapping(value = "/mhOnlineRate/v1")
    public JSONResult mhOnlineRate(@Validated(MhOnlineRateReq.Query.class) @RequestBody MhOnlineRateReq req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        return mhDataLogService.mhOnlineRate(req);
    }

    /**
     * 井下基本参数统计
     */
    @PostMapping(value = "/paramCount/v1")
    public JSONResult paramCount(@Validated(ParamCountReq.Query.class) @RequestBody ParamCountReq req, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        return mhDataLogService.paramCount(req);
    }
}
