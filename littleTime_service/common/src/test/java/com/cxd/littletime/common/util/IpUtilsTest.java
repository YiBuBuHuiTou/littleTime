package com.cxd.littletime.common.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class IpUtilsTest {

    @Test
    public void isSingleIP() {
        String ips = "10.10.10.1-10.10.10.222";
        String ip = "106.54.198.188";
        Assert.assertFalse(IpUtils.isSingleIP(ips));
        Assert.assertTrue(IpUtils.isSingleIP(ip));
    }

    @Test
    public void isIP() {
        String ip = "10.198.457.79";
        String lp = "546.88.11.255";
        String ep = "10.10.10.0";
        Assert.assertFalse(IpUtils.isIP(lp));
        Assert.assertFalse(IpUtils.isIP(ip));
        Assert.assertTrue(IpUtils.isIP(ep));

    }

    @Test
    public void ip2Long() {
        String ip = "10.10.10.10";
        Assert.assertEquals(168430090L,IpUtils.ip2Long(ip));
    }
}