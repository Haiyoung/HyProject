package com.haiyoung.jedisDistributedLock;

import redis.clients.jedis.Jedis;

import java.util.UUID;

public class LockTest {

    public static void main(String[] args) throws Exception{

        String guid = UUID.randomUUID().toString();

        Jedis jedis = JedisFactory.getJedis();
        boolean result = JedisDistributedLock.getLock(jedis, "haiyoung", guid);

//        JedisDistributedLock.getLock(jedis, "haiyoung001", guid, 1000*30L);

        if(result){
            System.out.println("加锁成功");
        }

        boolean result1 = JedisDistributedLock.releaseLock(jedis, "haiyoung", guid);

        if(result1){
            System.out.println("解锁成功");
        }else{
            System.out.println("解锁失败");
        }

//        boolean result1 = JedisDistributedLock.releaseLock(jedis, "haiyoung001", guid);
//
//        if(result1){
//            System.out.println("解锁成功");
//        }
    }
}
