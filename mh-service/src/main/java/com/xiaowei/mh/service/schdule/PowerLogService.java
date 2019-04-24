package com.xiaowei.mh.service.schdule;

import com.xiaowei.mh.mapper.dataobject.CwStandardLogDO;
import com.xiaowei.mh.mapper.dataobject.PowerLogDO;
import com.xiaowei.mh.mapper.dataobject.RwStandardLogDO;
import com.xiaowei.mh.mapper.mapper.CwStandardLogMapper;
import com.xiaowei.mh.mapper.mapper.PowerLogMapper;
import com.xiaowei.mh.mapper.mapper.RwStandardLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * created by 韩金群 2019/2/22
 */
@Service
@Slf4j
public class PowerLogService {
    @Autowired
    private PowerLogMapper powerLogMapper;
    @Autowired
    private CwStandardLogMapper cwStandardLogMapper;
    @Autowired
    private RwStandardLogMapper rwStandardLogMapper;
/**
 * 电梯电量统计日志表
 * */
    @Async
    public void insertPowerLog(PowerLogDO powerLogDO) {
        powerLogMapper.insert(powerLogDO);
    }
    /**
     * 饮用水标准参数
     * */
    @Async
    public void insertStandardCwLog(CwStandardLogDO cwStandardLogDO) {
        cwStandardLogMapper.insert(cwStandardLogDO);
    }
    /**
     * 地表水标准参数
     * */
    @Async
    public void insertStandardRwLog(RwStandardLogDO rwStandardLogDO)
    {
        rwStandardLogMapper.insert(rwStandardLogDO);
    }
}
