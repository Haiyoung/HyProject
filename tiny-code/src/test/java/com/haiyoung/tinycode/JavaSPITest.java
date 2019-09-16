package com.haiyoung.tinycode;

import com.haiyoung.tinycode.spi.Robot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ServiceLoader;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-08-20 12:58
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaSPITest {

    @Test
    public void sayHello() throws Exception {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }

    public static void main(String[] args) {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        for(Robot x : serviceLoader){
            x.sayHello();
        }
    }
}
