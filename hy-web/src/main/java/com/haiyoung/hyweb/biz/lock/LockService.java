package com.haiyoung.hyweb.biz.lock;

import com.haiyoung.hyweb.biz.file.FileService;
import com.haiyoung.hyweb.mapper.LockMapper;
import com.haiyoung.hyweb.util.CodecUtils;
import com.haiyoung.hyweb.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.util.Date;

@Service
public class LockService {
    private static final Logger log = LoggerFactory.getLogger(LockService.class);

    @Autowired
    private LockMapper lockMapper;

    private String getCurrentThread(){
        String jvm = ManagementFactory.getRuntimeMXBean().getName();
        String threadId = String.valueOf(Thread.currentThread().getId());
        return threadId + "@" + jvm;
    }

    public LockInfo tryLock(String lockType, String lockObject, String lockThread) {
        Lock lock = null;
        lock = lockMapper.getLockByUnionKey(lockType, lockObject, lockThread);
        if (lock != null) {
            return new LockInfo(lock.getId(), false);
        }
        try {
            String lockId = CodecUtils.generateGUID();
            lockMapper.createLock(
                    lockId,
                    lockThread,
                    lockType,
                    lockObject,
                    new Date()
            );
            return new LockInfo(lockId, true);
        } catch (Exception e) {
            log.warn(String.format("对 %s 加锁失败", lockType + "[" + lockObject + "]"));
            return null;
        }
    }

    public void unLock(String lockId, boolean force){
        if(StringUtils.isEmpty(lockId)){
            return;
        }
        try{
            lockMapper.unLockById(lockId,getCurrentThread(),force);
        }catch (Exception e){
            log.warn(String.format("对 %s 解锁失败", "lock" + "[" + lockId + "]"));
        }
    }

    public void unLock(String lockType, String lockObject, boolean force){
        if(StringUtils.isEmpty(lockType) || StringUtils.isEmpty(lockObject)){
            return;
        }
        try{
            lockMapper.unLockByUnionKey(lockType,lockObject,getCurrentThread(),force);
        }catch (Exception e){
            log.warn(String.format("对 %s 解锁失败", lockType + "[" + lockObject + "]"));
        }
    }
}
