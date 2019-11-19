package com.cxd.littletime.common.crypto;

import com.cxd.littletime.common.constant.ENCRYPT_TYPE;

/**
 * 加密接口
 * @author YiBuBuHuiTou
 */
public interface CryptoCommon {
    /**
     * 加密
     * @param key 加密的key
     * @param algorithm  加密算法
     * @param content   需要加密的内容
     * @return   加密结果
     */
    public String symmetricEncrypt(String key,ENCRYPT_TYPE algorithm, String content);

    /**
     * 加密
     * @param key 加密的key
     * @param algorithm  加密算法
     * @param content   需要加密的内容
     * @return   加密结果
     */
    public String symmetricDecrypt(String key, ENCRYPT_TYPE algorithm, String content);

    /**
     * 加密
     * @param key 加密的key
     * @param algorithm  加密算法
     * @param content   需要加密的内容
     * @return   加密结果
     */
    public String asymmetricEncrypt(String key, ENCRYPT_TYPE algorithm, String content);

    /**
     * 加密
     * @param key 加密的key
     * @param algorithm  加密算法
     * @param content   需要加密的内容
     * @return   加密结果
     */
    public String asymmetricDecrypt(String key, ENCRYPT_TYPE algorithm, String content);

    /**
     * 加密
     * @param algorithm  加密算法
     * @param content   需要加密的内容
     * @return   加密结果
     */
    public String hashAlgorithm(ENCRYPT_TYPE algorithm, String content);
}

