package com.cms.test.aspect;


import com.cms.test.exception.CustomException;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Around("* com.cms.test.controller.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            log.info("request init for path={}", request.getPathInfo());
            Object proceed = joinPoint.proceed();
            log.info("request end for path={}", request.getPathInfo());
            return proceed;
        }
        catch (Exception ex){
            log.error("logExecutionTime ex={}",ex.getMessage());
            if(ex instanceof CustomException){
                throw (CustomException) ex;
            }
            if(ex instanceof RuntimeException){
                throw (RuntimeException) ex;
            }
            throw ex;
        }
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
