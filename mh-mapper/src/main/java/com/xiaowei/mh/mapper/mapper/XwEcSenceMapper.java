package com.xiaowei.mh.mapper.mapper;

import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import com.xiaowei.mh.mapper.result.v2.EcBrandResult;
import com.xiaowei.mh.mapper.result.v2.EcYearResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_EC_SENCE.
 * 电梯场景表
 */
public interface XwEcSenceMapper {
    /**
     * 查询电梯品牌统计
     * */
    List<EcBrandResult>brandEcByMix(List<DeviceRoleDeviceRea> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
    /**
     * 电梯使用年限统计
     * */
    List<EcYearResult>yearEcByMix(List<DeviceRoleDeviceRea> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
}
