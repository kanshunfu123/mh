package com.xiaowei.mh.mapper.mapper;
import com.xiaowei.mh.mapper.home.DeviceRoleDeviceRea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table D_EXCESS_CW.
 * 饮用水超标数据统计
 */
public interface ExcessCwMapper {
    /**
     * 查询数量
     */
    Integer countByMix(@Param("list") List<DeviceRoleDeviceRea> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
