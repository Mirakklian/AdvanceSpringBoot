package com.rest.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ExecutionTimeTrackerAdvice {
	
private Logger LOG;

	
	@Autowired
	public ExecutionTimeTrackerAdvice(Logger LOG) {
		super();
		this.LOG = LOG;
	}
	
	@Around("@annotation(com.rest.advice.LogExecutionTime)")
	public Object executionTimeLogger(ProceedingJoinPoint poinPoint) throws Throwable {
		
		MethodSignature methodSign=(MethodSignature)poinPoint.getSignature();
		Class<?> className=methodSign.getDeclaringType();
		Long startTime=System.currentTimeMillis();
		//LOG.info("==============================Log Time Execution Begin=========================");
		//LOG.info("{} execuution Started---> {}",className.getName(),startTime);
		
		Object result=poinPoint.proceed();
		
		Long endTime=System.currentTimeMillis();
		//LOG.info("{} execuution Started---> {}",methodName,endTime);
		LOG.info("[{}] [{}] Total Execution Time---> {} ms",className.getSimpleName(),className.getName(),(endTime-startTime));
		//LOG.info("==============================Log Time Execution End============================");
		
		return result;
	}

}
