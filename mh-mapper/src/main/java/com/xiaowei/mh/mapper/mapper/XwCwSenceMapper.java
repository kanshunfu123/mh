package com.xiaowei.mh.mapper.mapper;

import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import com.xiaowei.mh.mapper.result.v2.MhMaterialResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_CW_SENCE.
 * 饮用水场景表
 */
public interface XwCwSenceMapper {
    /**
     * 井盖材质数量统计
     * */
    List<MhMaterialResult> materialCwByMix(List<DeviceRoleDeviceRea> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
}
