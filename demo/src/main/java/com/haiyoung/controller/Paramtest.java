package com.haiyoung.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-03-04 15:58
 * @Version 1.0
 */
@RestController(value = "/test")
public class Paramtest {


    @GetMapping
    public String test(@RequestParam(value = "str", required = false)List<String> stringList){
        System.out.println(stringList);
        return "hello world!";
    }

}
