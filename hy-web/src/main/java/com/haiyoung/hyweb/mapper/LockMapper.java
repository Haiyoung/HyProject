package com.haiyoung.hyweb.mapper;

import com.haiyoung.hyweb.biz.lock.Lock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface LockMapper {

    //通过id获取锁
    Lock getLockById(@Param("id") String id);

    //通过lockType 和 lockObject联合主键获取锁
    Lock getLockByUnionKey(
            @Param("lockType") String lockType,
            @Param("lockObject") String lockObject,
            @Param("lockThread") String lockThread 
    );

    //获取所有锁
    List<Lock> findAll();

    //添加锁
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void createLock(
            @Param("id") String id,
            @Param("lockThread") String lockThread,
            @Param("lockType") String lockType,
            @Param("lockObject") String lockObject,
            @Param("lockDate") Date lockDate
            );

    //根据id解锁
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void unLockById(
            @Param("id") String id,
            @Param("lockThread") String lockThread,
            @Param("force") boolean force
    );

    //根据unionKey解锁
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void unLockByUnionKey(
            @Param("lockType") String lockType,
            @Param("lockObject") String lockOBject,
            @Param("lockThread") String lockThread,
            @Param("force") boolean force
    );
}
