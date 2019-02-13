package com.haiyoung.distributedlock.jedisDistributedLock;


import redis.clients.jedis.Jedis;

public class JedisFactory {

    public static Jedis getJedis(){
        try{
            return new Jedis("127.0.0.1", 6379);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
