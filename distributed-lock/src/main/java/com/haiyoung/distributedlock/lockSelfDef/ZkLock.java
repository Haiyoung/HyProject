package com.haiyoung.distributedlock.lockSelfDef;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-02-12 20:40
 * @Version 1.0
 */
public class ZkLock implements MyZKLock{

    private String LockName = "dis_lock";
    private LockInfo lockInfo;
    // 此变量的目的是确保连接上zookeeper Server
    private CountDownLatch latch = new CountDownLatch(1);
    private String connectString = "0.0.0.0:2181";
    private int sessionTimeout = 60 * 1000;

    // zookeeperk客户端
    private ZooKeeper zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

        @Override
        public void process(WatchedEvent event) {
            if (Event.KeeperState.SyncConnected == event.getState() && Event.EventType.None == event.getType()) {
                latch.countDown();
            }
        }
    });

    // 构造函数
    public ZkLock(LockInfo lockInfo) throws IOException, InterruptedException {
        this.lockInfo = lockInfo;
        latch.await();
    }

    @Override
    public boolean tryLock() {

        try {
            if (zooKeeper.exists(lockInfo.getLockName(), false) != null) {

                String currentOwner = new String(zooKeeper.getData(lockInfo.getLockName(), false, null));
                System.out.println("已经被加上锁了，锁的持有者是：" + currentOwner);
                return false;
            } else {
                zooKeeper.create(lockInfo.getLockName(), lockInfo.getLockOwner().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL);
                return true;
            }

        } catch (KeeperException e) {

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("####################222222222222222####################");
        }

        return false;
    }

    @Override
    public void lock() {

        if (!tryLock()) {
            CountDownLatch releaseSignal = new CountDownLatch(1);
            try {
                zooKeeper.exists(lockInfo.getLockName(), new Watcher() {

                    @Override
                    public void process(WatchedEvent event) {
                        if (lockInfo.getLockName().equals(event.getPath())
                                && Event.EventType.NodeDeleted.equals(event.getType())) {
                            releaseSignal.countDown();
                        }
                    }
                });
                releaseSignal.await();
                // 递归调用自己
                lock();
            } catch (KeeperException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else {
            System.out.println(lockInfo.getLockOwner() + ":上锁成功");
        }

    }

    @Override
    public void unlock() {
        try {
            if (zooKeeper.exists(lockInfo.getLockName(), false) != null) {
                String existOwner = new String(zooKeeper.getData(lockInfo.getLockName(), null, null), "UTF-8");
                if (lockInfo.getLockOwner().equals(existOwner)) {
                    zooKeeper.delete(lockInfo.getLockName(), -1);
                    System.out.println(lockInfo.getLockOwner() + ": 解锁成功");
                } else {
                    System.out.println(lockInfo.getLockOwner() + ":无法释放锁，因为没有获得锁");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
