package com.haiyoung;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@ResponseBody
public class RequestParamTest {

    @RequestMapping(value = "/testRequestParam")
    public void testRequestParam(HttpServletRequest request,
           @RequestParam(value = "params[]", required = false) String[] params){

        System.out.println(Arrays.toString(params));
//        http://localhost:8080/demo/testRequestParam?params[]=1&params[]=2
    }
}
