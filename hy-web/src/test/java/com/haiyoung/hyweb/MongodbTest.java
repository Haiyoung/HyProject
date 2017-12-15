package com.haiyoung.hyweb;

import com.haiyoung.hyweb.biz.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void saveUser(){
        User user = new User();
        user.setId(2);
        user.setUserId("test1");
        user.setPassword("1234");
        user.setUserName("test1");
        mongoTemplate.save(user);
        mongoTemplate.insert(user, "mongoTest");
        System.out.println("mongoTemplate.save(user);");
    }

}
