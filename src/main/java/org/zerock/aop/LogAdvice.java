package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;


//설정의 첫번째 aspect인지 알려준다. 
@Aspect
@Log4j
//두번째 스프링이 컴퍼넌트인지 알려준다. 스프링의 빈이 관리해달라고 정한다.
@Component
public class LogAdvice {

	
	@Around("execution(* org.zerock.service.*Service*.*(..))")
	  public Object logTime( ProceedingJoinPoint pjp) {
	    
	    long start = System.currentTimeMillis();
	    log.info("Target: " + pjp.getTarget());
	    log.info("Param: " + Arrays.toString(pjp.getArgs()));
	    //invoke method 
	    Object result = null;
	    
	    try {
	      result = pjp.proceed();
	    } catch (Throwable e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    
	    long end = System.currentTimeMillis();
	    log.info("TIME: "  + (end - start));
	    
	    return result;
	  }

//	@After("execution(* org.zerock.service.SampleService*.*(..)")
//	public void logAfter() {
//		log.info("========================");
//		log.info("=            끝                    =");
//		log.info("========================");
//	}
}
