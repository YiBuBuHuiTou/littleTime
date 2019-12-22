package com.cxd.littletime.common.exception;

import com.cxd.littletime.common.util.TestInfoUtils;
import org.junit.*;

import static org.junit.Assert.*;

public class NotSupportedKeyExceptionTest {
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

    @Test
    public void testGetExceptionMessage() {
        Assert.assertEquals("TEST is not supported! please change another key.",new NotSupportedKeyException("TEST").getExceptionMessage());
    }
    @Test(expected = NotSupportedKeyException.class)
    public void testException() throws NotSupportedKeyException {
        throw new NotSupportedKeyException("testKey");
    }
}