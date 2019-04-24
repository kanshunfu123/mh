package com.xiaowei.mh.mapper.mapper;

import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import com.xiaowei.mh.mapper.result.SevenFaultResult;
import com.xiaowei.mh.mapper.result.TodayFaultResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_MH_SENCE.
 * 井盖场景信息表
 */
public interface HomeMapper {
    /**
     * 电梯今日事件数
     */
    Integer ecTodayFaultNum(List<DeviceRoleDeviceRea> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 井盖今日事件数
     */
    Integer mhTodayFaultNum(List<DeviceRoleDeviceRea> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 地表水今日事件数
     */
    Integer rwTodayFaultNum(List<DeviceRoleDeviceRea> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 饮用水今日事件数
     */
    Integer cwTodayFaultNum(List<DeviceRoleDeviceRea> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 电梯今日故障最新信息
     */
    TodayFaultResult ecTodayFault(List<DeviceRoleDeviceRea> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 井盖今日故障最新信息
     */
    TodayFaultResult mhTodayFault(List<DeviceRoleDeviceRea> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 地表水今日故障最新信息
     */
    TodayFaultResult rwTodayFault(List<DeviceRoleDeviceRea> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 饮用水今日故障最新信息
     */
    TodayFaultResult cwTodayFault(List<DeviceRoleDeviceRea> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 查询电梯七日故障数
     */
    List<SevenFaultResult> sevenFaultEc(List<DeviceRoleDeviceRea> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 查询井盖七日故障数
     */
    List<SevenFaultResult> sevenFaultMh(List<DeviceRoleDeviceRea> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 查询地表水七日故障数
     */
    List<SevenFaultResult> sevenFaultRw(List<DeviceRoleDeviceRea> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 查询饮用水七日故障数
     */
    List<SevenFaultResult> sevenFaultCw(List<DeviceRoleDeviceRea> list, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

}
