package com.xiaowei.mh.service.datalog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xiaowei.mh.common.util.RedisKeyEnum;
import com.xiaowei.mh.common.util.RedisUtil;
import com.xiaowei.mh.mapper.dataobject.XwEquipmentMhDO;
import com.xiaowei.mh.service.protocol.XiaoweiProtocolV1;
import com.xiaowei.mh.service.protocol.manhole.ThreeDataSuccess;
import com.xiaowei.mh.service.protocol.manhole.XiaoweiMhDataReq;
import com.xiaowei.mh.service.rabbitmq.queue.AlarmMsgQueue;
import com.xiaowei.mh.service.rabbitmq.wechat.WechatTopicSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>Title:ActiveMQService</p>
 * <p>Description:</p>
 *
 * @Author WingsChan
 * @date 2018/10/16
 */
@Service
public class ActiveMQService {

    private static final Logger log = LoggerFactory.getLogger(ActiveMQService.class);

    private static final String ALARM_STATUS_FAULT = "1";
    private static final String ALARM_STATUS_RECOVERY = "2";
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private WechatTopicSender wechatMHTopicSender;

    public void sendMhAlarmMessage(XiaoweiProtocolV1 msg) {
        try {
            XiaoweiMhDataReq req = msg.getXiaoweiMhDataReq();
            if (req != null) {
                AlarmMsgQueue msgReq = initAlarmMsgQueue(req, "");
                //报警信息，根据设备类型而定，电梯的话为发生故障、故障恢复等，水箱的话为水质信息。
                HashMap<String, String> alarmInfo = new HashMap<>();
                alarmInfo.put("status", ALARM_STATUS_FAULT);
                alarmInfo.put("images", "");
                //处理故障信息
                String faultInfo = req.getFaultInfo();
                int index = 1;
                //取每一位填入对应故障中
                Integer fault = Integer.valueOf(faultInfo);
                while (fault != 0 && index <= 8) {
                    int value = fault % 2;
                    dealMhFaultInfo(index, alarmInfo, value);
                    fault = fault / 2;
                    index++;
                }
                msgReq.setAlarm_info(alarmInfo);
                //微信
                wechatMHTopicSender.send(JSONObject.toJSONString(msgReq), null);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void sendNewMhAlarmMessage(ThreeDataSuccess dataSuccess) {
        try {
            AlarmMsgQueue msgReq = new AlarmMsgQueue();
            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + dataSuccess.getDeviceNo());
            XwEquipmentMhDO mhDO = null;
            if (o != null) {
                mhDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentMhDO>() {
                });
                msgReq.setAddress(mhDO.getStreet() + mhDO.getDeviceCode());

            } else {
                msgReq.setAddress("未知地址");
            }
            msgReq.setAlarm_time(System.currentTimeMillis());
            msgReq.setDevice_no(dataSuccess.getDeviceNo());
            msgReq.setId(dataSuccess.getDeviceNo());
            //其他想附件信息，比如建议
            msgReq.setMsg("");

            //报警信息，根据设备类型而定，电梯的话为发生故障、故障恢复等，水箱的话为水质信息。
            HashMap<String, String> alarmInfo = new HashMap<>();
            alarmInfo.put("status", ALARM_STATUS_FAULT);
            alarmInfo.put("images", "");
            //判断错误状态
            //状态	按bit位处理，0：正常	1：异常，bit0：设备状态 【1】 bit1：井盖状态报警 【2】	bit5：硫化氢报警 【32】
            if (!dataSuccess.getFaultInfo().equals("0")) {
                alarmInfo.put("manhole_cover_alarm", "1");
            }
            msgReq.setAlarm_info(alarmInfo);
            //增加部分新参数
            wechatMHTopicSender.send(JSONObject.toJSONString(msgReq), null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 初始化队列对象
     *
     * @param req
     * @param msg
     * @return
     */
    private AlarmMsgQueue initAlarmMsgQueue(XiaoweiMhDataReq req, String msg) {
        AlarmMsgQueue msgReq = new AlarmMsgQueue();
        Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + req.getDeviceNo());
        XwEquipmentMhDO mhDO = null;
        if (o != null) {
            mhDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentMhDO>() {
            });
            msgReq.setAddress(mhDO.getStreet() + mhDO.getDeviceCode());

        } else {
            msgReq.setAddress("未知地址");
        }
        msgReq.setAlarm_time(System.currentTimeMillis());
        msgReq.setDevice_no(req.getDeviceNo());
        msgReq.setId(req.getDeviceNo());
        //其他想附件信息，比如建议
        msgReq.setMsg(msg);
        return msgReq;
    }

    private void dealMhFaultInfo(int index, HashMap<String, String> alarmInfo, int value) {
        switch (index) {
            case 1:
                alarmInfo.put("water_level_alarm", value + "");
                break;
            case 2:
                alarmInfo.put("manhole_cover_alarm", value + "");
                break;
            default:
                break;
        }
    }
}
