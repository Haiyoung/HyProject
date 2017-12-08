package com.haiyoung.hyweb;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@EnableScheduling
public class HyWebApplication {

	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
//		SpringApplication.run(HyWebApplication.class, args);
/*		SpringApplication application = new SpringApplication(HyWebApplication.class);
//		application.setBannerMode(Banner.Mode.OFF);
		*//*获取所有启动时注入的bean*//*
		application.run(args);*/
		ApplicationContext ctx = SpringApplication.run(HyWebApplication.class, args);
		String[] beanNames = ctx.getBeanDefinitionNames();
		for(String item:beanNames){
			System.out.println(item);
		}
	}
}
