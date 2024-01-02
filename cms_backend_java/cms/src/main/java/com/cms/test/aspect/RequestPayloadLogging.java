package com.cms.test.aspect;


import com.cms.test.validation.ToJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class RequestPayloadLogging {

    ObjectMapper objectMapper = new ObjectMapper();

    @Around("@annotation(com.cms.test.validation.ToJson)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

    @Before(value = "execution(* com.cms.test.controller.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String path = request.getRequestURI();
        log.info("requestInfo path={}",path);
//      Object[] objects   = joinPoint.getArgs();
//     Class cls = objects[0].getClass();
//     if(cls.getAnnotation(ToJson.class)!=null){
//
//
//     }
        System.out.println("Before method:" + joinPoint.getSignature());
    }
}
