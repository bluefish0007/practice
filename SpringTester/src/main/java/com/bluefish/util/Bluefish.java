package com.bluefish.util;

/**
 * Created by tianjiyuan on 16/3/29.
 */
public class Bluefish {

    public static class out{
        public static void println(Object cls , Object obj){
            System.out.println(cls.getClass().getName() + " : " + obj);
        }
    }
}
