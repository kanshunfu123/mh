package com.xiaowei.mh.mapper.mapper;

import com.xiaowei.mh.mapper.dataobject.CwDataLogDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table D_CW_DATA_LOG.
 * 饮用水日志表
 */
public interface CwDataLogMapper {

    /**
     * 查询饮用水最新一条数据
     */
    public CwDataLogDO getLatestLogByNo(@Param("deviceNo") String deviceNo);

    /**
     * 统计当天的错误信息
     */
    public List<CwDataLogDO> faultTodayCw(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 查询当天的历史记录
     */
    public List<Date> todayList(@Param("deviceNo") String deviceNo, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
    /**
     * 查询饮用水标准值记录
     * */
    public List<CwDataLogDO>standardCw(@Param("deviceNo") String deviceNo, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
