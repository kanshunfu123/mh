package com.xiaowei.mh.mapper.mapper;

import com.xiaowei.mh.mapper.dataobject.XwEquipmentMhDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_EQUIPMENT_MH.
 * 井盖设备表
 */
public interface EquipmentMhMapper {
    //根据设备编号查询设备信息
    XwEquipmentMhDO getInfoByNo(@Param("deviceNo") String deviceNo);
}
