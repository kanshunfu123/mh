package com.xiaowei.mh.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.TypeReference;
import com.xiaowei.mh.common.common.JSONResult;
import com.xiaowei.mh.common.config.JwtProperties;
import com.xiaowei.mh.mapper.dataobject.SysUser;
import com.xiaowei.mh.common.util.JwtTokenUtil;
import com.xiaowei.mh.common.util.RedisUtil;
import com.xiaowei.mh.common.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MOMO on 2018/12/28.
 */
@RestController
@Slf4j
public class AuthController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private RedisUtil redisUtil;

    //会话失效时间
    private final static Long EXPIREDREDIS = 60 * 60 * 2L;
    @RequestMapping("/auth")
    public JSONResult auth(@RequestHeader(value = "userInfo",required = false)String userInfo,
                           @RequestHeader(value = "sffffff",required = false)String sffffff,
                           @RequestHeader(value = "token",required = false)String tokena) {
        // randomKey和token已经生成完毕
        final String randomKey = jwtTokenUtil.getRandomKey();
        SysUser sysUserGwRes = new SysUser();
        sysUserGwRes.setSysUserName("李杰");
        sysUserGwRes.setAreaId(1L);
        sysUserGwRes.setId(1L);

        final String token = jwtTokenUtil.generateToken(JSONObject.toJSONString(sysUserGwRes), randomKey);
        String uuid= StrUtil.genUUID();
        redisUtil.set("user"+ uuid,token);
        redisUtil.set("user"+ sysUserGwRes.getId(),token);
        redisUtil.expire("user"+uuid,EXPIREDREDIS);
        return JSONResult.ok(token);
    }
    @RequestMapping("/checkToken")
    public JSONResult checkToken(@RequestHeader(value = "token",required = false)String token, HttpServletRequest request) {
        String header = jwtProperties.getHeader();
        String requestHeader = request.getHeader(header);
        String userInfo = jwtTokenUtil.getUsernameFromToken(requestHeader);
        SysUser redisUser = JSON.parseObject(userInfo, new TypeReference<SysUser>() {
        });
        log.info("GwSysUserServiceImpl===" + redisUtil.get(redisUser.getId() + ""));

        Map<String, Object> map = new HashMap<>();
        String getAudienceFromToken = jwtTokenUtil.getAudienceFromToken(requestHeader);
        String getUsernameFromToken = jwtTokenUtil.getUsernameFromToken(requestHeader);
        Date getIssuedAtDateFromToken = jwtTokenUtil.getIssuedAtDateFromToken(requestHeader);
        Date getExpirationDateFromToken = jwtTokenUtil.getExpirationDateFromToken(requestHeader);
        String getMd5KeyFromToken = jwtTokenUtil.getMd5KeyFromToken(requestHeader);
        map.put("获取jwt接收者getAudienceFromToken==", getAudienceFromToken);
        map.put("获取用户名从token中getUsernameFromToken==", getUsernameFromToken);
        map.put("获取jwt发布时间oken中getIssuedAtDateFromToken==", getIssuedAtDateFromToken);
        map.put("获取jwt失效时间token中getExpirationDateFromToken==", getExpirationDateFromToken);
        map.put("获取md5 key从token中getMd5KeyFromToken==", getMd5KeyFromToken);
        log.info("TestController===" + redisUtil.get(redisUser.getId() + ""));
        return JSONResult.ok(map);
    }
}
