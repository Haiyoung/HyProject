package com.haiyoung.hyweb.web.controller;

import com.haiyoung.hyweb.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/page")
public class LoginController{

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public String login(Map<String,Object> map,
                        @RequestParam(value = "url", required = false) String url){
        //重定向到登录页时，要记录跳转之前的页面url
        if(StringUtils.isNotEmpty(url)){
            map.put("url", url);
        }
        return "login";
    }
}