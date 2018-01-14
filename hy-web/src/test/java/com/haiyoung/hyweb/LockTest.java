package com.haiyoung.hyweb;

import com.haiyoung.hyweb.biz.lock.Lock;
import com.haiyoung.hyweb.mapper.LockMapper;
import com.haiyoung.hyweb.util.CodecUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LockTest {

    @Autowired
    private LockMapper lockMapper;

    @Test
    public void getTest(){
        Lock lock = lockMapper.getLockById("001");
        System.out.println(lock.toString());
    }

    @Test
    public void getAllTest(){
        List<Lock> list = lockMapper.findAll();
        for(Lock item : list){
            System.out.println(item.toString());
        }
    }

    @Test
    public void getLockByUnionKeyTest() {
        Lock lock = lockMapper.getLockByUnionKey("01", "object_001", "");
        System.out.println(lock.toString());
        System.out.println(lock.getId());
    }

    @Test
    public void lockInsertTest(){
        Date now = new Date();
        lockMapper.createLock(
                CodecUtils.generateGUID(),
                "thread_009",
                "01",
                "object_009",
                now
                );
    }

    @Test
    public void unLockTest(){
        lockMapper.unLockById(
                "78dbebb7cb164b27a766bbf5d6deefb8",
                "thread_009",
                false
        );
    }

    @Test
    public void unLockByUnionKey(){
        lockMapper.unLockByUnionKey(
                "",
                "",
                "",
                false
        );
    }
}
