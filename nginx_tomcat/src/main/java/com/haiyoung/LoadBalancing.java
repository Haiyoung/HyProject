package com.haiyoung;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

/**
 * Created by Haiyoung on 2018/5/1.
 */
@RestController
@RequestMapping( value = "/test")
public class LoadBalancing {

    @RequestMapping( value = "/loadBalancing")
    public String loadBalancingTest(HttpServletRequest request) throws UnknownHostException {
        String ip = java.net.InetAddress.getLocalHost().getHostAddress();
        int post = request.getLocalPort();
        System.out.println(ip + ":" + post);
        return ip + ":" + post;
    }
}
