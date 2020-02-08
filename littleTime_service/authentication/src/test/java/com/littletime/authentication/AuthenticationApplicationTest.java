package com.littletime.authentication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationApplicationTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate redisTemplate2;
    @Test
    public void testUpdateTenant() {
        redisTemplate.opsForValue().set("test1", "value1");
        redisTemplate2.opsForValue().set("test2", "value2");
    }

}