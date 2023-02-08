package com.artiinfosystem.net.utility;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Aspect
@Component
public class MethodLoggingAspect {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("within(com.artiinfosystem.net.controller.CustomerController)")
	public Object logMethod(ProceedingJoinPoint joinpoint) throws Throwable {
		logger.info("*************************API Request *********************");
		MethodSignature signature = (MethodSignature) joinpoint.getSignature();
		RequestMapping requestMapping= signature.getMethod().getDeclaringClass().getAnnotation(RequestMapping.class);
		StringBuilder baseURI =new StringBuilder("[");
		if(null != requestMapping) {
			baseURI.append(requestMapping.value()[0]);
		}
		logger.info(joinpoint.getArgs().length != 0 ? baseURI.append("/").append(joinpoint.getArgs()[0]).append("]").toString(): baseURI.append("]").toString());
		Object object = joinpoint.proceed();
		logger.info("*************************API Response *********************");
		logger.info(object.toString());
		return object;
	}
}
