package com.xiaowei.mh.service.mhdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.xiaowei.feign.client.home.HomeFeignV2;
import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.fegin.AccessDeviceReq;
import com.xiaowei.mh.common.req.fegin.AccessReq;
import com.xiaowei.mh.common.req.fegin.DeviceUserRoleV2Req;
import com.xiaowei.mh.common.res.home.*;
import com.xiaowei.mh.common.res.power.AccessDeviceRes;
import com.xiaowei.mh.common.res.power.DeviceRateRes;
import com.xiaowei.mh.common.res.power.SituationFaultRes;
import com.xiaowei.mh.common.res.redis.RedisUser;
import com.xiaowei.mh.common.util.DateUtils;
import com.xiaowei.mh.common.util.RedisKeyEnum;
import com.xiaowei.mh.common.util.RedisUtil;
import com.xiaowei.mh.common.v2.AreaV2Req;
import com.xiaowei.mh.mapper.dataobject.*;
import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import com.xiaowei.mh.mapper.home.DeviceRoleRea;
import com.xiaowei.mh.mapper.mapper.FaultLogV2Mapper;
import com.xiaowei.mh.mapper.mapper.OfflineLogV2Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

/**
 * created by 韩金群 2019/2/20
 */
@Service
@Slf4j
public class HomeV2Service {
    private static final int TOTAL_NUMBER_EC = 1440;
    private static final int TOTAL_NUMBER_MH = 10;
    private static final int TOTAL_NUMBER_RW = 8;
    private static final int TOTAL_NUMBER_CW = 48;
    @Autowired
    private FaultLogV2Mapper faultLogV2Mapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private OfflineLogV2Mapper offlineLogV2Mapper;
    @Autowired
    private HomeFeignV2 homeFeignV2;

    /**
     * 首页今日统计数
     */
    public JSONResult todayCountV2(RedisUser redisUser, AreaV2Req areaV2Req) {
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
        req.setParentId(areaV2Req.getParentId());
        req.setLevel(areaV2Req.getLevel());
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
        if (redisUser.getGroupId() == 1) {
            //总台数,运行总时长
            JSONResult jsonResult = homeFeignV2.homeTotalV2(areaV2Req);
            HomeTotalRes homeTotalRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<HomeTotalRes>() {
            });
            if (homeTotalRes != null) {
                count += homeTotalRes.getCount();
                ecTime += homeTotalRes.getRunTime() / 1000;
            }
            //事件数
            List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(ec, provinceId, cityId, null, beginTime, endTime);
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
                if (deviceRoleRea.getMh().size() > 0) {
                    mh = deviceRoleRea.getMh();
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
                    for (int i = 0; i < mh.size(); i++) {
                        mhTime += (date.getTime() - DateUtils.getStrToDate(mh.get(i).getSetupTime()).getTime()) / 1000;
                    }
                }
                if (deviceRoleRea.getRw().size() > 0) {
                    rw = deviceRoleRea.getRw();
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
                    for (int i = 0; i < rw.size(); i++) {
                        rwTime += (date.getTime() - DateUtils.getStrToDate(rw.get(i).getSetupTime()).getTime()) / 1000;
                    }
                }
                if (deviceRoleRea.getCw().size() > 0) {
                    cw = deviceRoleRea.getCw();
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
     * 首页数据：当月数据监测占比
     */
    public JSONResult monthCountLineV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        MonthCountLineRes result = new MonthCountLineRes();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
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
        req.setParentId(areaV2Req.getParentId());
        req.setLevel(areaV2Req.getLevel());
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeignV2.totalSizeV2(areaV2Req);
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            total += totalSizeRes.getEc() * TOTAL_NUMBER_EC + totalSizeRes.getMh() * TOTAL_NUMBER_MH + totalSizeRes.getRw() * TOTAL_NUMBER_RW + totalSizeRes.getCw() * TOTAL_NUMBER_CW;
            offSize += offlineLogV2Mapper.offlineCount(ec, provinceId, cityId, null, beginTime, endTime);
            faultSize += faultLogV2Mapper.faultSize(ec, provinceId, cityId, null, beginTime, endTime);
        } else {
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getEc().size() > 0) {
                    ec = deviceRoleRea.getEc();
                    total += ec.size() * TOTAL_NUMBER_EC;
                    offSize += offlineLogV2Mapper.offlineCount(ec, provinceId, cityId, "3", beginTime, endTime);
                    faultSize += faultLogV2Mapper.faultSize(ec, provinceId, cityId, "3", beginTime, endTime);
                }
                if (deviceRoleRea.getMh().size() > 0) {
                    mh = deviceRoleRea.getMh();
                    total += mh.size() * TOTAL_NUMBER_MH;
                    offSize += offlineLogV2Mapper.offlineCount(mh, provinceId, cityId, "1", beginTime, endTime);
                    faultSize += faultLogV2Mapper.faultSize(mh, provinceId, cityId, "1", beginTime, endTime);
                }
                if (deviceRoleRea.getRw().size() > 0) {
                    rw = deviceRoleRea.getRw();
                    total += rw.size() * TOTAL_NUMBER_RW;
                    offSize += offlineLogV2Mapper.offlineCount(rw, provinceId, cityId, "0", beginTime, endTime);
                    faultSize += faultLogV2Mapper.faultSize(rw, provinceId, cityId, "0", beginTime, endTime);
                }
                if (deviceRoleRea.getCw().size() > 0) {
                    cw = deviceRoleRea.getCw();
                    total += cw.size() * TOTAL_NUMBER_CW;
                    offSize += offlineLogV2Mapper.offlineCount(cw, provinceId, cityId, "1", beginTime, endTime);
                    faultSize += faultLogV2Mapper.faultSize(cw, provinceId, cityId, "2", beginTime, endTime);
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

    /**
     * 场景故障图
     */
    public JSONResult situationFaultV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        SituationFaultRes result = new SituationFaultRes();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        Date now = new Date();
        String endTime = DateUtils.dateToString(now);
        String beginTime = DateUtils.dateToString(DateUtils.dayAdd(now, -30));
        req.setUserId(redisUser.getId());
        req.setParentId(areaV2Req.getParentId());
        req.setLevel(areaV2Req.getLevel());
        Integer ecTotal = 0;
        Integer mhTotal = 0;
        Integer rwTotal = 0;
        Integer cwTotal = 0;

        if (redisUser.getGroupId() == 1) {
            ecTotal = faultLogV2Mapper.faultSize(ec, provinceId, cityId, "3", beginTime, endTime);
            mhTotal = faultLogV2Mapper.faultSize(mh, provinceId, cityId, "1", beginTime, endTime);
            rwTotal = faultLogV2Mapper.faultSize(rw, provinceId, cityId, "0", beginTime, endTime);
            cwTotal = faultLogV2Mapper.faultSize(cw, provinceId, cityId, "2", beginTime, endTime);
            int total = ecTotal + mhTotal + rwTotal + cwTotal;
            result = situationFaultRateXW(ecTotal, mhTotal, rwTotal, cwTotal, total);
        } else {
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getEc().size() > 0) {
                    ec = deviceRoleRea.getEc();
                    ecTotal = faultLogV2Mapper.faultSize(ec, provinceId, cityId, "3", beginTime, endTime);
                }
                if (deviceRoleRea.getMh().size() > 0) {
                    mh = deviceRoleRea.getMh();
                    mhTotal = faultLogV2Mapper.faultSize(mh, provinceId, cityId, "1", beginTime, endTime);
                }
                if (deviceRoleRea.getRw().size() > 0) {
                    rw = deviceRoleRea.getRw();
                    rwTotal = faultLogV2Mapper.faultSize(rw, provinceId, cityId, "0", beginTime, endTime);
                }
                if (deviceRoleRea.getCw().size() > 0) {
                    cw = deviceRoleRea.getCw();
                    cwTotal = faultLogV2Mapper.faultSize(cw, provinceId, cityId, "2", beginTime, endTime);
                }
                int total = ecTotal + mhTotal + rwTotal + cwTotal;
                int deviceTotal = ec.size() + mh.size() + rw.size() + cw.size();
                result = situationFaultRateDSF(ec.size(), mh.size(), rw.size(), cw.size(), ecTotal, mhTotal, rwTotal, cwTotal, deviceTotal, total);
            }
        }

        return JSONResult.ok(result);
    }

    /**
     * 首页场景设备数量及占比
     */
    public JSONResult deviceRateV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        Integer ecNum = 0;
        Integer mhNum = 0;
        Integer rwNum = 0;
        Integer cwNum = 0;
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        req.setUserId(redisUser.getId());
        req.setParentId(areaV2Req.getParentId());
        req.setLevel(areaV2Req.getLevel());
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeignV2.totalSizeV2(areaV2Req);
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            if (totalSizeRes != null) {
                ecNum = totalSizeRes.getEc();
                mhNum = totalSizeRes.getMh();
                rwNum = totalSizeRes.getRw();
                cwNum = totalSizeRes.getCw();
            }
        } else {
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                if (deviceRoleRea.getEc().size() > 0) {
                    ecNum = deviceRoleRea.getEc().size();
                }
                if (deviceRoleRea.getMh().size() > 0) {
                    mhNum = deviceRoleRea.getMh().size();
                }
                if (deviceRoleRea.getRw().size() > 0) {
                    rwNum = deviceRoleRea.getRw().size();
                }
                if (deviceRoleRea.getCw().size() > 0) {
                    cwNum = deviceRoleRea.getCw().size();
                }
            }
        }
        int total = ecNum + mhNum + rwNum + cwNum;
        return JSONResult.ok(situationRate(ecNum, mhNum, rwNum, cwNum, total));
    }

    /**
     * 半月事件趋势图
     */
    public JSONResult sevenTwoFaultV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        SevenFaultRes result = new SevenFaultRes();
        List<AlarmDetailRes> alarmList = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
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
            List<FaultLogDO> faultLogListEc = faultLogV2Mapper.sevenTwoFault(ec, provinceId, cityId, "3", sevenBeginTime, endTime);
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
            List<FaultLogDO> faultLogListMh = faultLogV2Mapper.sevenTwoFault(mh, provinceId, cityId, "1", sevenBeginTime, endTime);
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
            List<FaultLogDO> faultLogListRw = faultLogV2Mapper.sevenTwoFault(rw, provinceId, cityId, "0", sevenBeginTime, endTime);
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
            List<FaultLogDO> faultLogListCw = faultLogV2Mapper.sevenTwoFault(cw, provinceId, cityId, "2", sevenBeginTime, endTime);
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
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
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
                    List<FaultLogDO> resultList = faultLogV2Mapper.sevenTwoFault(ec, provinceId, cityId, "3", sevenBeginTime, endTime);
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
                    List<FaultLogDO> resultList = faultLogV2Mapper.sevenTwoFault(mh, provinceId, cityId, "1", sevenBeginTime, endTime);
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
                    List<FaultLogDO> resultList = faultLogV2Mapper.sevenTwoFault(rw, provinceId, cityId, "0", sevenBeginTime, endTime);
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
                    List<FaultLogDO> resultList = faultLogV2Mapper.sevenTwoFault(cw, provinceId, cityId, "2", sevenBeginTime, endTime);
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
     * 本年接入设备趋势图
     */
    public JSONResult accessDeviceV2(RedisUser redisUser, AreaV2Req areaV2Req) {
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<AccessDeviceRes> result = new ArrayList<>();
        List<DeviceRoleDeviceRea> accessTotal = new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        req.setUserId(redisUser.getId());
        req.setLevel(areaV2Req.getLevel());
        req.setParentId(areaV2Req.getParentId());
        int curMonth = DateUtils.getCurrentMonth();
        if (redisUser.getGroupId() == 1) {
            AccessReq accessReq = new AccessReq();
            accessReq.setList(judgeMonth(curMonth));
            accessReq.setAreaV2Req(areaV2Req);
            JSONResult jsonResult = homeFeignV2.accessDeviceV2(accessReq);
            List<AccessDeviceRes> totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<AccessDeviceRes>>() {
            });
            result = totalSizeRes;
        } else {
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
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
     * 今日最新事件
     */
    public JSONResult homeTodayEventV2(RedisUser redisUser, AreaV2Req areaV2Req) {
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
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        //判断是否是小为账号
        if (redisUser.getGroupId() == 1) {
            List<FaultLogDO> faultLogDOS = faultLogV2Mapper.sevenTwoFault(ec, provinceId, cityId, null, beginTime, endTime);
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
                    if ("0".equals(list.get(0).getDeviceType())) {
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
                    } else if ("1".equals(list.get(0).getDeviceType())) {
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

                    } else if ("2".equals(list.get(0).getDeviceType())) {
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
                    } else if ("3".equals(list.get(0).getDeviceType())) {
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
                            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_EC_INFO.getKey() + list.get(0).getDeviceNo());
                            if (o != null) {
                                XwEquipmentEcDO ecDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentEcDO>() {
                                });
                                if (ecDO != null) {
                                    res.setAddress(list.get(0).getAddress());
                                    res.setDeviceNo(list.get(0).getDeviceNo());
                                    res.setTime(DateUtils.dateToString(list.get(0).getCollectTime(),"HH:mm:ss"));
                                    res.setType("ec");
                                    res.setEvent("电梯故障");
                                    res.setDealState("待处理");
                                    res.setLevel(4);
                                    res.setLastId(ecDO.getVillageId());
                                    res.setLastAddress(ecDO.getVillageName());
                                    result.add(res);
                                }
                            }

                        }
                    }
                }
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
                            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + list.get(0).getDeviceNo());
                            if (o != null) {
                                XwEquipmentMhDO mhDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentMhDO>() {
                                });
                                if (mhDO != null) {
                                    res.setAddress(list.get(0).getAddress());
                                    res.setDeviceNo(list.get(0).getDeviceNo());
                                    res.setTime(DateUtils.dateToString(list.get(0).getCollectTime(),"HH:mm:ss"));
                                    res.setType("mh");
                                    res.setEvent("井盖报警");
                                    res.setDealState("待处理");
                                    res.setLevel(3);
                                    res.setLastId(mhDO.getStreetId());
                                    res.setLastAddress(mhDO.getStreet());
                                    result.add(res);
                                }
                            }

                        }
                    }
                }
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
                            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_RW_INFO.getKey() + list.get(0).getDeviceNo());
                            if (o != null) {
                                XwEquipmentRwDO rwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentRwDO>() {
                                });
                                if (rwDO != null) {
                                    res.setAddress(list.get(0).getAddress());
                                    res.setDeviceNo(list.get(0).getDeviceNo());
                                    res.setTime(DateUtils.dateToString(list.get(0).getCollectTime(),"HH:mm:ss"));
                                    res.setType("rw");
                                    res.setEvent("地表水报警");
                                    res.setDealState("待处理");
                                    res.setLevel(2);
                                    res.setLastId(rwDO.getAreaId());
                                    res.setLastAddress(rwDO.getArea());
                                    result.add(res);
                                }
                            }

                        }
                    }
                }
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
                            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_CW_INFO.getKey() + list.get(0).getDeviceNo());
                            if (o != null) {
                                XwEquipmentCwDO cwDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentCwDO>() {
                                });
                                if (cwDO != null) {
                                    res.setAddress(list.get(0).getAddress());
                                    res.setDeviceNo(list.get(0).getDeviceNo());
                                    res.setTime(DateUtils.dateToString(list.get(0).getCollectTime(),"HH:mm:ss"));
                                    res.setType("cw");
                                    res.setEvent("饮用水故障");
                                    res.setDealState("待处理");
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
     * 计算月份统计
     */
    private static List<AccessDeviceRes> monthCount(List<DeviceRoleDeviceRea> accessTotal, Integer curMonth) {
        List<AccessDeviceRes> result = new ArrayList<>();
        String curYear = DateUtils.getCurrentYear() + "";
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
            String year = accessTotal.get(i).getSetupTime().substring(0, 4);
            String month = accessTotal.get(i).getSetupTime().substring(5, 7);
            if (curYear.equals(year) && "01".equals(month)) {
                total1++;
            }
            if (curYear.equals(year) && "02".equals(month)) {
                total2++;
            }
            if (curYear.equals(year) && "03".equals(month)) {
                total3++;
            }
            if (curYear.equals(year) && "04".equals(month)) {
                total4++;
            }
            if (curYear.equals(year) && "05".equals(month)) {
                total5++;
            }
            if (curYear.equals(year) && "06".equals(month)) {
                total6++;
            }
            if (curYear.equals(year) && "07".equals(month)) {
                total7++;
            }
            if (curYear.equals(year) && "08".equals(month)) {
                total8++;
            }
            if (curYear.equals(year) && "09".equals(month)) {
                total9++;
            }
            if (curYear.equals(year) && "10".equals(month)) {
                total10++;
            }
            if (curYear.equals(year) && "11".equals(month)) {
                total11++;
            }
            if (curYear.equals(year) && "12".equals(month)) {
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
     * 计算场景设备占比
     */
    private static List<DeviceRateRes> situationRate(Integer ecNum, Integer mhNum, Integer rwNum, Integer cwNum, Integer total) {
        List<DeviceRateRes> result = new ArrayList<>();
        if (total == 0) {
            return result;
        } else {
            BigDecimal cw = null;
            BigDecimal a = new BigDecimal(total);
            BigDecimal b = new BigDecimal(ecNum);
            BigDecimal c = new BigDecimal(mhNum);
            BigDecimal d = new BigDecimal(rwNum);
            BigDecimal ec = b.divide(a, 4, RoundingMode.DOWN);
            BigDecimal mh = c.divide(a, 4, RoundingMode.DOWN);
            BigDecimal rw = d.divide(a, 4, RoundingMode.DOWN);
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            cw = new BigDecimal("1").subtract(ec).subtract(mh).subtract(rw);
            if(ecNum>0)
            {
                DeviceRateRes res=new DeviceRateRes();
                res.setName("ec");
                res.setNum(ecNum);
                res.setRate(nf.format(ec));
                result.add(res);
            }
            if(mhNum>0)
            {
                DeviceRateRes res=new DeviceRateRes();
                res.setName("mh");
                res.setNum(mhNum);
                res.setRate(nf.format(mh));
                result.add(res);
            }
            if(rwNum>0)
            {
                DeviceRateRes res=new DeviceRateRes();
                res.setName("rw");
                res.setNum(rwNum);
                res.setRate(nf.format(rw));
                result.add(res);
            }
            if(cwNum>0)
            {
                DeviceRateRes res=new DeviceRateRes();
                res.setName("cw");
                res.setNum(cwNum);
                res.setRate(nf.format(cw));
                result.add(res);
            }
        }
        return result;
    }

    /**
     * 计算场景故障率占比(小为)
     */
    private static SituationFaultRes situationFaultRateXW(int ecTotal, int mhTotal, int rwTotal, int cwTotal, int total) {
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
    private static SituationFaultRes situationFaultRateDSF(int ecSize, int mhSize, int rwSize, int cwSize, int ecTotal, int mhTotal, int rwTotal, int cwTotal, int deviceTotal, int total) {
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
            if (ecSize == 0) {
                result.setHaveEc(0);
            }
            if (mhSize == 0) {
                result.setHaveMh(0);
            }
            if (rwSize == 0) {
                result.setHaveRw(0);
            }
            if (cwSize == 0) {
                result.setHaveCw(0);
            }
            if (total == 0) {
                result.setEcRate("0%");
                result.setMhRate("0%");
                result.setRwRate("0%");
                result.setCwRate("0%");
            } else {
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
}
