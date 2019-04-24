package com.xiaowei.mh.service.rabbitmq.newmh;

import com.rabbitmq.client.Channel;
import com.xiaowei.mh.common.util.BaseUtils;
import com.xiaowei.mh.service.datalog.DealDataLogTask;
import com.xiaowei.mh.service.protocol.manhole.DataVo;
import com.xiaowei.mh.service.protocol.manhole.DeviceDataVo;
import com.xiaowei.mh.service.protocol.manhole.NewLogVo;
import com.xiaowei.mh.service.protocol.manhole.ThreeDataSuccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 消息路由规则：四种模式（topic、direct、fanout、header）
 * topic：根据绑定关键字通配符规则匹配、比较灵活
 * direct：默认，根据routingKey完全匹配，好处是先匹配再发送
 * fanout：不需要指定routingkey，相当于群发
 * header：不太常用，可以自定义匹配规则
 */
@Component
@Slf4j
public class NewMHCustomerRecever {
    @Autowired
    @Qualifier(value = "dealDataLogTask")
    private DealDataLogTask dealDataLogTask;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = NewMHTopicConfig.QUEUE,//队列名称
                    durable = NewMHTopicConfig.DERABLE),//是否持久化
            exchange = @Exchange(value = NewMHTopicConfig.EXCHANGE, //交换机名称
                    durable = NewMHTopicConfig.DERABLE,//是否持久化o
                    type = NewMHTopicConfig.TYPE, //消息路由规则
                    ignoreDeclarationExceptions = NewMHTopicConfig.IGNOREDECEXCEPTION), // 忽略声明异常
            key = NewMHTopicConfig.KEY_V1 // 绑定的路由键或模式。
    )
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        log.info("消费端Payload: " + message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        //TODO  处理业务逻辑
        String msg = (String) message.getPayload();
        dealJMSMessageTwo(msg);
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }

    public void dealJMSMessageTwo(String text) {
        DeviceDataVo deviceDataVo = com.alibaba.fastjson.JSON.parseObject(text, DeviceDataVo.class);
        DataVo req = deviceDataVo.getService().getData();
        ThreeDataSuccess success = new ThreeDataSuccess();
        BeanUtils.copyProperties(req, success);
        success.setDeviceNo(BaseUtils.bytesToHexFun3(BaseUtils.base64StrToByteArray(req.getImei())));
        success.setCcid(BaseUtils.bytesToHexFun3(BaseUtils.base64StrToByteArray(req.getCcid())));
        success.setDevID(BaseUtils.bytesToHexFun3(BaseUtils.base64StrToByteArray(req.getDevID())));
        success.setDataType(req.getDataType());
        success.setWellOpenSts(req.getCover());
        success.setDevType(req.getDevType() + "");
        if (req.getSignalRSRP() != null) {
            Integer signalRSRP = req.getSignalRSRP();
            success.setSignalRSRP((float) signalRSRP / 10);
        } else {
            success.setSignalRSRP(null);
        }
        if (req.getSignalRSSI() != null) {
            Integer signalRSSI = req.getSignalRSSI();
            success.setSignalRSSI((float) signalRSSI / 10);
        } else {
            success.setSignalRSSI(null);
        }
        if (req.getSignalSnr() != null) {
            Integer signalSnr = req.getSignalSnr();
            success.setSignalSnr((float) signalSnr / 10);
        } else {
            success.setSignalSnr(null);
        }
        success.setFaultInfo(req.getStatus() + "");
        success.setPowerMh(req.getBat());
        success.setWaterLine(req.getWaterSwitch());
        if (req.getHumi() != null) {
            success.setHumidity(req.getHumi());
        } else {
            success.setHumidity(null);
        }

        //d_new_log表
        NewLogVo newLogVo = new NewLogVo();
        newLogVo.setDeviceId(deviceDataVo.getDeviceId());
        newLogVo.setDeviceType(success.getDevType());
        newLogVo.setEventTime(deviceDataVo.getService().getEventTime().replace("T", "").replace("Z", ""));
        newLogVo.setRecTime(new Date());
        newLogVo.setGatewayId(deviceDataVo.getGatewayId());
        newLogVo.setImei(deviceDataVo.getService().getData().getImei());
        newLogVo.setRecData(text);
        newLogVo.setNotifyType(deviceDataVo.getNotifyType());
        dealDataLogTask.doThreeMh(success, newLogVo);
    }
}