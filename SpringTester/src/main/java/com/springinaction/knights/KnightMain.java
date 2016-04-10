package com.springinaction.knights;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by tianjiyuan on 16/3/14.
 */
public class KnightMain {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Knight knight = (Knight)context.getBean("knight");
        knight.embarkOnRequest();
    }
}
