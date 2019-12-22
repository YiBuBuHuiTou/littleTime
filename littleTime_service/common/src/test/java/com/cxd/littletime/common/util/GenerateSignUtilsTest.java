package com.cxd.littletime.common.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class GenerateSignUtilsTest {

    @Test(timeout = 100)
    public void generateSignatureByAES() throws Exception{
        SortedMap<String,String> sortedMap = new TreeMap<String,String>();
        sortedMap.put("testKey1","testvalue1");
        sortedMap.put("testvalue1","testKey1");
        sortedMap.put("timeStamp",Long.toString(1576922894903L));
        sortedMap.put("you","me");
        Assert.assertEquals("4fcd007d0eb88b928a2c1ffdc8ad7127aa06eafe1eec22bc809ad95a5833009f",GenerateSignUtils.generateSignatureByAES(sortedMap));
    }
}