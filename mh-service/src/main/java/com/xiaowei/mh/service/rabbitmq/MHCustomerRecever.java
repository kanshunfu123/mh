package com.xiaowei.mh.service.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * 消息路由规则：四种模式（topic、direct、fanout、header）
 * topic：根据绑定关键字通配符规则匹配、比较灵活
 * direct：默认，根据routingKey完全匹配，好处是先匹配再发送
 * fanout：不需要指定routingkey，相当于群发
 * header：不太常用，可以自定义匹配规则
 */
@Component
@Slf4j
public class MHCustomerRecever {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MHTopicConfig.QUEUE,//队列名称
                    durable = MHTopicConfig.DERABLE),//是否持久化
            exchange = @Exchange(value = MHTopicConfig.EXCHANGE, //交换机名称
                    durable = MHTopicConfig.DERABLE,//是否持久化o
                    type = MHTopicConfig.TYPE, //消息路由规则
                    ignoreDeclarationExceptions = MHTopicConfig.IGNOREDECEXCEPTION), // 忽略声明异常
            key = MHTopicConfig.KEY_V1 // 绑定的路由键或模式。
    )
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        log.info("消费端Payload: " + message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        //TODO  处理业务逻辑




        //手工ACK
        channel.basicAck(deliveryTag, false);
    }

}