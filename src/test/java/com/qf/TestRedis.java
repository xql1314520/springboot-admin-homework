package com.qf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith ( SpringRunner. class )
@SpringBootTest
public class TestRedis {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testString(){
        redisTemplate.opsForValue ().set("name","张三");
        redisTemplate.expire ( "name",30,TimeUnit.SECONDS );
        Object name = redisTemplate.opsForValue ( ).get ( "name" );
        System.out.println (name );


    }

}
