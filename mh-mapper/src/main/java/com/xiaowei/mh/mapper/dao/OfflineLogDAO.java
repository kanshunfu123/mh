package com.xiaowei.mh.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mh.mapper.dataobject.OfflineLogDO;
import com.xiaowei.mh.mapper.mapper.OfflineLogDOMapper;

/**
* The Table D_OFFLINE_LOG.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 离线日志表（每天统计一次
*/
@Repository
public class OfflineLogDAO{

    @Autowired
    private OfflineLogDOMapper offlineLogDOMapper;

    /**
     * desc:插入表:D_OFFLINE_LOG.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO D_OFFLINE_LOG( DEL_FLAG ,CREATE_BY ,DEVICE_NO ,DEVICE_TYPE ,CREATE_TIME ,COLLECT_TIME )VALUES( #{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{deviceNo,jdbcType=VARCHAR} ,#{deviceType,jdbcType=CHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{collectTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(OfflineLogDO entity){
        return offlineLogDOMapper.insert(entity);
    }
    /**
     * desc:更新表:D_OFFLINE_LOG.<br/>
     * descSql =  UPDATE D_OFFLINE_LOG SET DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,DEVICE_NO = #{deviceNo,jdbcType=VARCHAR} ,DEVICE_TYPE = #{deviceType,jdbcType=CHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,COLLECT_TIME = #{collectTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(OfflineLogDO entity){
        return offlineLogDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:D_OFFLINE_LOG.<br/>
     * descSql =  DELETE FROM D_OFFLINE_LOG WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return offlineLogDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:D_OFFLINE_LOG.<br/>
     * descSql =  SELECT * FROM D_OFFLINE_LOG WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return OfflineLogDO
     */
    public OfflineLogDO getById(Long id){
        return offlineLogDOMapper.getById(id);
    }
}
