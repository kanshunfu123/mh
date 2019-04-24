package com.xiaowei.mh.mapper.mapper;

import com.xiaowei.mh.mapper.dataobject.NewLogDO;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table D_NEW_LOG.
 * 设备接收数据新日志表
 */
public interface NewLogMapper {

    int insert(NewLogDO entity);
}
