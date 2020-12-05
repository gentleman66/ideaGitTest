package com.bjpowernode;


import jdk.nashorn.internal.ir.WithNode;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJedis {
    public static void main(String[] args) {
        /*Jedis jedis=new Jedis("192.168.137.109",6379);*/
        JedisPool jedisPool = RedisUtils.open("192.168.137.109", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.flushDB();
        jedis.set("str1","str1芜湖起飞");
        jedis.set("str2","str2降落");
        jedis.append("str1","哈哈");
        System.out.println(jedis.get("str1"));
        System.out.println(jedis.strlen("str1"));
        System.out.println("======================");
        Map<String,String> map=new  HashMap<String,String>();
        map.put("name","吴彦祖");
        map.put("age","40");
        map.put("id","999");
        jedis.hmset("Star",map);
        List<String> tmget=jedis.hmget("Star","id","age","name");
        for(String i : tmget){
            System.out.println(i);
        }
        System.out.println("----------------------------------");
        jedis.lpush("str3","a","b","c");
        System.out.println(jedis.lrange("str3",0,9));
        System.out.println("------------------------------------");
        jedis.sadd("str4","insert","update","delete","select");
        System.out.println(jedis.scard("str4"));
        System.out.println(jedis.smembers("str4"));
        System.out.println(jedis.srandmember("str4",2));
        System.out.println("---------------------------------------------");
        jedis.zadd("student",80,"zhangsan");
        System.out.println(jedis.zrangeWithScores("student",0,1));

        jedisPool.close();
    }
}
