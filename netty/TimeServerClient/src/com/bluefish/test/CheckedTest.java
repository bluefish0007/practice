package com.bluefish.test;

import java.io.IOException;

public class CheckedTest {  
	  
    public static void main(String[] args) {  
    	
//    	  checkedTest();  
        //����Ҫ��������׳�  
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
        //����Ҫ��������׳����׳�����Ҳ������д����  
        try {  
            throw new IOException();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        System.out.println("Say,hello world!");  
    }  
}  