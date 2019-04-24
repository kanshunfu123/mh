package com.xiaowei.mh.mapper.mapper;

import com.xiaowei.mh.mapper.dataobject.MhDataLogDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table D_MH_DATA_LOG.
 * 井盖日志
 */
public interface MhDataLogMapper {
    /**
     * 井盖分页
     */
    List<MhDataLogDO> mhHistoryPage(@Param("deviceNo") String deviceNo, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询井盖分页下一页第一条------>v2
     */
    MhDataLogDO mhNext(@Param("deviceNo") String deviceNo, @Param("fetchNum") Integer fetchNum, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询最新的一条信息
     */
    public MhDataLogDO getLatestLogByNo(@Param("deviceNo") String deviceNo);

    //查看是否在线
    Integer getMhStatusByNo(@Param("deviceNo") String deviceNo, @Param("timeOut") Integer timeOut);

    //设备在线率查询
    public List<Date> onlineRate(@Param("deviceNo") String deviceNo, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 根据时间条件查询设备的最新数据
     */
    List<MhDataLogDO> mhGas(@Param("deviceNo") String deviceNo, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 根据时间条件查询昨日设备深度（1条）
     */
    MhDataLogDO mhGasYes(@Param("deviceNo") String deviceNo, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 统计当天的错误信息
     */
    public List<MhDataLogDO> faultTodayMh(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 查询当天的历史记录
     */
    public List<Date> todayList(@Param("deviceNo") String deviceNo, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    int insert(MhDataLogDO mhDataLogDO);

    /**
     * 查询井盖电量
     */
    public List<MhDataLogDO> mhPowerList(@Param("deviceNo") String deviceNo, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
