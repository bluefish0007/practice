package com.springinaction.springidol;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by tianjiyuan on 16/3/15.
 */
public class Auditorium {

    public void turnOnLights(){
        System.out.println("turn On the Lights");
    }

    public void turnOffLights(){
        System.out.println("turn Off the Lights");
    }

    public  static void main(String[]args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-idol.xml");
        Object c = context.getBean("auditorium");
        c = null;
        System.gc();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Created by tianjiyuan on 16/3/20.
     */
    @Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Qualifier
    public static @interface StringedInstrument {
    }
}
