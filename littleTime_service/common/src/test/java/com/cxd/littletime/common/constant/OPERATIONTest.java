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
        Assert.assertEquals(OPERATION.REGISTER.toString(),"REGISTER");
        Assert.assertEquals(OPERATION.LOGIN.toString(),"LOGIN");
        Assert.assertEquals(OPERATION.CHANGE_PASSWORD.toString(), "CHANGE_PASSWORD");
        Assert.assertEquals(OPERATION.DELETE_PASSWORD.toString(), "DELETE_PASSWORD");
        Assert.assertEquals(OPERATION.ENCRYPT.toString(), "ENCRYPT");
        Assert.assertEquals(OPERATION.DECRYPT.toString(),"DECRYPT");
    }

    /**
     * Method: getNameByIndex(int index)
     */
    @Test
    public void testGetNameByIndex() throws Exception {
        Assert.assertEquals(OPERATION.getNameByIndex(0),OPERATION.REGISTER);
        Assert.assertEquals(OPERATION.getNameByIndex(1),OPERATION.LOGIN);
        Assert.assertEquals(OPERATION.getNameByIndex(2),OPERATION.CHANGE_PASSWORD);
        Assert.assertEquals(OPERATION.getNameByIndex(3),OPERATION.DELETE_PASSWORD);
        Assert.assertEquals(OPERATION.getNameByIndex(4),OPERATION.ENCRYPT);
        Assert.assertEquals(OPERATION.getNameByIndex(5),OPERATION.DECRYPT);
    }


} 
