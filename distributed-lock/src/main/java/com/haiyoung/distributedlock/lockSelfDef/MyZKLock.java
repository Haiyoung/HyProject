package com.haiyoung.distributedlock.lockSelfDef;

import org.apache.zookeeper.KeeperException;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-02-12 20:31
 * @Version 1.0
 */
public interface MyZKLock {

    /**
     * 尝试加锁,对应加锁步骤1
     *
     * @return 加锁成功，返回true  加锁失败，返回false
     */
    public boolean tryLock() throws KeeperException, InterruptedException;

    /**
     * 同步加锁，对应加锁步骤3
     */
    public void lock() throws KeeperException, InterruptedException;

    /**
     * 解锁，释放锁
     */
    public void unlock();
}
