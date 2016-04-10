package com.springinaction.springidol;

/**
 * Created by tianjiyuan on 16/3/29.
 */
public class ThinkerImp implements Thinker {
    private String thought;
    private String def;
    public void think(String thought, String def) {
        this.thought = thought;
        this.def = def;
    }

    public String getThought(){
        return thought;
    }
}
