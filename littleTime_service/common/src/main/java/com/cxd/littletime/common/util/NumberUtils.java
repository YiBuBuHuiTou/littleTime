package com.cxd.littletime.common.util;

import java.util.regex.Pattern;

/**
 * @author YiBuBuHuiTou
 * 数字工具类
 */
public class NumberUtils {

    /**
     * 校验数字 【数字校验范围太广泛，未实现】
     * @param obj
     * @return
     */
    @Deprecated
    public static boolean numberCheck(Object obj) {
        if (obj instanceof Number) {
            return true;
        } else if (obj instanceof String){
            if (!StringUtils.isEmpty((String)obj)) {
                Pattern pattern = Pattern.compile("^[0-9]*$");
                return pattern.matcher((String)obj).matches();
            }
            return false;
        } else {
            return false;
        }
    }
}
