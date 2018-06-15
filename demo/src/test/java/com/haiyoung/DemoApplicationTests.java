package com.haiyoung;

import com.haiyoung.restTemplate.demo.RestTemplateTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private RestTemplateTest restTemplateTest;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRest(){
		restTemplateTest.postTest();
	}

}
