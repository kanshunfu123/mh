package com.xiaowei.mh.service.schdule;

import com.xiaowei.mh.mapper.dataobject.FaultLogDO;
import com.xiaowei.mh.mapper.mapper.FaultLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by 韩金群
 * CreateTime 2019/1/19
 */
@Service
@Slf4j
public class FaultLogService {
    @Autowired
    private FaultLogMapper faultLogMapper;

    @Async
    public void insertFaultLog(FaultLogDO faultLogDO) {
        faultLogMapper.insert(faultLogDO);
    }
}
