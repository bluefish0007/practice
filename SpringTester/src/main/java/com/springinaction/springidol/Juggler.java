package com.springinaction.springidol;

/**
 * Created by tianjiyuan on 16/3/14.
 */
public class Juggler implements Performer {

    private int beanBags = 3;
    public Juggler(){

    }

    public Juggler(int beanBags){
        this.beanBags = beanBags;
    }
    public void perform() throws PerformerException {
        System.out.println("JUGGLING " + beanBags + " BEANBAGS");
    }
}
