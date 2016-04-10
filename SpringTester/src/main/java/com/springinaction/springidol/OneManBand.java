package com.springinaction.springidol;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;

/**
 * Created by tianjiyuan on 16/3/15.
 */
public class OneManBand implements Performer {
    public void perform() throws PerformerException {
//        for(String key : instruments.stringPropertyNames()){
//            System.out.println(key + " : " + instruments.get(key) );
//        }
        System.out.println(myProp);
    }

    //private Collection<Instrument> instruments;

    //private  Instrument[] instruments;

    //private Map<String, Instrument> instruments;

    private Properties instruments;

    public void setInstruments(Properties instruments){
        this.instruments = instruments;
    }

    public static void main(String[] args){
        ApplicationContext context  = new ClassPathXmlApplicationContext("spring-idol.xml");
        Performer performer = (Performer)context.getBean("hank");
        performer.perform();
    }

    private Object myProp;

    public void setMyProp(Object myProp){
        this.myProp = myProp;
    }
}
