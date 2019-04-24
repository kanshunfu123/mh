package com.xiaowei.mh.service.schdule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xiaowei.feign.client.home.HomeFeign;
import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.util.DateUtils;
import com.xiaowei.mh.common.util.RedisKeyEnum;
import com.xiaowei.mh.common.util.RedisUtil;
import com.xiaowei.mh.mapper.dataobject.*;
import com.xiaowei.mh.mapper.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 韩金群
 * CreateTime 2019/1/19
 */
@Service
@Slf4j
public class SchduleService {
    @Autowired
    private EcDataLogMapper ecDataLogMapper;
    @Autowired
    private MhDataLogMapper mhDataLogMapper;
    @Autowired
    private RwDataLogMapper rwDataLogMapper;
    @Autowired
    private CwDataLogMapper cwDataLogMapper;
    @Autowired
    private FaultLogService faultLogService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private HomeFeign homeFeign;
    @Autowired
    private OfflineLogService offlineLogService;
    @Autowired
    private PowerLogService powerLogService;

    /**
     * 电梯故障定时器
     */
    @Scheduled(cron = "2 0/30 * * * ?  ")
    public void faultTodayEc() {
        String beginTime = DateUtils.addMinute(new Date(),-30);
        String endTime = DateUtils.dateToString(new Date());
        List<EcDataLogDO> ecList = ecDataLogMapper.faultTodayEc(beginTime, endTime);
        for (int i = 0; i < ecList.size(); i++) {
            if (!"0869858030023593".equals(ecList.get(i).getDeviceNo())) {
                //token获取设备信息
                FaultLogDO faultLogDO = new FaultLogDO();
                Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_EC_INFO.getKey() + ecList.get(i).getDeviceNo());
                if (o != null) {
                    XwEquipmentEcDO ecDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentEcDO>() {
                    });
                    if (ecDO != null) {
                        faultLogDO.setAddress(ecDO.getVillageName() + ecDO.getDeviceCode());
                        faultLogDO.setProvinceId(ecDO.getProvinceId());
                        faultLogDO.setProvinceName(ecDO.getProvince());
                        faultLogDO.setCityId(ecDO.getCityId());
                        faultLogDO.setCityName(ecDO.getCity());
                        faultLogDO.setAreaId(ecDO.getAreaId());
                        faultLogDO.setAreaName(ecDO.getArea());
                    }
                } else {
                    faultLogDO.setAddress("未知");
                }
                faultLogDO.setDeviceType("3");
                faultLogDO.setCollectTime(ecList.get(i).getCollectTime());
                faultLogDO.setDeviceNo(ecList.get(i).getDeviceNo());
                faultLogDO.setFaultInfo(ecList.get(i).getFaultInfo());
                faultLogDO.setFault1(ecList.get(i).getFault1());
                faultLogDO.setFault2(ecList.get(i).getFault2());
                faultLogDO.setFault3(ecList.get(i).getFault3());
                faultLogDO.setFault4(ecList.get(i).getFault4());
                faultLogDO.setFault5(ecList.get(i).getFault5());
                faultLogDO.setFault6(ecList.get(i).getFault6());
                faultLogDO.setFault7(ecList.get(i).getFault7());
                faultLogDO.setFault8(ecList.get(i).getFault8());
                faultLogDO.setCreateTime(new Date());
                faultLogService.insertFaultLog(faultLogDO);
            }
        }
    }

    /**
     * 饮用水故障定时器
     */
    @Scheduled(cron = "7 0/30 * * * ?  ")
    public void faultTodayCw() {
        String beginTime = DateUtils.addMinute(new Date(),-30);
        String endTime = DateUtils.dateToString(new Date());
        List<CwDataLogDO> cwList = cwDataLogMapper.faultTodayCw(beginTime, endTime);
        for (int i = 0; i < cwList.size(); i++) {
            //token获取设备信息
            FaultLogDO faultLogDO = new FaultLogDO();
            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_CW_INFO.getKey() + cwList.get(i).getDeviceNo());
            if (o != null) {
                XwEquipmentCwDO cwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentCwDO>() {
                });
                if (cwDO != null) {
                    faultLogDO.setAddress(cwDO.getVillageName() + cwDO.getDeviceCode());
                    faultLogDO.setProvinceId(cwDO.getProvinceId());
                    faultLogDO.setProvinceName(cwDO.getProvince());
                    faultLogDO.setCityId(cwDO.getCityId());
                    faultLogDO.setCityName(cwDO.getCity());
                    faultLogDO.setAreaId(cwDO.getAreaId());
                    faultLogDO.setAreaName(cwDO.getArea());
                }
            } else {
                faultLogDO.setAddress("未知");
            }
            faultLogDO.setDeviceType("2");
            faultLogDO.setCollectTime(cwList.get(i).getCollectTime());
            faultLogDO.setDeviceNo(cwList.get(i).getDeviceNo());
            faultLogDO.setFaultInfo(cwList.get(i).getFaultInfo());
            faultLogDO.setFault1(cwList.get(i).getFault1());
            faultLogDO.setFault2(cwList.get(i).getFault2());
            faultLogDO.setFault3(cwList.get(i).getFault3());
            faultLogDO.setFault4(cwList.get(i).getFault4());
            faultLogDO.setFault5(cwList.get(i).getFault5());
            faultLogDO.setFault6(cwList.get(i).getFault6());
            faultLogDO.setFault7(cwList.get(i).getFault7());
            faultLogDO.setFault8(cwList.get(i).getFault8());
            faultLogDO.setCreateTime(new Date());
            faultLogService.insertFaultLog(faultLogDO);
        }
    }

    /**
     * 井盖故障定时器
     */
    @Scheduled(cron = "12 0/30 * * * ?  ")
    public void faultTodayMh() {
        String beginTime = DateUtils.addMinute(new Date(),-30);
        String endTime = DateUtils.dateToString(new Date());
        List<MhDataLogDO> mhList = mhDataLogMapper.faultTodayMh(beginTime, endTime);
        for (int i = 0; i < mhList.size(); i++) {
            //token获取设备信息
            FaultLogDO faultLogDO = new FaultLogDO();
            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + mhList.get(i).getDeviceNo());
            if (o != null) {
                XwEquipmentMhDO mhDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentMhDO>() {
                });
                if (mhDO != null) {
                    faultLogDO.setAddress(mhDO.getAddress() + mhDO.getDeviceCode());
                    faultLogDO.setProvinceId(mhDO.getProvinceId());
                    faultLogDO.setProvinceName(mhDO.getProvince());
                    faultLogDO.setCityId(mhDO.getCityId());
                    faultLogDO.setCityName(mhDO.getCity());
                    faultLogDO.setAreaId(mhDO.getAreaId());
                    faultLogDO.setAreaName(mhDO.getArea());
                }
            } else {
                faultLogDO.setAddress("未知");
            }
            faultLogDO.setDeviceType("1");
            faultLogDO.setCollectTime(mhList.get(i).getCollectTime());
            faultLogDO.setDeviceNo(mhList.get(i).getDeviceNo());
            faultLogDO.setFaultInfo(mhList.get(i).getFaultInfo());
            faultLogDO.setFault1(mhList.get(i).getFault1());
            faultLogDO.setFault2(mhList.get(i).getFault2());
            faultLogDO.setFault3(mhList.get(i).getFault3());
            faultLogDO.setFault4(mhList.get(i).getFault4());
            faultLogDO.setFault5(mhList.get(i).getFault5());
            faultLogDO.setFault6(mhList.get(i).getFault6());
            faultLogDO.setFault7(mhList.get(i).getFault7());
            faultLogDO.setFault8(mhList.get(i).getFault8());
            if(mhList.get(i).getWaterLine()!=null)
            {
                if(mhList.get(i).getWaterLine()==1)
                {
                    faultLogDO.setFault8(1);
                }
                if(mhList.get(i).getWaterLine()==2)
                {
                    faultLogDO.setFault8(2);
                }
                if(mhList.get(i).getWaterLine()==3)
                {
                    faultLogDO.setFault8(3);
                }
            }
            faultLogDO.setCreateTime(new Date());
            faultLogService.insertFaultLog(faultLogDO);
        }
    }

    /**
     * 河道水故障定时器
     */
    @Scheduled(cron = "17 0/30 * * * ?  ")
    public void faultTodayRw() {
        String beginTime = DateUtils.addMinute(new Date(),-30);
        String endTime = DateUtils.dateToString(new Date());
        List<RwDataLogDO> rwList = rwDataLogMapper.faultTodayRw(beginTime, endTime);
        for (int i = 0; i < rwList.size(); i++) {
            //token获取设备信息
            FaultLogDO faultLogDO = new FaultLogDO();
            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_RW_INFO.getKey() + rwList.get(i).getDeviceNo());
            if (o != null) {
                XwEquipmentRwDO rwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentRwDO>() {
                });
                if (rwDO != null) {
                    faultLogDO.setAddress(rwDO.getDeviceName() + rwDO.getDeviceCode());
                    faultLogDO.setProvinceId(rwDO.getProvinceId());
                    faultLogDO.setProvinceName(rwDO.getProvince());
                    faultLogDO.setCityId(rwDO.getCityId());
                    faultLogDO.setCityName(rwDO.getCity());
                    faultLogDO.setAreaId(rwDO.getAreaId());
                    faultLogDO.setAreaName(rwDO.getArea());
                }
            } else {
                faultLogDO.setAddress("未知");
            }
            faultLogDO.setDeviceType("0");
            faultLogDO.setCollectTime(rwList.get(i).getCollectTime());
            faultLogDO.setDeviceNo(rwList.get(i).getDeviceNo());
            faultLogDO.setFaultInfo(rwList.get(i).getFaultInfo());
            faultLogDO.setFault1(rwList.get(i).getFault1());
            faultLogDO.setFault2(rwList.get(i).getFault2());
            faultLogDO.setFault3(rwList.get(i).getFault3());
            faultLogDO.setFault4(rwList.get(i).getFault4());
            faultLogDO.setFault5(rwList.get(i).getFault5());
            faultLogDO.setFault6(rwList.get(i).getFault6());
            faultLogDO.setFault7(rwList.get(i).getFault7());
            faultLogDO.setFault8(rwList.get(i).getFault8());
            faultLogDO.setCreateTime(new Date());
            faultLogService.insertFaultLog(faultLogDO);
        }
    }

    /**
     * 电梯离线统计
     */
    @Scheduled(cron = "01 0/30 * * * ?  ")
    public void offlineEc() {
        JSONResult jsonResult = homeFeign.deviceNoListEc();
        List<String> listEc = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<String>>() {
        });
        for (int i = 0; i < listEc.size(); i++) {
            if (!"0869858030023593".equals(listEc.get(i))) {
                offlineLogService.offEc(listEc.get(i));
            }
        }
    }

    /**
     * 井盖离线统计
     */
    @Scheduled(cron = "01 0/30 * * * ?  ")
    public void offlineMh() {
        JSONResult jsonResult = homeFeign.deviceNoListMh();
        List<String> listMh = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<String>>() {
        });
        for (int i = 0; i < listMh.size(); i++) {
            System.out.println("newmh");
            offlineLogService.offMh(listMh.get(i));
        }
    }

    /**
     * 河道水离线统计
     */
    @Scheduled(cron = "01 0/30 * * * ?  ")
    public void offlineRw() {
        JSONResult jsonResult = homeFeign.deviceNoListRw();
        List<String> listRw = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<String>>() {
        });
        for (int i = 0; i < listRw.size(); i++) {
            System.out.println("rw");
            offlineLogService.offRw(listRw.get(i));
        }
    }

    /**
     * 饮用水离线统计
     */
    @Scheduled(cron = "01 0/30 * * * ?  ")
    public void offlineCw() {
        JSONResult jsonResult = homeFeign.deviceNoListCw();
        List<String> listCw = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<String>>() {
        });
        for (int i = 0; i < listCw.size(); i++) {
            System.out.println("cw");
            offlineLogService.offCw(listCw.get(i));
        }
    }

  /*  *//**
     * 电梯电量统计
     *//*
    @Scheduled(cron = "30 50 23 1/1 * ?  ")
    public void powerEc() {
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        JSONResult jsonResult = homeFeign.deviceNoListEc();
        List<String> listEc = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<String>>() {
        });
        for (int i = 0; i < listEc.size(); i++) {
            String deviceNo = listEc.get(i);
            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_EC_INFO.getKey() + deviceNo);
            List<EcDataLogDO> list = ecDataLogMapper.ecPowerList(deviceNo, beginTime, endTime);
            for (int j = 0; j < list.size(); j++) {
                if (o != null) {
                    XwEquipmentEcDO ecDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentEcDO>() {
                    });
                    if (ecDO != null) {
                        PowerLogDO powerLogDO = new PowerLogDO();
                        powerLogDO.setDeviceNo(deviceNo);
                        powerLogDO.setDeviceType("3");
                        powerLogDO.setPowerCollect(list.get(j).getAtPowerPct());
                        powerLogDO.setPowerLaunch(list.get(j).getCtPowerPct());
                        powerLogDO.setProvinceId(ecDO.getProvinceId());
                        powerLogDO.setProvinceName(ecDO.getProvince());
                        powerLogDO.setCityId(ecDO.getCityId());
                        powerLogDO.setCityName(ecDO.getCity());
                        powerLogDO.setAreaId(ecDO.getAreaId());
                        powerLogDO.setAreaName(ecDO.getArea());
                        powerLogDO.setCollectTime(list.get(j).getCollectTime());
                        powerLogDO.setCreateTime(new Date());
                        powerLogService.insertPowerLog(powerLogDO);
                    }
                }
            }
        }
    }

    *//**
     * 井盖电量统计
     *//*
    @Scheduled(cron = "30 51 23 1/1 * ?  ")
    public void powerMh() {
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        JSONResult jsonResult = homeFeign.deviceNoListMh();
        List<String> listMh = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<String>>() {
        });
        for (int i = 0; i < listMh.size(); i++) {
            String deviceNo = listMh.get(i);
            List<MhDataLogDO> list = mhDataLogMapper.mhPowerList(deviceNo, beginTime, endTime);
            for (int j = 0; j < list.size(); j++) {
                Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + deviceNo);
                if (o != null) {
                    XwEquipmentMhDO mhDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentMhDO>() {
                    });
                    if (mhDO != null) {
                        PowerLogDO powerLogDO = new PowerLogDO();
                        powerLogDO.setDeviceNo(deviceNo);
                        powerLogDO.setDeviceType("1");
                        powerLogDO.setPowerCollect(list.get(j).getPowerMh());
                        powerLogDO.setPowerLaunch(list.get(j).getPowerMh());
                        powerLogDO.setCollectTime(list.get(j).getCollectTime());
                        powerLogDO.setProvinceId(mhDO.getProvinceId());
                        powerLogDO.setProvinceName(mhDO.getProvince());
                        powerLogDO.setCityId(mhDO.getCityId());
                        powerLogDO.setCityName(mhDO.getCity());
                        powerLogDO.setAreaId(mhDO.getAreaId());
                        powerLogDO.setAreaName(mhDO.getArea());
                        powerLogDO.setCreateTime(new Date());
                        powerLogService.insertPowerLog(powerLogDO);
                    }
                }
            }
        }
    }

    *//**
     * 地表水电量统计
     *//*
    @Scheduled(cron = "30 52 23 1/1 * ?  ")
    public void powerRw() {
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        JSONResult jsonResult = homeFeign.deviceNoListRw();
        List<String> listRw = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<String>>() {
        });
        for (int i = 0; i < listRw.size(); i++) {
            String deviceNo = listRw.get(i);
            List<RwDataLogDO> list = rwDataLogMapper.rwPowerList(deviceNo, beginTime, endTime);
            for (int j = 0; j < list.size(); j++) {
                Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_RW_INFO.getKey() + deviceNo);
                if (o != null) {
                    XwEquipmentRwDO rwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentRwDO>() {
                    });
                    if (rwDO != null) {
                        PowerLogDO powerLogDO = new PowerLogDO();
                        powerLogDO.setDeviceNo(deviceNo);
                        powerLogDO.setDeviceType("0");
                        powerLogDO.setPowerCollect(Integer.valueOf(list.get(j).getPowerPct()));
                        powerLogDO.setPowerLaunch(Integer.valueOf(list.get(j).getPowerPct()));
                        powerLogDO.setCollectTime(list.get(j).getCollectTime());
                        powerLogDO.setProvinceId(rwDO.getProvinceId());
                        powerLogDO.setProvinceName(rwDO.getProvince());
                        powerLogDO.setCityId(rwDO.getCityId());
                        powerLogDO.setCityName(rwDO.getCity());
                        powerLogDO.setAreaId(rwDO.getAreaId());
                        powerLogDO.setAreaName(rwDO.getArea());
                        powerLogDO.setCreateTime(new Date());
                        powerLogService.insertPowerLog(powerLogDO);
                    }
                }
            }
        }
    }*/

    /**
     * 饮用水标准参数值统计
     */
    @Scheduled(cron = "5 28 1 1/1 * ?  ")
    public void standardCw() {
        System.out.println(11);
        String str1 = DateUtils.getYesterday();
        String str2 = DateUtils.getTomorrow();
        Date date1 = DateUtils.getStrToDate(str1);
        Date date2 = DateUtils.getStrToDate(str2);
        String beginTime = DateUtils.dateToString(DateUtils.dayAdd(date1, -1));
        String endTime = DateUtils.dateToString(DateUtils.dayAdd(date2, -1));
        JSONResult jsonResult = homeFeign.deviceNoListCw();
        List<String> listCw = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<String>>() {
        });
        for (int i = 0; i < listCw.size(); i++) {
            String deviceNo = listCw.get(i);
            List<CwDataLogDO> list = cwDataLogMapper.standardCw(deviceNo, beginTime, endTime);
            for (int j = 0; j < list.size(); j++) {
                Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_CW_INFO.getKey() + deviceNo);
                if (o != null) {
                    XwEquipmentCwDO cwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentCwDO>() {
                    });
                    if (cwDO != null) {
                        CwStandardLogDO cwStandardLogDO = new CwStandardLogDO();
                        cwStandardLogDO.setProvinceId(cwDO.getProvinceId());
                        cwStandardLogDO.setProvinceName(cwDO.getProvince());
                        cwStandardLogDO.setCityId(cwDO.getCityId());
                        cwStandardLogDO.setCityName(cwDO.getCity());
                        cwStandardLogDO.setAreaId(cwDO.getAreaId());
                        cwStandardLogDO.setAreaName(cwDO.getArea());
                        cwStandardLogDO.setDeviceNo(deviceNo);
                        cwStandardLogDO.setPh(list.get(j).getPh());
                        cwStandardLogDO.setChlorine(list.get(j).getChlorine());
                        cwStandardLogDO.setOxygen(list.get(j).getOxygen());
                        cwStandardLogDO.setTurbidity(list.get(j).getTurbidity());
                        cwStandardLogDO.setCreateTime(new Date());
                        cwStandardLogDO.setCollectTime(list.get(j).getCollectTime());
                        powerLogService.insertStandardCwLog(cwStandardLogDO);
                    }
                }
            }
        }
    }
    /**
     * 地表水标准参数值统计
     */
    @Scheduled(cron = "5 29 1 1/1 * ?  ")
    public void standardRw() {
        System.out.println(11);
        String str1 = DateUtils.getYesterday();
        String str2 = DateUtils.getTomorrow();
        Date date1 = DateUtils.getStrToDate(str1);
        Date date2 = DateUtils.getStrToDate(str2);
        String beginTime = DateUtils.dateToString(DateUtils.dayAdd(date1, -1));
        String endTime = DateUtils.dateToString(DateUtils.dayAdd(date2, -1));
        JSONResult jsonResult = homeFeign.deviceNoListRw();
        List<String> listRw = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<String>>() {
        });
        for (int i = 0; i < listRw.size(); i++) {
            String deviceNo = listRw.get(i);
            List<RwDataLogDO> list = rwDataLogMapper.rwPowerList(deviceNo, beginTime, endTime);
            for (int j = 0; j < list.size(); j++) {
                Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_RW_INFO.getKey() + deviceNo);
                if (o != null) {
                    XwEquipmentRwDO rwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentRwDO>() {
                    });
                    if (rwDO != null) {
                        RwStandardLogDO rwStandardLogDO = new RwStandardLogDO();
                        rwStandardLogDO.setProvinceId(rwDO.getProvinceId());
                        rwStandardLogDO.setProvinceName(rwDO.getProvince());
                        rwStandardLogDO.setCityId(rwDO.getCityId());
                        rwStandardLogDO.setCityName(rwDO.getCity());
                        rwStandardLogDO.setAreaId(rwDO.getAreaId());
                        rwStandardLogDO.setAreaName(rwDO.getArea());
                        rwStandardLogDO.setDeviceNo(deviceNo);
                        rwStandardLogDO.setPh(list.get(j).getPh());
                        rwStandardLogDO.setConductivity(list.get(j).getConductivity());
                        rwStandardLogDO.setOxygen(list.get(j).getRdo());
                        rwStandardLogDO.setTurbidity(list.get(j).getTss());
                        rwStandardLogDO.setCreateTime(new Date());
                        rwStandardLogDO.setCollectTime(list.get(j).getCollectTime());
                        powerLogService.insertStandardRwLog(rwStandardLogDO);
                    }
                }
            }
        }
    }

}
