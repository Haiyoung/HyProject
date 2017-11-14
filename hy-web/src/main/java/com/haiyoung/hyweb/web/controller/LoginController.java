package com.haiyoung.hyweb.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/page")
public class LoginController{

    private static final Logger log = Logger.getLogger(LoginController.class);

    @RequestMapping("/login")
    private String login(Map<String,Object> map){
        log.info("login page!");
        return "login";
    }
}