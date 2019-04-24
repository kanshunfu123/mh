package com.xiaowei.mh.common.util.hex;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by 李杰 on 2018/7/27.
 */
public class LevelUtil {
    public final static String SEPARATOR = ".";
    public final static String ROOT = "0";

    //0
    //0.1
    //0.1.1
    //0.1.3
    //0.4


    public static String  calculateLevel(String parentLevel, long parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }
    public static String calculateLevelString(String parentLevel, String parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }
}
