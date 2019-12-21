package com.cxd.littletime.common.util;

import com.cxd.littletime.common.constant.ENCRYPT_TYPE;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

/**
 * @author YiBuBuHuiTou
 */
public class GenerateSignUtils {

    /**
     * 根据parameter 生成签名（使用时间戳作为key，时间戳的补足16字节作向量加密后制作摘要验证请求，）
     * @param parameters
     * @return
     */
    public static String generateSignatureByAES(SortedMap<String, String> parameters) throws Exception {
        String signature = null;
        // 判断参数是否为空（参数至少大于2否则是非法参数：sign）
        if (parameters != null && parameters.size() > 2) {
            String timeStamp = parameters.get("timeStamp");
            // AES 解密 向量 key 忘记实现了

            if (false) {
                String ivParameter = null;
                if (StringUtils.isEmpty(timeStamp)) {
                    return null;
                } else {
                    int timeStampSize = timeStamp.length();
                    // 字符串少于16字节补足16字节
                    if (timeStampSize <= 16) {
//                    for (int i = 16;; i--) {
//                        StringBuilder prefix = new StringBuilder();
//                        if (i == timeStampSize) {
//                            for (int j = 0; j < 16 - timeStampSize; j++) {
//                                prefix.append(j);
//                            }
//                        }
//                        ivParameter = prefix.append(timeStamp).toString();
//                        if (ivParameter.length() >= 16) {
//                            break;
//                        }
//                    }
                        int lack = 16 - timeStampSize;
                        StringBuilder prefix = new StringBuilder();
                        for (int i = 0; i <= lack; i++) {
                            prefix.append(i);
                        }
                        ivParameter = prefix.append(timeStamp).toString();
                        //多于16字节取前16字节
                    } else {
                        ivParameter = timeStamp.substring(0, 16);
                    }
                }
            }

            //生成计算摘要的字符串
            StringBuilder sortedString = new StringBuilder();
            Iterator<Map.Entry<String, String>> it =  parameters.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> keyAndValue = it.next();
                sortedString.append("&").append(keyAndValue.getKey()).append("=").append(specialUrlEncode(keyAndValue.getValue()));
            }
            try {
                // 使用SHA256算法计算摘要
                signature = CryptoUtils.hashAlgorithm(ENCRYPT_TYPE.SHA256, sortedString.toString());
            } catch (NoSuchAlgorithmException e) {
                LogUtils.getDebugLogger().error("签名生成失败。没有找到该算法： " + ENCRYPT_TYPE.SHA256);
                return null;
            }
        }
        return signature;
    }

    private static String specialUrlEncode(String value) throws Exception {
        return java.net.URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }
}
