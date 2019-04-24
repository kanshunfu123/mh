package com.xiaowei.mh.mapper.mapper;
import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import com.xiaowei.mh.mapper.result.v2.MhTypeResult;
import com.xiaowei.mh.mapper.result.v2.RwLevelResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_RW_SENCE.
 * 河道水场景
 */
public interface XwRwSenceMapper {
    /**
     * 地表水使用类型占比统计
     * */
    List<MhTypeResult> typeRwByMix(List<DeviceRoleDeviceRea> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
    /**
     * 地表水水质等级占比统计
     * */
    List<RwLevelResult> levelRwByMix(List<DeviceRoleDeviceRea> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
}
