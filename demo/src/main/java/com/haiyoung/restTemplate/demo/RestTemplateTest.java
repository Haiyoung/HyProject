package com.haiyoung.restTemplate.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;

    public void postTest(){

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        String obj = null;
        try{
            obj = restTemplate.postForObject("https://blog.csdn.net/account090909/articlexx/details/78872142", entity, String.class);
        }catch(Exception e){
            throw e;
        }
        System.out.println(obj);
    }
}
