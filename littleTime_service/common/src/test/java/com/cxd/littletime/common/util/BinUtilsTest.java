package com.cxd.littletime.common.util;

import org.junit.Assert;
import org.junit.Test;

public class BinUtilsTest {

    @Test
    public void getBit() {
        Assert.assertTrue(BinUtils.getBit("010101010110",4));
    }

    @Test
    public void testGetBit() {
        Assert.assertTrue(BinUtils.getBit(16,4));
    }
}