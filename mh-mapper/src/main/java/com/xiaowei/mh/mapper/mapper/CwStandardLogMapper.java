package com.xiaowei.mh.mapper.mapper;

import com.xiaowei.mh.mapper.dataobject.CwStandardLogDO;
import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table D_CW_STANDARD_LOG.
 * 饮用水的参数统计
 */
public interface CwStandardLogMapper {

    /**
     * 插入饮用水的参数统计
     */
    int insert(CwStandardLogDO entity);

    /**
     * 数值统计
     */
    List<CwStandardLogDO> standardCount(List<DeviceRoleDeviceRea> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
