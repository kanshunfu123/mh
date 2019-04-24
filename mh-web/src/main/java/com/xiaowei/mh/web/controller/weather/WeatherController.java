package com.xiaowei.mh.web.controller.weather;

import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.req.WeatherReq;
import com.xiaowei.mh.common.weather.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 韩金群
 * CreateTime 2019/1/14
 */
@Slf4j
@RestController
@RequestMapping(value = "/mh")
public class WeatherController {
    /**
     * 获取天气接口
     */
    @PostMapping("/getWeatherByCode/v1")
    public JSONResult getWeatherByCode(@RequestBody WeatherReq req, @RequestHeader(value = "testHeader", required = false) String testHeader) {
        return JSONResult.ok(Weather.getWeatherByCode(req.getCode()));
    }
}
