package com.xiaowei.mh.mapper.mapper;

import com.xiaowei.mh.mapper.dataobject.XwMhSenceDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_MH_SENCE.
 * 井盖场景信息表
 */
public interface MhSenceMapper {
    //根据编号查询场景信息
    XwMhSenceDO ecSenceByNo(@Param("deviceNo") String deviceNo);
}
