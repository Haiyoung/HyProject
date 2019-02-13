package com.haiyoung.zookeeperLock.lockSelfDef;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-02-12 20:28
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LockInfo {

    // 锁的名字，体现为zookeeper上的节点名
    private String lockName;
    // 锁的持有者，体现为zookeeper锁节点的数据
    private String lockOwner;
}
