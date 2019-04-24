package com.xiaowei.mh.common.weather;

import com.xiaowei.mh.common.util.HttpClientUtil;

/**
 * Created by 韩金群
 * CreateTime 2019/1/14
 */
public class Weather {
    private static final String CHARSET = "utf-8"; //编码格式
    private static final String WEATHER_URL = "http://t.weather.sojson.com/api/weather/city/"; //天气地址
    private static final String SHANGHAI_CODE = "101020100";

    //通过code获取天气
    public static String getWeatherByCode(String code) {
        String url = Weather.WEATHER_URL;
        if (url!=null && !"".equals(code.trim())) {
            url += code;
        } else {
            url += Weather.SHANGHAI_CODE;
        }
        String str = HttpClientUtil.doGet(url, CHARSET);
        return str;
    }
}
