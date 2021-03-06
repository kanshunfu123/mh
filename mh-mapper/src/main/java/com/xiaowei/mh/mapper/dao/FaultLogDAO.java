package com.xiaowei.mh.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mh.mapper.dataobject.FaultLogDO;
import com.xiaowei.mh.mapper.mapper.FaultLogDOMapper;

/**
* The Table D_FAULT_LOG.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 错误日志表（定时）
*/
@Repository
public class FaultLogDAO{

    @Autowired
    private FaultLogDOMapper faultLogDOMapper;

    /**
     * desc:插入表:D_FAULT_LOG.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO D_FAULT_LOG( ADDRESS ,DEL_FLAG ,DEVICE_NO ,FAULT_INFO ,DEVICE_TYPE ,FAULT_1 ,FAULT_2 ,FAULT_3 ,FAULT_4 ,FAULT_5 ,FAULT_6 ,FAULT_7 ,FAULT_8 ,CREATE_TIME ,COLLECT_TIME )VALUES( #{address,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{deviceNo,jdbcType=VARCHAR} ,#{faultInfo,jdbcType=VARCHAR} ,#{deviceType,jdbcType=CHAR} ,#{fault1,jdbcType=INTEGER} ,#{fault2,jdbcType=INTEGER} ,#{fault3,jdbcType=INTEGER} ,#{fault4,jdbcType=INTEGER} ,#{fault5,jdbcType=INTEGER} ,#{fault6,jdbcType=INTEGER} ,#{fault7,jdbcType=INTEGER} ,#{fault8,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{collectTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(FaultLogDO entity){
        return faultLogDOMapper.insert(entity);
    }
    /**
     * desc:更新表:D_FAULT_LOG.<br/>
     * descSql =  UPDATE D_FAULT_LOG SET ADDRESS = #{address,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,DEVICE_NO = #{deviceNo,jdbcType=VARCHAR} ,FAULT_INFO = #{faultInfo,jdbcType=VARCHAR} ,DEVICE_TYPE = #{deviceType,jdbcType=CHAR} ,FAULT_1 = #{fault1,jdbcType=INTEGER} ,FAULT_2 = #{fault2,jdbcType=INTEGER} ,FAULT_3 = #{fault3,jdbcType=INTEGER} ,FAULT_4 = #{fault4,jdbcType=INTEGER} ,FAULT_5 = #{fault5,jdbcType=INTEGER} ,FAULT_6 = #{fault6,jdbcType=INTEGER} ,FAULT_7 = #{fault7,jdbcType=INTEGER} ,FAULT_8 = #{fault8,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,COLLECT_TIME = #{collectTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(FaultLogDO entity){
        return faultLogDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:D_FAULT_LOG.<br/>
     * descSql =  DELETE FROM D_FAULT_LOG WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return faultLogDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:D_FAULT_LOG.<br/>
     * descSql =  SELECT * FROM D_FAULT_LOG WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return FaultLogDO
     */
    public FaultLogDO getById(Long id){
        return faultLogDOMapper.getById(id);
    }
}
