package com.haiyoung.hyweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessTest {

    @Test
    public void processTest() throws IOException{

        Process process = Runtime.getRuntime().exec("ls -l", null, new File("/home/haiyoung"));
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while(br.read() != -1){
            System.out.println(br.readLine());
        }
//        System.out.println(process.getInputStream());
    }
}
