package com.springinaction.springidol;

/**
 * Created by tianjiyuan on 16/3/14.
 */
public class PoeticJuggler extends Juggler {

    private Poem poem;

    public PoeticJuggler(int beanBags , Poem poem){
        super(beanBags);
        this.poem = poem;
    }
    public PoeticJuggler(Poem poem){
        this.poem = poem;
    }

    @Override
    public void perform() throws PerformerException {
        super.perform();
        System.out.println("While recition ..... ");
        poem.recite();
    }
}
