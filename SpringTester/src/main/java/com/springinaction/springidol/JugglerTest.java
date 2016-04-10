package com.springinaction.springidol;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by tianjiyuan on 16/3/14.
 */
public class JugglerTest {

    public  static void main(String [] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-idol.xml");
        Juggler juggler = (Juggler)context.getBean("duke");
        juggler.perform();

        Juggler juggler2 = (Juggler)context.getBean("duke");
        juggler2.perform();

        System.out.println(juggler == juggler2);
    }
}
