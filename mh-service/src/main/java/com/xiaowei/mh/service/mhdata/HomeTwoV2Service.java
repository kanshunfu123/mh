package com.xiaowei.mh.service.mhdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.xiaowei.feign.client.home.HomeFeignV2;
import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.fegin.DeviceUserRoleReq;
import com.xiaowei.mh.common.req.fegin.DeviceUserRoleV2Req;
import com.xiaowei.mh.common.req.fegin.HomeOneReq;
import com.xiaowei.mh.common.res.home.*;
import com.xiaowei.mh.common.res.power.PowerEcRes;
import com.xiaowei.mh.common.res.power.PowerOtherRes;
import com.xiaowei.mh.common.res.power.PowerRateRes;
import com.xiaowei.mh.common.res.redis.RedisUser;
import com.xiaowei.mh.common.util.DateUtils;
import com.xiaowei.mh.common.util.RedisKeyEnum;
import com.xiaowei.mh.common.util.RedisUtil;
import com.xiaowei.mh.common.v2.AreaV2Req;
import com.xiaowei.mh.mapper.dataobject.*;
import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import com.xiaowei.mh.mapper.home.DeviceRoleRea;
import com.xiaowei.mh.mapper.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

/**
 * created by 韩金群 2019/2/21
 */
@Slf4j
@Service
public class HomeTwoV2Service {
    @Autowired
    private HomeFeignV2 homeFeignV2;
    @Autowired
    private FaultLogV2Mapper faultLogV2Mapper;
    @Autowired
    private OfflineLogV2Mapper offlineLogV2Mapper;
    @Autowired
    private PowerCountV2Mapper powerCountV2Mapper;
    @Autowired
    private ExcessRwMapper excessRwMapper;
    @Autowired
    private RedisUtil redisUtil;
    private static final int TOTAL_NUMBER_EC = 1440;
    private static final int TOTAL_NUMBER_MH = 10;
    private static final int TOTAL_NUMBER_RW = 8;
    private static final int TOTAL_NUMBER_CW = 48;

    /**
     * 电梯场景统计
     */
    public JSONResult todayCountEcV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        TodayCountRes result = new TodayCountRes();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
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
            homeOneReq.setLevel(areaV2Req.getLevel());
            homeOneReq.setParentId(areaV2Req.getParentId());
            //电梯总台数,运行总时长
            JSONResult jsonResult = homeFeignV2.homeTotalOneV2(homeOneReq);
            HomeTotalRes homeTotalRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<HomeTotalRes>() {
            });
            if (homeTotalRes != null) {
                count += homeTotalRes.getCount();
                ecTime += homeTotalRes.getRunTime() / 1000;
            }
            //事件数
            List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(ec, provinceId, cityId, "3", beginTime, endTime);
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
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getEc().size() > 0) {
                    ec = deviceRoleRea.getEc();
                    //台数
                    count += ec.size();
                    //事件数
                    List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(ec, provinceId, cityId, "3", beginTime, endTime);
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
    public JSONResult todayCountMhV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        TodayCountRes result = new TodayCountRes();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
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
            homeOneReq.setLevel(areaV2Req.getLevel());
            homeOneReq.setParentId(areaV2Req.getParentId());
            //电梯总台数,运行总时长
            JSONResult jsonResult = homeFeignV2.homeTotalOneV2(homeOneReq);
            HomeTotalRes homeTotalRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<HomeTotalRes>() {
            });
            if (homeTotalRes != null) {
                count += homeTotalRes.getCount();
                mhTime += homeTotalRes.getRunTime() / 1000;
            }
            //事件数
            List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(mh, provinceId, cityId, "1", beginTime, endTime);
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
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getMh().size() > 0) {
                    mh = deviceRoleRea.getMh();
                    //台数
                    count += mh.size();
                    //事件数
                    List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(mh, provinceId, cityId, "1", beginTime, endTime);
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
    public JSONResult todayCountRwV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        TodayCountRes result = new TodayCountRes();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
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
            homeOneReq.setLevel(areaV2Req.getLevel());
            homeOneReq.setParentId(areaV2Req.getParentId());
            //地表水总台数,运行总时长
            JSONResult jsonResult = homeFeignV2.homeTotalOneV2(homeOneReq);
            HomeTotalRes homeTotalRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<HomeTotalRes>() {
            });
            if (homeTotalRes != null) {
                count += homeTotalRes.getCount();
                rwTime += homeTotalRes.getRunTime() / 1000;
            }
            //事件数
            List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(rw, provinceId, cityId, "0", beginTime, endTime);
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
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getRw().size() > 0) {
                    rw = deviceRoleRea.getRw();
                    //台数
                    count += rw.size();
                    //事件数
                    List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(rw, provinceId, cityId, "0", beginTime, endTime);
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
    public JSONResult todayCountCwV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        TodayCountRes result = new TodayCountRes();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
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
            homeOneReq.setLevel(areaV2Req.getLevel());
            homeOneReq.setParentId(areaV2Req.getParentId());
            //电梯总台数,运行总时长
            JSONResult jsonResult = homeFeignV2.homeTotalOneV2(homeOneReq);
            HomeTotalRes homeTotalRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<HomeTotalRes>() {
            });
            if (homeTotalRes != null) {
                count += homeTotalRes.getCount();
                cwTime += homeTotalRes.getRunTime() / 1000;
            }
            //事件数
            List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(cw, provinceId, cityId, "2", beginTime, endTime);
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
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getCw().size() > 0) {
                    cw = deviceRoleRea.getCw();
                    //台数
                    count += cw.size();
                    //事件数
                    List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(cw, provinceId, cityId, "2", beginTime, endTime);
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
    public JSONResult monitorCountEcV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        List<MonitorCountRes> result = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        int total = 0;
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeignV2.totalSizeV2(areaV2Req);
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            total += totalSizeRes.getEc() * TOTAL_NUMBER_EC * 30;
            for (int i = 1; i > -5; i--) {
                int offSize = 0;
                int faultSize = 0;
                String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                offSize += offlineLogV2Mapper.offlineCount(ec, provinceId, cityId, "3", beginTime, endTime);
                faultSize += faultLogV2Mapper.faultSize(ec, provinceId, cityId, "3", beginTime, endTime);
                MonitorCountRes res = monitorRate(total, offSize, faultSize);
                res.setMonth(beginTime.substring(5, 7) + "月");
                result.add(res);
            }
        } else {
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                ec = deviceRoleRea.getEc();
               if(ec.size()>0)
               {
                   total += ec.size() * TOTAL_NUMBER_EC * 30;
                   for (int i = 1; i > -5; i--) {
                       int offSize = 0;
                       int faultSize = 0;
                       String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                       String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                       faultSize += faultLogV2Mapper.faultSize(ec, provinceId, cityId, "3", beginTime, endTime);
                       offSize += offlineLogV2Mapper.offlineCount(ec, provinceId, cityId, "3", beginTime, endTime);
                       MonitorCountRes res = monitorRate(total, offSize, faultSize);
                       res.setMonth(beginTime.substring(5, 7) + "月");
                       result.add(res);
                   }
               }
            }
        }
        Collections.reverse(result);
        return JSONResult.ok(result);
    }

    /**
     * 井盖数据监测占比
     */
    public JSONResult monitorCountMhV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        List<MonitorCountRes> result = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        int total = 0;
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeignV2.totalSizeV2(areaV2Req);
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            total += totalSizeRes.getMh() * TOTAL_NUMBER_MH * 30;
            for (int i = 1; i > -5; i--) {
                int offSize = 0;
                int faultSize = 0;
                String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                offSize += offlineLogV2Mapper.offlineCount(mh, provinceId, cityId, "1", beginTime, endTime);
                faultSize += faultLogV2Mapper.faultSize(mh, provinceId, cityId, "1", beginTime, endTime);
                MonitorCountRes res = monitorRate(total, offSize, faultSize);
                res.setMonth(beginTime.substring(5, 7) + "月");
                result.add(res);
            }
        } else {
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                mh = deviceRoleRea.getMh();
                if(mh.size()>0)
                {
                    total += mh.size() * TOTAL_NUMBER_MH * 30;
                    for (int i = 1; i > -5; i--) {
                        int offSize = 0;
                        int faultSize = 0;
                        String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                        String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                        faultSize += faultLogV2Mapper.faultSize(mh, provinceId, cityId, "1", beginTime, endTime);
                        offSize += offlineLogV2Mapper.offlineCount(mh, provinceId, cityId, "1", beginTime, endTime);
                        MonitorCountRes res = monitorRate(total, offSize, faultSize);
                        res.setMonth(beginTime.substring(5, 7) + "月");
                        result.add(res);
                    }
                }
            }
        }
        Collections.reverse(result);
        return JSONResult.ok(result);
    }

    /**
     * 河道水数据监测占比
     */
    public JSONResult monitorCountRwV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        List<MonitorCountRes> result = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        int total = 0;
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeignV2.totalSizeV2(areaV2Req);
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            total += totalSizeRes.getRw() * TOTAL_NUMBER_RW * 30;
            for (int i = 1; i > -5; i--) {
                int offSize = 0;
                int faultSize = 0;
                String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                offSize += offlineLogV2Mapper.offlineCount(rw, provinceId, cityId, "0", beginTime, endTime);
                faultSize += faultLogV2Mapper.faultSize(rw, provinceId, cityId, "0", beginTime, endTime);
                MonitorCountRes res = monitorRate(total, offSize, faultSize);
                res.setMonth(beginTime.substring(5, 7) + "月");
                result.add(res);
            }
        } else {
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                rw = deviceRoleRea.getMh();
               if(rw.size()>0)
               {
                   total += rw.size() * TOTAL_NUMBER_RW * 30;
                   for (int i = 1; i > -5; i--) {
                       int offSize = 0;
                       int faultSize = 0;
                       String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                       String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                       faultSize += faultLogV2Mapper.faultSize(rw, provinceId, cityId, "0", beginTime, endTime);
                       offSize += offlineLogV2Mapper.offlineCount(rw, provinceId, cityId, "0", beginTime, endTime);
                       MonitorCountRes res = monitorRate(total, offSize, faultSize);
                       res.setMonth(beginTime.substring(5, 7) + "月");
                       result.add(res);
                   }
               }
            }
        }
        Collections.reverse(result);
        return JSONResult.ok(result);
    }

    /**
     * 饮用水数据监测占比
     */
    public JSONResult monitorCountCwV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        List<MonitorCountRes> result = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        int total = 0;
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeignV2.totalSizeV2(areaV2Req);
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            total += totalSizeRes.getCw() * TOTAL_NUMBER_CW * 30;
            for (int i = 1; i > -5; i--) {
                int offSize = 0;
                int faultSize = 0;
                String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                offSize += offlineLogV2Mapper.offlineCount(cw, provinceId, cityId, "2", beginTime, endTime);
                faultSize += faultLogV2Mapper.faultSize(cw, provinceId, cityId, "2", beginTime, endTime);
                MonitorCountRes res = monitorRate(total, offSize, faultSize);
                res.setMonth(beginTime.substring(5, 7) + "月");
                result.add(res);
            }
        } else {
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                cw = deviceRoleRea.getCw();
                if(cw.size()>0)
                {
                    total += cw.size() * TOTAL_NUMBER_CW * 30;
                    for (int i = 1; i > -5; i--) {
                        int offSize = 0;
                        int faultSize = 0;
                        String beginTime = DateUtils.dateToString(DateUtils.getMonthBeginTime(i));
                        String endTime = DateUtils.dateToString(DateUtils.getMonthEndTime(i));
                        faultSize += faultLogV2Mapper.faultSize(cw, provinceId, cityId, "2", beginTime, endTime);
                        offSize += offlineLogV2Mapper.offlineCount(cw, provinceId, cityId, "2", beginTime, endTime);
                        MonitorCountRes res = monitorRate(total, offSize, faultSize);
                        res.setMonth(beginTime.substring(5, 7) + "月");
                        result.add(res);
                    }
                }
            }
        }
        Collections.reverse(result);
        return JSONResult.ok(result);
    }

    /**
     * 电梯场景最新事件
     */
    public JSONResult homeTodayEventEcV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<HomeTodayEventRes> result = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        //判断是否是小为账号
        if (redisUser.getGroupId() == 1) {
            List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(ec, provinceId, cityId, "3", beginTime, endTime);
            Set<String> stringSet = Sets.newHashSet();
            Multimap<String, FaultLogDO> multimap = ArrayListMultimap.create();
            if (!CollectionUtils.isEmpty(faultLogDOS)) {
                faultLogDOS.forEach(faultLogDO -> {
                    stringSet.add(faultLogDO.getDeviceNo());
                    multimap.put(faultLogDO.getDeviceNo(), faultLogDO);
                });
            }
            for (String str : stringSet) {
                HomeTodayEventRes res = new HomeTodayEventRes();
                List<FaultLogDO> list = (List<FaultLogDO>) multimap.get(str);
                if (list.size() > 0) {
                    res.setAddress(list.get(0).getAddress());
                    res.setDeviceNo(list.get(0).getDeviceNo());
                    res.setTime(DateUtils.dateToString(list.get(0).getCollectTime(),"HH:mm:ss"));
                    res.setDealState("待处理");
                    Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_EC_INFO.getKey() + list.get(0).getDeviceNo());
                    if (o != null) {
                        XwEquipmentEcDO ecDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentEcDO>() {
                        });
                        if (ecDO != null) {
                            res.setType("ec");
                            res.setEvent("电梯故障");
                            res.setLevel(4);
                            res.setLastId(ecDO.getVillageId());
                            res.setLastAddress(ecDO.getVillageName());
                            result.add(res);
                        }
                    }
                }
            }

        } else {
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getEc().size() > 0) {
                    ec = deviceRoleRea.getEc();
                    List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(ec, provinceId, cityId, "3", beginTime, endTime);
                    Set<String> stringSet = Sets.newHashSet();
                    Multimap<String, FaultLogDO> multimap = ArrayListMultimap.create();
                    if (!CollectionUtils.isEmpty(faultLogDOS)) {
                        faultLogDOS.forEach(faultLogDO -> {
                            stringSet.add(faultLogDO.getDeviceNo());
                            multimap.put(faultLogDO.getDeviceNo(), faultLogDO);
                        });
                    }
                    for (String str : stringSet) {
                        HomeTodayEventRes res = new HomeTodayEventRes();
                        List<FaultLogDO> list = (List<FaultLogDO>) multimap.get(str);
                        if (list.size() > 0) {
                            res.setAddress(list.get(0).getAddress());
                            res.setDeviceNo(list.get(0).getDeviceNo());
                            res.setTime(DateUtils.dateToString(list.get(0).getCollectTime(),"HH:mm:ss"));
                            res.setDealState("待处理");
                            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_EC_INFO.getKey() + list.get(0).getDeviceNo());
                            if (o != null) {
                                XwEquipmentEcDO ecDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentEcDO>() {
                                });
                                if (ecDO != null) {
                                    res.setType("ec");
                                    res.setEvent("电梯故障");
                                    res.setLevel(4);
                                    res.setLastId(ecDO.getVillageId());
                                    res.setLastAddress(ecDO.getVillageName());
                                    result.add(res);
                                }
                            }
                        }
                    }
                }
            }
        }

        return JSONResult.ok(result);
    }

    /**
     * 井盖场景今日最新事件
     */
    public JSONResult homeTodayEventMhV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<HomeTodayEventRes> result = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        //判断是否是小为账号
        if (redisUser.getGroupId() == 1) {
            List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(mh, provinceId, cityId, "1", beginTime, endTime);
            Set<String> stringSet = Sets.newHashSet();
            Multimap<String, FaultLogDO> multimap = ArrayListMultimap.create();
            if (!CollectionUtils.isEmpty(faultLogDOS)) {
                faultLogDOS.forEach(faultLogDO -> {
                    stringSet.add(faultLogDO.getDeviceNo());
                    multimap.put(faultLogDO.getDeviceNo(), faultLogDO);
                });
            }
            for (String str : stringSet) {
                HomeTodayEventRes res = new HomeTodayEventRes();
                List<FaultLogDO> list = (List<FaultLogDO>) multimap.get(str);
                if (list.size() > 0) {
                    res.setAddress(list.get(0).getAddress());
                    res.setDeviceNo(list.get(0).getDeviceNo());
                    res.setTime(DateUtils.dateToString(list.get(0).getCollectTime(),"HH:mm:ss"));
                    res.setDealState("待处理");
                    Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + list.get(0).getDeviceNo());
                    if (o != null) {
                        XwEquipmentMhDO mhDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentMhDO>() {
                        });
                        if (mhDO != null) {
                            res.setType("mh");
                            res.setEvent("井盖报警");
                            res.setLevel(3);
                            res.setLastId(mhDO.getStreetId());
                            res.setLastAddress(mhDO.getStreet());
                            result.add(res);
                        }
                    }
                }
            }

        } else {
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getMh().size() > 0) {
                    mh = deviceRoleRea.getMh();
                    List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(mh, provinceId, cityId, "1", beginTime, endTime);
                    Set<String> stringSet = Sets.newHashSet();
                    Multimap<String, FaultLogDO> multimap = ArrayListMultimap.create();
                    if (!CollectionUtils.isEmpty(faultLogDOS)) {
                        faultLogDOS.forEach(faultLogDO -> {
                            stringSet.add(faultLogDO.getDeviceNo());
                            multimap.put(faultLogDO.getDeviceNo(), faultLogDO);
                        });
                    }
                    for (String str : stringSet) {
                        HomeTodayEventRes res = new HomeTodayEventRes();
                        List<FaultLogDO> list = (List<FaultLogDO>) multimap.get(str);
                        if (list.size() > 0) {
                            res.setAddress(list.get(0).getAddress());
                            res.setDeviceNo(list.get(0).getDeviceNo());
                            res.setTime(DateUtils.dateToString(list.get(0).getCollectTime(),"HH:mm:ss"));
                            res.setDealState("待处理");
                            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + list.get(0).getDeviceNo());
                            if (o != null) {
                                XwEquipmentMhDO mhDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentMhDO>() {
                                });
                                if (mhDO != null) {
                                    res.setType("mh");
                                    res.setEvent("井盖报警");
                                    res.setLevel(3);
                                    res.setLastId(mhDO.getStreetId());
                                    res.setLastAddress(mhDO.getStreet());
                                    result.add(res);
                                }
                            }
                        }
                    }
                }
            }
        }
        return JSONResult.ok(result);
    }

    /**
     * 地表水今日最新事件
     */
    public JSONResult homeTodayEventRwV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<HomeTodayEventRes> result = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        //判断是否是小为账号
        if (redisUser.getGroupId() == 1) {
            List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(rw, provinceId, cityId, "0", beginTime, endTime);
            Set<String> stringSet = Sets.newHashSet();
            Multimap<String, FaultLogDO> multimap = ArrayListMultimap.create();
            if (!CollectionUtils.isEmpty(faultLogDOS)) {
                faultLogDOS.forEach(faultLogDO -> {
                    stringSet.add(faultLogDO.getDeviceNo());
                    multimap.put(faultLogDO.getDeviceNo(), faultLogDO);
                });
            }
            for (String str : stringSet) {
                HomeTodayEventRes res = new HomeTodayEventRes();
                List<FaultLogDO> list = (List<FaultLogDO>) multimap.get(str);
                if (list.size() > 0) {
                    res.setAddress(list.get(0).getAddress());
                    res.setDeviceNo(list.get(0).getDeviceNo());
                    res.setTime(DateUtils.dateToString(list.get(0).getCollectTime(),"HH:mm:ss"));
                    res.setDealState("待处理");
                    Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_RW_INFO.getKey() + list.get(0).getDeviceNo());
                    if (o != null) {
                        XwEquipmentRwDO rwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentRwDO>() {
                        });
                        if (rwDO != null) {
                            res.setType("rw");
                            res.setEvent("地表水故障");
                            res.setLevel(2);
                            res.setLastId(rwDO.getAreaId());
                            res.setAddress(list.get(0).getAddress());
                            res.setLastAddress(rwDO.getArea());
                            result.add(res);
                        }
                    }
                }
            }

        } else {
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getRw().size() > 0) {
                    rw = deviceRoleRea.getRw();
                    List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(rw, provinceId, cityId, "0", beginTime, endTime);
                    Set<String> stringSet = Sets.newHashSet();
                    Multimap<String, FaultLogDO> multimap = ArrayListMultimap.create();
                    if (!CollectionUtils.isEmpty(faultLogDOS)) {
                        faultLogDOS.forEach(faultLogDO -> {
                            stringSet.add(faultLogDO.getDeviceNo());
                            multimap.put(faultLogDO.getDeviceNo(), faultLogDO);
                        });
                    }
                    for (String str : stringSet) {
                        HomeTodayEventRes res = new HomeTodayEventRes();
                        List<FaultLogDO> list = (List<FaultLogDO>) multimap.get(str);
                        if (list.size() > 0) {
                            res.setAddress(list.get(0).getAddress());
                            res.setDeviceNo(list.get(0).getDeviceNo());
                            res.setTime(DateUtils.dateToString(list.get(0).getCollectTime(),"HH:mm:ss"));
                            res.setDealState("待处理");
                            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_RW_INFO.getKey() + list.get(0).getDeviceNo());
                            if (o != null) {
                                XwEquipmentRwDO rwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentRwDO>() {
                                });
                                if (rwDO != null) {
                                    res.setType("rw");
                                    res.setEvent("地表水故障");
                                    res.setLevel(2);
                                    res.setLastId(rwDO.getAreaId());
                                    res.setAddress(list.get(0).getAddress());
                                    res.setLastAddress(rwDO.getArea());
                                    result.add(res);
                                }
                            }
                        }
                    }
                }
            }
        }

        return JSONResult.ok(result);
    }

    /**
     * 饮用水今日最新事件
     */
    public JSONResult homeTodayEventCwV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<HomeTodayEventRes> result = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        //判断是否是小为账号
        if (redisUser.getGroupId() == 1) {
            List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(cw, provinceId, cityId, "2", beginTime, endTime);
            Set<String> stringSet = Sets.newHashSet();
            Multimap<String, FaultLogDO> multimap = ArrayListMultimap.create();
            if (!CollectionUtils.isEmpty(faultLogDOS)) {
                faultLogDOS.forEach(faultLogDO -> {
                    stringSet.add(faultLogDO.getDeviceNo());
                    multimap.put(faultLogDO.getDeviceNo(), faultLogDO);
                });
            }
            for (String str : stringSet) {
                HomeTodayEventRes res = new HomeTodayEventRes();
                List<FaultLogDO> list = (List<FaultLogDO>) multimap.get(str);
                if (list.size() > 0) {
                    res.setAddress(list.get(0).getAddress());
                    res.setDeviceNo(list.get(0).getDeviceNo());
                    res.setTime(DateUtils.dateToString(list.get(0).getCollectTime(),"HH:mm:ss"));
                    res.setDealState("待处理");
                    Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_CW_INFO.getKey() + list.get(0).getDeviceNo());
                    if (o != null) {
                        XwEquipmentCwDO cwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentCwDO>() {
                        });
                        if (cwDO != null) {
                            res.setType("cw");
                            res.setEvent("饮用水报警");
                            res.setLevel(4);
                            res.setLastId(cwDO.getVillageId());
                            res.setLastAddress(cwDO.getVillageName());
                            result.add(res);
                        }
                    }
                }
            }

        } else {
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getCw().size() > 0) {
                    cw = deviceRoleRea.getCw();
                    List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(cw, provinceId, cityId, "2", beginTime, endTime);
                    Set<String> stringSet = Sets.newHashSet();
                    Multimap<String, FaultLogDO> multimap = ArrayListMultimap.create();
                    if (!CollectionUtils.isEmpty(faultLogDOS)) {
                        faultLogDOS.forEach(faultLogDO -> {
                            stringSet.add(faultLogDO.getDeviceNo());
                            multimap.put(faultLogDO.getDeviceNo(), faultLogDO);
                        });
                    }
                    for (String str : stringSet) {
                        HomeTodayEventRes res = new HomeTodayEventRes();
                        List<FaultLogDO> list = (List<FaultLogDO>) multimap.get(str);
                        if (list.size() > 0) {
                            res.setAddress(list.get(0).getAddress());
                            res.setDeviceNo(list.get(0).getDeviceNo());
                            res.setTime(DateUtils.dateToString(list.get(0).getCollectTime(),"HH:mm:ss"));
                            res.setDealState("待处理");
                            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_CW_INFO.getKey() + list.get(0).getDeviceNo());
                            if (o != null) {
                                XwEquipmentCwDO cwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentCwDO>() {
                                });
                                if (cwDO != null) {
                                    res.setType("cw");
                                    res.setEvent("饮用水报警");
                                    res.setLevel(4);
                                    res.setLastId(cwDO.getVillageId());
                                    res.setLastAddress(cwDO.getVillageName());
                                    result.add(res);
                                }
                            }
                        }
                    }
                }
            }
        }

        return JSONResult.ok(result);
    }

    /**
     * 电梯今日事件占比
     */
    public JSONResult todayEventRateEcV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<TodayCountLineRes> result = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        int total = 0;
        int faultSize = 0;
        req.setUserId(redisUser.getId());
        req.setParentId(areaV2Req.getParentId());
        req.setLevel(areaV2Req.getLevel());
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeignV2.totalSizeV2(areaV2Req);
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            total += totalSizeRes.getEc() * TOTAL_NUMBER_EC;
            faultSize += faultLogV2Mapper.faultSize(ec, provinceId, cityId, "3", beginTime, endTime);
        } else {
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getEc().size() > 0) {
                    ec = deviceRoleRea.getEc();
                    total += ec.size() * TOTAL_NUMBER_EC;
                    faultSize += faultLogV2Mapper.faultSize(ec, provinceId, cityId, "3", beginTime, endTime);
                }
            }
        }
        if (total == 0) {
            TodayCountLineRes res1=new TodayCountLineRes();
            res1.setName("正常");
            res1.setRate("0.00%");
            TodayCountLineRes res2=new TodayCountLineRes();
            res2.setName("警告");
            res2.setRate("0.00%");
            TodayCountLineRes res3=new TodayCountLineRes();
            res3.setName("故障");
            res3.setRate("0.00%");
            result.add(res1);
            result.add(res2);
            result.add(res3);
        } else {
            BigDecimal a = new BigDecimal(total);
            BigDecimal c = new BigDecimal(faultSize);
            BigDecimal on = null;
            BigDecimal fault = c.divide(a, 4, RoundingMode.DOWN);
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            on = new BigDecimal("1").subtract(fault);
            TodayCountLineRes res1=new TodayCountLineRes();
            res1.setName("正常");
            res1.setRate(nf.format(on));
            result.add(res1);
            TodayCountLineRes res2=new TodayCountLineRes();
            res2.setName("警告");
            res2.setRate("0.00%");
            result.add(res2);
            TodayCountLineRes res3=new TodayCountLineRes();
            res3.setName("故障");
            res3.setRate(nf.format(fault));
            result.add(res3);
        }
        return JSONResult.ok(result);
    }

    /**
     * 井盖今日事件占比
     */
    public JSONResult todayEventRateMhV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<TodayCountLineRes> result = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        int total = 0;
        int offSize = 0;
        int faultSize = 0;
        req.setUserId(redisUser.getId());
        req.setParentId(areaV2Req.getParentId());
        req.setLevel(areaV2Req.getLevel());
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeignV2.totalSizeV2(areaV2Req);
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            total += totalSizeRes.getMh() * TOTAL_NUMBER_MH;
            offSize += offlineLogV2Mapper.offlineCount(mh, provinceId, cityId, "1", beginTime, endTime);
            faultSize += faultLogV2Mapper.faultSize(mh, provinceId, cityId, "1", beginTime, endTime);
        } else {
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {

                if (deviceRoleRea.getMh().size() > 0) {
                    mh = deviceRoleRea.getMh();
                    total += mh.size() * TOTAL_NUMBER_MH;
                    offSize += offlineLogV2Mapper.offlineCount(mh, provinceId, cityId, "1", beginTime, endTime);
                    faultSize += faultLogV2Mapper.faultSize(mh, provinceId, cityId, "1", beginTime, endTime);
                }
            }
        }
        if (total == 0) {
            TodayCountLineRes res1=new TodayCountLineRes();
            res1.setName("正常");
            res1.setRate("0.00%");
            result.add(res1);
            TodayCountLineRes res2=new TodayCountLineRes();
            res2.setName("异常");
            res2.setRate("0.00%");
            result.add(res2);
        } else {
            BigDecimal a = new BigDecimal(total);
            BigDecimal b = new BigDecimal(offSize+faultSize);
            BigDecimal on = null;
            BigDecimal error = b.divide(a, 4, RoundingMode.DOWN);
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            on = new BigDecimal("1").subtract(error);
            TodayCountLineRes res1=new TodayCountLineRes();
            res1.setName("正常");
            res1.setRate(nf.format(on));
            result.add(res1);
            TodayCountLineRes res2=new TodayCountLineRes();
            res2.setName("异常");
            res2.setRate(nf.format(error));
            result.add(res2);
        }
        return JSONResult.ok(result);
    }

    /**
     * 地表水今日事件占比
     */
    public JSONResult todayEventRateRwV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<TodayCountLineRes> result = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        int total = 0;
        int offSize = 0;
        int faultSize = 0;
        req.setUserId(redisUser.getId());
        req.setParentId(areaV2Req.getParentId());
        req.setLevel(areaV2Req.getLevel());
        int level1=0;
        int level2=0;
        int level3=0;
        int level4=0;
        int level5=0;
        int level6=0;
        List<String>list=new ArrayList<>();
        if (redisUser.getGroupId() == 1) {
            HomeOneReq homeOneReq=new HomeOneReq();
            homeOneReq.setType("rw");
            homeOneReq.setLevel(areaV2Req.getLevel());
            homeOneReq.setParentId(areaV2Req.getParentId());
            JSONResult jsonResult = homeFeignV2.homeDeviceOneV2(homeOneReq);
            list = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference< List<String>>() {
            });

        } else {
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getRw().size() > 0) {
                    rw = deviceRoleRea.getRw();
                    for (int i=0;i<rw.size();i++)
                    {
                        list.add(rw.get(i).getDeviceNo());
                    }
                }
            }
        }
        for (int i=0;i<list.size();i++)
        {
            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_RW_LATEST.getKey() + list.get(i));
            if (o != null) {
                RwDataLogDO rwDataLogDO = JSON.parseObject(o + "", new TypeReference<RwDataLogDO>() {
                });
                if(rwDataLogDO!=null)
                {
                    int level=countRwLevel(rwDataLogDO);
                    if(level==1)
                    {
                        level1++;
                    }
                    if(level==2)
                    {
                        level2++;
                    }
                    if(level==3)
                    {
                        level3++;
                    }
                    if(level==4)
                    {
                        level4++;
                    }
                    if(level==5)
                    {
                        level5++;
                    }
                    if(level==6)
                    {
                        level6++;
                    }
                }
            }
        }
        result=countRwLevel(level1,level2,level3,level4,level5,level6);
        return JSONResult.ok(result);
    }
    private List<TodayCountLineRes>countRwLevel(int level1,int level2,int level3,int level4,int level5,int level6)
    {
        int total=level1+level2+level3+level4+level5+level6;
        List<TodayCountLineRes>result=new ArrayList<>();
        TodayCountLineRes res1=new TodayCountLineRes();
        res1.setName("Ⅰ类");
        res1.setRate("0.00%");
        TodayCountLineRes res2=new TodayCountLineRes();
        res2.setName("Ⅱ类");
        res2.setRate("0.00%");
        TodayCountLineRes res3=new TodayCountLineRes();
        res3.setName("Ⅲ类");
        res3.setRate("0.00%");
        TodayCountLineRes res4=new TodayCountLineRes();
        res4.setName("Ⅳ类");
        res4.setRate("0.00%");
        TodayCountLineRes res5=new TodayCountLineRes();
        res5.setName("Ⅴ类");
        res5.setRate("0.00%");
        TodayCountLineRes res6=new TodayCountLineRes();
        res6.setName("Ⅵ类");
        res6.setRate("0.00%");
       if(total>0)
       {
           BigDecimal tt=new BigDecimal(total);
           BigDecimal a = new BigDecimal(level1);
           BigDecimal b = new BigDecimal(level2);
           BigDecimal c = new BigDecimal(level3);
           BigDecimal d = new BigDecimal(level4);
           BigDecimal e = new BigDecimal(level5);
           BigDecimal f = new BigDecimal(level6);
           BigDecimal aa = a.divide(tt, 4, RoundingMode.DOWN);
           BigDecimal bb = b.divide(tt, 4, RoundingMode.DOWN);
           BigDecimal cc = c.divide(tt, 4, RoundingMode.DOWN);
           BigDecimal dd = d.divide(tt, 4, RoundingMode.DOWN);
           BigDecimal ee = e.divide(tt, 4, RoundingMode.DOWN);
           BigDecimal ff = f.divide(tt, 4, RoundingMode.DOWN);
           NumberFormat nf = NumberFormat.getPercentInstance();
           nf.setMinimumFractionDigits(2); //最小小数位数
           if(level1>0)
           {
               res1.setRate(nf.format(aa));
           }
           if(level2>0)
           {
               res2.setRate(nf.format(bb));
           }
           if(level3>0)
           {
               res3.setRate(nf.format(cc));
           }
           if(level4>0)
           {
               res4.setRate(nf.format(dd));
           }
           if(level5>0)
           {
               res5.setRate(nf.format(ee));
           }
           if(level6>0)
           {
               res6.setRate(nf.format(ff));
           }
       }
        result.add(res1);
        result.add(res2);
        result.add(res3);
        result.add(res4);
        result.add(res5);
        result.add(res6);
        return result;
    }
    /**
     * 计算等级大小
     *
     * @param details
     * @return
     */
    private Integer countRwLevel(RwDataLogDO details) {
        Integer level = 1;
        //溶氧
        String rdo = details.getRdo();
        int  rdoResLevel = initRdoRes(rdo);
        if (rdoResLevel > level) {
            level = rdoResLevel;
        }
        //浊度
        String tss = details.getTss();
        int tssResLevel = initTssRes(tss);
        if (tssResLevel > level) {
            level = tssResLevel;
        }
        //电导率
        String conductivity = details.getConductivity();
        int conductivityResLevel = initConductivityRes(conductivity);
        if (conductivityResLevel >level) {
            level=conductivityResLevel;
        }
        //ph
        String ph = details.getPh();
        int phResLevel = initPhRes(ph);
        if (phResLevel > level) {
            level = phResLevel;
        }
        return level;
    }
    private Integer
    initDepthRes(String depth, Integer alarmDepth) {
        return 1;
    }

    private Integer initConductivityRes(String conductivity) {
        int level=1;
        if(StringUtils.isNotBlank(conductivity))
        {
            Float conductivityInt = Float.valueOf(conductivity);
            // 2000以下I类水
            if (conductivityInt <= 2000) {
                level=3;
            }
            else if (conductivityInt>2000){
                level=4;
            }
            else
            {
                level=6;
            }
        }
        return level;
    }

    private Integer initTssRes(String tss) {
        Integer level=1;
        if(StringUtils.isNotBlank(tss))
        {
            Float tssFloat = Float.valueOf(tss);
            //3以下I类水
            if (tssFloat <= 3) {
                level=1;
            } else if (tssFloat <= 10) {
                level=5;
            } else {
                level=6;
            }
        }
        return level;
    }

    private Integer initPhRes(String ph) {
       int level=1;
       if(StringUtils.isNotBlank(ph))
       {
           Float phFloat = Float.valueOf(ph);
           if (phFloat >= 6 && phFloat <= 9) {
               level=4;
           }  else {
               level=6;
           }
       }
       return level;
    }

    private Integer initRdoRes(String rdo) {
        int level=1;
        if(StringUtils.isNotBlank(rdo))
        {
            Float rdoFloat = Float.valueOf(rdo);
            // 2000以下I类水
            if (rdoFloat >= 7.5) {
                level=1;
            } else if (rdoFloat >= 6) {
                level=2;
            } else if (rdoFloat >= 5) {
                level=3;
            } else if (rdoFloat >= 3) {
                level=4;
            } else if (rdoFloat >= 2) {
                level=5;
            } else {
                level=6;
            }
        }
        return level;
    }

    /**
     * 饮用水今日事件占比
     */
    public JSONResult todayEventRateCwV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<TodayCountLineRes> result = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        int total = 0;
        int offSize = 0;
        req.setUserId(redisUser.getId());
        req.setParentId(areaV2Req.getParentId());
        req.setLevel(areaV2Req.getLevel());
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeignV2.totalSizeV2(areaV2Req);
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            total += totalSizeRes.getCw() * TOTAL_NUMBER_CW;
            offSize += excessRwMapper.countByMix(cw, provinceId, cityId, beginTime, endTime);
        } else {
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getCw().size() > 0) {
                    cw = deviceRoleRea.getCw();
                    total += cw.size() * TOTAL_NUMBER_CW;
                    offSize += excessRwMapper.countByMix(cw, provinceId, cityId, beginTime, endTime);
                }
            }
        }
        if (total == 0) {
            TodayCountLineRes res1=new TodayCountLineRes();
            res1.setName("正常");
            res1.setRate("0.00%");
            result.add(res1);
            TodayCountLineRes res2=new TodayCountLineRes();
            res2.setName("超标");
            res2.setRate("0.00%");
            result.add(res2);
        } else {
            BigDecimal a = new BigDecimal(total);
            BigDecimal b = new BigDecimal(offSize);
            BigDecimal on = null;
            BigDecimal off = b.divide(a, 4, RoundingMode.DOWN);
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            on = new BigDecimal("1").subtract(off);
            TodayCountLineRes res1=new TodayCountLineRes();
            res1.setName("正常");
            res1.setRate(nf.format(on));
            result.add(res1);
            TodayCountLineRes res2=new TodayCountLineRes();
            res2.setName("超标");
            res2.setRate(nf.format(off));
            result.add(res2);
        }
        return JSONResult.ok(result);
    }

    /**
     * 电梯电量统计
     */
    public JSONResult powerEcV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        PowerEcRes result = new PowerEcRes();
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
        if (redisUser.getGroupId() == 1) {
            //采集端
            int atPowerRateMax = powerCountV2Mapper.powerCollectMax(ec, "3", provinceId, cityId);
            int atPowerRateMin = powerCountV2Mapper.powerCollectMin(ec, "3", provinceId, cityId);
            int totalAt = atPowerRateMax + atPowerRateMin;
            PowerRateRes powerRateResAt = powerRate(totalAt, atPowerRateMax, atPowerRateMin);
            result.setAtPowerRateMax(powerRateResAt.getPowerMax());
            result.setAtPowerRateMin(powerRateResAt.getPowerMin());
            //发射端
            int ctPowerRateMax = powerCountV2Mapper.powerLaunchMax(ec, "3", provinceId, cityId);
            int ctPowerRateMin = powerCountV2Mapper.powerLaunchMin(ec, "3", provinceId, cityId);
            int totalCt = ctPowerRateMax + ctPowerRateMin;
            PowerRateRes powerRateResCt = powerRate(totalCt, ctPowerRateMax, ctPowerRateMin);
            result.setCtPowerRateMax(powerRateResCt.getPowerMax());
            result.setCtPowerRateMin(powerRateResCt.getPowerMin());
        } else {
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                ec = deviceRoleRea.getEc();
               if(ec.size()>0)
               {
                   //采集端
                   int atPowerRateMax = powerCountV2Mapper.powerCollectMax(ec, "3", provinceId, cityId);
                   int atPowerRateMin = powerCountV2Mapper.powerCollectMin(ec, "3", provinceId, cityId);
                   int totalAt = atPowerRateMax + atPowerRateMin;
                   PowerRateRes powerRateResAt = powerRate(totalAt, atPowerRateMax, atPowerRateMin);
                   result.setAtPowerRateMax(powerRateResAt.getPowerMax());
                   result.setAtPowerRateMin(powerRateResAt.getPowerMin());
                   //发射端
                   int ctPowerRateMax = powerCountV2Mapper.powerLaunchMax(ec, "3", provinceId, cityId);
                   int ctPowerRateMin = powerCountV2Mapper.powerLaunchMin(ec, "3", provinceId, cityId);
                   int totalCt = ctPowerRateMax + ctPowerRateMin;
                   PowerRateRes powerRateResCt = powerRate(totalCt, ctPowerRateMax, ctPowerRateMin);
                   result.setAtPowerRateMax(powerRateResCt.getPowerMax());
                   result.setAtPowerRateMin(powerRateResCt.getPowerMin());
               }
            }
        }
        return JSONResult.ok(result);
    }

    /**
     * 井盖电量统计
     */
    public JSONResult powerMhV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        PowerOtherRes result = new PowerOtherRes();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
        if (redisUser.getGroupId() == 1) {
            int powerRateMax = powerCountV2Mapper.powerCollectMax(mh, "1", provinceId, cityId);
            int powerRateMin = powerCountV2Mapper.powerCollectMin(mh, "1", provinceId, cityId);
            int total = powerRateMax + powerRateMin;
            PowerRateRes powerRateRes = powerRate(total, powerRateMax, powerRateMin);
            result.setPowerRateMax(powerRateRes.getPowerMax());
            result.setPowerRateMin(powerRateRes.getPowerMin());
        } else {
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                mh = deviceRoleRea.getMh();
               if(mh.size()>0)
               {
                   int powerRateMax = powerCountV2Mapper.powerCollectMax(mh, "1", provinceId, cityId);
                   int powerRateMin = powerCountV2Mapper.powerCollectMin(mh, "1", provinceId, cityId);
                   int total = powerRateMax + powerRateMin;
                   PowerRateRes powerRateRes = powerRate(total, powerRateMax, powerRateMin);
                   result.setPowerRateMax(powerRateRes.getPowerMax());
                   result.setPowerRateMin(powerRateRes.getPowerMin());
               }
            }
        }
        return JSONResult.ok(result);
    }

    /**
     * 地表水电量统计
     */
    public JSONResult powerRwV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        PowerOtherRes result = new PowerOtherRes();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
        if (redisUser.getGroupId() == 1) {
            int powerRateMax = powerCountV2Mapper.powerCollectMax(rw, "0", provinceId, cityId);
            int powerRateMin = powerCountV2Mapper.powerCollectMin(rw, "0", provinceId, cityId);
            int total = powerRateMax + powerRateMin;
            PowerRateRes powerRateRes = powerRate(total, powerRateMax, powerRateMin);
            result.setPowerRateMax(powerRateRes.getPowerMax());
            result.setPowerRateMin(powerRateRes.getPowerMin());
        } else {
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                rw = deviceRoleRea.getRw();
               if(rw.size()>0)
               {
                   int powerRateMax = powerCountV2Mapper.powerCollectMax(rw, "0", provinceId, cityId);
                   int powerRateMin = powerCountV2Mapper.powerCollectMin(rw, "0", provinceId, cityId);
                   int total = powerRateMax + powerRateMin;
                   PowerRateRes powerRateRes = powerRate(total, powerRateMax, powerRateMin);
                   result.setPowerRateMin(powerRateRes.getPowerMin());
                   result.setPowerRateMax(powerRateRes.getPowerMax());
               }
            }
        }
        return JSONResult.ok(result);
    }

    /**
     * 饮用水电量统计
     */
    public JSONResult powerCwV2(RedisUser redisUser,AreaV2Req areaV2Req) {
        PowerOtherRes result = new PowerOtherRes();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        if (redisUser.getGroupId() == 1) {
        } else {
        }
        result.setPowerRateMax("100%");
        result.setPowerRateMin("0%");
        return JSONResult.ok(result);
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
