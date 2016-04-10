package com.springinaction.springidol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by tianjiyuan on 16/3/15.
 */
public class Instrumentalist implements Performer {

    @Value("sey")
    private String song;

    public Instrumentalist(){

    }


    public Instrumentalist(Instrument instrument){
        this.instrument = instrument;
    }

    public void perform() throws PerformerException {
        System.out.println("Play " + song);
        instrument.play();
    }

    public void setSong(String song){
        this.song = song;
    }

    public String screamSong(){
        return song;
    }

    @Autowired
    //@Auditorium.StringedInstrument
    private Instrument instrument;

   // @Autowired
    public void setInstrument(Instrument instrument){
        this.instrument = instrument;
    }



    public void heresYourInstrument( Instrument  instrument){
        this.instrument = instrument;
    }


    public static void main(String [] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-idol.xml");
        Instrumentalist author = (Instrumentalist)context.getBean("kenny");
        author.perform();
    }
}
