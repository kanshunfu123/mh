package com.xiaowei.mh.service.mhdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.mhdata.MhHistoryPageReq;
import com.xiaowei.mh.common.req.mhdata.MhInfoReq;
import com.xiaowei.mh.common.req.mhdata.MhOnlineRateReq;
import com.xiaowei.mh.common.req.mhdata.ParamCountReq;
import com.xiaowei.mh.common.res.mhdata.*;
import com.xiaowei.mh.common.util.DateUtils;
import com.xiaowei.mh.common.util.RedisKeyEnum;
import com.xiaowei.mh.common.util.RedisUtil;
import com.xiaowei.mh.mapper.dataobject.MhDataLogDO;
import com.xiaowei.mh.mapper.dataobject.XwEquipmentMhDO;
import com.xiaowei.mh.mapper.dataobject.XwMhSenceDO;
import com.xiaowei.mh.mapper.mapper.EquipmentMhMapper;
import com.xiaowei.mh.mapper.mapper.MhDataLogMapper;
import com.xiaowei.mh.mapper.mapper.MhSenceMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 韩金群
 * CreateTime 2019/1/12
 */
@Service
@Slf4j
public class MhDataLogService {
    public static final Integer DEVICE_TIME_OUT_MH = 7 * 60 * 60;
    @Autowired
    private MhDataLogMapper mhDataLogMapper;
    @Autowired
    private EquipmentMhMapper equipmentMhMapper;
    @Autowired
    private MhSenceMapper mhSenceMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 井盖历史数据分页
     */
    public JSONResult mhHistoryPage(MhHistoryPageReq req) {
        if (StringUtils.isNotBlank(req.getStartTime()) == false) {
            req.setStartTime(null);
            req.setEndTime(null);
        }
        if (StringUtils.isNotBlank(req.getEndTime()) == false) {
            req.setStartTime(null);
            req.setEndTime(null);
        }
        if (req.getStartTime() != null && req.getEndTime() != null) {
            if (req.getStartTime().equals(req.getEndTime())) {
                req.setStartTime(null);
                req.setEndTime(null);
            }
        }
        XwEquipmentMhDO mhDO = null;
        //token获取设备信息
        Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + req.getDeviceNo());
        if (o != null) {
            mhDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentMhDO>() {
            });
        }
        PageHelper.startPage(req.getCurrPageNo(), req.getLimit());
        List<MhDataLogDO> list = mhDataLogMapper.mhHistoryPage(req.getDeviceNo(), req.getStartTime(), req.getEndTime());
        PageInfo<MhDataLogDO> pageInfo1 = new PageInfo<>(list);
        //分页后处理
        List<MhDataLogRes> result = new ArrayList<>();
        List<MhDataLogDO> mhDataLogDOS = pageInfo1.getList();
        int size = mhDataLogDOS.size();
        for (int i = 0; i < mhDataLogDOS.size(); i++) {
            MhDataLogRes res = new MhDataLogRes();
            BeanUtils.copyProperties(mhDataLogDOS.get(i), res);
            //信号强度
            if (StringUtils.isNotBlank(mhDataLogDOS.get(i).getNetworkSignal())) {
                res.setSignal(mhDataLogDOS.get(i).getNetworkSignal() + "dBm");
            }
            if (mhDataLogDOS.get(i).getSignalRsrp() != null) {
                res.setSignal(mhDataLogDOS.get(i).getSignalRsrp() + "dBm");
            }
            //电量
            if (mhDataLogDOS.get(i).getPowerMh() != null) {
                res.setPowerPct(mhDataLogDOS.get(i).getPowerMh() + "%");
            } else {
                res.setPowerPct("无数据");
            }
            //温度
            if (mhDataLogDOS.get(i).getTemperature() != null) {
                res.setTemperature(mhDataLogDOS.get(i).getTemperature() + "℃");
            } else {
                res.setTemperature("无数据");
            }
            //开关闭合状态
            if (mhDataLogDOS.get(i).getWellOpenSts() != null) {
                if (mhDataLogDOS.get(i).getWellOpenSts() == 0) {
                    res.setWellOpenSts("闭合");
                } else {
                    res.setWellOpenSts("开启");
                }
            } else {
                res.setWellOpenSts("无数据");
            }
            //根据信息处理
            if (mhDO != null) {
                //水位
                if (mhDO.getHaveWaterLine() == 1) {
                    if (mhDataLogDOS.get(i).getWaterLine() != null) {
                        res.setHaveWaterLine(1);
                        if (mhDataLogDOS.get(i).getWaterLine() == 0) {
                            res.setWaterLine("正常");
                        } else if (mhDataLogDOS.get(i).getWaterLine() == 1) {
                            res.setWaterLine("一级报警");
                        } else {
                            res.setWaterLine("二级报警");
                        }
                    } else {
                        res.setWaterLine("无数据");
                    }

                } else {
                    res.setHaveWaterLine(0);
                    res.setWaterLine("");
                }
                //可燃气
                if (mhDO.getHaveGas() == 1) {
                    res.setHaveGas(1);
                    if (StringUtils.isNotBlank(mhDataLogDOS.get(i).getFireGasCon())) {
                        res.setFireGasCon(mhDataLogDOS.get(i).getFireGasCon() + "%LEL");
                    } else {
                        res.setFireGasCon("无数据");
                    }
                } else {
                    res.setHaveGas(0);
                    res.setFireGasCon("");
                }
                //硫化氢
                if (mhDO.getHaveH2s() == 1) {
                    res.setHaveH2s(1);
                    if (mhDataLogDOS.get(i).getGasH2s() != null) {
                        res.setGasH2s(mhDataLogDOS.get(i).getGasH2s() + "ppm");
                    } else {
                        res.setGasH2s("无数据");
                    }

                } else {
                    res.setHaveH2s(0);
                    res.setGasH2s("");
                }
            } else {
                //设备信息在redis中不存在的情况
                res.setHaveGas(0);
                res.setFireGasCon("");
                res.setHaveGas(0);
                res.setFireGasCon("");
                res.setHaveH2s(0);
                res.setGasH2s("");
            }

            res.setCollectTime(DateUtils.dateToString(mhDataLogDOS.get(i).getCollectTime()));
            if (i < size - 1) {
                MhDataLogDO first = mhDataLogDOS.get(i);
                MhDataLogDO next = mhDataLogDOS.get(i + 1);
                res.setMistiming(DateUtils.millToaTime(first.getCollectTime().getTime(), next.getCollectTime().getTime()));
            } else if (i == size - 1) {
                MhDataLogDO first = mhDataLogDOS.get(i);
                //判断最后一条
                int fetchNum = req.getLimit() * req.getCurrPageNo();
                MhDataLogDO cwNext = mhDataLogMapper.mhNext(req.getDeviceNo(), fetchNum, req.getStartTime(), req.getEndTime());
                if (cwNext != null) {
                    res.setMistiming(DateUtils.millToaTime(first.getCollectTime().getTime(), cwNext.getCollectTime().getTime()));
                }
            }
            result.add(res);
        }
        PageInfo<MhDataLogRes> pageInfo = new PageInfo<>(result);
        pageInfo.setPageNum(req.getCurrPageNo());
        pageInfo.setTotal(pageInfo1.getTotal());
        return JSONResult.ok(pageInfo);
    }

    /**
     * 井盖的详情
     */
    public JSONResult mhInfo(MhInfoReq req) {
        MhInfoRes result = new MhInfoRes();
        MhEquipmentRes equipment = new MhEquipmentRes();
        MhSenceRes sence = new MhSenceRes();
        MhLatestRes latest = new MhLatestRes();
        XwEquipmentMhDO mhDO = null;
        XwMhSenceDO xwMhSenceDO=null;
        //token获取设备信息
        Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + req.getDeviceNo());
        if (o != null) {
            mhDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentMhDO>() {
            });
            //设备新消息
            if (mhDO != null) {
                BeanUtils.copyProperties(mhDO, equipment);
                if (mhDO.getSetupTime() != null) {
                    equipment.setSetupTime(DateUtils.dateToString(mhDO.getSetupTime()));
                } else {
                    equipment.setSetupTime("");
                }
                if (mhDO.getProductTime() != null) {
                    equipment.setProductTime(DateUtils.dateToString(mhDO.getProductTime()));
                } else {
                    equipment.setProductTime("");
                }
                //场景信息
                Object omh = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_SENCE.getKey() + req.getDeviceNo());
                if(omh!=null)
                {
                    xwMhSenceDO=JSON.parseObject(omh + "", new TypeReference<XwMhSenceDO>() {
                    });

                    BeanUtils.copyProperties(xwMhSenceDO, sence);
                    if (StringUtils.isNotBlank(mhDO.getAddress())) {
                        sence.setLocation(mhDO.getProvince() + mhDO.getCity() + mhDO.getArea() + mhDO.getAddress());
                    } else {
                        sence.setLocation(mhDO.getProvince() + mhDO.getCity() + mhDO.getArea());
                    }
                    if (xwMhSenceDO.getSenceInstallTime() != null) {
                        sence.setSenceInstallTime(DateUtils.dateToString(xwMhSenceDO.getSenceInstallTime()));
                    } else {
                        sence.setSenceInstallTime("");
                    }
                    if (StringUtils.isNotBlank(xwMhSenceDO.getMhSize())) {
                        sence.setMhSize(xwMhSenceDO.getMhSize() + "MM");
                    } else {
                        sence.setMhSize("暂无数据");
                    }
                    if (StringUtils.isNotBlank(xwMhSenceDO.getMhLoad())) {
                        sence.setMhLoad(xwMhSenceDO.getMhLoad() + "KN");
                    } else {
                        sence.setMhLoad("暂无数据");
                    }
                }
                //最新数据
                MhDataLogDO ecDataLogDO = mhDataLogMapper.getLatestLogByNo(req.getDeviceNo());
                if (ecDataLogDO != null) {
                    BeanUtils.copyProperties(ecDataLogDO, latest);
                    //判断状态
                    Integer status = mhDataLogMapper.getMhStatusByNo(req.getDeviceNo(), DEVICE_TIME_OUT_MH);
                    String faultInfo = ecDataLogDO.getFaultInfo();
                    if (StringUtils.isNotBlank(faultInfo)) {
                        if (Integer.valueOf(faultInfo) > 0) {
                            latest.setState("故障");
                        } else {
                            if (status != null) {
                                if (status > 0) {
                                    latest.setState("在线");
                                } else {
                                    latest.setState("离线");
                                }
                            } else {
                                latest.setState("离线");
                            }
                        }
                    }
                    //信号强度
                    if (StringUtils.isNotBlank(ecDataLogDO.getNetworkSignal())) {
                        latest.setSignal(ecDataLogDO.getNetworkSignal().substring(1, ecDataLogDO.getNetworkSignal().length()));
                    }
                    if (ecDataLogDO.getSignalRsrp() != null) {
                        String signal = ecDataLogDO.getSignalRsrp() + "";
                        latest.setSignal(signal.substring(1, signal.length()));
                    }
                    //电量
                    if (ecDataLogDO.getPowerMh() != null) {
                        latest.setPowerPct(ecDataLogDO.getPowerMh() + "");
                    } else {
                        latest.setPowerPct("无数据");
                    }
                    //温度
                    if (ecDataLogDO.getTemperature() != null) {
                        latest.setTemperature(ecDataLogDO.getTemperature() + "℃");
                    } else {
                        latest.setTemperature("无数据");
                    }
                    //开关闭合状态
                    if (ecDataLogDO.getWellOpenSts() != null) {
                        if (ecDataLogDO.getWellOpenSts() == 0) {
                            latest.setWellOpenSts("闭合");
                        } else {
                            latest.setWellOpenSts("开启");
                        }
                    } else {
                        latest.setWellOpenSts("无数据");
                    }
                    //水位
                    if (mhDO.getHaveWaterLine() == 1) {
                        latest.setHaveWaterLine(1);
                        if (ecDataLogDO.getWaterLine() != null) {
                            if (ecDataLogDO.getWaterLine() == 0) {
                                latest.setWaterLine("正常");
                            } else if (ecDataLogDO.getWaterLine() == 1) {
                                latest.setWaterLine("一级报警");
                            } else {
                                latest.setWaterLine("二级报警");
                            }
                        } else {
                            latest.setWaterLine("无数据");
                        }

                    } else {
                        latest.setHaveWaterLine(0);
                        latest.setWaterLine("");
                    }
                    //可燃气
                    if (mhDO.getHaveGas() == 1) {
                        latest.setHaveGas(1);
                        if (StringUtils.isNotBlank(ecDataLogDO.getFireGasCon())) {
                            latest.setFireGasCon(ecDataLogDO.getFireGasCon() + "%LEL");
                        } else {
                            latest.setFireGasCon("无数据");
                        }

                    } else {
                        latest.setHaveGas(0);
                        latest.setFireGasCon("");
                    }
                    //硫化氢
                    if (mhDO.getHaveH2s() == 1) {
                        latest.setHaveH2s(1);
                        if (ecDataLogDO.getGasH2s() != null) {
                            latest.setGasH2s(ecDataLogDO.getGasH2s() + "ppm");
                        } else {
                            latest.setGasH2s("无数据");
                        }

                    } else {
                        latest.setHaveH2s(0);
                        latest.setGasH2s("");
                    }
                    latest.setCollectTime(DateUtils.dateToString(ecDataLogDO.getCollectTime()));
                    latest.setHumidity("15%");
                    latest.setPowerType("市电");
                }
            }

        }

        result.setEquipment(equipment);
        result.setSence(sence);
        result.setLatest(latest);
        return JSONResult.ok(result);
    }

    /**
     * 设备在线率
     */
    public JSONResult mhOnlineRate(MhOnlineRateReq req) {
        MhOnlineRateRes result = new MhOnlineRateRes();
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.dateToString(new Date());
        List<Date> list = mhDataLogMapper.onlineRate(req.getDeviceNo(), beginTime, endTime);
        result = onlineRate(list);
        return JSONResult.ok(result);
    }

    /**
     * 井下基本参数统计
     */
    public JSONResult paramCount(ParamCountReq req) {
        MhBasicCountRes result = new MhBasicCountRes();
        String beginTime = DateUtils.getYesterday();
        String endTime = DateUtils.getTomorrow();
        Date yesBegin = DateUtils.dayAdd(DateUtils.getStrToDate(beginTime), -1);
        String yesBeginTime = DateUtils.dateToString(yesBegin);
        Date yesEnd = DateUtils.dayAdd(DateUtils.getStrToDate(endTime), -1);
        String yesEndTime = DateUtils.dateToString(yesEnd);
        List<MhParamRes> paramList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        List<String> dataListGas = new ArrayList<>();
        List<String> dataListH2s = new ArrayList<>();
        XwEquipmentMhDO mhDO = null;
        Object o = redisUtil.get(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + req.getDeviceNo());
        if (o != null) {
            mhDO = JSON.parseObject(o + "", new TypeReference<XwEquipmentMhDO>() {
            });
        }

        //昨日数据
        MhDataLogDO yes = mhDataLogMapper.mhGasYes(req.getDeviceNo(), yesBeginTime, yesEndTime);
        if (yes != null) {
            dateList.add(DateUtils.dateToString(yes.getCollectTime()));
            //参数统计
            dataListGas.add(yes.getFireGasCon());
            dataListH2s.add(yes.getGasH2s() + "");
        }
        //当日数据
        List<MhDataLogDO> mhDataLogDOS = mhDataLogMapper.mhGas(req.getDeviceNo(), beginTime, endTime);
        if (!CollectionUtils.isEmpty(mhDataLogDOS)) {
            mhDataLogDOS.forEach(mhDataLogDO -> {
                dateList.add(DateUtils.dateToString(mhDataLogDO.getCollectTime()));
                //参数统计
                dataListGas.add(mhDataLogDO.getFireGasCon());
                dataListH2s.add(mhDataLogDO.getGasH2s() + "");
            });
        }
        //可燃气
        MhParamRes paramGas = new MhParamRes();
        paramGas.setName("可燃气");
        paramGas.setUnit("mg/L");
        paramGas.setDataList(dataListGas);
        //硫化氢
        MhParamRes paramH2s = new MhParamRes();
        paramH2s.setName("硫化氢");
        paramH2s.setUnit("ppm");
        paramH2s.setDataList(dataListH2s);
        result.setDateList(dateList);
        if (mhDO != null) {
            if (mhDO.getHaveH2s() == 1) {
                paramList.add(paramH2s);
            }
            if (mhDO.getHaveGas() == 1) {
                paramList.add(paramGas);
            }
        }
        result.setParamList(paramList);
        return JSONResult.ok(result);
    }

    //在线率计算
    private MhOnlineRateRes onlineRate(List<Date> list) {
        MhOnlineRateRes mhOnlineRateRes = new MhOnlineRateRes();
        if (CollectionUtils.isEmpty(list)) {
            mhOnlineRateRes.setOnline("0.00%");
            mhOnlineRateRes.setOffline("100%");
            return mhOnlineRateRes;
        } else {
            int size = list.size();
            Date date = DateUtils.getStrToDate(DateUtils.formatDate2YYYYMMDD() + " 00:00:00");
            if (size == 1) {
                long dateDiff = DateUtils.getDatePoor(list.get(0), date);
                if (dateDiff > DEVICE_TIME_OUT_MH) {
                    mhOnlineRateRes.setOffline("100%");
                    mhOnlineRateRes.setOnline("0%");
                } else {
                    mhOnlineRateRes.setOffline("0%");
                    mhOnlineRateRes.setOnline("100%");
                }
                return mhOnlineRateRes;
            } else {
                //在线次数
                int count = 0;
                int count1 = 0;
                for (int i = 0; i < size; i++) {
                    if (i == 0) {
                        long dateDiff = DateUtils.getDatePoor(list.get(0), date);
                        if (dateDiff > DEVICE_TIME_OUT_MH) {
                            count1++;
                        } else {
                            count++;
                        }
                    } else if ((size - 1) == i) {
                        long dateDiff = DateUtils.getDatePoor(new Date(), list.get(i));
                        if (dateDiff > DEVICE_TIME_OUT_MH) {
                            count1++;
                        } else {
                            count++;
                        }
                    } else {
                        Date before = list.get(i - 1);
                        Date after = list.get(i);
                        long dateDiff = DateUtils.getDatePoor(after, before);
                        if (dateDiff > DEVICE_TIME_OUT_MH) {
                            count1++;
                        } else {
                            count++;
                        }
                    }
                }
                BigDecimal a;
                BigDecimal b;
                BigDecimal bignum3 = null;
                a = new BigDecimal(count1);
                b = new BigDecimal(size);
                BigDecimal on = a.divide(b, 4, RoundingMode.DOWN);
                NumberFormat nf = NumberFormat.getPercentInstance();
                nf.setMinimumFractionDigits(2); //最小小数位数
                bignum3 = new BigDecimal("1").subtract(on);
                mhOnlineRateRes.setOffline(nf.format(on));
                mhOnlineRateRes.setOnline(nf.format(bignum3));
                return mhOnlineRateRes;
            }
        }
    }
}
