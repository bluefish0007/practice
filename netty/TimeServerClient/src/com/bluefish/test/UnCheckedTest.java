package com.bluefish.test;

public class UnCheckedTest {  
	  
    public static void main(String[] args) {  
        //���ò���Ҫ����  
        unCheckedTest();  
    }  
      
    public static void unCheckedTest() throws RuntimeException{  
        System.out.println("Say,hello world!");  
    }  
      
      
    public static void unCheckedTest2() {  
        System.out.println("Say,hello world!");  
        //���ñ�������׳�  
        throw new RuntimeException();  
    }  
  
} 