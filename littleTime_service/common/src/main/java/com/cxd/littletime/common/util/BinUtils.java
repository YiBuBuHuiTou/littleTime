package com.cxd.littletime.common.util;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/21 23:48
 * @Version 1.0
 **/
public class BinUtils {

    public static boolean getBit(String bin, int bitNum) {
        StringBuilder sb = new StringBuilder(bin);

        char bit = sb.reverse().charAt(bitNum);
        return bit == '0' ? false : true;
    }

    public static boolean getBit(int bin, int bitNum) {
        String binStr = Integer.toString(bin, 2);
        if (bin < 0) {
            return false;
        }
        return getBit(binStr, bitNum);
    }
}
