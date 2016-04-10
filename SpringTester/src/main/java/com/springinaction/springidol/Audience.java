package com.springinaction.springidol;

import com.bluefish.util.Bluefish;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by tianjiyuan on 16/3/27.
 */
public class Audience {
    static Logger logger = Logger.getLogger(Audience.class);
    public void takeSeats(){
       // System.out.println("The Audience is taking their seats.");
        Bluefish.out.println(this, "The Audience is taking their seats.");
    }

    public void turnOffCellPhones(){
        Bluefish.out.println(this, "turn Off CellPhones");
    }

    public void applaund(){
        Bluefish.out.println(this , "CLAP CLAP CLAP");
    }

    public void demandRefound(){
        Bluefish.out.println(this,"Boo We want our money back");
    }

    /**
     * Created by tianjiyuan on 16/3/29.
     */

}
