package com.haiyoung.hyweb;

import com.haiyoung.hyweb.biz.lock.Lock;
import com.haiyoung.hyweb.mapper.LockMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
