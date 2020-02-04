package com.cxd.littletime.common.util; 

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* JWTUtils Tester. 
* 
* @author <Authors name> 
* @since <pre>2�� 2, 2020</pre> 
* @version 1.0 
*/ 
public class JWTUtilsTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: createJWT(String id, String subject, long ttlMillis) 
* 
*/ 
@Test
public void testCreateJWT() throws Exception { 
    String subject = "{userId=1,pass='123'}";
    Assert.assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ0ZXN0IiwiaXNzIjoibGl0dGxlVGltZSIsImlhdCI6MTU4MDY0OTM0Miwic3ViIjoie3VzZXJJZD0xLHBhc3M9JzEyMyd9IiwiZXhwIjoyNTgwNjQ5MzQyfQ.RkNhjH9DOiXa0R-Va0wh2NP-72-R8peK-hu9T-PBZnE",
            JWTUtils.createJWT("test",subject,1000000000000L));
} 

/** 
* 
* Method: refreshJWT(String token, long ttlMills) 
* 
*/ 
@Test
public void testRefreshJWT() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: parseJWT(String token) 
* 
*/ 
@Test
public void testParseJWT() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: validateJWT(String token) 
* 
*/ 
@Test
public void testValidateJWT() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: getSecurityKey() 
* 
*/ 
@Test
public void testGetSecurityKey() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = JWTUtils.getClass().getMethod("getSecurityKey"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
