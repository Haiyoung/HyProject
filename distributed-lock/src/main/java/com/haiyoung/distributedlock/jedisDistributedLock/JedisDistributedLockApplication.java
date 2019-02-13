package com.haiyoung.distributedlock.jedisDistributedLock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JedisDistributedLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(JedisDistributedLockApplication.class, args);
	}
}
