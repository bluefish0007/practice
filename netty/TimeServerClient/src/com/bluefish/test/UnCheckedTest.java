package com.bluefish.test;

public class UnCheckedTest {  
	  
    public static void main(String[] args) {  
        //调用不需要捕获  
        unCheckedTest();  
    }  
      
    public static void unCheckedTest() throws RuntimeException{  
        System.out.println("Say,hello world!");  
    }  
      
      
    public static void unCheckedTest2() {  
        System.out.println("Say,hello world!");  
        //不用被捕获和抛出  
        throw new RuntimeException();  
    }  
  
} 