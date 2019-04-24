package com.xiaowei.mh.mapper.mapper;

import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table D_CW_DATA_LOG.
 * 饮用水日志表
 */
public interface PowerCountV2Mapper {
    /**
     * 查询某个场景采集端电量大于50的数量
     */
    Integer powerCollectMax(List<DeviceRoleDeviceRea> list, @Param("deviceType") String deviceType, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 查询某个场景采集端电量小与50的数量
     */
    Integer powerCollectMin(List<DeviceRoleDeviceRea> list, @Param("deviceType") String deviceType, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 查询某个场景发射端电量大于50的数量
     */
    Integer powerLaunchMax(List<DeviceRoleDeviceRea> list, @Param("deviceType") String deviceType, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 查询某个场景发射端电量小于50的数量
     */
    Integer powerLaunchMin(List<DeviceRoleDeviceRea> list, @Param("deviceType") String deviceType, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
}
