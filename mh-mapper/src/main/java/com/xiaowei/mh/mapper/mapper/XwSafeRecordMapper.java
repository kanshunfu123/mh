package com.xiaowei.mh.mapper.mapper;

import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_SAFE_RECORD.
 * 维保记录表
 */
public interface XwSafeRecordMapper {
    /**
     * 根据权限，省市，月份查询数量
     * */
    int countByMix(List<DeviceRoleDeviceRea> list,@Param("deviceType")String deviceType,@Param("safeYear")Integer safeYear,@Param("safeMonth")Integer safeMonth, @Param("provinceId")Long provinceId,@Param("cityId")Long cityId);
}
