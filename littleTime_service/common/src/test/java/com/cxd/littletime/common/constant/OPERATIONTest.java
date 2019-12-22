package com.cxd.littletime.common.constant;

import com.cxd.littletime.common.util.TestInfoUtils;
import org.junit.*;

/**
 * OPERATION Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十一月 18, 2019</pre>
 */
public class OPERATIONTest {
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
    /**
     * Method: toString()
     */
    @Test
    public void testToString() throws Exception {
        Assert.assertEquals("REGISTER",OPERATION.REGISTER.toString());
        Assert.assertEquals("LOGIN",OPERATION.LOGIN.toString());
        Assert.assertEquals("CHANGE_PASSWORD",OPERATION.CHANGE_PASSWORD.toString());
        Assert.assertEquals("DELETE_PASSWORD",OPERATION.DELETE_PASSWORD.toString());
        Assert.assertEquals("ENCRYPT",OPERATION.ENCRYPT.toString());
        Assert.assertEquals("DECRYPT",OPERATION.DECRYPT.toString());
    }

    /**
     * Method: getNameByIndex(int index)
     */
    @Test
    public void testGetNameByIndex() throws Exception {
        Assert.assertEquals(OPERATION.REGISTER,OPERATION.getNameByIndex(0));
        Assert.assertEquals(OPERATION.LOGIN,OPERATION.getNameByIndex(1));
        Assert.assertEquals(OPERATION.CHANGE_PASSWORD,OPERATION.getNameByIndex(2));
        Assert.assertEquals(OPERATION.DELETE_PASSWORD,OPERATION.getNameByIndex(3));
        Assert.assertEquals(OPERATION.ENCRYPT,OPERATION.getNameByIndex(4));
        Assert.assertEquals(OPERATION.DECRYPT,OPERATION.getNameByIndex(5));
    }


} 
