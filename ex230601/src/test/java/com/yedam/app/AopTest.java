package com.yedam.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.app.aop.AaaService;
import com.yedam.app.aop.Cats;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class AopTest {

	@Autowired
	Cats myCat;
	
	@Autowired
	AaaService aService;
	
	@Test
	public void aopTest() {
		Cats cat = new Cats(); 	// new 연산자를 만들면 spring 이 관여하지 않는 부분임.
		cat.printInfo();
		System.out.println();
		myCat.printInfo();
	}
	
	@Test
	public void txTest() {
		aService.insert();
	}
	
	
}