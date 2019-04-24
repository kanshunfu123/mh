package com.xiaowei.mh.service.schdule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiaowei.mh.common.util.DateUtils;
import com.xiaowei.mh.common.util.RedisKeyEnum;
import com.xiaowei.mh.common.util.RedisUtil;
import com.xiaowei.mh.mapper.dataobject.*;
import com.xiaowei.mh.mapper.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 韩金群
 * CreateTime 2019/1/21
 */
@Service
@Slf4j
public class OfflineLogService {
    private static final long DEVICE_TIME_OUT_EC = 60 * 60;
    private static final long DEVICE_TIME_OUT_MH = 7 * 60 * 60;
    private static final long DEVICE_TIME_OUT_RW = 6 * 60 * 60 + 30 * 60;
    private static final long DEVICE_TIME_OUT_CW = 60 * 60;
    @Autowired
    private EcDataLogMapper ecDataLogMapper;
    @Autowired
    private MhDataLogMapper mhDataLogMapper;
    @Autowired
    private RwDataLogMapper rwDataLogMapper;
    @Autowired
    private CwDataLogMapper cwDataLogMapper;
    @Autowired
    private OfflineLogMapper offlineLogMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Async
    public void offEc(String deviceNo) {
        String beginTime = DateUtils.addHour(new Date(),-2);
        String endTime = DateUtils.dateToString(new Date());
        List<Date> list = ecDataLogMapper.todayList(deviceNo, beginTime, endTime);
        if (list.size() > 1) {
            log.info("统计处理电梯离线。。");
            for (int i = 1; i < list.size(); i++) {
                Date before = list.get(i-1);
                Date after = list.get(i);
                long dateDiff = DateUtils.getDatePoor(after, before);
                System.out.println(dateDiff+"ec");
                if (dateDiff > DEVICE_TIME_OUT_EC) {
                    Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_EC_INFO.getKey() + deviceNo);
                    if (o != null) {
                        XwEquipmentEcDO ecDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentEcDO>() {
                        });
                        if(ecDO!=null)
                        {
                            OfflineLogDO offlineLogDO = new OfflineLogDO();
                            offlineLogDO.setCollectTime(after);
                            offlineLogDO.setDeviceNo(deviceNo);
                            offlineLogDO.setDeviceType("3");
                            offlineLogDO.setCreateTime(new Date());
                            offlineLogDO.setCreateBy("sys");
                            offlineLogDO.setProvinceId(ecDO.getProvinceId());
                            offlineLogDO.setProvinceName(ecDO.getProvince());
                            offlineLogDO.setCityId(ecDO.getCityId());
                            offlineLogDO.setCityName(ecDO.getCity());
                            offlineLogDO.setAreaId(ecDO.getAreaId());
                            offlineLogDO.setAreaName(ecDO.getArea());
                            offlineLogMapper.insert(offlineLogDO);
                        }
                    }

                }
            }
        }
    }

    @Async
    public void offMh(String deviceNo) {
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        List<Date> list = mhDataLogMapper.todayList(deviceNo, beginTime, endTime);
        if (list.size() > 1) {
            log.info("统计处理井盖离线。。");
            for (int i = 1; i < list.size(); i++) {
                Date before = list.get(i-1);
                Date after = list.get(i);
                long dateDiff = DateUtils.getDatePoor(after, before);
                System.out.println(dateDiff+"mh");
                if (dateDiff > DEVICE_TIME_OUT_MH) {
                    Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + deviceNo);
                    if (o != null) {
                        XwEquipmentMhDO mhDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentMhDO>() {
                        });
                        if(mhDO!=null)
                        {
                            OfflineLogDO offlineLogDO = new OfflineLogDO();
                            offlineLogDO.setCollectTime(after);
                            offlineLogDO.setDeviceNo(deviceNo);
                            offlineLogDO.setDeviceType("1");
                            offlineLogDO.setCreateTime(new Date());
                            offlineLogDO.setCreateBy("sys");
                            offlineLogDO.setProvinceId(mhDO.getProvinceId());
                            offlineLogDO.setProvinceName(mhDO.getProvince());
                            offlineLogDO.setCityId(mhDO.getCityId());
                            offlineLogDO.setCityName(mhDO.getCity());
                            offlineLogDO.setAreaId(mhDO.getAreaId());
                            offlineLogDO.setAreaName(mhDO.getArea());
                            offlineLogMapper.insert(offlineLogDO);
                        }
                    }
                }
            }
        }
    }

    @Async
    public void offRw(String deviceNo) {
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        List<Date> list = rwDataLogMapper.todayList(deviceNo, beginTime, endTime);
        if (list.size() > 1) {
            log.info("统计处理地表水离线。。");
            for (int i = 1; i < list.size(); i++) {
                Date before = list.get(i-1);
                Date after = list.get(i);
                long dateDiff = DateUtils.getDatePoor(after, before);
                System.out.println(dateDiff+"rw");
                if (dateDiff > DEVICE_TIME_OUT_RW) {
                    Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_RW_INFO.getKey() + deviceNo);
                    if (o != null) {
                        XwEquipmentRwDO rwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentRwDO>() {
                        });
                        if(rwDO!=null)
                        {
                            OfflineLogDO offlineLogDO = new OfflineLogDO();
                            offlineLogDO.setCollectTime(after);
                            offlineLogDO.setDeviceNo(deviceNo);
                            offlineLogDO.setDeviceType("0");
                            offlineLogDO.setCreateTime(new Date());
                            offlineLogDO.setCreateBy("sys");
                            offlineLogDO.setProvinceId(rwDO.getProvinceId());
                            offlineLogDO.setProvinceName(rwDO.getProvince());
                            offlineLogDO.setCityId(rwDO.getCityId());
                            offlineLogDO.setCityName(rwDO.getCity());
                            offlineLogDO.setAreaId(rwDO.getAreaId());
                            offlineLogDO.setAreaName(rwDO.getArea());
                            offlineLogMapper.insert(offlineLogDO);
                        }
                    }
                }
            }
        }
    }

    @Async
    public void offCw(String deviceNo) {
        String beginTime = DateUtils.addHour(new Date(),-2);
        String endTime = DateUtils.dateToString(new Date());
        List<Date> list = cwDataLogMapper.todayList(deviceNo, beginTime, endTime);
        if (list.size() > 1) {
            log.info("统计处理饮用水离线。。");
            for (int i = 1; i < list.size(); i++) {
                Date before = list.get(i-1);
                Date after = list.get(i);
                long dateDiff = DateUtils.getDatePoor(after, before);
                System.out.println(dateDiff+"cw");
                if (dateDiff > DEVICE_TIME_OUT_CW) {
                    Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_CW_INFO.getKey() + deviceNo);
                    if (o != null) {
                        XwEquipmentCwDO cwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentCwDO>() {
                        });
                        if(cwDO!=null)
                        {
                            OfflineLogDO offlineLogDO = new OfflineLogDO();
                            offlineLogDO.setCollectTime(after);
                            offlineLogDO.setDeviceNo(deviceNo);
                            offlineLogDO.setDeviceType("2");
                            offlineLogDO.setCreateTime(new Date());
                            offlineLogDO.setCreateBy("sys");
                            offlineLogDO.setProvinceId(cwDO.getProvinceId());
                            offlineLogDO.setProvinceName(cwDO.getProvince());
                            offlineLogDO.setCityId(cwDO.getCityId());
                            offlineLogDO.setCityName(cwDO.getCity());
                            offlineLogDO.setAreaId(cwDO.getAreaId());
                            offlineLogDO.setAreaName(cwDO.getArea());
                            offlineLogMapper.insert(offlineLogDO);
                        }
                    }
                }
            }
        }
    }
}
