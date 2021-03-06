package com.xiaowei.mh.mapper.mapper;

import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import com.xiaowei.mh.mapper.result.v2.MhDiameterResult;
import com.xiaowei.mh.mapper.result.v2.MhMaterialResult;
import com.xiaowei.mh.mapper.result.v2.MhTypeResult;
import com.xiaowei.mh.mapper.result.v2.MhYearResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_MH_SENCE.
 * 井盖场景信息表
 */
public interface XwMhSenceMapper {
    /**
     * 井盖使用年限统计
     * */
    List<MhYearResult>yearMhByMix(List<DeviceRoleDeviceRea> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
    /**
     * 井盖材质数量统计
     * */
    List<MhMaterialResult>materialMhByMix(List<DeviceRoleDeviceRea> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
    /**
     * 井盖直径占比统计
     * */
    List<MhDiameterResult>diameterMhByMix(List<DeviceRoleDeviceRea> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
    /**
     * 井盖使用类型占比统计
     * */
    List<MhTypeResult>typeMhByMix(List<DeviceRoleDeviceRea> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
}
