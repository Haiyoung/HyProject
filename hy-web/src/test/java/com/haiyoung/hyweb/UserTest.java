package com.haiyoung.hyweb;

import com.haiyoung.hyweb.biz.user.User;
import com.haiyoung.hyweb.biz.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void userToStringTest(){
        User user = userService.getUserById("haiyoung");
        System.out.println(user.toString());
    }
}
