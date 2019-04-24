package com.xiaowei.mh.service.rabbitmq.oldmh;

import com.rabbitmq.client.Channel;
import com.xiaowei.mh.common.util.hex.BytesHexStrTranslate;
import com.xiaowei.mh.service.datalog.DealDataLogTask;
import com.xiaowei.mh.service.protocol.XiaoweiProtocolV1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class OldMHCustomerRecever {
    @Autowired
    @Qualifier(value = "dealDataLogTask")
    private DealDataLogTask dealDataLogTask;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = OldMHTopicSenderTopicConfig.QUEUE,//队列名称
                    durable = OldMHTopicSenderTopicConfig.DERABLE),//是否持久化
            exchange = @Exchange(value = OldMHTopicSenderTopicConfig.EXCHANGE, //交换机名称
                    durable = OldMHTopicSenderTopicConfig.DERABLE,//是否持久化o
                    type = OldMHTopicSenderTopicConfig.TYPE, //消息路由规则
                    ignoreDeclarationExceptions = OldMHTopicSenderTopicConfig.IGNOREDECEXCEPTION), // 忽略声明异常
            key = OldMHTopicSenderTopicConfig.KEY_V1 // 绑定的路由键或模式。
    )
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        log.info("消费端Payload: " + message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        //TODO  处理业务逻辑
        String msg = (String) message.getPayload();
        dealJMSMessage(msg);


        //手工ACK
        channel.basicAck(deliveryTag, false);
    }

    private void dealJMSMessage(String text) {
        byte[] data = BytesHexStrTranslate.toBytes(text);
        //切割 头部 开始1个字节
        Integer start = 0;
        byte[] headByte = subBytes(data, start, 1);
        start += 1;
        // 长度 2个字节
        byte[] lenByte = subBytes(data, start, 2);
        start += 2;
        String lengthHex = BytesHexStrTranslate.bytesToHexFun3(lenByte);
        Integer length = Integer.parseInt(lengthHex, 16);

        byte[] dataByte = subBytes(data, start, length);
        start += length;

        byte[] endByte = subBytes(data, start, 1);

        XiaoweiProtocolV1 protocol = new XiaoweiProtocolV1(length, dataByte);
        protocol.initData();

        dealDataLogTask.doDealDataLogTask(protocol, text);
    }

    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }

}