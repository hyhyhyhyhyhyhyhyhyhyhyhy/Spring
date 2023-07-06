package com.yedam.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {
	
	// 포인트 컷 : 조인 포인트 (비즈니스 로직과 관련된 메소드) 중에서 Advice(공통코드)가 적용될 메소드
	@Pointcut("within(com.yedam.app.aop.*)") // 괄호 안 : 필터링 
	public void allPointCut() {} 
	
	// 메소드 명이 포인트 컷을 지칭하는 이름이 될 것
	
	// Weaving : 포인트 컷에 적용하고자 하는 + Advice + 동작시점 (3가지를 적용 해야함)0
	@Around("allPointCut()")	// around : 앞뒤로 동작시킴 
	public Object logger(ProceedingJoinPoint joinpoint) throws Throwable {
		// Aop가 적용되는 메서드의 이름
		String signatureStr = joinpoint.getSignature().toString();
		System.out.println("시작 : " + signatureStr);
		
		// 공통기능
		System.out.println("핵심 기능 전 실행 - 공통기능 : " + System.currentTimeMillis());
		
		try {
			Object obj = joinpoint.proceed(); // 어떤 메소드를 반환할지 모르기 때문에 그냥 최상위 클래스 Object 로 사용
			return obj;
		}finally {
			// 공통기능 (Around를 썼기 때문에)
			System.out.println("핵심 기능 후 실행 - 공통 기능 : " + System.currentTimeMillis());
		}
	}
	
}