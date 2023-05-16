package com.binubalan.HappyShoppy.AuthService.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;

@Aspect
@Component
public class UserServiceAspect {
    LocalTime startTime;
    @Before(value = "execution(* com.binubalan.HappyShoppy.AuthService.services.UserServiceImpl.getUsers(..))")
    public void beforeAdvise(JoinPoint joinPoint){
        startTime = LocalTime.now();
        System.out.println("AOP Advise called before");
        System.out.println("Signature :" + joinPoint.getSignature());
        System.out.println("Kind :" + joinPoint.getKind());
        System.out.println("Target :" + joinPoint.getTarget().getClass().toGenericString());

    }
    @Before(value = "execution(* com.binubalan.HappyShoppy.AuthService.services.UserServiceImpl.getUsers(..))")
    public void before2Advise(JoinPoint joinPoint){
        startTime = LocalTime.now();
        System.out.println("AOP Advise called before II");
        System.out.println("Signature :" + joinPoint.getSignature());

    }
    @After(value = "execution(* com.binubalan.HappyShoppy.AuthService.services.UserServiceImpl.getUsers(..))")
    public void afterAdvise(JoinPoint joinPoint){
        System.out.println("AOP Advise called After");
        System.out.println("Signature :" + joinPoint.getSignature());
        LocalTime timeNow = LocalTime.now();
        Duration between = Duration.between(startTime, timeNow);
        System.out.println("Time taken to execute: " + between.getNano());
    }
}
