package com.xiaowei.mh.common.util;

/**
 * Created by 韩金群
 * CreateTime 2019/1/15
 */
public enum RedisKeyEnum {
    REDIS_KEY_MH_LATEST("ml:", -1, "井盖最后一条数据"),
    REDIS_KEY_RW_LATEST("rl:", -1, "地表水最后一条数据"),
    REDIS_KEY_EC_INFO("ec:", -1, "电梯对象"),
    //
    REDIS_KEY_CW_INFO("cw:", -1, "饮用水对象"),
    //
    REDIS_KEY_MH_INFO("mh:", -1, "井盖对象"),
    //
    REDIS_KEY_RW_INFO("rw:", -1, "河道水对象"),
    REDIS_KEY_EC_SENCE("es:", -1, "电梯场景"),
    REDIS_KEY_CW_SENCE("cs:", -1, "饮用水场景"),
    REDIS_KEY_MH_SENCE("ms:", -1, "井盖场景"),
    REDIS_KEY_RW_SENCE("rs:", -1, "河道水场景"),;
    private final String key;
    private final int expireTime;
    private final String keyInfo;

    RedisKeyEnum(String key, int expireTime, String keyInfo) {
        this.key = key;
        this.expireTime = expireTime;
        this.keyInfo = keyInfo;
    }

    public String getKey() {
        return key;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public String getKeyInfo() {
        return keyInfo;
    }
}
