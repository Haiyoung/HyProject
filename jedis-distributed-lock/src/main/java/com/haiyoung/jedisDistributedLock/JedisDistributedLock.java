package com.haiyoung.jedisDistributedLock;

import redis.clients.jedis.Jedis;

import java.util.Collections;

public class JedisDistributedLock {

    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;

    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    // 获取锁，不设置超时时间
    public static boolean getLock(Jedis jedis, String lockKey, String requestId){
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST);
        if(LOCK_SUCCESS.equals(result)){
            return true;
        }
        return false;
    }

    // 获取锁, 设置超时时间，单位为毫秒
    public static boolean getLock(Jedis jedis, String lockKey, String requestId, Long expireTime){

        /**
         * jedis.set(key, value, nxxx, expx, time)
         *
         * Set the string value as value of the key. The string can't be longer than 1073741824 bytes (1
         * GB).
         * @param key
         * @param value
         * @param NXXX NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key if it already exist.
         * @param EXPX EX|PX, expire time units: EX = seconds; PX = milliseconds
         *
         * @return Status code reply set成功，返回 OK
         */
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if(LOCK_SUCCESS.equals(result)){
            return true;
        }
        return false;
    }

    //释放锁
    public static boolean releaseLock(Jedis jedis, String lockKey, String requestId){

        // Lua脚本
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if(RELEASE_SUCCESS.equals(result)){
            return true;
        }
        return false;
    }
}
