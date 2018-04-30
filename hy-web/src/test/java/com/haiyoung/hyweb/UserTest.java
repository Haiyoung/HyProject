package com.haiyoung.hyweb;

import com.haiyoung.hyweb.biz.user.Role;
import com.haiyoung.hyweb.biz.user.User;
import com.haiyoung.hyweb.biz.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void rolesTest(){
        List<Role> list = userService.getUsers();
        list.forEach(user ->{
            System.out.println(user.getRoleId()+":"+user.getRoleName());
        });
    }

    @Test
    public void rolesTest2(){
        List<String> paramsList = new ArrayList<>();
        paramsList.add("R900");
        paramsList.add("R200");
        List<Role> list = userService.getUsers(paramsList);
        list.forEach(user ->{
            System.out.println(user.getRoleId()+":"+user.getRoleName());
        });
    }
}
