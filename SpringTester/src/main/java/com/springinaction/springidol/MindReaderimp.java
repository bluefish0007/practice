package com.springinaction.springidol;

/**
 * Created by tianjiyuan on 16/3/29.
 */
public class MindReaderimp implements MindReader {
    private String thought;
    private String def;

    public void interceptThought(String thought, String def) {
        this.thought = thought;
        this.def = def;
    }

    public String getThought() {
        return this.thought + this.def;
    }
}
