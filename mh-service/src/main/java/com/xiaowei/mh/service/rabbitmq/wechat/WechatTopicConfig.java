package com.xiaowei.mh.service.rabbitmq.wechat;

/**
 * Created by 韩金群
 * CreateTime 2019/1/26
 */
public class WechatTopicConfig {
    //交换机名称
    public final static String EXCHANGE = "wechat-Exchanges";
    //路由key
    public final static String ROUTINGKEY_V1 = "wechat-V1.xiaowei";
    public final static String ROUTINGKEY_V2 = "wechat-V2.xiaowei";
    //队列名称
    public final static String QUEUE = "queue-wechat";
    //是否持久化
    public final static String DERABLE = "true";
    //消息路由规则
    public final static String TYPE = "topic";
    // 忽略声明异常
    public final static String IGNOREDECEXCEPTION = "true";
    /**
     *  绑定的路由键或模式。
     *      *（星号）：可以（只能）匹配一个单词
     *       #（井号）：可以匹配多个单词（或者零个）
     */
    public final static String KEY_V1 = "wechat-V1.#";
    public final static String KEY_V2 = "wechat-V2.#";
}
