package com.xiaowei.mh.service.datalog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiaowei.mh.common.util.RedisKeyEnum;
import com.xiaowei.mh.common.util.RedisUtil;
import com.xiaowei.mh.mapper.dataobject.XwEquipmentMhDO;
import com.xiaowei.mh.service.protocol.CMDEnum;
import com.xiaowei.mh.service.protocol.XiaoweiProtocolV1;
import com.xiaowei.mh.service.protocol.manhole.NewLogVo;
import com.xiaowei.mh.service.protocol.manhole.ThreeDataSuccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * Created by 韩金群
 * CreateTime 2019/1/24
 */
@Component("dealDataLogTask")
public class DealDataLogTask {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DataLogService dataLogService;
    @Autowired
    private ActiveMQService activeMQService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
     */
    @Async("threadPoolTaskExecutor")
    public Future<String> doDealDataLogTask(XiaoweiProtocolV1 msg, String text) {
        try {
            //处理井盖数据
            dataLogService.dealMhData(msg, text);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new AsyncResult<>("AutoLotBeginTask accomplished!");
    }

    @Async("threadPoolTaskExecutor")
    public Future<String> doThreeMh(ThreeDataSuccess req, NewLogVo newLogVo) {
        try {
            //处理第三方井盖数据
            dataLogService.doMhTwo(req, newLogVo);
            if (2 == req.getDataType()) {
                //微信处理 判断是否推送
                XwEquipmentMhDO mhDOWX = null;
                Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + req.getDeviceNo());
                if (o != null) {
                    mhDOWX = JSON.parseObject(o + "", new TypeReference<XwEquipmentMhDO>() {
                    });
                }
                if (mhDOWX != null) {
                    if (mhDOWX.getFaultPush() != null) {
                        if (mhDOWX.getFaultPush() == 1) {
                            activeMQService.sendNewMhAlarmMessage(req);
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new AsyncResult<>("AutoLotBeginTask accomplished!");
    }
}
