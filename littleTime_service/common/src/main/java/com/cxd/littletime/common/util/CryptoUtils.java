package com.cxd.littletime.common.util;

import com.cxd.littletime.common.constant.ENCRYPT_TYPE;
import com.cxd.littletime.common.exception.NotSupportedKeyException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author YiBuBuHuiTou
 */
public class CryptoUtils {
    /**
     * 秘钥默认向量
     */
    private static  final String ivParameter = "chenxdlovedaiyy.";

    /**
     * 加密方式CBC、ECB
     */
    private static final String AES_ENCRYPT_MODE = "CBC";

    /**
     *加密
     * @param key 加密的key
     * @param algorithm  加密算法
     * @param content   需要加密的内容
     * @return
     */
    public static String symmetricEncrypt(String key, ENCRYPT_TYPE algorithm, String content) {
        String result = null;
        // 判断key 和 内容是否为空，如果为空直接返回null
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(content)) {
            return null;
        }
        switch (algorithm) {
            case AES:
                result = aesEncrypt(key, content);
                break;

            default: break;
        }
        return result;
    }

    /**
     * 解密
     * @param key 加密的key
     * @param algorithm  加密算法
     * @param content   需要加密的内容
     * @return
     */
    public static String symmetricDecrypt(String key, ENCRYPT_TYPE algorithm, String content) {
        String result = null;
        // 判断key 和 内容是否为空，如果为空直接返回null
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(content)) {
            return null;
        }
        switch (algorithm) {
            case AES:
                result = aesDecrypt(key, content);
                break;

            default: break;
        }
        return result;
    }

    /**
     * 未实现
     * @param key 加密的key
     * @param algorithm  加密算法
     * @param content   需要加密的内容
     * @return
     */
    public static String asymmetricDecrypt(String key, ENCRYPT_TYPE algorithm, String content) {
        return null;
    }

    /**
     * 未实现
     * @param key 加密的key
     * @param algorithm  加密算法
     * @param content   需要加密的内容
     * @return
     */
    public static String asymmetricEncrypt(String key, ENCRYPT_TYPE algorithm, String content) {
        return null;
    }

    /**
     * 未实现
     * @param algorithm  加密算法
     * @param content   需要加密的内容
     * @return
     */
    public static String hashAlgorithm(ENCRYPT_TYPE algorithm, String content) throws NoSuchAlgorithmException {
        String result = null;
        switch (algorithm) {
            case MD5:
                // 生成一个MD5摘要
                MessageDigest messageDigest_md5 = MessageDigest.getInstance("MD5");
                // 使用指定字节数组更新摘要
                messageDigest_md5.update(content.getBytes());
                // 最终生成摘要并转为16进制
                result = new BigInteger(1, messageDigest_md5.digest()).toString(16);
                break;
            case SHA256:
                MessageDigest messageDigest_sha256 = MessageDigest.getInstance("SHA-256");
                messageDigest_sha256.update(content.getBytes());
                result = new BigInteger(1, messageDigest_sha256.digest()).toString(16);
                break;
            default: break;


        }
        return result;
    }

    /**
     * AES加密
     * @param key     加密key
     * @param content 加密内容
     * @param ivP     自定义加密向量
     * @return        加密后的base64码
     */
    private static String aesEncrypt(String key, String content, String ivP) {
        String result = null;
        try {
            // 将key转为AES秘钥
            SecretKeySpec secretKeySpec = convertKeyToAESKey(key);

            // 获取密码器
            Cipher cipher = null;
            if ("CBC".equals(AES_ENCRYPT_MODE)) {
                cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                //CBC 模式需要一个向量
                IvParameterSpec iv = new IvParameterSpec(ivP.getBytes());
                // 设置密码器的运行模式（加密/解密），使用秘钥和向量初始化密码器
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec,iv);
            } else if ("ECB".equals(AES_ENCRYPT_MODE)) {
                cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                // 设置密码器的运行模式（加密/解密），使用秘钥初始化密码器
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            } else {
                return null;
            }
            // 取得要加密的字符串的字节数组
            byte[] contentBytes = content.getBytes();
            // 密码器加密数据
            byte[] encrypted = cipher.doFinal(contentBytes);
            // 将加密后的字节数组转为Base64码
            result = Base64.getEncoder().encodeToString(encrypted);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param key      加密key
     * @param content  加密内容
     * @return         密文
     */
    private static String aesEncrypt(String key, String content) {
        return aesEncrypt(key, content, ivParameter);
    }


    /**
     * AES解密
     * @param key
     * @param content
     * @param ivP
     * @return
     */
    private static String aesDecrypt(String key, String content, String ivP) {
        String result = null;
        try {
            // 将秘钥转换为AES专用秘钥
            SecretKeySpec secretKeySpec = convertKeyToAESKey(key);
            //获取密码器
            Cipher cipher = null;
            if ("CBC".equals(AES_ENCRYPT_MODE)) {
                cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                //CBC 模式需要一个向量
                IvParameterSpec iv = new IvParameterSpec(ivP.getBytes());
                // 设置密码器的运行模式（加密/解密），使用秘钥和向量初始化密码器
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            } else if ("ECB".equals(AES_ENCRYPT_MODE)) {
                cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                // 设置密码器的运行模式（加密/解密），使用秘钥初始化密码器
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            } else {
                return null;
            }
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(content));
            result = new String(decrypted);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解密
     * @param key 解密key
     * @param content  密文
     * @return    明文
     */
    private static String aesDecrypt(String key, String content) {
        return aesDecrypt(key, content, ivParameter);
    }

    /**
     * 将key转换为AES专用秘钥
     * @param key
     * @return
     */
    private static SecretKeySpec convertKeyToAESKey(String key) {
        SecretKeySpec secretKeySpec = null;
        try {
            // AES秘钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            // 使用所给的字符串用秘钥生成器生成一个128位(16字节)的秘钥
            keyGenerator.init(128, new SecureRandom(key.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            //获取秘钥的二进制字节数组
            byte[] secretKeyBytes = secretKey.getEncoded();
            //判断秘钥是否合法
            if (secretKeyBytes == null) {
                throw new NotSupportedKeyException(key);
            }
            // 转换为AES专用秘钥
            secretKeySpec = new SecretKeySpec(secretKeyBytes, "AES");
        } catch (NotSupportedKeyException e) {
            //TODO
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            //TODO
            e.printStackTrace();
        }
        return secretKeySpec;
    }
}
