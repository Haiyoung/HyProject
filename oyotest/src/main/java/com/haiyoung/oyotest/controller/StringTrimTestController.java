package com.haiyoung.oyotest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Haiyang
 * @Date: 2018/12/16 下午9:59
 */

@RestController
public class StringTrimTestController {

    @GetMapping(value = "/stringStrim")
    public String stringTrim(@RequestParam(value = "string") String string){
        return string;
    }

    @GetMapping(value = "/ping")
    public String ping(){
        return "pong";
    }
}
