package com.haiyoung.hyweb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/page")
public class LoginController{

    @RequestMapping("/login")
    private String login(Map<String,Object> map){

        return "login";
    }
}