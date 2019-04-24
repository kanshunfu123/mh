package com.xiaowei.mh.service.mhdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.xiaowei.feign.client.home.DeviceListFeign;
import com.xiaowei.feign.client.home.HomeFeign;
import com.xiaowei.feign.client.home.UserDeviceFeign;
import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.fegin.DeviceUserRoleReq;
import com.xiaowei.mh.common.res.home.*;
import com.xiaowei.mh.common.res.redis.RedisUser;
import com.xiaowei.mh.common.util.DateUtils;
import com.xiaowei.mh.common.util.RedisKeyEnum;
import com.xiaowei.mh.common.util.RedisUtil;
import com.xiaowei.mh.mapper.dataobject.*;
import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import com.xiaowei.mh.mapper.home.DeviceRoleRea;
import com.xiaowei.mh.mapper.mapper.FaultLogMapper;
import com.xiaowei.mh.mapper.mapper.HomeMapper;
import com.xiaowei.mh.mapper.mapper.OfflineLogMapper;
import com.xiaowei.mh.mapper.result.SevenFaultResult;
import com.xiaowei.mh.mapper.result.TodayFaultResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by 韩金群
 * CreateTime 2019/1/14
 */
@Service
public class HomeService {
    private static final int TOTAL_NUMBER_EC = 1440;
    private static final int TOTAL_NUMBER_MH = 10;
    private static final int TOTAL_NUMBER_RW = 8;
    private static final int TOTAL_NUMBER_CW = 48;
    @Autowired
    private UserDeviceFeign userDeviceFeign;
    @Autowired
    private HomeFeign homeFeign;
    @Autowired
    private FaultLogMapper faultLogMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private OfflineLogMapper offlineLogMapper;

    /**
     * 今日统计数
     */
    public JSONResult todayCount(RedisUser redisUser) {
        TodayCountRes result = new TodayCountRes();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        Date date = new Date();
        Long ecTime = 0L;
        Long mhTime = 0L;
        Long rwTime = 0L;
        Long cwTime = 0L;
        Integer count = 0;
        Integer countEvent = 0;
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        String strTime = DateUtils.dateToString2(new Date());
        System.out.println(strTime);
        if (redisUser.getGroupId() == 1) {
            //总台数,运行总时长
            JSONResult jsonResult = homeFeign.homeTotal();
            HomeTotalRes homeTotalRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<HomeTotalRes>() {
            });
            if (homeTotalRes != null) {
                count += homeTotalRes.getCount();
                ecTime += homeTotalRes.getRunTime() / 1000;
            }
            //事件数
            List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(ec, null, beginTime, endTime);
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
                if (deviceRoleRea.getMh().size() > 0) {
                    mh = deviceRoleRea.getMh();
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
                    for (int i = 0; i < mh.size(); i++) {
                        mhTime += (date.getTime() - DateUtils.getStrToDate(mh.get(i).getSetupTime()).getTime()) / 1000;
                    }
                }
                if (deviceRoleRea.getRw().size() > 0) {
                    rw = deviceRoleRea.getRw();
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
                    for (int i = 0; i < rw.size(); i++) {
                        rwTime += (date.getTime() - DateUtils.getStrToDate(rw.get(i).getSetupTime()).getTime()) / 1000;
                    }
                }
                if (deviceRoleRea.getCw().size() > 0) {
                    cw = deviceRoleRea.getCw();
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
                    for (int i = 0; i < cw.size(); i++) {
                        cwTime += (date.getTime() - DateUtils.getStrToDate(cw.get(i).getSetupTime()).getTime()) / 1000;
                    }
                }
            }
        }
        Long total = ecTime + mhTime + rwTime + cwTime;
        result.setRunTime(DateUtils.secondToTime(total));
        //台数
        result.setTotal(count + "台");
        //事件数
        result.setEventNum(countEvent + "件");
        result.setWaitNum(countEvent + "件");
        return JSONResult.ok(result);
    }

    /**
     * 今日最新事件
     */
    public JSONResult homeTodayEvent(RedisUser redisUser) {
        List<HomeTodayEventRes> result = new ArrayList<>();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        //判断是否是小为账号
        if (redisUser.getGroupId() == 1) {
            List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(ec, null, beginTime, endTime);
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
                    res.setTime(DateUtils.dateToString(list.get(0).getCollectTime()));
                    res.setDealState("待处理");
                    if ("0".equals(list.get(0).getDeviceType())) {
                        res.setType("地表水");
                        res.setEvent("地表水故障");
                    } else if ("1".equals(list.get(0).getDeviceType())) {
                        res.setType("井盖");
                        res.setEvent("井盖报警");
                    } else if ("2".equals(list.get(0).getDeviceType())) {
                        res.setType("饮用水");
                        res.setEvent("饮用水报警");
                    } else {
                        res.setType("电梯");
                        res.setEvent("电梯故障");
                    }
                    result.add(res);
                }
            }

        } else {
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getEc().size() > 0) {
                    ec = deviceRoleRea.getEc();
                    List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(ec, "3", beginTime, endTime);
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
                            res.setTime(DateUtils.dateToString(list.get(0).getCollectTime()));
                            res.setType("电梯");
                            res.setEvent("电梯故障");
                            res.setDealState("待处理");
                            result.add(res);
                        }
                    }
                }
                if (deviceRoleRea.getMh().size() > 0) {
                    mh = deviceRoleRea.getMh();
                    List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(mh, "1", beginTime, endTime);
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
                            res.setTime(DateUtils.dateToString(list.get(0).getCollectTime()));
                            res.setType("井盖");
                            res.setEvent("井盖报警");
                            res.setDealState("待处理");
                            result.add(res);
                        }
                    }
                }
                if (deviceRoleRea.getRw().size() > 0) {
                    rw = deviceRoleRea.getRw();
                    List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(rw, "0", beginTime, endTime);
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
                            res.setTime(DateUtils.dateToString(list.get(0).getCollectTime()));
                            res.setType("地表水");
                            res.setEvent("地表水报警");
                            res.setDealState("待处理");
                            result.add(res);
                        }
                    }
                }
                if (deviceRoleRea.getCw().size() > 0) {
                    cw = deviceRoleRea.getCw();
                    List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(cw, "2", beginTime, endTime);
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
                            res.setTime(DateUtils.dateToString(list.get(0).getCollectTime()));
                            res.setType("饮用水");
                            res.setEvent("饮用水故障");
                            res.setDealState("待处理");
                            result.add(res);
                        }
                    }
                }
            }
        }

        return JSONResult.ok(result);
    }

    /**
     * 半月事件趋势图
     */
    public JSONResult sevenTwoFault(RedisUser redisUser) {
        SevenFaultRes result = new SevenFaultRes();
        List<AlarmDetailRes> alarmList = new ArrayList<>();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        //获取当前时间
        Date now = new Date();
        String nowString = DateUtils.dateToString2(now);
        List<String> dayList = new ArrayList<>();
        List<Date> dateList = new ArrayList<>();
        dayList.add(nowString);
        dateList.add(now);
        for (int i = 1; i < 14; i++) {
            Date date = new Date();
            date = DateUtils.dayAdd(now, -i);
            String string = DateUtils.dateToString2(date);
            dayList.add(string);
            dateList.add(date);
        }
        Collections.reverse(dayList);
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        //查询条件时间
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        Date sevenBegin = DateUtils.dayAdd(DateUtils.getStrToDate(beginTime), -6);
        String sevenBeginTime = DateUtils.dateToString(sevenBegin);
        //小为
        if (redisUser.getGroupId() == 1) {
            //电梯
            List<Integer> dataEcList = new ArrayList<>();
            for (int i = 0; i < 14; i++) {
                dataEcList.add(0);
            }
            Set<String> stringSetEc = Sets.newHashSet();
            Multimap<String, String> multimapEc = ArrayListMultimap.create();
            List<FaultLogDO> faultLogListEc = faultLogMapper.sevenTwoFault(ec, "3", sevenBeginTime, endTime);
            for (int i = 0; i < faultLogListEc.size(); i++) {
                stringSetEc.add(DateUtils.dateToString2(faultLogListEc.get(i).getCollectTime()));
                multimapEc.put(DateUtils.dateToString2(faultLogListEc.get(i).getCollectTime()), faultLogListEc.get(i).getDeviceNo());
            }
            for (int i = 0; i < dayList.size(); i++) {
                for (String str : stringSetEc) {
                    if (dayList.get(i).equals(str)) {
                        //处理当日井盖事件数量
                        List<String> initList = (List<String>) multimapEc.get(str);
                        Set<String> test = Sets.newHashSet();
                        for (String string : initList) {
                            test.add(string);
                        }
                        dataEcList.set(i, test.size());
                    }
                }
            }
            AlarmDetailRes resEc = new AlarmDetailRes();
            resEc.setName("电梯");
            resEc.setDataList(dataEcList);
            alarmList.add(resEc);
            //井盖处理
            List<Integer> dataMhList = new ArrayList<>();
            for (int i = 0; i < 14; i++) {
                dataMhList.add(0);
            }
            Set<String> stringSetMh = Sets.newHashSet();
            Multimap<String, String> multimapMh = ArrayListMultimap.create();
            List<FaultLogDO> faultLogListMh = faultLogMapper.sevenTwoFault(mh, "1", sevenBeginTime, endTime);
            for (int i = 0; i < faultLogListMh.size(); i++) {
                stringSetMh.add(DateUtils.dateToString2(faultLogListMh.get(i).getCollectTime()));
                multimapMh.put(DateUtils.dateToString2(faultLogListMh.get(i).getCollectTime()), faultLogListMh.get(i).getDeviceNo());
            }
            for (int i = 0; i < dayList.size(); i++) {
                for (String str : stringSetMh) {
                    if (dayList.get(i).equals(str)) {
                        List<String> initList = (List<String>) multimapMh.get(str);
                        Set<String> test = Sets.newHashSet();
                        for (String string : initList) {
                            test.add(string);
                        }
                        dataMhList.set(i, test.size());
                    }
                }
            }
            AlarmDetailRes resMh = new AlarmDetailRes();
            resMh.setName("井盖");
            resMh.setDataList(dataMhList);
            alarmList.add(resMh);
            //地表水处理
            List<Integer> dataRwList = new ArrayList<>();
            for (int i = 0; i < 14; i++) {
                dataRwList.add(0);
            }
            Set<String> stringSetRw = Sets.newHashSet();
            Multimap<String, String> multimapRw = ArrayListMultimap.create();
            List<FaultLogDO> faultLogListRw = faultLogMapper.sevenTwoFault(rw, "0", sevenBeginTime, endTime);
            for (int i = 0; i < faultLogListRw.size(); i++) {
                stringSetRw.add(DateUtils.dateToString2(faultLogListRw.get(i).getCollectTime()));
                multimapRw.put(DateUtils.dateToString2(faultLogListRw.get(i).getCollectTime()), faultLogListRw.get(i).getDeviceNo());
            }
            for (int i = 0; i < dayList.size(); i++) {
                for (String str : stringSetRw) {
                    if (dayList.get(i).equals(str)) {
                        List<String> initList = (List<String>) multimapRw.get(str);
                        Set<String> test = Sets.newHashSet();
                        for (String string : initList) {
                            test.add(string);
                        }
                        dataRwList.set(i, test.size());
                    }
                }
            }
            AlarmDetailRes resRw = new AlarmDetailRes();
            resRw.setName("地表水");
            resRw.setDataList(dataRwList);
            alarmList.add(resRw);
            //饮用水处理
            List<Integer> dataCwList = new ArrayList<>();
            for (int i = 0; i < 14; i++) {
                dataCwList.add(0);
            }
            Set<String> stringSetCw = Sets.newHashSet();
            Multimap<String, String> multimapCw = ArrayListMultimap.create();
            List<FaultLogDO> faultLogListCw = faultLogMapper.sevenTwoFault(cw, "2", sevenBeginTime, endTime);
            for (int i = 0; i < faultLogListCw.size(); i++) {
                stringSetCw.add(DateUtils.dateToString2(faultLogListCw.get(i).getCollectTime()));
                multimapCw.put(DateUtils.dateToString2(faultLogListCw.get(i).getCollectTime()), faultLogListCw.get(i).getDeviceNo());
            }
            for (int i = 0; i < dayList.size(); i++) {
                for (String str : stringSetCw) {
                    if (dayList.get(i).equals(str)) {
                        List<String> initList = (List<String>) multimapCw.get(str);
                        Set<String> test = Sets.newHashSet();
                        for (String string : initList) {
                            test.add(string);
                        }
                        dataCwList.set(i, test.size());
                    }
                }
            }
            AlarmDetailRes resCw = new AlarmDetailRes();
            resCw.setName("饮用水");
            resCw.setDataList(dataCwList);
            alarmList.add(resCw);
        } else {
            //非第三方
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                //电梯处理
                if (deviceRoleRea.getEc().size() > 0) {
                    ec = deviceRoleRea.getEc();
                    List<Integer> dataEcList = new ArrayList<>();
                    for (int i = 0; i < 14; i++) {
                        dataEcList.add(0);
                    }
                    Set<String> stringSet = Sets.newHashSet();
                    Multimap<String, String> multimap = ArrayListMultimap.create();
                    List<FaultLogDO> resultList = faultLogMapper.sevenTwoFault(ec, "3", sevenBeginTime, endTime);
                    for (int i = 0; i < resultList.size(); i++) {
                        stringSet.add(DateUtils.dateToString2(resultList.get(i).getCollectTime()));
                        multimap.put(DateUtils.dateToString2(resultList.get(i).getCollectTime()), resultList.get(i).getDeviceNo());
                    }
                    for (int i = 0; i < dayList.size(); i++) {
                        for (String str : stringSet) {
                            if (dayList.get(i).equals(str)) {
                                List<String> initList = (List<String>) multimap.get(str);
                                Set<String> test = Sets.newHashSet();
                                for (String string : initList) {
                                    test.add(string);
                                }
                                dataEcList.set(i, test.size());
                            }
                        }
                    }
                    AlarmDetailRes res = new AlarmDetailRes();
                    res.setName("电梯");
                    res.setDataList(dataEcList);
                    alarmList.add(res);
                }
                //井盖处理
                if (deviceRoleRea.getMh().size() > 0) {
                    mh = deviceRoleRea.getMh();
                    List<Integer> dataMhList = new ArrayList<>();
                    for (int i = 0; i < 14; i++) {
                        dataMhList.add(0);
                    }
                    Set<String> stringSet = Sets.newHashSet();
                    Multimap<String, String> multimap = ArrayListMultimap.create();
                    List<FaultLogDO> resultList = faultLogMapper.sevenTwoFault(mh, "1", sevenBeginTime, endTime);
                    for (int i = 0; i < resultList.size(); i++) {
                        stringSet.add(DateUtils.dateToString2(resultList.get(i).getCollectTime()));
                        multimap.put(DateUtils.dateToString2(resultList.get(i).getCollectTime()), resultList.get(i).getDeviceNo());
                    }
                    for (int i = 0; i < dayList.size(); i++) {
                        for (String str : stringSet) {
                            if (dayList.get(i).equals(str)) {
                                List<String> initList = (List<String>) multimap.get(str);
                                Set<String> test = Sets.newHashSet();
                                for (String string : initList) {
                                    test.add(string);
                                }
                                dataMhList.set(i, test.size());
                            }
                        }
                    }
                    AlarmDetailRes res = new AlarmDetailRes();
                    res.setName("井盖");
                    res.setDataList(dataMhList);
                    alarmList.add(res);
                }
                //地表水
                if (deviceRoleRea.getRw().size() > 0) {
                    rw = deviceRoleRea.getRw();
                    List<Integer> dataRwList = new ArrayList<>();
                    for (int i = 0; i < 14; i++) {
                        dataRwList.add(0);
                    }
                    Set<String> stringSet = Sets.newHashSet();
                    Multimap<String, String> multimap = ArrayListMultimap.create();
                    List<FaultLogDO> resultList = faultLogMapper.sevenTwoFault(rw, "0", sevenBeginTime, endTime);
                    for (int i = 0; i < resultList.size(); i++) {
                        stringSet.add(DateUtils.dateToString2(resultList.get(i).getCollectTime()));
                        multimap.put(DateUtils.dateToString2(resultList.get(i).getCollectTime()), resultList.get(i).getDeviceNo());
                    }
                    for (int i = 0; i < dayList.size(); i++) {
                        for (String str : stringSet) {
                            if (dayList.get(i).equals(str)) {
                                List<String> initList = (List<String>) multimap.get(str);
                                Set<String> test = Sets.newHashSet();
                                for (String string : initList) {
                                    test.add(string);
                                }
                                dataRwList.set(i, test.size());
                            }
                        }
                    }
                    AlarmDetailRes res = new AlarmDetailRes();
                    res.setName("地表水");
                    res.setDataList(dataRwList);
                    alarmList.add(res);
                }
                //饮用水处理
                if (deviceRoleRea.getCw().size() > 0) {
                    cw = deviceRoleRea.getCw();
                    List<Integer> dataCwList = new ArrayList<>();
                    for (int i = 0; i < 14; i++) {
                        dataCwList.add(0);
                    }
                    Set<String> stringSet = Sets.newHashSet();
                    Multimap<String, String> multimap = ArrayListMultimap.create();
                    List<FaultLogDO> resultList = faultLogMapper.sevenTwoFault(cw, "2", sevenBeginTime, endTime);
                    for (int i = 0; i < resultList.size(); i++) {
                        stringSet.add(DateUtils.dateToString2(resultList.get(i).getCollectTime()));
                        multimap.put(DateUtils.dateToString2(resultList.get(i).getCollectTime()), resultList.get(i).getDeviceNo());
                    }
                    for (int i = 0; i < dayList.size(); i++) {
                        for (String str : stringSet) {
                            if (dayList.get(i).equals(str)) {
                                List<String> initList = (List<String>) multimap.get(str);
                                Set<String> test = Sets.newHashSet();
                                for (String string : initList) {
                                    test.add(string);
                                }
                                dataCwList.set(i, test.size());
                            }
                        }
                    }
                    AlarmDetailRes res = new AlarmDetailRes();
                    res.setName("饮用水");
                    res.setDataList(dataCwList);
                    alarmList.add(res);
                }
            }
        }

        result.setDateList(dayList);
        result.setAlarmList(alarmList);
        return JSONResult.ok(result);
    }

    /**
     * 最新接入设备
     */
    public JSONResult latestDevice(RedisUser redisUser) {
        List<LatestDeviceRes> result = new ArrayList<>();
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        if (redisUser.getGroupId() == 1) {
            //小为
            JSONResult jsonResult = homeFeign.latestDevice();
            List<LatestDeviceRes> latestList = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<LatestDeviceRes>>() {
            });
            result = latestList;
        } else {
            //第三方
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getEc().size() > 0) {
                    ec = deviceRoleRea.getEc();
                    int ecSize = ec.size();
                    if (ecSize > 20) {
                        ecSize = 20;
                    }
                    for (int i = 0; i < ecSize; i++) {
                        LatestDeviceRes res = new LatestDeviceRes();
                        res.setType("电梯");
                        res.setDeviceNo(ec.get(i).getDeviceNo());
                        res.setSetupTime(ec.get(i).getSetupTime());
                        Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_EC_INFO.getKey() + ec.get(i).getDeviceNo());
                        if (o != null) {
                            XwEquipmentEcDO ecDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentEcDO>() {
                            });
                            res.setAddress(ecDO.getVillageName() + ecDO.getDeviceCode());
                        } else {
                            res.setAddress("未知");
                        }
                        result.add(res);
                    }
                }
                if (deviceRoleRea.getMh().size() > 0) {
                    mh = deviceRoleRea.getMh();
                    int mhSize = mh.size();
                    if (mhSize > 10) {
                        mhSize = 10;
                    }
                    for (int i = 0; i < mhSize; i++) {
                        LatestDeviceRes res = new LatestDeviceRes();
                        res.setType("井盖");
                        res.setDeviceNo(mh.get(i).getDeviceNo());
                        res.setSetupTime(mh.get(i).getSetupTime());
                        Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + mh.get(i).getDeviceNo());
                        if (o != null) {
                            XwEquipmentMhDO mhDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentMhDO>() {
                            });
                            res.setAddress(mhDO.getAddress() + mhDO.getDeviceCode());
                        } else {
                            res.setAddress("未知");
                        }
                        result.add(res);
                    }
                }
                if (deviceRoleRea.getRw().size() > 0) {
                    rw = deviceRoleRea.getRw();
                    int rwSize = rw.size();
                    if (rwSize > 10) {
                        rwSize = 10;
                    }
                    for (int i = 0; i < rwSize; i++) {
                        LatestDeviceRes res = new LatestDeviceRes();
                        res.setType("地表水");
                        res.setDeviceNo(rw.get(i).getDeviceNo());
                        res.setSetupTime(rw.get(i).getSetupTime());
                        Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_RW_INFO.getKey() + rw.get(i).getDeviceNo());
                        if (o != null) {
                            XwEquipmentRwDO rwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentRwDO>() {
                            });
                            res.setAddress(rwDO.getDeviceName() + rwDO.getDeviceCode());
                        } else {
                            res.setAddress("未知");
                        }
                        result.add(res);
                    }
                }
                if (deviceRoleRea.getCw().size() > 0) {
                    cw = deviceRoleRea.getCw();
                    int cwSize = cw.size();
                    if (cwSize > 10) {
                        cwSize = 10;
                    }
                    for (int i = 0; i < cwSize; i++) {
                        LatestDeviceRes res = new LatestDeviceRes();
                        res.setType("饮用水");
                        res.setDeviceNo(cw.get(i).getDeviceNo());
                        res.setSetupTime(cw.get(i).getSetupTime());
                        Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_CW_INFO.getKey() + cw.get(i).getDeviceNo());
                        if (o != null) {
                            XwEquipmentCwDO cwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentCwDO>() {
                            });
                            res.setAddress(cwDO.getVillageName() + cwDO.getDeviceCode());
                        } else {
                            res.setAddress("未知");
                        }
                        result.add(res);
                    }
                }
            }
        }
        Collections.sort(result, new Comparator<LatestDeviceRes>() {
            @Override
            public int compare(LatestDeviceRes o1, LatestDeviceRes o2) {
                int mark = 1;
                Date date0 = DateUtils.getStrToDate(o1.getSetupTime());
                Date date1 = DateUtils.getStrToDate(o2.getSetupTime());
                if (date0.getTime() > date1.getTime()) {
                    mark = -1;
                }
                if (o1.getSetupTime().equals(o2.getSetupTime())) {
                    mark = 0;
                }
                return mark;
            }
        });
        return JSONResult.ok(result);
    }

    /**
     * 首页数据：当月数据监测占比
     */
    public JSONResult monthCountLine(RedisUser redisUser) {
        MonthCountLineRes result = new MonthCountLineRes();
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        String beginTime = DateUtils.monthZeroTime();
        String endTime = DateUtils.dateToString(new Date());
        int total = 0;
        int offSize = 0;
        int faultSize = 0;
        req.setUserId(redisUser.getId());
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeign.totalSize();
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            total += totalSizeRes.getEc() * TOTAL_NUMBER_EC + totalSizeRes.getMh() * TOTAL_NUMBER_MH + totalSizeRes.getRw() * TOTAL_NUMBER_RW + totalSizeRes.getCw() * TOTAL_NUMBER_CW;
            offSize += offlineLogMapper.offlineCount(ec, null, beginTime, endTime);
            faultSize += faultLogMapper.faultSize(ec, null, beginTime, endTime);
        } else {
            //第三方
            JSONResult jsonResult = userDeviceFeign.deviceRoleByUserIdService(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getEc().size() > 0) {
                    ec = deviceRoleRea.getEc();
                    total += ec.size() * TOTAL_NUMBER_EC;
                    offSize += offlineLogMapper.offlineCount(ec, "3", beginTime, endTime);
                    faultSize += faultLogMapper.faultSize(ec, "3", beginTime, endTime);
                }
                if (deviceRoleRea.getMh().size() > 0) {
                    mh = deviceRoleRea.getMh();
                    total += mh.size() * TOTAL_NUMBER_MH;
                    offSize += offlineLogMapper.offlineCount(mh, "1", beginTime, endTime);
                    faultSize += faultLogMapper.faultSize(mh, "1", beginTime, endTime);
                }
                if (deviceRoleRea.getRw().size() > 0) {
                    rw = deviceRoleRea.getRw();
                    total += rw.size() * TOTAL_NUMBER_RW;
                    offSize += offlineLogMapper.offlineCount(rw, "0", beginTime, endTime);
                    faultSize += faultLogMapper.faultSize(rw, "0", beginTime, endTime);
                }
                if (deviceRoleRea.getCw().size() > 0) {
                    cw = deviceRoleRea.getCw();
                    total += cw.size() * TOTAL_NUMBER_CW;
                    offSize += offlineLogMapper.offlineCount(cw, "1", beginTime, endTime);
                    faultSize += faultLogMapper.faultSize(cw, "2", beginTime, endTime);
                }
            }
        }
        if (total == 0) {
            result.setOnline("0.00%");
            result.setOffline("100.00%");
            result.setFaultline("0.00%");
        } else {
            BigDecimal a = new BigDecimal(total);
            BigDecimal b = new BigDecimal(offSize);
            BigDecimal c = new BigDecimal(faultSize);
            BigDecimal on = null;
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
        return JSONResult.ok(result);
    }

}
