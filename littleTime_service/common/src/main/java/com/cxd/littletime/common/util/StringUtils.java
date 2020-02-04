package com.cxd.littletime.common.util;

import java.util.Random;

/**
 * 字符串工具类，所有方法均为静态方法
 * @author YiBuBuHuiTou
 */
public class StringUtils {

    private static final int CREDENTIAL_LENGTH = 18;
    /**
     * 判断字符串是否为空
     * @param str  需要判断的字符串
     * @return      判断结果
     */
    public static boolean isEmpty(String str) {
        boolean result = true;
        if (str!= null && !str.equals("")) {
            result = false;
        }
        return result;
    }

    /**
     * 生成随机身份id
     * @return
     */
    public static String generateCredential() {
        return generateRandomString(CREDENTIAL_LENGTH);
    }
    /**
     * 随机生成字符串
     * @param length
     * @return
     */
    public static String generateRandomString(int length) {
        StringBuilder credential = new StringBuilder();
        Random random = new Random();
        long tmp = -1;
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            switch (number) {
                case 0:
                    tmp = Math.round(Math.random()*25+65);
                    credential.append(String.valueOf((char)tmp));
                    break;
                case 1:
                    tmp = Math.round(Math.random()*25+97);
                    credential.append(String.valueOf((char)tmp));
                    break;
                case 2:
                    credential.append(random.nextInt(10));
                    break;
                default:
                    break;
            }
        }
        return credential.toString();
    }
}
