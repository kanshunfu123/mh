package com.xiaowei.mh.mapper.mapper;

import com.xiaowei.mh.mapper.dataobject.OfflineLogDO;
import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table D_OFFLINE_LOG.
 * 离线日志表（每天统计一次
 */
public interface OfflineLogMapper {
    /**
     * 插入离线日志统计表
     */
    int insert(OfflineLogDO offlineLogDO);

    int offlineCount(List<DeviceRoleDeviceRea> list, @Param("deviceType") String deviceType, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
