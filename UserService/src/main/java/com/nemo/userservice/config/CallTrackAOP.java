package com.nemo.userservice.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class CallTrackAOP {

    @Pointcut("within(com.nemo.userservice.service.*)")
    public void logMethodPointCut(){

    }

    @Around("logMethodPointCut()")
    public Object LogBeforeMethodCall(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        log.info("method started " + proceedingJoinPoint.getSignature().getName());

        Object returnValue=proceedingJoinPoint.proceed();
        System.out.println(returnValue);

        log.info("method completed:  "+proceedingJoinPoint.getSignature().getName());
        return  returnValue;

    }

}
