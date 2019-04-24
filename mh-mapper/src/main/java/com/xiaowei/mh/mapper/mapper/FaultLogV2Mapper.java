package com.xiaowei.mh.mapper.mapper;


import com.xiaowei.mh.mapper.dataobject.FaultLogDO;
import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table D_FAULT_LOG.
 * 错误日志表（定时）
 */
public interface FaultLogV2Mapper {

    /**
     * 查询当日的所有错误
     */
    List<FaultLogDO> todayFaultList(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 根据不同类型查询指定日期的所有错误
     */
    List<FaultLogDO> sevenTwoFault(@Param("list") List<DeviceRoleDeviceRea> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId, @Param("deviceType") String deviceType, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 查询数量
     */
    Integer faultSize(@Param("list") List<DeviceRoleDeviceRea> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId, @Param("deviceType") String deviceType, @Param("beginTime") String beginTime, @Param("endTime") String endTime);


}
