package com.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class TimeAspect {

//    @Around("execution(* com.web.controller.*.*(..))")
    public Object bandleControllerMethod(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        for (Object o : args) {
            System.out.println(o);
        }
        System.out.println("Aspect start");
        long start = new Date().getTime();
        Object object = point.proceed();
        System.out.println("Aspect 耗时" + (new Date().getTime() - start));
        System.out.println("Aspect end");
        return object;
    }
}
