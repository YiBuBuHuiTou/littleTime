package com.cxd.littletime.common.constant;

import com.cxd.littletime.common.util.TestInfoUtils;
import org.junit.*;

/**
 * PERMISSION Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十一月 18, 2019</pre>
 */
public class PERMISSIONTest {

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
        Assert.assertEquals("LOGIN",PERMISSION.LOGIN.toString());
        Assert.assertEquals("REGISTER",PERMISSION.REGISTER.toString());
    }

    /**
     * Method: getNameByIndex(int index)
     */
    @Test
    public void testGetNameByIndex() throws Exception {
        Assert.assertEquals(PERMISSION.REGISTER,PERMISSION.getNameByIndex(0));
        Assert.assertEquals(PERMISSION.LOGIN,PERMISSION.getNameByIndex(1));
    }
} 
