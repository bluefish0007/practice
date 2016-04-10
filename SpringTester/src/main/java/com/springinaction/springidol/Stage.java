package com.springinaction.springidol;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by tianjiyuan on 16/3/14.
 */
public class Stage {

    private static class StageSingleton{
        public static Stage instance = new Stage();
    }
    private Stage(){

    }

    public static Stage getInstance(){
        return StageSingleton.instance;
    }

    public static void main(String [] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-idol.xml");
        Stage stage = (Stage)context.getBean("stage");
        System.out.println(stage);
    }
}
