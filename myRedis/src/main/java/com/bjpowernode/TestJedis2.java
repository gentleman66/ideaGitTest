package com.bjpowernode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

public class TestJedis2 {
    public static void main(String[] args) {
        JedisPool jedisPool = RedisUtils.open("192.168.137.109", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.flushDB();
        //开启事务
        Transaction multi= jedis.multi();
        multi.set("str1","aaa");
        multi.set("str2","bbb");

        //提交事务
        List<Object> list= multi.exec();
        for (Object i :list){
            System.out.println(i);
        }

        //关闭事务
        multi.close();
    }
}
