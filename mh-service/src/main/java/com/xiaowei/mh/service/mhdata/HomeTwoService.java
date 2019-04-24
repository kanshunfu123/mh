package com.xiaowei.mh.service.mhdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.xiaowei.feign.client.home.HomeFeign;
import com.xiaowei.feign.client.home.UserDeviceFeign;
import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.fegin.AccessDeviceReq;
import com.xiaowei.mh.common.req.fegin.DeviceUserRoleReq;
import com.xiaowei.mh.common.req.fegin.HomeOneReq;
import com.xiaowei.mh.common.res.home.HomeTotalRes;
import com.xiaowei.mh.common.res.home.MonitorCountRes;
import com.xiaowei.mh.common.res.home.TodayCountRes;
import com.xiaowei.mh.common.res.home.TotalSizeRes;
import com.xiaowei.mh.common.res.power.*;
import com.xiaowei.mh.common.res.redis.RedisUser;
import com.xiaowei.mh.common.util.DateUtils;
import com.xiaowei.mh.mapper.dataobject.FaultLogDO;
import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import com.xiaowei.mh.mapper.home.DeviceRoleRea;
import com.xiaowei.mh.mapper.mapper.FaultLogMapper;
import com.xiaowei.mh.mapper.mapper.OfflineLogMapper;
import com.xiaowei.mh.mapper.mapper.PowerCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

/**
 * created by 韩金群 2019/2/15
 */
@Service
public class HomeTwoService {
    @Autowired
    private UserDeviceFeign userDeviceFeign;
    @Autowired
    private HomeFeign homeFeign;
    @Autowired
    private FaultLogMapper faultLogMapper;
    @Autowired
    private OfflineLogMapper offlineLogMapper;
    @Autowired
    private PowerCountMapper powerCountMapper;
    private static final int TOTAL_NUMBER_EC = 1440;
    private static final int TOTAL_NUMBER_MH = 10;
    private static final int TOTAL_NUMBER_RW = 8;
    private static final int TOTAL_NUMBER_CW = 48;

    /**
     * 电梯场景统计
     */
    public JSONResult todayCountEc(RedisUser redisUser) {
        TodayCountRes result = new TodayCountRes();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        Date date = new Date();
        Long ecTime = 0L;
        Integer count = 0;
        Integer countEvent = 0;
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        String strTime = DateUtils.dateToString2(new Date());
        if (redisUser.getGroupId() == 1) {
            HomeOneReq homeOneReq = new HomeOneReq();
            homeOneReq.setType("ec");
            //电梯总台数,运行总时长
            JSONResult jsonResult = homeFeign.homeTotalOne(homeOneReq);
            HomeTotalRes homeTotalRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<HomeTotalRes>() {
            });
            if (homeTotalRes != null) {
                count += homeTotalRes.getCount();
                ecTime += homeTotalRes.getRunTime() / 1000;
            }
            //事件数
            List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(ec, "3", beginTime, endTime);
            Set<String> stringSet = Sets.newHashSet();
            Multimap<String, String> multimap = ArrayListMultimap.create();
            for (int i = 0; i < faultLogDOS.size(); i++) {
                multimap.put(DateUtils.dateToString2(faultLogDOS.get(i).getCollectTime()), faultLogDOS.get(i).getDeviceNo());
            }
            List<String> initList = (List<String>) multimap.get(strTime);
            for (int i = 0; i < initList.size(); i++) {
                stringSet.add(initList.get(i));
            }
            countEvent += stringSet.size();

        } else {
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getEc().size() > 0) {
                    ec = deviceRoleRea.getEc();
                    //台数
                    count += ec.size();
                    //事件数
                    List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(ec, "3", beginTime, endTime);
                    Set<String> stringSet = Sets.newHashSet();
                    Multimap<String, String> multimap = ArrayListMultimap.create();
                    for (int i = 0; i < faultLogDOS.size(); i++) {
                        multimap.put(DateUtils.dateToString2(faultLogDOS.get(i).getCollectTime()), faultLogDOS.get(i).getDeviceNo());
                    }
                    List<String> initList = (List<String>) multimap.get(strTime);
                    for (int i = 0; i < initList.size(); i++) {
                        stringSet.add(initList.get(i));
                    }
                    countEvent += stringSet.size();
                    //时间
                    for (int i = 0; i < ec.size(); i++) {
                        ecTime += (date.getTime() - DateUtils.getStrToDate(ec.get(i).getSetupTime()).getTime()) / 1000;
                    }
                }
            }
        }
        Long total = ecTime;
        result.setRunTime(DateUtils.secondToTime(total));
        //台数
        result.setTotal(count + "台");
        //事件数
        result.setEventNum(countEvent + "件");
        result.setWaitNum(countEvent + "件");
        return JSONResult.ok(result);
    }

    /**
     * 井盖场景统计
     */
    public JSONResult todayCountMh(RedisUser redisUser) {
        TodayCountRes result = new TodayCountRes();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        Date date = new Date();
        Long mhTime = 0L;
        Integer count = 0;
        Integer countEvent = 0;
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        String strTime = DateUtils.dateToString2(new Date());
        if (redisUser.getGroupId() == 1) {
            HomeOneReq homeOneReq = new HomeOneReq();
            homeOneReq.setType("mh");
            //电梯总台数,运行总时长
            JSONResult jsonResult = homeFeign.homeTotalOne(homeOneReq);
            HomeTotalRes homeTotalRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<HomeTotalRes>() {
            });
            if (homeTotalRes != null) {
                count += homeTotalRes.getCount();
                mhTime += homeTotalRes.getRunTime() / 1000;
            }
            //事件数
            List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(mh, "1", beginTime, endTime);
            Set<String> stringSet = Sets.newHashSet();
            Multimap<String, String> multimap = ArrayListMultimap.create();
            for (int i = 0; i < faultLogDOS.size(); i++) {
                multimap.put(DateUtils.dateToString2(faultLogDOS.get(i).getCollectTime()), faultLogDOS.get(i).getDeviceNo());
            }
            List<String> initList = (List<String>) multimap.get(strTime);
            for (int i = 0; i < initList.size(); i++) {
                stringSet.add(initList.get(i));
            }
            countEvent += stringSet.size();

        } else {
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getMh().size() > 0) {
                    mh = deviceRoleRea.getMh();
                    //台数
                    count += mh.size();
                    //事件数
                    List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(mh, "1", beginTime, endTime);
                    Set<String> stringSet = Sets.newHashSet();
                    Multimap<String, String> multimap = ArrayListMultimap.create();
                    for (int i = 0; i < faultLogDOS.size(); i++) {
                        multimap.put(DateUtils.dateToString2(faultLogDOS.get(i).getCollectTime()), faultLogDOS.get(i).getDeviceNo());
                    }
                    List<String> initList = (List<String>) multimap.get(strTime);
                    for (int i = 0; i < initList.size(); i++) {
                        stringSet.add(initList.get(i));
                    }
                    countEvent += stringSet.size();
                    //时间
                    for (int i = 0; i < mh.size(); i++) {
                        mhTime += (date.getTime() - DateUtils.getStrToDate(mh.get(i).getSetupTime()).getTime()) / 1000;
                    }
                }
            }
        }
        Long total = mhTime;
        result.setRunTime(DateUtils.secondToTime(total));
        //台数
        result.setTotal(count + "台");
        //事件数
        result.setEventNum(countEvent + "件");
        result.setWaitNum(countEvent + "件");
        return JSONResult.ok(result);
    }

    /**
     * 地表水场景统计
     */
    public JSONResult todayCountRw(RedisUser redisUser) {
        TodayCountRes result = new TodayCountRes();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        Date date = new Date();
        Long rwTime = 0L;
        Integer count = 0;
        Integer countEvent = 0;
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        String strTime = DateUtils.dateToString2(new Date());
        if (redisUser.getGroupId() == 1) {
            HomeOneReq homeOneReq = new HomeOneReq();
            homeOneReq.setType("rw");
            //地表水总台数,运行总时长
            JSONResult jsonResult = homeFeign.homeTotalOne(homeOneReq);
            HomeTotalRes homeTotalRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<HomeTotalRes>() {
            });
            if (homeTotalRes != null) {
                count += homeTotalRes.getCount();
                rwTime += homeTotalRes.getRunTime() / 1000;
            }
            //事件数
            List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(rw, "0", beginTime, endTime);
            Set<String> stringSet = Sets.newHashSet();
            Multimap<String, String> multimap = ArrayListMultimap.create();
            for (int i = 0; i < faultLogDOS.size(); i++) {
                multimap.put(DateUtils.dateToString2(faultLogDOS.get(i).getCollectTime()), faultLogDOS.get(i).getDeviceNo());
            }
            List<String> initList = (List<String>) multimap.get(strTime);
            for (int i = 0; i < initList.size(); i++) {
                stringSet.add(initList.get(i));
            }
            countEvent += stringSet.size();

        } else {
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getRw().size() > 0) {
                    rw = deviceRoleRea.getRw();
                    //台数
                    count += rw.size();
                    //事件数
                    List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(rw, "0", beginTime, endTime);
                    Set<String> stringSet = Sets.newHashSet();
                    Multimap<String, String> multimap = ArrayListMultimap.create();
                    for (int i = 0; i < faultLogDOS.size(); i++) {
                        multimap.put(DateUtils.dateToString2(faultLogDOS.get(i).getCollectTime()), faultLogDOS.get(i).getDeviceNo());
                    }
                    List<String> initList = (List<String>) multimap.get(strTime);
                    for (int i = 0; i < initList.size(); i++) {
                        stringSet.add(initList.get(i));
                    }
                    countEvent += stringSet.size();
                    //时间
                    for (int i = 0; i < rw.size(); i++) {
                        rwTime += (date.getTime() - DateUtils.getStrToDate(rw.get(i).getSetupTime()).getTime()) / 1000;
                    }
                }
            }
        }
        Long total = rwTime;
        result.setRunTime(DateUtils.secondToTime(total));
        //台数
        result.setTotal(count + "台");
        //事件数
        result.setEventNum(countEvent + "件");
        result.setWaitNum(countEvent + "件");
        return JSONResult.ok(result);
    }

    /**
     * 饮用水场景统计
     */
    public JSONResult todayCountCw(RedisUser redisUser) {
        TodayCountRes result = new TodayCountRes();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        Date date = new Date();
        Long cwTime = 0L;
        Integer count = 0;
        Integer countEvent = 0;
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        String strTime = DateUtils.dateToString2(new Date());
        if (redisUser.getGroupId() == 1) {
            HomeOneReq homeOneReq = new HomeOneReq();
            homeOneReq.setType("cw");
            //电梯总台数,运行总时长
            JSONResult jsonResult = homeFeign.homeTotalOne(homeOneReq);
            HomeTotalRes homeTotalRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<HomeTotalRes>() {
            });
            if (homeTotalRes != null) {
                count += homeTotalRes.getCount();
                cwTime += homeTotalRes.getRunTime() / 1000;
            }
            //事件数
            List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(cw, "2", beginTime, endTime);
            Set<String> stringSet = Sets.newHashSet();
            Multimap<String, String> multimap = ArrayListMultimap.create();
            for (int i = 0; i < faultLogDOS.size(); i++) {
                multimap.put(DateUtils.dateToString2(faultLogDOS.get(i).getCollectTime()), faultLogDOS.get(i).getDeviceNo());
            }
            List<String> initList = (List<String>) multimap.get(strTime);
            for (int i = 0; i < initList.size(); i++) {
                stringSet.add(initList.get(i));
            }
            countEvent += stringSet.size();

        } else {
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getCw().size() > 0) {
                    cw = deviceRoleRea.getCw();
                    //台数
                    count += cw.size();
                    //事件数
                    List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(cw, "2", beginTime, endTime);
                    Set<String> stringSet = Sets.newHashSet();
                    Multimap<String, String> multimap = ArrayListMultimap.create();
                    for (int i = 0; i < faultLogDOS.size(); i++) {
                        multimap.put(DateUtils.dateToString2(faultLogDOS.get(i).getCollectTime()), faultLogDOS.get(i).getDeviceNo());
                    }
                    List<String> initList = (List<String>) multimap.get(strTime);
                    for (int i = 0; i < initList.size(); i++) {
                        stringSet.add(initList.get(i));
                    }
                    countEvent += stringSet.size();
                    //时间
                    for (int i = 0; i < cw.size(); i++) {
                        cwTime += (date.getTime() - DateUtils.getStrToDate(cw.get(i).getSetupTime()).getTime()) / 1000;
                    }
                }
            }
        }
        Long total = cwTime;
        result.setRunTime(DateUtils.secondToTime(total));
        //台数
        result.setTotal(count + "台");
        //事件数
        result.setEventNum(countEvent + "件");
        result.setWaitNum(countEvent + "件");
        return JSONResult.ok(result);
    }

    /**
     * 电梯数据监测占比
     */
    public JSONResult monitorCountEc(RedisUser redisUser) {
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        List<MonitorCountRes> result = new ArrayList<>();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        int total = 0;
        req.setUserId(redisUser.getId());
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeign.totalSize();
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            total += totalSizeRes.getEc() * TOTAL_NUMBER_EC * 30;
            for (int i = 1; i > -5; i--) {
                int offSize = 0;
                int faultSize = 0;
                String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                offSize += offlineLogMapper.offlineCount(ec, "3", beginTime, endTime);
                faultSize += faultLogMapper.faultSize(ec, "3", beginTime, endTime);
                MonitorCountRes res = monitorRate(total, offSize, faultSize);
                res.setMonth(beginTime.substring(5, 7) + "月");
                result.add(res);
            }
        } else {
            //第三方
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                ec = deviceRoleRea.getEc();
                total += ec.size() * TOTAL_NUMBER_EC * 30;
                for (int i = 1; i > -5; i--) {
                    int offSize = 0;
                    int faultSize = 0;
                    String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                    String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                    faultSize += faultLogMapper.faultSize(ec, "3", beginTime, endTime);
                    offSize += offlineLogMapper.offlineCount(ec, "3", beginTime, endTime);
                    MonitorCountRes res = monitorRate(total, offSize, faultSize);
                    res.setMonth(beginTime.substring(5, 7) + "月");
                    result.add(res);
                }
            }
        }
        Collections.reverse(result);
        return JSONResult.ok(result);
    }

    /**
     * 井盖数据监测占比
     */
    public JSONResult monitorCountMh(RedisUser redisUser) {
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        List<MonitorCountRes> result = new ArrayList<>();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        int total = 0;
        req.setUserId(redisUser.getId());
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeign.totalSize();
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            total += totalSizeRes.getMh() * TOTAL_NUMBER_MH * 30;
            for (int i = 1; i > -5; i--) {
                int offSize = 0;
                int faultSize = 0;
                String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                offSize += offlineLogMapper.offlineCount(mh, "1", beginTime, endTime);
                faultSize += faultLogMapper.faultSize(mh, "1", beginTime, endTime);
                MonitorCountRes res = monitorRate(total, offSize, faultSize);
                res.setMonth(beginTime.substring(5, 7) + "月");
                result.add(res);
            }
        } else {
            //第三方
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                mh = deviceRoleRea.getMh();
                total += mh.size() * TOTAL_NUMBER_MH * 30;
                for (int i = 1; i > -5; i--) {
                    int offSize = 0;
                    int faultSize = 0;
                    String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                    String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                    faultSize += faultLogMapper.faultSize(mh, "1", beginTime, endTime);
                    offSize += offlineLogMapper.offlineCount(mh, "1", beginTime, endTime);
                    MonitorCountRes res = monitorRate(total, offSize, faultSize);
                    res.setMonth(beginTime.substring(5, 7) + "月");
                    result.add(res);
                }
            }
        }
        Collections.reverse(result);
        return JSONResult.ok(result);
    }

    /**
     * 河道水数据监测占比
     */
    public JSONResult monitorCountRw(RedisUser redisUser) {
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        List<MonitorCountRes> result = new ArrayList<>();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        int total = 0;
        req.setUserId(redisUser.getId());
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeign.totalSize();
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            total += totalSizeRes.getRw() * TOTAL_NUMBER_RW * 30;
            for (int i = 1; i > -5; i--) {
                int offSize = 0;
                int faultSize = 0;
                String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                offSize += offlineLogMapper.offlineCount(rw, "0", beginTime, endTime);
                faultSize += faultLogMapper.faultSize(rw, "0", beginTime, endTime);
                MonitorCountRes res = monitorRate(total, offSize, faultSize);
                res.setMonth(beginTime.substring(5, 7) + "月");
                result.add(res);
            }
        } else {
            //第三方
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                rw = deviceRoleRea.getMh();
                total += rw.size() * TOTAL_NUMBER_RW * 30;
                for (int i = 1; i > -5; i--) {
                    int offSize = 0;
                    int faultSize = 0;
                    String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                    String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                    faultSize += faultLogMapper.faultSize(rw, "0", beginTime, endTime);
                    offSize += offlineLogMapper.offlineCount(rw, "0", beginTime, endTime);
                    MonitorCountRes res = monitorRate(total, offSize, faultSize);
                    res.setMonth(beginTime.substring(5, 7) + "月");
                    result.add(res);
                }
            }
        }
        Collections.reverse(result);
        return JSONResult.ok(result);
    }

    /**
     * 饮用水数据监测占比
     */
    public JSONResult monitorCountCw(RedisUser redisUser) {
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        List<MonitorCountRes> result = new ArrayList<>();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        int total = 0;
        req.setUserId(redisUser.getId());
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeign.totalSize();
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            total += totalSizeRes.getCw() * TOTAL_NUMBER_CW * 30;
            for (int i = 1; i > -5; i--) {
                int offSize = 0;
                int faultSize = 0;
                String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                offSize += offlineLogMapper.offlineCount(cw, "2", beginTime, endTime);
                faultSize += faultLogMapper.faultSize(cw, "2", beginTime, endTime);
                MonitorCountRes res = monitorRate(total, offSize, faultSize);
                res.setMonth(beginTime.substring(5, 7) + "月");
                result.add(res);
            }
        } else {
            //第三方
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                cw = deviceRoleRea.getCw();
                total += cw.size() * TOTAL_NUMBER_CW * 30;
                for (int i = 1; i > -5; i--) {
                    int offSize = 0;
                    int faultSize = 0;
                    String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                    String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                    faultSize += faultLogMapper.faultSize(cw, "2", beginTime, endTime);
                    offSize += offlineLogMapper.offlineCount(cw, "2", beginTime, endTime);
                    MonitorCountRes res = monitorRate(total, offSize, faultSize);
                    res.setMonth(beginTime.substring(5, 7) + "月");
                    result.add(res);
                }
            }
        }
        Collections.reverse(result);
        return JSONResult.ok(result);
    }

    /**
     * 电梯电量统计
     */
    public JSONResult powerEc(RedisUser redisUser) {
        PowerEcRes result = new PowerEcRes();
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        if (redisUser.getGroupId() == 1) {
            //采集端
            int atPowerRateMax = powerCountMapper.ecCountAtPowerPctMax(ec);
            int atPowerRateMin = powerCountMapper.ecCountAtPowerPctMin(ec);
            int totalAt = atPowerRateMax + atPowerRateMin;
            PowerRateRes powerRateResAt = powerRate(totalAt, atPowerRateMax, atPowerRateMin);
            result.setAtPowerRateMax(powerRateResAt.getPowerMax());
            result.setAtPowerRateMin(powerRateResAt.getPowerMin());
            //发射端
            int ctPowerRateMax = powerCountMapper.ecCountCtPowerPctMax(ec);
            int ctPowerRateMin = powerCountMapper.ecCountAtPowerPctMin(ec);
            int totalCt = ctPowerRateMax + ctPowerRateMin;
            PowerRateRes powerRateResCt = powerRate(totalCt, ctPowerRateMax, ctPowerRateMin);
            result.setCtPowerRateMax(powerRateResCt.getPowerMax());
            result.setCtPowerRateMin(powerRateResCt.getPowerMin());
        } else {
            //第三方
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                ec = deviceRoleRea.getEc();
                //采集端
                int atPowerRateMax = powerCountMapper.ecCountAtPowerPctMax(ec);
                int atPowerRateMin = powerCountMapper.ecCountAtPowerPctMin(ec);
                int totalAt = atPowerRateMax + atPowerRateMin;
                PowerRateRes powerRateResAt = powerRate(totalAt, atPowerRateMax, atPowerRateMin);
                result.setAtPowerRateMax(powerRateResAt.getPowerMax());
                result.setAtPowerRateMin(powerRateResAt.getPowerMin());
                //发射端
                int ctPowerRateMax = powerCountMapper.ecCountCtPowerPctMax(ec);
                int ctPowerRateMin = powerCountMapper.ecCountAtPowerPctMin(ec);
                int totalCt = ctPowerRateMax + ctPowerRateMin;
                PowerRateRes powerRateResCt = powerRate(totalCt, ctPowerRateMax, ctPowerRateMin);
                result.setAtPowerRateMax(powerRateResCt.getPowerMax());
                result.setAtPowerRateMin(powerRateResCt.getPowerMin());
            }
        }
        return JSONResult.ok(result);
    }

    /**
     * 井盖电量统计
     */
    public JSONResult powerMh(RedisUser redisUser) {
        PowerOtherRes result = new PowerOtherRes();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        if (redisUser.getGroupId() == 1) {
            int powerRateMax = powerCountMapper.mhCountCtPowerPctMax(mh);
            int powerRateMin = powerCountMapper.mhCountCtPowerPctMin(mh);
            int total = powerRateMax + powerRateMin;
            PowerRateRes powerRateRes = powerRate(total, powerRateMax, powerRateMin);
            result.setPowerRateMax(powerRateRes.getPowerMax());
            result.setPowerRateMin(powerRateRes.getPowerMin());
        } else {
            //第三方
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                mh = deviceRoleRea.getMh();
                int powerRateMax = powerCountMapper.mhCountCtPowerPctMax(mh);
                int powerRateMin = powerCountMapper.mhCountCtPowerPctMin(mh);
                int total = powerRateMax + powerRateMin;
                PowerRateRes powerRateRes = powerRate(total, powerRateMax, powerRateMin);
                result.setPowerRateMax(powerRateRes.getPowerMax());
                result.setPowerRateMin(powerRateRes.getPowerMin());
            }
        }
        return JSONResult.ok(result);
    }

    /**
     * 地表水电量统计
     */
    public JSONResult powerRw(RedisUser redisUser) {
        PowerOtherRes result = new PowerOtherRes();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        if (redisUser.getGroupId() == 1) {
            int powerRateMax = powerCountMapper.rwCountCtPowerPctMax(rw);
            int powerRateMin = powerCountMapper.rwCountCtPowerPctMin(rw);
            int total = powerRateMax + powerRateMin;
            PowerRateRes powerRateRes = powerRate(total, powerRateMax, powerRateMin);
            result.setPowerRateMax(powerRateRes.getPowerMax());
            result.setPowerRateMin(powerRateRes.getPowerMin());
        } else {
            //第三方
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                rw = deviceRoleRea.getRw();
                int powerRateMax = powerCountMapper.rwCountCtPowerPctMax(rw);
                int powerRateMin = powerCountMapper.rwCountCtPowerPctMin(rw);
                int total = powerRateMax + powerRateMin;
                PowerRateRes powerRateRes = powerRate(total, powerRateMax, powerRateMin);
                result.setPowerRateMin(powerRateRes.getPowerMin());
                result.setPowerRateMax(powerRateRes.getPowerMax());
            }
        }
        return JSONResult.ok(result);
    }

    /**
     * 饮用水电量统计
     */
    public JSONResult powerCw(RedisUser redisUser) {
        PowerOtherRes result = new PowerOtherRes();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        if (redisUser.getGroupId() == 1) {
           /* int powerRateMax = powerCountMapper.cwCountCtPowerPctMax(cw);
            int powerRateMin = powerCountMapper.cwCountCtPowerPctMin(cw);
            int total = powerRateMax + powerRateMin;
            PowerRateRes powerRateRes = powerRate(total, powerRateMax, powerRateMin);
            result.setPowerRateMax(powerRateRes.getPowerMax());
            result.setPowerRateMin(powerRateRes.getPowerMin());*/
        } else {
            /*//第三方
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if(deviceRoleRea!=null)
            {
                cw=deviceRoleRea.getCw();
                int powerRateMax = powerCountMapper.cwCountCtPowerPctMax(cw);
                int powerRateMin = powerCountMapper.cwCountCtPowerPctMin(cw);
                int total = powerRateMax + powerRateMin;
                PowerRateRes powerRateRes = powerRate(total, powerRateMax, powerRateMin);
                result.setPowerRateMax(powerRateRes.getPowerMax());
                result.setPowerRateMin(powerRateRes.getPowerMin());
            }*/
        }
        result.setPowerRateMax("100%");
        result.setPowerRateMin("0%");
        return JSONResult.ok(result);
    }

    /**
     * 场景故障图
     */
    public JSONResult situationFault(RedisUser redisUser) {
        SituationFaultRes result=new SituationFaultRes();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        Date now = new Date();
        String endTime = DateUtils.dateToString(now);
        String beginTime = DateUtils.dateToString(DateUtils.dayAdd(now, -30));
        req.setUserId(redisUser.getId());
        Integer ecTotal = 0;
        Integer mhTotal = 0;
        Integer rwTotal = 0;
        Integer cwTotal = 0;

        if (redisUser.getGroupId() == 1) {
            ecTotal = faultLogMapper.faultSize(ec, "3", beginTime, endTime);
            mhTotal = faultLogMapper.faultSize(mh, "1", beginTime, endTime);
            rwTotal = faultLogMapper.faultSize(rw, "0", beginTime, endTime);
            cwTotal = faultLogMapper.faultSize(cw, "2", beginTime, endTime);
            int total=ecTotal+mhTotal+rwTotal+cwTotal;
            result=situationFaultRateXW(ecTotal, mhTotal, rwTotal,cwTotal, total);
        } else {
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getEc().size() > 0) {
                    ec = deviceRoleRea.getEc();
                    ecTotal = faultLogMapper.faultSize(ec, "3", beginTime, endTime);
                }
                if (deviceRoleRea.getMh().size() > 0) {
                    mh = deviceRoleRea.getMh();
                    mhTotal = faultLogMapper.faultSize(mh, "1", beginTime, endTime);
                }
                if (deviceRoleRea.getRw().size() > 0) {
                    rw = deviceRoleRea.getRw();
                    rwTotal = faultLogMapper.faultSize(rw, "0", beginTime, endTime);
                }
                if (deviceRoleRea.getCw().size() > 0) {
                    cw = deviceRoleRea.getCw();
                    cwTotal = faultLogMapper.faultSize(cw, "2", beginTime, endTime);
                }
                int total=ecTotal+mhTotal+rwTotal+cwTotal;
                int deviceTotal=ec.size()+mh.size()+rw.size()+cw.size();
                result=situationFaultRateDSF(ec.size(),mh.size(),rw.size(),cw.size(),ecTotal,mhTotal,rwTotal,cwTotal,deviceTotal,total);
            }
        }

        return JSONResult.ok(result);
    }

    /**
     * 本年接入设备趋势图
     */
    public JSONResult accessDevice(RedisUser redisUser) {
        List<AccessDeviceRes> result = new ArrayList<>();
        List<DeviceRoleDeviceRea> accessTotal = new ArrayList<>();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        int curMonth = DateUtils.getCurrentMonth();
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeign.accessDevice(judgeMonth(curMonth));
            List<AccessDeviceRes> totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<AccessDeviceRes>>() {
            });
            result = totalSizeRes;
        } else {
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getEc().size() > 0) {
                    accessTotal.addAll(deviceRoleRea.getEc());
                }
                if (deviceRoleRea.getMh().size() > 0) {
                    accessTotal.addAll(deviceRoleRea.getMh());
                }
                if (deviceRoleRea.getRw().size() > 0) {
                    accessTotal.addAll(deviceRoleRea.getRw());
                }
                if (deviceRoleRea.getCw().size() > 0) {
                    accessTotal.addAll(deviceRoleRea.getCw());
                }
                System.out.println(accessTotal.size());
                result = monthCount(accessTotal, curMonth);
            }
        }
        return JSONResult.ok(result);
    }
    /**
     * 地区使用分布图
     * */
    public JSONResult areaDistributed()
    {
        return JSONResult.ok();
    }
    /**
     * 计算月份统计
     */
    private static List<AccessDeviceRes> monthCount(List<DeviceRoleDeviceRea> accessTotal, Integer curMonth) {
        List<AccessDeviceRes> result = new ArrayList<>();
        String curYear=DateUtils.getCurrentYear()+"";
        Integer total1 = 0;
        Integer total2 = 0;
        Integer total3 = 0;
        Integer total4 = 0;
        Integer total5 = 0;
        Integer total6 = 0;
        Integer total7 = 0;
        Integer total8 = 0;
        Integer total9 = 0;
        Integer total10 = 0;
        Integer total11 = 0;
        Integer total12 = 0;
        for (int i = 0; i < accessTotal.size(); i++) {
            String year=accessTotal.get(i).getSetupTime().substring(0,4);
            String month = accessTotal.get(i).getSetupTime().substring(5, 7);
            if (curYear.equals(year)&&"01".equals(month)) {
                total1++;
            }
            if (curYear.equals(year)&&"02".equals(month)) {
                total2++;
            }
            if (curYear.equals(year)&&"03".equals(month)) {
                total3++;
            }
            if (curYear.equals(year)&&"04".equals(month)) {
                total4++;
            }
            if (curYear.equals(year)&&"05".equals(month)) {
                total5++;
            }
            if (curYear.equals(year)&&"06".equals(month)) {
                total6++;
            }
            if (curYear.equals(year)&&"07".equals(month)) {
                total7++;
            }
            if (curYear.equals(year)&&"08".equals(month)) {
                total8++;
            }
            if (curYear.equals(year)&&"09".equals(month)) {
                total9++;
            }
            if (curYear.equals(year)&&"10".equals(month)) {
                total10++;
            }
            if (curYear.equals(year)&&"11".equals(month)) {
                total11++;
            }
            if (curYear.equals(year)&&"12".equals(month)) {
                total12++;
            }
        }
        for (int i = 1; i <= curMonth; i++) {
            if (i == 1) {
                AccessDeviceRes res = new AccessDeviceRes();
                res.setMonth(i + "月");
                res.setNumber(total1);
                result.add(res);
            }
            if (i == 2) {
                AccessDeviceRes res = new AccessDeviceRes();
                res.setMonth(i + "月");
                res.setNumber(total2);
                result.add(res);
            }
            if (i == 3) {
                AccessDeviceRes res = new AccessDeviceRes();
                res.setMonth(i + "月");
                res.setNumber(total3);
                result.add(res);
            }
            if (i == 4) {
                AccessDeviceRes res = new AccessDeviceRes();
                res.setMonth(i + "月");
                res.setNumber(total4);
                result.add(res);
            }
            if (i == 5) {
                AccessDeviceRes res = new AccessDeviceRes();
                res.setMonth(i + "月");
                res.setNumber(total5);
                result.add(res);
            }
            if (i == 6) {
                AccessDeviceRes res = new AccessDeviceRes();
                res.setMonth(i + "月");
                res.setNumber(total6);
                result.add(res);
            }
            if (i == 7) {
                AccessDeviceRes res = new AccessDeviceRes();
                res.setMonth(i + "月");
                res.setNumber(total7);
                result.add(res);
            }
            if (i == 8) {
                AccessDeviceRes res = new AccessDeviceRes();
                res.setMonth(i + "月");
                res.setNumber(total8);
                result.add(res);
            }
            if (i == 9) {
                AccessDeviceRes res = new AccessDeviceRes();
                res.setMonth(i + "月");
                res.setNumber(total9);
                result.add(res);
            }
            if (i == 10) {
                AccessDeviceRes res = new AccessDeviceRes();
                res.setMonth(i + "月");
                res.setNumber(total10);
                result.add(res);
            }
            if (i == 11) {
                AccessDeviceRes res = new AccessDeviceRes();
                res.setMonth(i + "月");
                res.setNumber(total11);
                result.add(res);
            }
            if (i == 12) {
                AccessDeviceRes res = new AccessDeviceRes();
                res.setMonth(i + "月");
                res.setNumber(total12);
                result.add(res);
            }
        }
        return result;
    }

    /**
     * 计算时间集合
     */
    private static List<AccessDeviceReq> judgeMonth(Integer size) {
        List<AccessDeviceReq> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if (size == 1) {
            list.add(1);
        } else {
            //处理之前月份
            for (int i = size - 1; i > 0; i--) {
                list.add(1 - i);
            }
            list.add(1);
        }
        for (Integer integer : list
        ) {
            AccessDeviceReq req = new AccessDeviceReq();
            req.setBeginTime(DateUtils.dateToString(DateUtils.getMonthBeginTime(integer)));
            req.setEndTime(DateUtils.dateToString(DateUtils.getMonthEndTime(integer)));
            result.add(req);
        }
        return result;
    }


    /**
     * 计算场景故障率占比(小为)
     */
    private static SituationFaultRes situationFaultRateXW(int ecTotal, int mhTotal, int rwTotal,int cwTotal, int total) {
        SituationFaultRes result = new SituationFaultRes();
        if (total == 0) {
            result.setEcRate("0%");
            result.setMhRate("0%");
            result.setRwRate("0%");
            result.setCwRate("0%");
        } else {
            BigDecimal on = null;
            BigDecimal e = new BigDecimal(cwTotal);
            BigDecimal a = new BigDecimal(total);
            BigDecimal b = new BigDecimal(ecTotal);
            BigDecimal c = new BigDecimal(mhTotal);
            BigDecimal d = new BigDecimal(rwTotal);
            BigDecimal ec = b.divide(a, 4, RoundingMode.DOWN);
            BigDecimal mh = c.divide(a, 4, RoundingMode.DOWN);
            BigDecimal rw = d.divide(a, 4, RoundingMode.DOWN);
            BigDecimal cw = e.divide(a, 4, RoundingMode.DOWN);
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            on = new BigDecimal("1").subtract(ec).subtract(mh).subtract(rw);
            result.setEcRate(nf.format(ec));
            result.setMhRate(nf.format(mh));
            result.setRwRate(nf.format(rw));
            result.setCwRate(nf.format(cw));
        }
        return result;
    }
    /**
     * 计算场景故障率占比(第三方)
     */
    private static SituationFaultRes situationFaultRateDSF(int ecSize, int mhSize, int rwSize,int cwSize,int ecTotal, int mhTotal, int rwTotal,int cwTotal,int deviceTotal,int total) {
        SituationFaultRes result = new SituationFaultRes();
        if (deviceTotal == 0) {
            result.setEcRate("0%");
            result.setMhRate("0%");
            result.setRwRate("0%");
            result.setCwRate("0%");
            result.setHaveEc(0);
            result.setHaveMh(0);
            result.setHaveRw(0);
            result.setHaveCw(0);
        } else {
            if(ecSize==0)
            {
                result.setHaveEc(0);
            }
            if(mhSize==0)
            {
                result.setHaveMh(0);
            }
            if(rwSize==0)
            {
                result.setHaveRw(0);
            }
            if(cwSize==0)
            {
                result.setHaveCw(0);
            }
            if(total==0)
            {
                result.setEcRate("0%");
                result.setMhRate("0%");
                result.setRwRate("0%");
                result.setCwRate("0%");
            }
            else
            {
                BigDecimal on = null;
                BigDecimal a = new BigDecimal(total);
                BigDecimal b = new BigDecimal(ecTotal);
                BigDecimal c = new BigDecimal(mhTotal);
                BigDecimal d = new BigDecimal(rwTotal);
                BigDecimal e = new BigDecimal(cwTotal);
                BigDecimal ec = b.divide(a, 4, RoundingMode.DOWN);
                BigDecimal mh = c.divide(a, 4, RoundingMode.DOWN);
                BigDecimal rw = d.divide(a, 4, RoundingMode.DOWN);
                BigDecimal cw = e.divide(a, 4, RoundingMode.DOWN);
                NumberFormat nf = NumberFormat.getPercentInstance();
                nf.setMinimumFractionDigits(2); //最小小数位数
                on = new BigDecimal("1").subtract(ec).subtract(mh).subtract(rw);
                result.setEcRate(nf.format(ec));
                result.setMhRate(nf.format(mh));
                result.setRwRate(nf.format(rw));
                result.setCwRate(nf.format(cw));
            }

        }
        return result;
    }

    /**
     * 计算电量比例
     */
    private static PowerRateRes powerRate(int total, int maxSize, int minSize) {
        PowerRateRes result = new PowerRateRes();
        if (total == 0) {
            result.setPowerMax("0%");
            result.setPowerMin("0%");
        } else {
            BigDecimal on = null;
            BigDecimal a = new BigDecimal(total);
            BigDecimal b = new BigDecimal(maxSize);
            BigDecimal c = new BigDecimal(minSize);
            BigDecimal max = b.divide(a, 4, RoundingMode.DOWN);
            BigDecimal min = c.divide(a, 4, RoundingMode.DOWN);
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            result.setPowerMax(nf.format(max));
            result.setPowerMin(nf.format(min));
        }
        return result;
    }

    /**
     * 计算在线，离线，故障率
     */
    private static MonitorCountRes monitorRate(int total, int offSize, int faultSize) {
        MonitorCountRes result = new MonitorCountRes();
        if (total == 0) {
            result.setFaultline("0.00%");
            result.setOnline("0.00%");
            result.setOffline("100.00%");
        } else {
            BigDecimal on = null;
            BigDecimal a = new BigDecimal(total);
            BigDecimal b = new BigDecimal(offSize);
            BigDecimal c = new BigDecimal(faultSize);
            BigDecimal off = b.divide(a, 4, RoundingMode.DOWN);
            BigDecimal fault = c.divide(a, 4, RoundingMode.DOWN);
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            on = new BigDecimal("1").subtract(off);
            on = on.subtract(fault);
            result.setFaultline(nf.format(fault));
            result.setOnline(nf.format(on));
            result.setOffline(nf.format(off));
        }
        return result;
    }
}
