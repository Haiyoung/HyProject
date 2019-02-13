package com.haiyoung.distributedlock.redissonDistributedLock;


import redis.clients.jedis.Jedis;

public class JedisFactory2 {

    public static Jedis getJedis(String ip, Integer port){
        try{
            return new Jedis(ip, port);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        Jedis jedis = getJedis("127.0.0.1", 6380);
        jedis.del("test6380");
        System.out.println(jedis.get("test6380"));

        jedis.set("test6380", "xxxxxxxxxxxxx");

        System.out.println(jedis.get("test6380"));

        Jedis jedis1 = getJedis("127.0.0.1", 6381);

        System.out.println(jedis1.get("test6381"));

        jedis1.set("test6381", "xxxxxxxxxxxxx");

        System.out.println(jedis1.get("test6381"));

        Jedis jedis2 = getJedis("127.0.0.1", 6382);

        System.out.println(jedis2.get("test6382"));

        jedis2.set("test6382", "xxxxxxxxxxxxx");

        System.out.println(jedis2.get("test6382"));
    }
}
