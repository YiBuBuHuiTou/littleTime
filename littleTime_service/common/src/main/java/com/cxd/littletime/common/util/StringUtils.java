package com.cxd.littletime.common.util;

/**
 * 字符串工具类，所有方法均为静态方法
 * @author YiBuBuHuiTou
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     * @param str  需要判断的字符串
     * @return      判断结果
     */
    public static boolean isEmpty(String str) {
        boolean result = true;
        if (str!= null && str != "") {
            result = false;
        }
        return result;
    }
}
