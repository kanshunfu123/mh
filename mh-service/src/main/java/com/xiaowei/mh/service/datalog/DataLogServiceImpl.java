package com.xiaowei.mh.service.datalog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xiaowei.mh.common.common.DateUtils;
import com.xiaowei.mh.common.util.RedisKeyEnum;
import com.xiaowei.mh.common.util.RedisUtil;
import com.xiaowei.mh.common.util.UUIdUtil;
import com.xiaowei.mh.common.util.hex.BytesHexStrTranslate;
import com.xiaowei.mh.mapper.dataobject.LogDO;
import com.xiaowei.mh.mapper.dataobject.MhDataLogDO;
import com.xiaowei.mh.mapper.dataobject.NewLogDO;
import com.xiaowei.mh.mapper.dataobject.XwEquipmentMhDO;
import com.xiaowei.mh.mapper.mapper.LogMapper;
import com.xiaowei.mh.mapper.mapper.MhDataLogMapper;
import com.xiaowei.mh.mapper.mapper.NewLogMapper;
import com.xiaowei.mh.service.protocol.CMDEnum;
import com.xiaowei.mh.service.protocol.DeviceTypeEnum;
import com.xiaowei.mh.service.protocol.XiaoweiProtocolV1;
import com.xiaowei.mh.service.protocol.manhole.NewLogVo;
import com.xiaowei.mh.service.protocol.manhole.ThreeDataSuccess;
import com.xiaowei.mh.service.protocol.manhole.XiaoweiMhDataReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by 韩金群
 * CreateTime 2019/1/24
 */
@Service
public class DataLogServiceImpl implements DataLogService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private NewLogMapper newLogMapper;
    @Autowired
    private MhDataLogMapper mhDataLogMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ActiveMQService activeMQService;

    @Override
    public void dealMhData(XiaoweiProtocolV1 protocolV1, String text) {
        try {
            //处理原data
            XiaoweiMhDataReq req = protocolV1.getXiaoweiMhDataReq();
            if (req != null) {
                initLog(req, protocolV1.getContent());
                //处理数据
                String deviceNo = req.getDeviceNo();
                XiaoweiMhDataReq xiaoweiMhDataReq = protocolV1.getXiaoweiMhDataReq();
                MhDataLogDO dataDO = new MhDataLogDO();
                BeanUtils.copyProperties(xiaoweiMhDataReq, dataDO);
                dataDO.setDeviceNo(deviceNo);
                Date myDate = new Date();
                dataDO.setCreateTime(myDate);
                dataDO.setCollectTime(myDate);
                dataDO.setAcquisitionUuid(UUIdUtil.next());
                //得到井盖数据
                dataDO.setCcid(xiaoweiMhDataReq.getCcid());
                dataDO.setFaultInfo(xiaoweiMhDataReq.getFaultInfo());
                dataDO.setWaterLine(xiaoweiMhDataReq.getWaterLine());
                dataDO.setWellOpenSts(xiaoweiMhDataReq.getWellCoverSts());
                dataDO.setFireGasCon(xiaoweiMhDataReq.getFireGasConcentration());
                dataDO.setTemperature(xiaoweiMhDataReq.getTemperature());
                dataDO.setCmd(protocolV1.getCmd());
                dataDO.setSwitchWaterLine(xiaoweiMhDataReq.getSwitchWaterLine());
                //处理故障信息
                String faultInfo = req.getFaultInfo();
                Class<?> clazz = Class.forName("com.xiaowei.mh.mapper.dataobject.MhDataLogDO");
                int index = 1;
                //初始化信息
                for (int i = 1; i <= 8; i++) {
                    Method method = clazz.getMethod("setFault" + i, Integer.class);
                    method.invoke(dataDO, 0);
                }
                //取每一位填入对应故障中
                Integer fault = Integer.valueOf(faultInfo);
                while (fault != 0 && index <= 8) {
                    int value = fault % 2;
                    Method method = clazz.getMethod("setFault" + index, Integer.class);
                    method.invoke(dataDO, value);
                    fault = fault / 2;
                    index++;
                }

                int i = mhDataLogMapper.insert(dataDO);
                redisUtil.set(RedisKeyEnum.REDIS_KEY_MH_LATEST.getKey() + req.getDeviceNo(), JSONObject.toJSONString(dataDO));
                //微信处理 判断是否推送
                if (CMDEnum.FAULT_ALARM.getCmd().equals(protocolV1.getCmd())) {
                    XwEquipmentMhDO mhDOWX = null;
                    Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + req.getDeviceNo());
                    if (o != null) {
                        mhDOWX = JSON.parseObject(o + "", new TypeReference<XwEquipmentMhDO>() {
                        });
                    }
                    if (mhDOWX != null) {
                        if (mhDOWX.getFaultPush() != null) {
                            if (mhDOWX.getFaultPush() == 1) {
                                activeMQService.sendMhAlarmMessage(protocolV1);
                                logger.warn(" - FAULT_ALARM:" + text);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(protocolV1.getXiaoweiMhDataReq().getDeviceNo() + "_" + e.getMessage(), e);
        }
    }

    @Override
    public void doMhTwo(ThreeDataSuccess req, NewLogVo newLogVo) {
        //处理第三方数据
        try {

            //日志表
            if (req != null) {
                //插入d_new_log表
                NewLogDO newLogDO = new NewLogDO();
                BeanUtils.copyProperties(newLogVo, newLogDO);
                newLogDO.setImei(req.getDeviceNo());
                newLogMapper.insert(newLogDO);
                //处理日志数据
                String deviceNo = req.getDeviceNo();
                MhDataLogDO dataDO = new MhDataLogDO();
                BeanUtils.copyProperties(req, dataDO);
                dataDO.setWaterLine(req.getWaterLine());
                dataDO.setDeviceNo(deviceNo);
                Date myDate = new Date();
                dataDO.setCreateTime(myDate);
                dataDO.setCollectTime(myDate);
                dataDO.setAcquisitionUuid(UUIdUtil.next());
                //得到井盖数据
                dataDO.setCcid(req.getCcid());
                dataDO.setFaultInfo(req.getFaultInfo());
                dataDO.setCellId(req.getCell_ID());
                dataDO.setLac(req.getLac());
                dataDO.setSignalRsrp(req.getSignalRSRP());
                dataDO.setSignalRssi(req.getSignalRSSI());
                dataDO.setSignalSnr(req.getSignalSnr());
                dataDO.setDataType(req.getDataType());
                dataDO.setGasH2s(req.getGasH2S());
                dataDO.setHumidity(req.getHumidity());
                //处理故障信息
                String faultInfo = req.getFaultInfo();
                Class<?> clazz = Class.forName("com.xiaowei.mh.mapper.dataobject.MhDataLogDO");
                int index = 1;
                //初始化信息
                for (int i = 1; i <= 8; i++) {
                    Method method = clazz.getMethod("setFault" + i, Integer.class);
                    method.invoke(dataDO, 0);
                }
                //取每一位填入对应故障中
                Integer fault = Integer.valueOf(faultInfo);
                while (fault != 0 && index <= 8) {
                    int value = fault % 2;
                    Method method = clazz.getMethod("setFault" + index, Integer.class);
                    method.invoke(dataDO, value);
                    fault = fault / 2;
                    index++;
                }
                //插入井盖日志
                mhDataLogMapper.insert(dataDO);
                redisUtil.set(RedisKeyEnum.REDIS_KEY_MH_LATEST.getKey() + req.getDeviceNo(), JSONObject.toJSONString(dataDO));
            }
        } catch (Exception e) {
            logger.error(req.getDeviceNo() + "_" + e.getMessage(), e);
        }
    }

    private void initLog(XiaoweiMhDataReq req, byte[] content) {
        String contentStr = BytesHexStrTranslate.bytesToHexFun3(content);
        StringBuilder builder = new StringBuilder();
        for (int i = 2; i < contentStr.length(); i++) {
            builder.append(contentStr.charAt(i));
            if (i != 0 && i % 2 == 1) {
                builder.append(" ");
            }
        }
        String c = builder.toString();
        LogDO logDO = new LogDO();
        logDO.setDataFlag("1");
        logDO.setDeviceNo(req.getDeviceNo());
        logDO.setRecData(c);
        logDO.setDeviceType(DeviceTypeEnum.MANHOLE.getDeviceType());
        logDO.setRecTime(DateUtils.dateToString(new Date()));
        logMapper.insert(logDO);
    }
}
