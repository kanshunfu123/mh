package com.xiaowei.mh.mapper.mapper;


import com.xiaowei.mh.mapper.dataobject.RwDataLogDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table D_RW_DATA_LOG.
 * 河道水采集日志
 */
public interface RwDataLogMapper {
    /**
     * 查询最新的一条信息
     */
    public RwDataLogDO getLatestLogByNo(@Param("deviceNo") String deviceNo);

    /**
     * 统计当天的错误信息
     */
    public List<RwDataLogDO> faultTodayRw(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 查询当天的历史记录
     */
    public List<Date> todayList(@Param("deviceNo") String deviceNo, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 查询地表水电量
     */
    public List<RwDataLogDO> rwPowerList(@Param("deviceNo") String deviceNo, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
