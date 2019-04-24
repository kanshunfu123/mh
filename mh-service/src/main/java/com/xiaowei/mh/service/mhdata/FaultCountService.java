package com.xiaowei.mh.service.mhdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.xiaowei.feign.client.home.UserDeviceFeign;
import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.fegin.DeviceUserRoleReq;
import com.xiaowei.mh.common.res.home.FaultRankingRes;
import com.xiaowei.mh.common.res.redis.RedisUser;
import com.xiaowei.mh.common.util.DateUtils;
import com.xiaowei.mh.mapper.dataobject.FaultLogDO;
import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import com.xiaowei.mh.mapper.home.DeviceRoleRea;
import com.xiaowei.mh.mapper.mapper.FaultLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by 韩金群
 * CreateTime 2019/1/19
 */
@Service
@Slf4j
public class FaultCountService {
    @Autowired
    private FaultLogMapper faultLogMapper;
    @Autowired
    private UserDeviceFeign userDeviceFeign;

    public JSONResult faultRanking(RedisUser redisUser) {
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        String beginTime = com.xiaowei.mh.common.util.DateUtils.getYesterday();
        String endTime = com.xiaowei.mh.common.util.DateUtils.getTomorrow();
        Date sevenBegin = DateUtils.dayAdd(DateUtils.getStrToDate(beginTime), -6);
        String sevenBeginTime = DateUtils.dateToString(sevenBegin);
        List<FaultRankingRes> result = new ArrayList<>();
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        //小为公司
        if (redisUser.getGroupId() == 1) {
            List<FaultLogDO> faultLogDOS = faultLogMapper.sevenTwoFault(ec, null, sevenBeginTime, endTime);
            Set<String> stringSet = Sets.newHashSet();
            Multimap<String, FaultLogDO> multimap = ArrayListMultimap.create();

            if (!CollectionUtils.isEmpty(faultLogDOS)) {
                faultLogDOS.forEach(faultLogDO -> {
                    stringSet.add(faultLogDO.getDeviceNo());
                    multimap.put(faultLogDO.getDeviceNo(), faultLogDO);
                });
            }
            for (String str : stringSet) {
                FaultRankingRes res = new FaultRankingRes();
                List<FaultLogDO> list = (List<FaultLogDO>) multimap.get(str);
                if (list.size() > 0) {
                    res.setAddress(list.get(0).getAddress());
                    res.setDeviceNo(list.get(0).getDeviceNo());
                    res.setNumber(list.size());
                    if ("0".equals(list.get(0).getDeviceType())) {
                        res.setType("地表水");
                    } else if ("1".equals(list.get(0).getDeviceType())) {
                        res.setType("井盖");
                    } else if ("2".equals(list.get(0).getDeviceType())) {
                        res.setType("饮用水");
                    } else {
                        res.setType("电梯");
                    }
                    result.add(res);
                }
            }
        }
        //第三方公司
        else {
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
                        FaultRankingRes res = new FaultRankingRes();
                        List<FaultLogDO> list = (List<FaultLogDO>) multimap.get(str);
                        if (list.size() > 0) {
                            res.setAddress(list.get(0).getAddress());
                            res.setDeviceNo(list.get(0).getDeviceNo());
                            res.setNumber(list.size());
                            res.setType("电梯");
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
                        FaultRankingRes res = new FaultRankingRes();
                        List<FaultLogDO> list = (List<FaultLogDO>) multimap.get(str);
                        if (list.size() > 0) {
                            res.setAddress(list.get(0).getAddress());
                            res.setDeviceNo(list.get(0).getDeviceNo());
                            res.setNumber(list.size());
                            res.setType("井盖");
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
                        FaultRankingRes res = new FaultRankingRes();
                        List<FaultLogDO> list = (List<FaultLogDO>) multimap.get(str);
                        if (list.size() > 0) {
                            res.setAddress(list.get(0).getAddress());
                            res.setDeviceNo(list.get(0).getDeviceNo());
                            res.setNumber(list.size());
                            res.setType("地表水");
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
                        FaultRankingRes res = new FaultRankingRes();
                        List<FaultLogDO> list = (List<FaultLogDO>) multimap.get(str);
                        if (list.size() > 0) {
                            res.setAddress(list.get(0).getAddress());
                            res.setDeviceNo(list.get(0).getDeviceNo());
                            res.setNumber(list.size());
                            res.setType("饮用水");
                            result.add(res);
                        }
                    }
                }
            }
        }

        Collections.sort(result, new Comparator<FaultRankingRes>() {
            @Override
            public int compare(FaultRankingRes o1, FaultRankingRes o2) {
                return o2.getNumber() - o1.getNumber();
            }
        });
        if(result.size()>11)
        {
            return JSONResult.ok(result.subList(0, 10));
        }
        return JSONResult.ok(result);
    }
}
