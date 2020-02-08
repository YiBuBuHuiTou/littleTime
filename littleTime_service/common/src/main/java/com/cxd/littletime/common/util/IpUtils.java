package com.cxd.littletime.common.util;

import java.util.List;
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

    /**
     * 判断设置的白名单黑名单 ip格式是否正确   xxx.xxx.xxx.xxx/xx   xxx.xxx.xxx.xxx,xxx.xxx.xxx.xxx......
     * @param ips
     * @return
     */
    public boolean checkIPListInvalid(String ips) {
        boolean validFlag = false;
        if(ips.contains("/")) {
            String[] tmp = ips.split("/");
            if(tmp.length != 2) {
                return false;
            }
            int mask = Integer.parseInt(tmp[1]);
            if(isIP(tmp[0]) && mask >= 0 && mask <= 32) {
                validFlag = true;
            }
        } else if(ips.contains(",")) {
            for(String ip : ips.split(",")) {
                validFlag = isIP(ip);
                if (!validFlag) {
                    break;
                }
            }
        } else {
            validFlag = isIP(ips);
        }
        return validFlag;
    }
    /**
     * 判断IP是否在名单里
     * @param ip
     * @param ipTables  10.10.10.0-10.10.10.100 or 10.10.10.1
     * @return
     */
    public static boolean isIpInIpList(String ip, List<String> ipTables) {
        boolean isIpInIpList = false;

        for (String ipStr : ipTables) {
            if (IpUtils.isSingleIP(ipStr) && !IpUtils.isIP(ipStr)) {
                continue;
            } else if (IpUtils.isSingleIP(ipStr)) {
                if (ip.equals(ipStr)) {
                    isIpInIpList = true;
                    break;
                }
            } else {
                long ipNum = IpUtils.ip2Long(ip);
                String[] ips = ipStr.split("-");
                long start = IpUtils.ip2Long(ips[0]);
                long end = IpUtils.ip2Long(ips[1]);
                long currentIp = IpUtils.ip2Long(ip);
                if (start != 0L && end != 0L && currentIp >= start && currentIp <= end) {
                    isIpInIpList = true;
                    break;
                }
            }
        }
        return isIpInIpList;
    }

    /**
     * 判断ip是否在数据库table中
     * @param ip
     * @param tableList eg：10.10.10.0/24  or 10.10.10.1
     * @return
     */
    public static boolean isIpInDBIpList(String ip, List<String> tableList) {
        boolean isIpInDBIpList = false;
        for (String ipStr : tableList) {
            if (ipStr.contains("/")) {
                String[] ipPart = ipStr.split("/");
                int netMask = Integer.parseInt(ipPart[1]);
                String[] subIps = ipPart[0].split("\\.");
                StringBuilder ipStart = null;
                StringBuilder ipEnd = null;
                if (netMask == 24) {
                    ipStart = new StringBuilder();
                    ipEnd = new StringBuilder();
                    ipStart.append(subIps[0]).append(".").
                            append(subIps[1]).append(".").
                            append(subIps[2]).append(".0");
                    ipEnd.append(subIps[0]).append(".").
                            append(subIps[1]).append(".").
                            append(subIps[2]).append(".255");
                } else if (netMask == 18) {
                    ipStart = new StringBuilder();
                    ipEnd = new StringBuilder();
                    ipStart.append(subIps[0]).append(".").
                            append(subIps[1]).append(".0.0");
                    ipEnd.append(subIps[0]).append(".").
                            append(subIps[1]).append(".255.255");
                } else if (netMask == 8) {
                    ipStart = new StringBuilder();
                    ipEnd = new StringBuilder();
                    ipStart.append(subIps[0]).append(".0.0.0");
                    ipEnd.append(subIps[0]).append(".255.255.255");
                } else {
                    if (IpUtils.isIP(ipPart[0]) && ip.equals(ipPart[0])) {
                        isIpInDBIpList = true;
                    }
                }

                if (ipStart != null && ipEnd != null) {
                    long currentIpNum = IpUtils.ip2Long(ip);
                    long startIpNum = IpUtils.ip2Long(ipStart.toString());
                    long endIpNum = IpUtils.ip2Long(ipEnd.toString());
                    if (currentIpNum >= startIpNum && currentIpNum <= endIpNum) {
                        isIpInDBIpList = true;
                        break;
                    }
                }
            } else if (ipStr.contains(",")) {
                String[] ips = ipStr.split(",");
                for (String ipTemp : ips) {
                    if (ip.equals(ipTemp)) {
                        isIpInDBIpList = true;
                        break;
                    }
                }
            } else {
                if (ip.equals(ipStr)) {
                    isIpInDBIpList = true;
                    break;
                }
            }
        }
        return isIpInDBIpList;
    }
}
