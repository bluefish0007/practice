package com.springinaction.springidol;

import com.bluefish.util.Bluefish;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by tianjiyuan on 16/3/29.
 */
public class AroundAudience {

    private void around(ProceedingJoinPoint joinPoint){
        Bluefish.out.println(this, "heihie");
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}