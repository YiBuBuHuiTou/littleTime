package com.cxd.littletime.common.constant;

import com.cxd.littletime.common.util.TestInfoUtils;
import org.junit.*;

/** 
* ENCRYPT_TYPE Tester. 
* 
* @author <Authors name> 
* @since <pre>十一月 18, 2019</pre> 
* @version 1.0 
*/ 
public class ENCRYPT_TYPETest {

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
    public void testToString() {
        Assert.assertEquals("MD5", ENCRYPT_TYPE.MD5.toString());
        Assert.assertEquals("SHA256", ENCRYPT_TYPE.SHA256.toString());
        Assert.assertEquals("AES",ENCRYPT_TYPE.AES.toString());
    }

    @Test
    public void testGetNameByIndex() {
        Assert.assertEquals(ENCRYPT_TYPE.getNameByIndex(0),ENCRYPT_TYPE.MD5);
        Assert.assertEquals(ENCRYPT_TYPE.getNameByIndex(1),ENCRYPT_TYPE.SHA256);
        Assert.assertEquals(ENCRYPT_TYPE.getNameByIndex(2),ENCRYPT_TYPE.AES);

    }

    @Test
    public void testGetIndex() {
        Assert.assertEquals(0,ENCRYPT_TYPE.MD5.getIndex());
        Assert.assertEquals(1,ENCRYPT_TYPE.SHA256.getIndex());
        Assert.assertEquals(2,ENCRYPT_TYPE.AES.getIndex());
    }
}
