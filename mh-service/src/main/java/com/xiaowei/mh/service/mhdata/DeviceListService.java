package com.xiaowei.mh.service.mhdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xiaowei.feign.client.home.DeviceListFeign;
import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.fegin.AreaToDeviceReq;
import com.xiaowei.mh.common.req.fegin.AreaToDeviceReq_v1;
import com.xiaowei.mh.common.req.home.DeviceListReq;
import com.xiaowei.mh.common.res.devicelist.DeviceListRes;
import com.xiaowei.mh.common.res.redis.RedisUser;
import com.xiaowei.mh.mapper.dataobject.CwDataLogDO;
import com.xiaowei.mh.mapper.dataobject.EcDataLogDO;
import com.xiaowei.mh.mapper.dataobject.MhDataLogDO;
import com.xiaowei.mh.mapper.dataobject.RwDataLogDO;
import com.xiaowei.mh.mapper.home.AreatodeviceRes;
import com.xiaowei.mh.mapper.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 韩金群
 * CreateTime 2019/1/16
 */
@Service
@Slf4j
public class DeviceListService {
    private static Integer EC_OFF_LINE_TIME = 60 * 60 * 1000;
    private static Integer MH_OFF_LINE_TIME = 7 * 60 * 60 * 1000;
    private static Integer RW_OFF_LINE_TIME = (6 * 60 * 60 + 30 * 60) * 1000;
    private static Integer CW_OFF_LINE_TIME = 30 * 60 * 1000;
    @Autowired
    private EcDataLogMapper ecDataLogMapper;
    @Autowired
    private MhDataLogMapper mhDataLogMapper;
    @Autowired
    private RwDataLogMapper rwDataLogMapper;
    @Autowired
    private CwDataLogMapper cwDataLogMapper;
    @Autowired
    private DeviceListFeign deviceListFeign;

    /**
     * 电梯设备列表（权限）
     */
    public JSONResult ecList(DeviceListReq req, RedisUser redisUser) {
        List<DeviceListRes> result = new ArrayList<>();
        //获取到的设备
        List<AreatodeviceRes> deviceList = new ArrayList<>();
        AreaToDeviceReq_v1 areaToDeviceReq_v1 = new AreaToDeviceReq_v1();
        areaToDeviceReq_v1.setSelectId(req.getSelectId());
        areaToDeviceReq_v1.setGroupId(redisUser.getGroupId());
        areaToDeviceReq_v1.setLevel(req.getLevel());
        areaToDeviceReq_v1.setUserId(redisUser.getId());
        JSONResult jsonResult = deviceListFeign.ec_v1(areaToDeviceReq_v1);
        deviceList = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<AreatodeviceRes>>() {
        });
        for (int i = 0; i < deviceList.size(); i++) {
            //最新一条数据
            EcDataLogDO latest = ecDataLogMapper.getLatestLogByNo(deviceList.get(i).getDeviceNo());
            DeviceListRes res = new DeviceListRes();
            res.setDeviceNo(deviceList.get(i).getDeviceNo());
            res.setTopShow(deviceList.get(i).getDeviceCode());
            //最新数据存在
            if (latest != null) {
                Integer floor = latest.getFloor();
                if(deviceList.get(i).getFloorDownNum()!=null &&deviceList.get(i).getFloorNum()!=null)
                {
                    floor=latest.getFloor() - deviceList.get(i).getFloorDownNum() > 0 ? latest.getFloor() - deviceList.get(i).getFloorDownNum() : latest.getFloor() - (deviceList.get(i).getFloorDownNum() + 1);
                }
                String faultInfo = latest.getFaultInfo();
                if (StringUtils.isNotBlank(faultInfo)) {
                    if (Integer.valueOf(faultInfo) > 0) {
                        res.setStatus(3);
                        res.setLowerShow("故障("+floor+"楼)");
                    } else {
                        if (System.currentTimeMillis() - latest.getCollectTime().getTime() > EC_OFF_LINE_TIME) {
                            res.setStatus(2);
                            res.setLowerShow("离线("+floor+"楼)");
                        } else {
                            //在线情况处理
                            res.setStatus(1);
                            //运行中
                            if (latest.getRunSts() == 1) {
                                if (latest.getRunDirection() == 0) {
                                    res.setLowerShow("向下("+floor+"楼)");
                                } else {
                                    res.setLowerShow("向上("+floor+"楼)");
                                }
                            } else {
                                res.setLowerShow(latest.getFloor() + "楼");
                                if (latest.getFloor() != 0) {
                                    res.setLowerShow(floor + "楼");
                                }
                            }
                        }
                    }
                }
            }
            //最新数据不存在
            else {
                res.setStatus(2);
                res.setLowerShow("离线");
            }
            result.add(res);
        }
        return JSONResult.ok(result);
    }

    /**
     * 井盖设备列表
     */
    public JSONResult mhList(DeviceListReq req, RedisUser redisUser) {
        List<DeviceListRes> result = new ArrayList<>();
        //获取到的设备
        List<AreatodeviceRes> deviceList = new ArrayList<>();
        AreaToDeviceReq_v1 areaToDeviceReq_v1 = new AreaToDeviceReq_v1();
        areaToDeviceReq_v1.setSelectId(req.getSelectId());
        areaToDeviceReq_v1.setGroupId(redisUser.getGroupId());
        areaToDeviceReq_v1.setLevel(req.getLevel());
        areaToDeviceReq_v1.setUserId(redisUser.getId());
        JSONResult jsonResult = deviceListFeign.mh_v1(areaToDeviceReq_v1);
        deviceList = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<AreatodeviceRes>>() {
        });
        for (int i = 0; i < deviceList.size(); i++) {
            //最新一条数据
            MhDataLogDO latest = mhDataLogMapper.getLatestLogByNo(deviceList.get(i).getDeviceNo());
            DeviceListRes res = new DeviceListRes();
            res.setDeviceNo(deviceList.get(i).getDeviceNo());
            res.setTopShow(deviceList.get(i).getDeviceName());
            res.setLowerShow(deviceList.get(i).getDeviceCode());
            //最新数据存在
            if (latest != null) {
                String faultInfo = latest.getFaultInfo();
                if (StringUtils.isNotBlank(faultInfo)) {
                    if (Integer.valueOf(faultInfo) > 0) {
                        res.setLowerShow("故障("+deviceList.get(i).getDeviceCode()+")");
                        res.setStatus(3);
                    } else {
                        if (System.currentTimeMillis() - latest.getCollectTime().getTime() > MH_OFF_LINE_TIME) {
                            res.setStatus(2);
                            res.setLowerShow("离线("+deviceList.get(i).getDeviceCode()+")");
                        } else {
                            res.setStatus(1);
                        }
                    }
                }
            }
            //最新数据不存在
            else {
                res.setStatus(2);
            }
            result.add(res);
        }
        return JSONResult.ok(result);
    }

    /**
     * 河道水设备列表
     */
    public JSONResult rwList(DeviceListReq req, RedisUser redisUser) {
        List<DeviceListRes> result = new ArrayList<>();
        //获取到的设备
        List<AreatodeviceRes> deviceList = new ArrayList<>();
        AreaToDeviceReq_v1 areaToDeviceReq_v1 = new AreaToDeviceReq_v1();
        areaToDeviceReq_v1.setSelectId(req.getSelectId());
        areaToDeviceReq_v1.setGroupId(redisUser.getGroupId());
        areaToDeviceReq_v1.setLevel(req.getLevel());
        areaToDeviceReq_v1.setUserId(redisUser.getId());
        JSONResult jsonResult = deviceListFeign.rw_v1(areaToDeviceReq_v1);
        deviceList = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<AreatodeviceRes>>() {
        });
        for (int i = 0; i < deviceList.size(); i++) {
            //最新一条数据
            RwDataLogDO latest = rwDataLogMapper.getLatestLogByNo(deviceList.get(i).getDeviceNo());
            DeviceListRes res = new DeviceListRes();
            res.setDeviceNo(deviceList.get(i).getDeviceNo());
            res.setTopShow(deviceList.get(i).getDeviceName());
            res.setLowerShow(deviceList.get(i).getDeviceCode());
            //最新数据存在
            if (latest != null) {
                String faultInfo = latest.getFaultInfo();
                if (StringUtils.isNotBlank(faultInfo)) {
                    if (Integer.valueOf(faultInfo) > 0) {
                        res.setStatus(3);
                        res.setLowerShow("故障("+deviceList.get(i).getDeviceCode()+")");
                    } else {
                        if (System.currentTimeMillis() - latest.getCollectTime().getTime() > RW_OFF_LINE_TIME) {
                            res.setStatus(2);
                            res.setLowerShow("离线("+deviceList.get(i).getDeviceCode()+")");
                        } else {
                            res.setStatus(1);
                        }
                    }
                }
            }
            //最新数据不存在
            else {
                res.setStatus(2);
            }
            result.add(res);
        }
        return JSONResult.ok(result);
    }

    /**
     * 饮用水列表
     */
    public JSONResult cwList(DeviceListReq req, RedisUser redisUser) {
        List<DeviceListRes> result = new ArrayList<>();
        //获取到的设备
        List<AreatodeviceRes> deviceList = new ArrayList<>();
        AreaToDeviceReq_v1 areaToDeviceReq_v1 = new AreaToDeviceReq_v1();
        areaToDeviceReq_v1.setSelectId(req.getSelectId());
        areaToDeviceReq_v1.setGroupId(redisUser.getGroupId());
        areaToDeviceReq_v1.setLevel(req.getLevel());
        areaToDeviceReq_v1.setUserId(redisUser.getId());
        JSONResult jsonResult = deviceListFeign.cw_v1(areaToDeviceReq_v1);
        deviceList = JSON.parseObject(JSONObject.toJSONString(jsonResult.getData()), new TypeReference<List<AreatodeviceRes>>() {
        });
        for (int i = 0; i < deviceList.size(); i++) {
            //最新一条数据
            CwDataLogDO latest = cwDataLogMapper.getLatestLogByNo(deviceList.get(i).getDeviceNo());
            DeviceListRes res = new DeviceListRes();
            res.setDeviceNo(deviceList.get(i).getDeviceNo());
            res.setTopShow(deviceList.get(i).getDeviceCode());
            res.setLowerShow(deviceList.get(i).getFloor() + "楼");
            //最新数据存在
            if (latest != null) {
                String faultInfo = latest.getFaultInfo();
                if (StringUtils.isNotBlank(faultInfo)) {
                    if (Integer.valueOf(faultInfo) > 0) {
                        res.setStatus(3);
                        res.setLowerShow("故障("+deviceList.get(i).getFloor()+"楼)");
                    } else {
                        if (System.currentTimeMillis() - latest.getCollectTime().getTime() > CW_OFF_LINE_TIME) {
                            res.setStatus(2);
                            res.setLowerShow("离线("+deviceList.get(i).getFloor()+"楼)");
                        } else {
                            res.setStatus(1);
                        }
                    }
                }
            }
            //最新数据不存在
            else {
                res.setStatus(2);
            }
            result.add(res);
        }
        return JSONResult.ok(result);
    }
}
