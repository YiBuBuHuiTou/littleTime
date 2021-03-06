package com.cxd.littletime.common.util;

import org.junit.*;

/**
 * StringUtils Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十一月 20, 2019</pre>
 */
public class StringUtilsTest {

    @Rule
    public TestInfoUtils testInfo = new TestInfoUtils();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: isEmpty(String str)
     */
    @Test
    public void testIsEmpty() throws Exception {
        Assert.assertTrue(StringUtils.isEmpty(null));
        Assert.assertTrue(StringUtils.isEmpty(""));
        Assert.assertFalse(StringUtils.isEmpty("test"));
    }
} 
