package com.helper.controlserver.Common.Aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimeChecker {

    @Pointcut(value = "execution(* com.ssafyhelper.controlserver.Domain.Log.Service.LogService.*(..))")
    private void cut() {

    }

    @Around("cut()")
    public Object executionTimeCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();

        try{
            return proceedingJoinPoint.proceed();
        }finally {
            long endTime = System.currentTimeMillis();
            log.info("{} - 실행시간 = {} sec", proceedingJoinPoint.getSignature().toShortString(), (endTime-startTime) / 1000 );
        }
    }
}
