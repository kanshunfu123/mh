package com.xiaowei.mh.common.req;

import java.io.Serializable;

/**
 * Created by 韩金群
 * CreateTime 2019/1/14
 */
public class WeatherReq implements Serializable {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
