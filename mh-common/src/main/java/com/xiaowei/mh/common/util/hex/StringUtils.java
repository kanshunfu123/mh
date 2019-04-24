package com.xiaowei.mh.common.util.hex;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.UUID;

/**
 * title:
 * description:
 * project:
 * company:
 * copyright:
 *
 * @author xuys
 * @version 1.0.0
 * @data 2017年8月4日 上午10:09:59
 */
public class StringUtils {
    private final static Log LOGGER = LogFactory.getLog(StringUtils.class);


    /**
     * 生成UUID主键
     *
     * @return
     */
    public static String genUUID() {

        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String genUUIDsubstring() {

        return UUID.randomUUID().toString().replaceAll("-", "").substring(16);
    }

    public static String setNoNull(Object o) {
        if (o == null) {
            return "";
        }
        return o.toString();
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            System.out.println(genUUID());
        }
    }

}
