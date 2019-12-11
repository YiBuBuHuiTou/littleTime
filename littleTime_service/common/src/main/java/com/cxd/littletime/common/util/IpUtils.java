package com.cxd.littletime.common.util;

import java.util.regex.Pattern;

/**
 * @author YiBuBuHuiTou
 */
public class IpUtils {

    /**
     * 判断是否是单个ip还是一个范围
     * @param ipList
     * @return
     */
    public static boolean isSingleIP(String ipList) {
        boolean isSingleIp = true;
        if(!StringUtils.isEmpty(ipList)) {
            String[] ips = ipList.trim().split("-");
            if (ips.length == 2) {
                isSingleIp = false;
            }
        }
        return isSingleIp;
    }

    /**
     * 判断是否是ip
     * @param ip
     * @return
     */
    public static boolean isIP(String ip) {
        boolean isIp = false;
        if (!StringUtils.isEmpty(ip)) {
            String ipReg = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            Pattern ipPattern = Pattern.compile(ipReg);
            isIp = ipPattern.matcher(ip).matches();
        }

        return isIp;
    }

    /**
     * ip转换为长整型
     * @param ip
     * @return
     */
    public static long ip2Long(String ip) {
        long ipLong = 0L;
        if (isIP(ip)) {
            String[] ips = ip.split("\\.");
            ipLong = Long.parseLong(ips[0],10) * 256L * 256L * 256L +
                    Long.parseLong(ips[1],10) * 256L * 256L +
                    Long.parseLong(ips[2],10) * 256L +
                    Long.parseLong(ips[3], 10);
        }
        return ipLong;
    }
}
