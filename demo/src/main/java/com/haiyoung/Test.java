package com.haiyoung;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Haiyoung on 2018/1/21.
 */
@RestController
@RequestMapping("/test")
public class Test {

    @RequestMapping("/haha")
    public String testHi(){
        return "haha gaga";
    }
}
