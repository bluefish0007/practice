package com.springinaction.springidol;

import com.bluefish.util.Bluefish;

/**
 * Created by tianjiyuan on 16/3/29.
 */
public class ContestantImp implements Contestant {

    public void receiveAward() {
        Bluefish.out.println(this, "receiveAward");
    }
}
