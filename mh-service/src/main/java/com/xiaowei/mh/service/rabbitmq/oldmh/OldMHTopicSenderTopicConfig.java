package com.xiaowei.mh.service.rabbitmq.oldmh;

/**
 * Created by MOMO on 2019/1/24.
 */
public class OldMHTopicSenderTopicConfig {
    //交换机名称
    public final static String EXCHANGE = "test-mh-old-Exchanges";
    //路由key
    public final static String ROUTINGKEY_V1 = "test-mholdV1.xiaowei";
    public final static String ROUTINGKEY_V2 = "test-mholdV2.xiaowei";
    //队列名称
    public final static String QUEUE = "test-mh-queue-old";
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
    public final static String KEY_V1 = "test-mholdV1.#";
    public final static String KEY_V2 = "test-mholdV2.#";
}
