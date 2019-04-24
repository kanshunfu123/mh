package com.xiaowei.mh.service.datalog;

import com.xiaowei.mh.service.protocol.XiaoweiProtocolV1;
import com.xiaowei.mh.service.protocol.manhole.NewLogVo;
import com.xiaowei.mh.service.protocol.manhole.ThreeDataSuccess;

/**
 * Created by 韩金群
 * CreateTime 2019/1/24
 */
public interface DataLogService {
    /**
     * 处理井盖数据
     *
     * @param msg
     */
    void dealMhData(XiaoweiProtocolV1 msg, String text);

    /**
     * 处理第三方井盖数据
     */
    void doMhTwo(ThreeDataSuccess req, NewLogVo newLogVo);
}
