package com.haiyoung.distributedlock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-02-13 11:17
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkLockCuratorDemoTest {

    // ZooKeeper 锁节点路径, 分布式锁的相关操作都是在这个节点上进行
    private final String lockPath = "/dis2-lock";
    // ZooKeeper 服务地址, 单机格式为:(0.0.0.0:2181), 集群格式为:(0.0.0.0:2181,0.0.0.0:2182,0.0.0.0:2183)
    private String connectString;
    // Curator 客户端重试策略
    private RetryPolicy retry;
    // Curator 客户端对象
    private CuratorFramework client;

    // 初始化资源
    @Before
    public void init() throws Exception {
        // 设置 ZooKeeper 服务地址为本机的 2181 端口
        connectString = "0.0.0.0:2181";
        // 重试策略
        // 初始休眠时间为 1000ms, 最大重试次数为 3
        retry = new ExponentialBackoffRetry(1000, 3);
        // 创建一个客户端, 60000(ms)为 session 超时时间, 15000(ms)为链接超时时间
        client = CuratorFrameworkFactory.newClient(connectString, 60000, 15000, retry);
        // 创建会话
        client.start();
    }

    @After
    public void close() {
        CloseableUtils.closeQuietly(client);
    }

    @Test
    public void sharedLock() throws Exception {
        // 创建共享锁
        InterProcessLock lock = new InterProcessSemaphoreMutex(client, lockPath);

        // 获取锁对象
        lock.acquire();

        // 测试是否可以重入
        // 超时获取锁对象(第一个参数为时间, 第二个参数为时间单位), 因为锁已经被获取, 所以返回 false
        Assert.assertFalse(lock.acquire(2, TimeUnit.SECONDS));
        // 释放锁
        lock.release();
    }
}
