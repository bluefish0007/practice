package com.bluefish.test;

import java.io.IOException;

public class CheckedTest {  
	  
    public static void main(String[] args) {  
    	
//    	  checkedTest();  
        //必须要捕获或者抛出  
        try {  
            checkedTest();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static void checkedTest() throws IOException{  
        System.out.println("Say,hello world!");  
    }  
  
    public static void checkedTest2() {  
        //必须要被捕获和抛出，抛出后面也不能再写代码  
        try {  
            throw new IOException();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        System.out.println("Say,hello world!");  
    }  
}  