package com.haiyoung.hyweb.web.controller;

import com.haiyoung.hyweb.biz.user.User;
import com.haiyoung.hyweb.biz.user.UserService;
import com.haiyoung.hyweb.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AuthenticateController {

    @Autowired
    private UserService userService;

    @RequestMapping("/authenticate")
    public boolean authenticate(
            HttpServletRequest request,
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "password") String password){

        if(StringUtils.isNotEmpty(userId)){
            User user = userService.getUserById(userId);
            if(user != null){
                if(password.equals(user.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }
}
