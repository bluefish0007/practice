package com.springinaction.springidol;

import org.springframework.stereotype.Component;

/**
 * Created by tianjiyuan on 16/3/20.
 */


@Component
public class Guitar implements Instrument {
    public void play() {
        System.out.println(getClass().getName() + ": BOON BOON BOON");
    }
}
