package com.xiaowei.mh.service.mhdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Sets;
import com.xiaowei.feign.client.home.HomeFeignV2;
import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.fegin.DeviceUserRoleV2Req;
import com.xiaowei.mh.common.req.fegin.HomeOneReq;
import com.xiaowei.mh.common.res.home.*;
import com.xiaowei.mh.common.res.power.StandardRateRes;
import com.xiaowei.mh.common.res.redis.RedisUser;
import com.xiaowei.mh.common.res.v2.*;
import com.xiaowei.mh.common.util.DateUtils;
import com.xiaowei.mh.common.util.RedisKeyEnum;
import com.xiaowei.mh.common.util.RedisUtil;
import com.xiaowei.mh.common.v2.AreaV2Req;
import com.xiaowei.mh.mapper.dataobject.*;
import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import com.xiaowei.mh.mapper.home.DeviceRoleRea;
import com.xiaowei.mh.mapper.mapper.*;
import com.xiaowei.mh.mapper.result.v2.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

/**
 * created by 韩金群 2019/2/27
 */
@Service
@Slf4j
public class HomeThreeV2Service {
    @Autowired
    private HomeFeignV2 homeFeignV2;
    @Autowired
    private FaultLogV2Mapper faultLogV2Mapper;
    @Autowired
    private XwSafeRecordMapper xwSafeRecordMapper;
    @Autowired
    private CwDataLogMapper cwDataLogMapper;
    @Autowired
    private CwStandardLogMapper cwStandardLogMapper;
    @Autowired
    private RwStandardLogMapper rwStandardLogMapper;
    @Autowired
    private XwEcSenceMapper xwEcSenceMapper;
    @Autowired
    private XwMhSenceMapper xwMhSenceMapper;
    @Autowired
    private XwRwSenceMapper xwRwSenceMapper;
    @Autowired
    private XwCwSenceMapper xwCwSenceMapper;
    @Autowired
    private ExcessCwMapper excessCwMapper;
    @Autowired
    private ExcessRwMapper excessRwMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 电梯年检月份统计
     */
    public JSONResult safeCountEc(RedisUser redisUser, AreaV2Req areaV2Req) {
        List<SafeCountRes> result = new ArrayList<>();
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        int curYear = DateUtils.getCurrentYear();
        int curMonth = DateUtils.getCurrentMonth();
        if (redisUser.getGroupId() == 1) {
            for (int i = 1; i <= curMonth; i++) {
                SafeCountRes res = new SafeCountRes();
                int count = xwSafeRecordMapper.countByMix(ec, "3", curYear, i, provinceId, cityId);
                res.setNumber(count);
                res.setMonth(i + "月");
                result.add(res);
            }
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                ec = deviceRoleRea.getEc();
                if(ec.size()>0)
                {
                    for (int i = 1; i <= curMonth; i++) {
                        SafeCountRes res = new SafeCountRes();
                        res.setMonth(i + "月");
                        int count = xwSafeRecordMapper.countByMix(ec, "3", curYear, i, provinceId, cityId);
                        res.setNumber(count);
                        result.add(res);
                    }
                }
            }
        }
        return JSONResult.ok(result);
    }

    /**
     * 饮用水达标数值占比(30)（用）
     */
    public JSONResult standardCw(RedisUser redisUser, AreaV2Req areaV2Req) {
        List<StandardCwRes> result = new ArrayList<>();
        String endTime = DateUtils.getTomorrow();
        String str1 = DateUtils.getYesterday();
        Date date1 = DateUtils.getStrToDate(str1);
        String beginTime = DateUtils.dateToString(DateUtils.dayAdd(date1, -30));
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        int phYesSize = 0;
        int phNoSize = 0;
        int oxygenYesSize = 0;
        int oxygenNoSize = 0;
        int chlorineYesSize = 0;
        int chlorineNoSize = 0;
        int turbidityYesSize = 0;
        int turbidityNoSize = 0;
        List<CwStandardLogDO> list = new ArrayList<>();
        if (redisUser.getGroupId() == 1) {
            //小为
            list = cwStandardLogMapper.standardCount(cw, provinceId, cityId, beginTime, endTime);
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                cw = deviceRoleRea.getCw();
                if(cw.size()>0)
                {
                    list = cwStandardLogMapper.standardCount(cw, provinceId, cityId, beginTime, endTime);
                }
            }
        }
        //计算数量
        for (int i = 0; i < list.size(); i++) {
            //判断ph
            Float ph = list.get(i).getPh();
            if (ph != null) {
                if (ph > 6.5 && ph < 8.5) {
                    phYesSize++;
                } else {
                    phNoSize++;
                }
            }
            //判断溶解氧
            Float oxygen = list.get(i).getOxygen();
            if (oxygen != null) {
                if (oxygen >= 3) {
                    oxygenYesSize++;
                } else {
                    oxygenNoSize++;
                }
            }
            //浊度
            Float turbidity = list.get(i).getTurbidity();
            if (turbidity != null) {
                if (turbidity <= 3) {
                    turbidityYesSize++;
                } else {
                    turbidityNoSize++;
                }
            }
            //余氯
            Float chlorine = list.get(i).getChlorine();
            if (chlorine != null) {
                if (chlorine <= 250) {
                    chlorineYesSize++;
                } else {
                    chlorineNoSize++;
                }
            }
        }
        //ph
        StandardRateRes phRes = standardRate(phYesSize, phNoSize);
        if(phYesSize+phNoSize>0)
        {
            StandardCwRes res=new StandardCwRes();
            res.setYes(phRes.getYesRate());
            res.setNo(phRes.getNoRate());
            res.setName("ph");
            result.add(res);
        }
        //溶解氧
        StandardRateRes oxygenRes = standardRate(oxygenYesSize, oxygenNoSize);
        if(oxygenYesSize+oxygenNoSize>0)
        {
            StandardCwRes res=new StandardCwRes();
            res.setYes(oxygenRes.getYesRate());
            res.setNo(oxygenRes.getNoRate());
            res.setName("溶解氧");
            result.add(res);
        }
        //浊度
        StandardRateRes turbidityRes = standardRate(turbidityYesSize, turbidityNoSize);
        if(turbidityYesSize+turbidityNoSize>0)
        {
            StandardCwRes res=new StandardCwRes();
            res.setYes(turbidityRes.getYesRate());
            res.setNo(turbidityRes.getNoRate());
            res.setName("浊度");
            result.add(res);
        }
        //余氯
        StandardRateRes chlorineRes = standardRate(chlorineYesSize, chlorineNoSize);
        if(chlorineYesSize+chlorineNoSize>0)
        {
            StandardCwRes res=new StandardCwRes();
            res.setYes(chlorineRes.getYesRate());
            res.setNo(chlorineRes.getNoRate());
            res.setName("余氯");
            result.add(res);
        }
        return JSONResult.ok(result);
    }

    /**
     * 电梯故障类型占比图
     */
    public JSONResult ecFaultType(RedisUser redisUser, AreaV2Req areaV2Req) {
        List<FaultTypeRes> result = new ArrayList<>();
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        String endTime = DateUtils.getTomorrow();
        String str1 = DateUtils.getYesterday();
        Date date1 = DateUtils.getStrToDate(str1);
        String beginTime = DateUtils.dateToString(DateUtils.dayAdd(date1, -30));
        Long provinceId = null;
        Long cityId = null;
        int fault1 = 0;
        int fault2 = 0;
        int fault3 = 0;
        int fault4 = 0;
        int fault5 = 0;
        int fault6 = 0;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<FaultLogDO> list = new ArrayList<>();
        if (redisUser.getGroupId() == 1) {
            //小为
            list = faultLogV2Mapper.sevenTwoFault(ec, provinceId, cityId, "3", beginTime, endTime);
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                ec = deviceRoleRea.getEc();
                if(ec.size()>0)
                {
                    list = faultLogV2Mapper.sevenTwoFault(ec, provinceId, cityId, "3", beginTime, endTime);
                }
            }
        }
        //计算故障
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFault1() > 0) {
                fault1++;
            }
            if (list.get(i).getFault2() > 0) {
                fault2++;
            }
            if (list.get(i).getFault3() > 0) {
                fault3++;
            }
            if (list.get(i).getFault4() > 0) {
                fault4++;
            }
            if (list.get(i).getFault5() > 0) {
                fault5++;
            }
            if (list.get(i).getFault6() > 0) {
                fault6++;
            }
        }
        result = countFaultEc(fault1, fault2, fault3, fault4, fault5, fault6);
        return JSONResult.ok(result);
    }
    /**
     * 井盖故障类型占比图
     * */
    public JSONResult mhFaultType(RedisUser redisUser,AreaV2Req areaV2Req)
    {
        List<FaultTypeRes> result = new ArrayList<>();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        String endTime = DateUtils.getTomorrow();
        String beginTime = DateUtils.getYesterday();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        int fault1=0; //开盖
        int fault2=0; //水位
        int fault3=0; //气体
        List<FaultLogDO> list = new ArrayList<>();
        if(redisUser.getGroupId()==1)
        {
            //小为
            list = faultLogV2Mapper.sevenTwoFault(mh, provinceId, cityId, "1", beginTime, endTime);
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                mh = deviceRoleRea.getMh();
                if(mh.size()>0)
                {
                    list = faultLogV2Mapper.sevenTwoFault(mh, provinceId, cityId, "1", beginTime, endTime);
                }
            }
        }
        for (int i=0;i<list.size();i++)
        {
            if(list.get(i).getFault1()>0)
            {
                fault1++;
            }
            if(list.get(i).getFault5()>0)
            {
                fault2++;
            }
        }
        result=countFaultMh(fault1,fault2,fault3);
        return JSONResult.ok(result);
    }
    /**
     * 地表水故障类型占比图
     * */
    public JSONResult rwFaultType(RedisUser redisUser,AreaV2Req areaV2Req)
    {
        List<FaultTypeRes> result = new ArrayList<>();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        String endTime = DateUtils.getTomorrow();
        String beginTime = DateUtils.getYesterday();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        int fault1=0; //高水位
        int fault2=0; //低水位
        int fault3=0; //水质
        if(redisUser.getGroupId()==1)
        {
            //小为
            fault3+= excessRwMapper.countByMix(rw, provinceId, cityId, beginTime, endTime);
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                rw = deviceRoleRea.getRw();
                if(rw.size()>0)
                {
                    fault3+= excessRwMapper.countByMix(rw, provinceId, cityId, beginTime, endTime);
                }
            }
        }
        result=countFaultRw(fault1,fault2,fault3);
        return JSONResult.ok(result);
    }
    /**
     * 饮用水故障类型占比图
     * */
    public JSONResult cwFaultType(RedisUser redisUser,AreaV2Req areaV2Req)
    {
        List<FaultTypeRes> result = new ArrayList<>();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        String endTime = DateUtils.getTomorrow();
        String beginTime = DateUtils.getYesterday();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        int fault1=0; //水箱开盖
        int fault2=0; //水位
        int fault3=0; //水质超标
        if(redisUser.getGroupId()==1)
        {
            //小为
            fault3 = excessCwMapper.countByMix(cw, provinceId, cityId, beginTime, endTime);
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                cw = deviceRoleRea.getCw();
                if(cw.size()>0)
                {
                    fault3 = excessCwMapper.countByMix(cw, provinceId, cityId, beginTime, endTime);
                }
            }
        }
        result=countFaultCw(fault1,fault2,fault3);
        return JSONResult.ok(result);
    }
    /**
     * 电梯场景品牌数量统计
     * */
    public JSONResult ecBrandCount(RedisUser redisUser,AreaV2Req areaV2Req)
    {
        List<EcBrandRes>result=new ArrayList<>();
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<EcBrandResult>list=new ArrayList<>();
        if(redisUser.getGroupId()==1)
        {
            list=xwEcSenceMapper.brandEcByMix(ec,provinceId,cityId);
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                ec = deviceRoleRea.getEc();
                if(ec.size()>0)
                {
                    list=xwEcSenceMapper.brandEcByMix(ec,provinceId,cityId);
                }
            }
        }
        for (int i=0;i<list.size();i++)
        {
            EcBrandRes res=new EcBrandRes();
            res.setBrand(list.get(i).getBrand());
            res.setNum(list.get(i).getNum());
            result.add(res);
        }
        return JSONResult.ok(result);
    }
    /**
     * 电梯场景使用年限数量统计
     * */
    public JSONResult ecYearCount(RedisUser redisUser,AreaV2Req areaV2Req)
    {
        List<EcYearRes>result=new ArrayList<>();
        List<DeviceRoleDeviceRea> ec = new ArrayList<>();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<EcYearResult>list=new ArrayList<>();
        if(redisUser.getGroupId()==1)
        {
            list=xwEcSenceMapper.yearEcByMix(ec,provinceId,cityId);
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                ec = deviceRoleRea.getEc();
                if(ec.size()>0)
                {
                    list=xwEcSenceMapper.yearEcByMix(ec,provinceId,cityId);
                }
            }
        }
        for (int i=0;i<list.size();i++)
        {
            EcYearRes res=new EcYearRes();
            res.setYear(list.get(i).getYear()+"年");
            res.setNum(list.get(i).getNum());
            result.add(res);
        }
        return JSONResult.ok(result);
    }
    /**
     * 井盖场景使用年限统计
     * */
    public JSONResult mhYearCount(RedisUser redisUser,AreaV2Req areaV2Req)
    {
        List<MhYearRes>result=new ArrayList<>();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<MhYearResult>list=new ArrayList<>();
        if(redisUser.getGroupId()==1)
        {
            list=xwMhSenceMapper.yearMhByMix(mh,provinceId,cityId);
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                mh = deviceRoleRea.getMh();
               if(mh.size()>0)
               {
                   list=xwMhSenceMapper.yearMhByMix(mh,provinceId,cityId);
               }
            }
        }
        for (int i=0;i<list.size();i++)
        {
            MhYearRes res=new MhYearRes();
            res.setYear(list.get(i).getYear()+"年");
            res.setNum(list.get(i).getNum());
            result.add(res);
        }
        return JSONResult.ok(result);
    }
    /**
     * 井盖场景材质数量统计
     * */
    public JSONResult mhMaterialCount(RedisUser redisUser,AreaV2Req areaV2Req)
    {
        List<MhMaterialRes>result=new ArrayList<>();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<MhMaterialResult>list=new ArrayList<>();
        if(redisUser.getGroupId()==1)
        {
            list=xwMhSenceMapper.materialMhByMix(mh,provinceId,cityId);
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                mh = deviceRoleRea.getMh();
                if(mh.size()>0)
                {
                    list=xwMhSenceMapper.materialMhByMix(mh,provinceId,cityId);
                }
            }
        }
        result=countMaterial(list);
        return JSONResult.ok(result);
    }
    /**
     * 井盖场景之直径占比统计
     * */
    public JSONResult mhDiameterCount(RedisUser redisUser,AreaV2Req areaV2Req)
    {
        List<MhDiameterRes>result=new ArrayList<>();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<MhDiameterResult>list=new ArrayList<>();
        if(redisUser.getGroupId()==1)
        {
            list=xwMhSenceMapper.diameterMhByMix(mh,provinceId,cityId);
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                mh = deviceRoleRea.getMh();
                if(mh.size()>0)
                {
                    list=xwMhSenceMapper.diameterMhByMix(mh,provinceId,cityId);
                }
            }
        }
        result=countDiameter(list);
        return JSONResult.ok(result);
    }
    /**
     * 井盖场景使用类型占比统计
     * */
    public JSONResult mhTypeCount(RedisUser redisUser,AreaV2Req areaV2Req)
    {
        List<MhTypeRes>result=new ArrayList<>();
        List<DeviceRoleDeviceRea> mh = new ArrayList<>();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<MhTypeResult>list=new ArrayList<>();
        if(redisUser.getGroupId()==1)
        {
            list=xwMhSenceMapper.typeMhByMix(mh,provinceId,cityId);
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                mh = deviceRoleRea.getMh();
              if(mh.size()>0)
              {
                  list=xwMhSenceMapper.typeMhByMix(mh,provinceId,cityId);
              }
            }
        }
        result=countType(list);
        return JSONResult.ok(result);
    }
    /**
     * 饮用水材质数量占比统计
     * */
    public JSONResult cwMaterialCount(RedisUser redisUser,AreaV2Req areaV2Req)
    {
        List<MhMaterialRes>result=new ArrayList<>();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<MhMaterialResult>list=new ArrayList<>();
        if(redisUser.getGroupId()==1)
        {
            list=xwCwSenceMapper.materialCwByMix(cw,provinceId,cityId);
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                cw = deviceRoleRea.getCw();
                if(cw.size()>0)
                {
                    list=xwCwSenceMapper.materialCwByMix(cw,provinceId,cityId);
                }
            }
        }
        result=countMaterial(list);
        return JSONResult.ok(result);
    }
    /**
     * 地表水使用类型占比统计
     * */
    public JSONResult rwTypeCount(RedisUser redisUser,AreaV2Req areaV2Req)
    {
        List<MhTypeRes>result=new ArrayList<>();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        List<MhTypeResult>list=new ArrayList<>();
        if(redisUser.getGroupId()==1)
        {
            list=xwRwSenceMapper.typeRwByMix(rw,provinceId,cityId);
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                rw = deviceRoleRea.getRw();
               if(rw.size()>0)
               {
                   list=xwRwSenceMapper.typeRwByMix(rw,provinceId,cityId);
               }
            }
        }
        result=countType(list);
        return JSONResult.ok(result);
    }
    /**
     * 地表水水质等级统计
     * */
    public JSONResult rwLevelCount(RedisUser redisUser,AreaV2Req areaV2Req)
    {
        List<RwLevelRes>result=new ArrayList<>();
        DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
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
        RwLevelRes res1=new RwLevelRes();
        res1.setLevel("Ⅰ类");
        res1.setNum(level1);
        RwLevelRes res2=new RwLevelRes();
        res2.setLevel("Ⅱ类");
        res2.setNum(level2);
        RwLevelRes res3=new RwLevelRes();
        res3.setLevel("Ⅲ类");
        res3.setNum(level3);
        RwLevelRes res4=new RwLevelRes();
        res4.setLevel("Ⅳ类");
        res4.setNum(level4);
        RwLevelRes res5=new RwLevelRes();
        res5.setLevel("Ⅴ类");
        res5.setNum(level5);
        RwLevelRes res6=new RwLevelRes();
        res6.setLevel("Ⅵ类");
        res6.setNum(level6);
        result.add(res1);
        result.add(res2);
        result.add(res3);
        result.add(res4);
        result.add(res5);
        result.add(res6);
        return JSONResult.ok(result);
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
     * 地表水达标数值占比(30)（用）
     */
    public JSONResult standardRw(RedisUser redisUser, AreaV2Req areaV2Req) {
        List<StandardRwRes>result=new ArrayList<>();
        String endTime = DateUtils.getTomorrow();
        String str1 = DateUtils.getYesterday();
        Date date1 = DateUtils.getStrToDate(str1);
        String beginTime = DateUtils.dateToString(DateUtils.dayAdd(date1, -30));
        List<DeviceRoleDeviceRea> rw = new ArrayList<>();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        int phYesSize = 0;
        int phNoSize = 0;
        int oxygenYesSize = 0;
        int oxygenNoSize = 0;
        int turbidityYesSize = 0;
        int turbidityNoSize = 0;
        int conductivityYesSize = 0;
        int conductivityNoSize = 0;
        List<RwStandardLogDO> list = new ArrayList<>();
        if (redisUser.getGroupId() == 1) {
            //小为
            list = rwStandardLogMapper.standardCount(rw, provinceId, cityId, beginTime, endTime);
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                rw = deviceRoleRea.getRw();
                if(rw.size()>0)
                {
                    list = rwStandardLogMapper.standardCount(rw, provinceId, cityId, beginTime, endTime);
                }
            }
        }
        //统计设备所在的等级
        Set<String>sets= Sets.newHashSet();
        Map<String,Integer>map=new HashMap<>();
        for (int i=0;i<list.size();i++)
        {
            if(StringUtils.isNotBlank(list.get(i).getDeviceNo()))
            {
                sets.add(list.get(i).getDeviceNo());
            }
        }
        for (String str:sets)
        {
            Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_RW_SENCE.getKey() + str);
            if (o != null) {
                XwRwSenceDO rwSenceDO = JSON.parseObject(o + "", new TypeReference<XwRwSenceDO>() {
                });
                if(rwSenceDO!=null) {
                    if(rwSenceDO.getRwGrade()!=null)
                    {
                        map.put(str,rwSenceDO.getRwGrade());
                    }
                }
                }
        }
        for (int i=0;i<list.size();i++)
        {
            String deviceNo=list.get(i).getDeviceNo();
            //处理ph值
            String ph=list.get(i).getPh();
            if(StringUtils.isNotBlank(ph))
            {
                Float f_ph=Float.valueOf(ph);
                int count=rwCountPh(f_ph);
                if(count>0)
                {
                    phYesSize++;
                }
                else
                {
                    phNoSize++;
                }
            }
            //处理溶解氧值
            String oxygen=list.get(i).getPh();
            if(StringUtils.isNotBlank(oxygen))
            {
                Float f_oxygen=Float.valueOf(oxygen);
                Integer level=map.get(deviceNo);
                int count=rwCountOxygen(level,f_oxygen);
                if(count>0)
                {
                    oxygenYesSize++;
                }
                else
                {
                    oxygenNoSize++;
                }
            }
            //处理浊度值
            String turbidity=list.get(i).getTurbidity();
            if(StringUtils.isNotBlank(turbidity))
            {
                Float f_turbidity=Float.valueOf(turbidity);
                Integer level=map.get(deviceNo);
                int count=rwCountTurbidity(level,f_turbidity);
                if(count>0)
                {
                    turbidityYesSize++;
                }
                else
                {
                    turbidityNoSize++;
                }
            }
            //处理电导率值
            String conductivity=list.get(i).getConductivity();
            if(StringUtils.isNotBlank(conductivity))
            {
                Float f_conductivity=Float.valueOf(conductivity);
                Integer level=map.get(deviceNo);
                int count=rwCountConductivity(level,f_conductivity);
                if(count>0)
                {
                    conductivityYesSize++;
                }
                else
                {
                    conductivityNoSize++;
                }
            }
        }
        //ph
        StandardRateRes phRes = standardRate(phYesSize, phNoSize);
        if(phYesSize+phNoSize>0)
        {
            StandardRwRes res=new StandardRwRes();
            res.setYes(phRes.getYesRate());
            res.setNo(phRes.getNoRate());
            res.setName("ph");
            result.add(res);
        }
        //溶解氧
        StandardRateRes oxygenRes = standardRate(oxygenYesSize, oxygenNoSize);
        if(oxygenYesSize+oxygenNoSize>0)
        {
            StandardRwRes res=new StandardRwRes();
            res.setYes(oxygenRes.getYesRate());
            res.setNo(oxygenRes.getNoRate());
            res.setName("溶解氧");
            result.add(res);
        }
        //浊度
        StandardRateRes turbidityRes = standardRate(turbidityYesSize, turbidityNoSize);
        if(turbidityYesSize+turbidityNoSize>0)
        {
            StandardRwRes res=new StandardRwRes();
            res.setYes(turbidityRes.getYesRate());
            res.setNo(turbidityRes.getNoRate());
            res.setName("浊度");
            result.add(res);
        }
        //电导率
        StandardRateRes conductivityRes = standardRate(conductivityYesSize, conductivityNoSize);
        if(conductivityYesSize+conductivityNoSize>0)
        {
            StandardRwRes res=new StandardRwRes();
            res.setYes(conductivityRes.getYesRate());
            res.setNo(conductivityRes.getNoRate());
            res.setName("电导率");
            result.add(res);
        }
        return JSONResult.ok(result);
    }
    /**
     * 水箱用水用量变化图
     * */
    public JSONResult cwWaterCount(RedisUser redisUser,AreaV2Req areaV2Req)
    {
        List<CwWaterRes>result=new ArrayList<>();
        List<DeviceRoleDeviceRea> cw = new ArrayList<>();
        Long provinceId = null;
        Long cityId = null;
        if (areaV2Req.getLevel() == 1) {
            provinceId = areaV2Req.getParentId();
        }
        if (areaV2Req.getLevel() == 2) {
            cityId = areaV2Req.getParentId();
        }
        Integer cwSize=0;
        if (redisUser.getGroupId() == 1) {
            JSONResult jsonResult = homeFeignV2.totalSizeV2(areaV2Req);
            TotalSizeRes totalSizeRes = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<TotalSizeRes>() {
            });
            cwSize=totalSizeRes.getCw();
        } else {
            DeviceUserRoleV2Req req = new DeviceUserRoleV2Req();
            req.setUserId(redisUser.getId());
            req.setLevel(areaV2Req.getLevel());
            req.setParentId(areaV2Req.getParentId());
            //第三方
            JSONResult jsonResult = homeFeignV2.deviceRoleByUserIdServiceV2(req);
            DeviceRoleRea deviceRoleRea = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<DeviceRoleRea>() {
            });
            if (deviceRoleRea != null) {
                cw = deviceRoleRea.getCw();
                if(cw.size()>0)
                {
                    cwSize=cw.size();
                }
            }
        }
        //判断当前时间
        String now=DateUtils.dateToString(new Date());
        Integer count=0;
        String str=now.substring(11,13);
        if("0".equals(str.substring(0,1)))
        {
            count=Integer.valueOf(str.substring(1,2))/4;
        }
        else
        {
            count=Integer.valueOf(str)/4;
        }
        for (int i=0;i<=count;i++)
        {
            if(i==0)
            {
                CwWaterRes res=new CwWaterRes();
                res.setTime("00:00");
                res.setSize(4*cwSize);
                result.add(res);
            }
            if(i==1)
            {
                CwWaterRes res=new CwWaterRes();
                res.setTime("04:00");
                res.setSize(2*cwSize);
                result.add(res);
            }
            if(i==2)
            {
                CwWaterRes res=new CwWaterRes();
                res.setTime("08:00");
                res.setSize(7*cwSize);
                result.add(res);
            }
            if(i==3)
            {
                CwWaterRes res=new CwWaterRes();
                res.setTime("12:00");
                res.setSize(6*cwSize);
                result.add(res);
            }
            if(i==4)
            {
                CwWaterRes res=new CwWaterRes();
                res.setTime("16:00");
                res.setSize(8*cwSize);
                result.add(res);
            }
            if(i==5)
            {
                CwWaterRes res=new CwWaterRes();
                res.setTime("20:00");
                res.setSize(11*cwSize);
                result.add(res);
            }
        }
        return JSONResult.ok(result);
    }
    //统计地表水的ph超标与否(1达标 -1超标)
    private static Integer rwCountPh(Float ph)
    {
        if(ph>6&&ph<9)
        {
            return 2;
        }
        else
        {
            return -1;
        }
    }
    //统计地表水的溶解氧超标与否(1达标 2超标)
    private static Integer rwCountOxygen(Integer level,Float oxygen)
    {
        if(level==null)
        {
            return 0;
        }
        if(level==1)
        {
            if(oxygen>=7.5)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        if(level==2)
        {
            if(oxygen>=6)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        if(level==3)
        {
            if(oxygen>=5)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        if(level==4)
        {
            if(oxygen>=3)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        if(level==5)
        {
            if(oxygen>=2)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        return 0;
    }
    //统计地表水的浊度超标与否(1达标 2超标)
    private static Integer rwCountTurbidity(Integer level,Float turbidity)
    {
        if(level==null)
        {
            return 0;
        }
        if(level<=3)
        {
            if(turbidity<=3)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else
        {
            if(turbidity<=10)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
    }
    //统计地表水的电导率超标与否(1达标 2超标)
    private static Integer rwCountConductivity(Integer level,Float conductivity)
    {
        if(level==null)
        {
            return 0;
        }
        if(level<=3)
        {
            if(conductivity<=2000)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else
        {
            if(conductivity>2000)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
    }
    //井盖场景使用类型占比统计（数量未知）
    private static List<MhTypeRes>countType(List<MhTypeResult>list)
    {
        List<MhTypeRes>result=new ArrayList<>();
        int total=0;
        for (int i=0;i<list.size();i++)
        {
            total+=list.get(i).getNum();
        }
        if(total==0)
        {
            return result;
        }
        else
        {
            BigDecimal ff=new BigDecimal("1");
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            BigDecimal tt=new BigDecimal(total);
            for (int i=0;i<list.size();i++)
            {
                if(list.size()>1)
                {
                    if(i==list.size()-1)
                    {
                        MhTypeRes res=new MhTypeRes();
                        res.setType(list.get(i).getType());
                        res.setRate(nf.format(ff));
                        result.add(res);
                        break;
                    }
                }
                MhTypeRes res=new MhTypeRes();
                BigDecimal aa=new BigDecimal(list.get(i).getNum());
                BigDecimal bb = aa.divide(tt, 4, RoundingMode.DOWN);
                res.setType(list.get(i).getType());
                res.setRate(nf.format(bb));
                result.add(res);
                ff = ff.subtract(bb);
            }
        }
        return result;
    }
    //井盖场景直径长度占比统计（数量未知）
    private static List<MhDiameterRes>countDiameter(List<MhDiameterResult>list)
    {
        List<MhDiameterRes>result=new ArrayList<>();
        int total=0;
        for (int i=0;i<list.size();i++)
        {
            total+=list.get(i).getNum();
        }
        if(total==0)
        {
            return result;
        }
        else
        {
            BigDecimal ff=new BigDecimal("1");
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            BigDecimal tt=new BigDecimal(total);
            for (int i=0;i<list.size();i++)
            {
                if(list.size()>1)
                {
                    if(i==list.size()-1)
                    {
                        MhDiameterRes res=new MhDiameterRes();
                        res.setDiameter(list.get(i).getDiameter()+"cm");
                        res.setRate(nf.format(ff));
                        result.add(res);
                        break;
                    }
                }
                MhDiameterRes res=new MhDiameterRes();
                BigDecimal aa=new BigDecimal(list.get(i).getNum());
                BigDecimal bb = aa.divide(tt, 4, RoundingMode.DOWN);
                res.setDiameter(list.get(i).getDiameter()+"cm");
                res.setRate(nf.format(bb));
                result.add(res);
                ff = ff.subtract(bb);
            }
        }
        return result;
    }
    //井盖场景材质数量统计（数量未知）
    private static List<MhMaterialRes>countMaterial(List<MhMaterialResult>list)
    {
        List<MhMaterialRes>result=new ArrayList<>();
        int total=0;
        for (int i=0;i<list.size();i++)
        {
            total+=list.get(i).getNum();
        }
        if(total==0)
        {
            return result;
        }
        else
        {
            BigDecimal ff=new BigDecimal("1");
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            BigDecimal tt=new BigDecimal(total);
            for (int i=0;i<list.size();i++)
            {
                if(list.size()>1)
                {
                    if(i==list.size()-1)
                    {
                        MhMaterialRes res=new MhMaterialRes();
                        res.setMaterial(list.get(i).getMaterial());
                        res.setRate(nf.format(ff));
                        result.add(res);
                        break;
                    }
                }
                MhMaterialRes res=new MhMaterialRes();
                BigDecimal aa=new BigDecimal(list.get(i).getNum());
                BigDecimal bb = aa.divide(tt, 4, RoundingMode.DOWN);
                res.setMaterial(list.get(i).getMaterial());
                res.setRate(nf.format(bb));
                result.add(res);
                ff = ff.subtract(bb);
            }
        }
        return result;
    }
    /**
     * 计算饮用水故障分布
     */
    private static List<FaultTypeRes> countFaultCw(int fault1, int fault2,int fault3) {
        List<FaultTypeRes> list = new ArrayList<>();
        int total = fault1 + fault2+fault3;
        if (total == 0) {
            FaultTypeRes res1 = new FaultTypeRes();
            res1.setFaultName("水箱开盖");
            res1.setFaultRate("0.00%");
            list.add(res1);
            FaultTypeRes res2 = new FaultTypeRes();
            res2.setFaultName("水位");
            res2.setFaultRate("0.00%");
            list.add(res2);
            FaultTypeRes res3 = new FaultTypeRes();
            res3.setFaultName("水质超标");
            res3.setFaultRate("0.00%");
            list.add(res3);
        } else {
            BigDecimal f33 = null;
            BigDecimal a = new BigDecimal(total);
            BigDecimal f1 = new BigDecimal(fault1);
            BigDecimal f2 = new BigDecimal(fault2);
            BigDecimal f11 = f1.divide(a, 4, RoundingMode.DOWN);
            BigDecimal f22 = f2.divide(a, 4, RoundingMode.DOWN);
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            f33 = new BigDecimal("1").subtract(f11);
            f33=f33.subtract(f22);
            FaultTypeRes res1 = new FaultTypeRes();
            res1.setFaultName("水箱开盖");
            res1.setFaultRate(nf.format(f11));
            list.add(res1);
            FaultTypeRes res2 = new FaultTypeRes();
            res2.setFaultName("水位");
            res2.setFaultRate(nf.format(f22));
            list.add(res2);
            FaultTypeRes res3 = new FaultTypeRes();
            res3.setFaultName("水质超标");
            res3.setFaultRate(nf.format(f33));
            list.add(res3);
        }
        return list;
    }
    /**
     * 计算地表水故障分布
     */
    private static List<FaultTypeRes> countFaultRw(int fault1, int fault2,int fault3) {
        List<FaultTypeRes> list = new ArrayList<>();
        int total = fault1 + fault2+fault3;
        if (total == 0) {
            FaultTypeRes res1 = new FaultTypeRes();
            res1.setFaultName("高水位");
            res1.setFaultRate("0.00%");
            list.add(res1);
            FaultTypeRes res2 = new FaultTypeRes();
            res2.setFaultName("低水位");
            res2.setFaultRate("0.00%");
            list.add(res2);
            FaultTypeRes res3 = new FaultTypeRes();
            res3.setFaultName("水质");
            res3.setFaultRate("0.00%");
            list.add(res3);
        } else {
            BigDecimal f33 = null;
            BigDecimal a = new BigDecimal(total);
            BigDecimal f1 = new BigDecimal(fault1);
            BigDecimal f2 = new BigDecimal(fault2);
            BigDecimal f11 = f1.divide(a, 4, RoundingMode.DOWN);
            BigDecimal f22 = f2.divide(a, 4, RoundingMode.DOWN);
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            f33 = new BigDecimal("1").subtract(f11);
            f33=f33.subtract(f22);
            FaultTypeRes res1 = new FaultTypeRes();
            res1.setFaultName("高水位");
            res1.setFaultRate(nf.format(f11));
            list.add(res1);
            FaultTypeRes res2 = new FaultTypeRes();
            res2.setFaultName("低水位");
            res2.setFaultRate(nf.format(f22));
            list.add(res2);
            FaultTypeRes res3 = new FaultTypeRes();
            res3.setFaultName("水质");
            res3.setFaultRate(nf.format(f33));
            list.add(res3);
        }
        return list;
    }
    /**
     * 计算井盖故障分布
     */
    private static List<FaultTypeRes> countFaultMh(int fault1, int fault2, int fault3) {
        List<FaultTypeRes> list = new ArrayList<>();
        int total = fault1 + fault2 + fault3;
        if (total == 0) {
            FaultTypeRes res1 = new FaultTypeRes();
            res1.setFaultName("开盖");
            res1.setFaultRate("0.00%");
            list.add(res1);
            FaultTypeRes res2 = new FaultTypeRes();
            res2.setFaultName("水位");
            res2.setFaultRate("0.00%");
            list.add(res2);
            FaultTypeRes res3 = new FaultTypeRes();
            res3.setFaultName("气体");
            res3.setFaultRate("0.00%");
            list.add(res3);
        } else {
            BigDecimal f33 = null;
            BigDecimal a = new BigDecimal(total);
            BigDecimal f1 = new BigDecimal(fault1);
            BigDecimal f2 = new BigDecimal(fault2);
            BigDecimal f11 = f1.divide(a, 4, RoundingMode.DOWN);
            BigDecimal f22 = f2.divide(a, 4, RoundingMode.DOWN);
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            f33 = new BigDecimal("1").subtract(f11).subtract(f22);
            FaultTypeRes res1 = new FaultTypeRes();
            res1.setFaultName("开盖");
            res1.setFaultRate(nf.format(f11));
            list.add(res1);
            FaultTypeRes res2 = new FaultTypeRes();
            res2.setFaultName("水位");
            res2.setFaultRate(nf.format(f22));
            list.add(res2);
            FaultTypeRes res3 = new FaultTypeRes();
            res3.setFaultName("气体");
            res3.setFaultRate(nf.format(f33));
            list.add(res3);
        }
        return list;
    }
    /**
     * 计算电梯故障分布
     */
    private static List<FaultTypeRes> countFaultEc(int fault1, int fault2, int fault3, int fault4, int fault5, int fault6) {
        List<FaultTypeRes> list = new ArrayList<>();
        int total = fault1 + fault2 + fault3 + fault4 + fault5 + fault6;
        if (total == 0) {
            FaultTypeRes res1 = new FaultTypeRes();
            res1.setFaultName("冲顶");
            res1.setFaultRate("0%");
            list.add(res1);
            FaultTypeRes res2 = new FaultTypeRes();
            res2.setFaultName("蹲底");
            res2.setFaultRate("0%");
            list.add(res2);
            FaultTypeRes res3 = new FaultTypeRes();
            res3.setFaultName("电梯速度异常");
            res3.setFaultRate("0%");
            list.add(res3);
            FaultTypeRes res4 = new FaultTypeRes();
            res4.setFaultName("非门区停车");
            res4.setFaultRate("0%");
            list.add(res4);
            FaultTypeRes res5 = new FaultTypeRes();
            res5.setFaultName("平层故障");
            res5.setFaultRate("0%");
            list.add(res5);
            FaultTypeRes res6 = new FaultTypeRes();
            res6.setFaultName("非门区开门");
            res6.setFaultRate("0%");
            list.add(res6);
        } else {
            BigDecimal f66 = null;
            BigDecimal a = new BigDecimal(total);
            BigDecimal f1 = new BigDecimal(fault1);
            BigDecimal f2 = new BigDecimal(fault2);
            BigDecimal f3 = new BigDecimal(fault3);
            BigDecimal f4 = new BigDecimal(fault4);
            BigDecimal f5 = new BigDecimal(fault5);
            BigDecimal f11 = f1.divide(a, 4, RoundingMode.DOWN);
            BigDecimal f22 = f2.divide(a, 4, RoundingMode.DOWN);
            BigDecimal f33 = f3.divide(a, 4, RoundingMode.DOWN);
            BigDecimal f44 = f4.divide(a, 4, RoundingMode.DOWN);
            BigDecimal f55 = f5.divide(a, 4, RoundingMode.DOWN);
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            f66 = new BigDecimal("1").subtract(f11).subtract(f22).subtract(f33).subtract(f44).subtract(f55);
            FaultTypeRes res1 = new FaultTypeRes();
            res1.setFaultName("冲顶");
            res1.setFaultRate(nf.format(f11));
            list.add(res1);
            FaultTypeRes res2 = new FaultTypeRes();
            res2.setFaultName("蹲底");
            res2.setFaultRate(nf.format(f22));
            list.add(res2);
            FaultTypeRes res3 = new FaultTypeRes();
            res3.setFaultName("电梯速度异常");
            res3.setFaultRate(nf.format(f33));
            list.add(res3);
            FaultTypeRes res4 = new FaultTypeRes();
            res4.setFaultName("非门区停车");
            res4.setFaultRate(nf.format(f44));
            list.add(res4);
            FaultTypeRes res5 = new FaultTypeRes();
            res5.setFaultName("平层故障");
            res5.setFaultRate(nf.format(f55));
            list.add(res5);
            FaultTypeRes res6 = new FaultTypeRes();
            res6.setFaultName("非门区开门");
            res6.setFaultRate(nf.format(f66));
            list.add(res6);
        }
        return list;
    }

    /**
     * 计算标准比例
     */
    private static StandardRateRes standardRate(int yes, int no) {
        StandardRateRes result = new StandardRateRes();
        int total = yes + no;
        if (total == 0) {
            result.setYesRate("0%");
            result.setNoRate("0%");
        } else {
            BigDecimal min = null;
            BigDecimal a = new BigDecimal(total);
            BigDecimal b = new BigDecimal(yes);
            BigDecimal c = new BigDecimal(no);
            BigDecimal max = b.divide(a, 4, RoundingMode.DOWN);
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2); //最小小数位数
            min = new BigDecimal("1").subtract(max);
            result.setYesRate(nf.format(max));
            result.setNoRate(nf.format(min));
        }
        return result;
    }


}
