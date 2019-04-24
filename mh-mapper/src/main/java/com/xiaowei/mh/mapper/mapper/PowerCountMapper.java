package com.xiaowei.mh.mapper.mapper;


import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table D_CW_DATA_LOG.
 * 饮用水日志表
 */
public interface PowerCountMapper {
    /**
     * 查询电梯采集端电量>=50的个数
     */
    Integer ecCountAtPowerPctMax(List<DeviceRoleDeviceRea> list);

    /**
     * 查询电梯采集端电量<50的个数
     */
    Integer ecCountAtPowerPctMin(List<DeviceRoleDeviceRea> list);

    /**
     * 查询电梯发射端电量>=50的个数
     */
    Integer ecCountCtPowerPctMax(List<DeviceRoleDeviceRea> list);

    /**
     * 查询电梯采集端电量<50的个数
     */
    Integer ecCountCtPowerPctMin(List<DeviceRoleDeviceRea> list);

    /**
     * 查询地表水发射端电量>=50的个数
     */
    Integer rwCountCtPowerPctMax(List<DeviceRoleDeviceRea> list);

    /**
     * 查询地表水采集端电量<50的个数
     */
    Integer rwCountCtPowerPctMin(List<DeviceRoleDeviceRea> list);

    /**
     * 查询井盖发射端电量>=50的个数
     */
    Integer mhCountCtPowerPctMax(List<DeviceRoleDeviceRea> list);

    /**
     * 查询井盖采集端电量<50的个数
     */
    Integer mhCountCtPowerPctMin(List<DeviceRoleDeviceRea> list);

    /**
     * 查询饮用水发射端电量>=50的个数
     */
    Integer cwCountCtPowerPctMax(List<DeviceRoleDeviceRea> list);

    /**
     * 查询饮用水采集端电量<50的个数
     */
    Integer cwCountCtPowerPctMin(List<DeviceRoleDeviceRea> list);
}
