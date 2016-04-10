package com.springinaction.anotation;

import com.bluefish.util.Bluefish;
import org.aspectj.lang.annotation.*;

/**
 * Created by tianjiyuan on 16/3/29.
 */
@Aspect
public class AnotationAudience {
    @Pointcut("execution(* com.springinaction.springidol.Performer.perform(..))")
    public void perform3(){
        Bluefish.out.println(this, "perform");
    }
    @Before("perform3()")
    public void turnOffPhone(){
        Bluefish.out.println(this, "turnOffPhone");
    }

    @Before("perform3()")
    public void tackSeats(){
        Bluefish.out.println(this, "tackSeats");
    }
    @AfterReturning("perform3()")
    public void applaund(){
        Bluefish.out.println(this, "applaund");
    }
    @AfterThrowing("perform3()")
    public void demandRefund(){
        Bluefish.out.println(this, "demandRefund");
    }
}
