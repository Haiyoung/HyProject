package com.haiyoung.hyweb.web.controller;

import com.haiyoung.hyweb.biz.user.User;
import com.haiyoung.hyweb.biz.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Haiyoung on 2017/11/30.
 */
@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public User getUser(HttpServletRequest request, @RequestParam(value = "userId") String userId){
        return userService.getUserById(userId);
    }
}
