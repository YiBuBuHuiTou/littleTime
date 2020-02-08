package com.cxd.littletime.common.util;

import com.cxd.littletime.common.constant.ENCRYPT_TYPE;
import org.junit.*;

import java.lang.reflect.Method;

public class CryptoUtilsTest {
    @Rule
    public TestInfoUtils testInfo = new TestInfoUtils();

    @Before
    public void before() throws Exception {
        System.out.println("被测试类: " + testInfo.getClassName() + ", 测试用例名: " + testInfo.getMethodName() + "开始测试");
    }

    @After
    public void after() throws Exception {
        System.out.println("被测试类: " + testInfo.getClassName() + ", 测试用例名: " + testInfo.getMethodName() + "结束测试");
    }


    @Test(timeout = 1000)
    public void symmetricEncrypt() {
        Assert.assertEquals("qFQ90G/277RVgLPj1bqG9g==", CryptoUtils.symmetricEncrypt("justyou", ENCRYPT_TYPE.AES,"AES加密测试"));
    }

    @Test(timeout = 1000)
    public void symmetricDecrypt() {
        Assert.assertEquals("AES加密测试", CryptoUtils.symmetricDecrypt("justyou",ENCRYPT_TYPE.AES,"qFQ90G/277RVgLPj1bqG9g=="));
    }

    @Test
    public void asymmetricDecrypt() {
        //TODO 非对称加密
    }

    @Test
    public void asymmetricEncrypt() {
        //TODO 非对称加密
    }

    @Test
    public void hashAlgorithm() throws Exception {
        Assert.assertEquals("299f7f705b183f35a29b258f88ce93d3", CryptoUtils.hashAlgorithm(ENCRYPT_TYPE.MD5,"MD5计算摘要"));
    }
    // 测试私有方法， 私有方法不测
    @Deprecated
    @Test(timeout = 1000)
    public void aesEncrypt() throws Exception {
        Method method = new CryptoUtils().getClass().getDeclaredMethod("aesEncrypt", String.class, String.class,String.class);
        method.setAccessible(true);
        Assert.assertEquals("qFQ90G/277RVgLPj1bqG9g==",method.invoke(new CryptoUtils(),"justyou","AES加密测试","chenxdlovedaiyy."));
    }
}