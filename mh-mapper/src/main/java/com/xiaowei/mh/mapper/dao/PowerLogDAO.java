package com.xiaowei.mh.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mh.mapper.dataobject.PowerLogDO;
import com.xiaowei.mh.mapper.mapper.PowerLogDOMapper;

/**
* The Table D_POWER_LOG.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* D_POWER_LOG
*/
@Repository
public class PowerLogDAO{

    @Autowired
    private PowerLogDOMapper powerLogDOMapper;

    /**
     * desc:插入表:D_POWER_LOG.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO D_POWER_LOG( AREA_ID ,CITY_ID ,PROVINCE_ID ,DEL_FLAG ,AREA_NAME ,CITY_NAME ,DEVICE_NO ,DEVICE_TYPE ,PROVINCE_NAME ,POWER_LAUNCH ,POWER_COLLECT ,CREATE_TIME ,COLLECT_TIME )VALUES( #{areaId,jdbcType=BIGINT} ,#{cityId,jdbcType=BIGINT} ,#{provinceId,jdbcType=BIGINT} ,#{delFlag,jdbcType=VARCHAR} ,#{areaName,jdbcType=VARCHAR} ,#{cityName,jdbcType=VARCHAR} ,#{deviceNo,jdbcType=VARCHAR} ,#{deviceType,jdbcType=CHAR} ,#{provinceName,jdbcType=VARCHAR} ,#{powerLaunch,jdbcType=INTEGER} ,#{powerCollect,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{collectTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(PowerLogDO entity){
        return powerLogDOMapper.insert(entity);
    }
    /**
     * desc:更新表:D_POWER_LOG.<br/>
     * descSql =  UPDATE D_POWER_LOG SET AREA_ID = #{areaId,jdbcType=BIGINT} ,CITY_ID = #{cityId,jdbcType=BIGINT} ,PROVINCE_ID = #{provinceId,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=VARCHAR} ,AREA_NAME = #{areaName,jdbcType=VARCHAR} ,CITY_NAME = #{cityName,jdbcType=VARCHAR} ,DEVICE_NO = #{deviceNo,jdbcType=VARCHAR} ,DEVICE_TYPE = #{deviceType,jdbcType=CHAR} ,PROVINCE_NAME = #{provinceName,jdbcType=VARCHAR} ,POWER_LAUNCH = #{powerLaunch,jdbcType=INTEGER} ,POWER_COLLECT = #{powerCollect,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,COLLECT_TIME = #{collectTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(PowerLogDO entity){
        return powerLogDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:D_POWER_LOG.<br/>
     * descSql =  DELETE FROM D_POWER_LOG WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return powerLogDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:D_POWER_LOG.<br/>
     * descSql =  SELECT * FROM D_POWER_LOG WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return PowerLogDO
     */
    public PowerLogDO getById(Long id){
        return powerLogDOMapper.getById(id);
    }
}
