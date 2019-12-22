package com.cxd.littletime.common.constant;

import com.cxd.littletime.common.util.TestInfoUtils;
import org.junit.*;

/**
 * AUTH_TYPE Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十一月 18, 2019</pre>
 */
public class AUTH_TYPETest {

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
        Assert.assertEquals("ENCRYPTED",AUTH_TYPE.ENCRYPTED.toString());
        Assert.assertEquals("DECRYPTED",AUTH_TYPE.DECRYPTED.toString());
    }

    /**
     * Method: getNameByIndex(int index)
     */
    @Test
    public void testGetNameByIndex() throws Exception {
        Assert.assertEquals(AUTH_TYPE.ENCRYPTED, AUTH_TYPE.getNameByIndex(0));
        Assert.assertEquals(AUTH_TYPE.DECRYPTED, AUTH_TYPE.getNameByIndex(1));
    }

} 
