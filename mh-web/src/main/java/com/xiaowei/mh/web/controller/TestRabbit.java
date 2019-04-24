package com.xiaowei.mh.web.controller;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by 李杰 on 2019/1/1.
 */
@RestController
@Slf4j
public class TestRabbit {


   /* @Autowired
    private RabbitTopicSender rabbitSender;
    @Autowired
    private RabbitDirectSender rabbitDirectSender;

    @PostMapping("/sendTopicRabbit")
    public void rabbitSender(@RequestBody Order order) {
        try {
            Map<String, Object> properties= Maps.newHashMap();
            properties.put("Test","test冯绍峰");
            rabbitSender.send(order,properties);
            rabbitSender.sendOrder(order);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
    @PostMapping("/sendBasicRabbit")
    public void rabbitDirectSender(@RequestBody Order order) {
        try {
            Map<String, Object> properties= Maps.newHashMap();
            properties.put("Test","test冯绍峰");
            rabbitDirectSender.send(order,properties);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }*/
}
