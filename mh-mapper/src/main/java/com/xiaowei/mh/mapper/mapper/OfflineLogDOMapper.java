package com.xiaowei.mh.mapper.mapper;

import com.xiaowei.mh.mapper.dataobject.OfflineLogDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table D_OFFLINE_LOG.
 * 离线日志表（每天统计一次
 */
public interface OfflineLogDOMapper{

    /**
     * desc:插入表:D_OFFLINE_LOG.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO D_OFFLINE_LOG( DEL_FLAG ,CREATE_BY ,DEVICE_NO ,DEVICE_TYPE ,CREATE_TIME ,COLLECT_TIME )VALUES( #{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{deviceNo,jdbcType=VARCHAR} ,#{deviceType,jdbcType=CHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{collectTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(OfflineLogDO entity);
    /**
     * desc:更新表:D_OFFLINE_LOG.<br/>
     * descSql =  UPDATE D_OFFLINE_LOG SET DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,DEVICE_NO = #{deviceNo,jdbcType=VARCHAR} ,DEVICE_TYPE = #{deviceType,jdbcType=CHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,COLLECT_TIME = #{collectTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(OfflineLogDO entity);
    /**
     * desc:根据主键删除数据:D_OFFLINE_LOG.<br/>
     * descSql =  DELETE FROM D_OFFLINE_LOG WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:D_OFFLINE_LOG.<br/>
     * descSql =  SELECT * FROM D_OFFLINE_LOG WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return OfflineLogDO
     */
    OfflineLogDO getById(Long id);
}
