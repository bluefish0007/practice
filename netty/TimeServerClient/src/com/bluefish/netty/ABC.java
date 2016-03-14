package com.bluefish.netty;

public class ABC {
	
	// 2^64 / 2^6  = 2^58 = 2^20* 2^20* 2*20 * 8 bytes  
	static long calc(long container , long repetTimes, long num){
		
		container = container | (1 << num);
		
		return container ;
	}
	

	public static void main(String [] args){
		
		long footMark = 0L;
		long repetTimes = 0L;
//		long footMarks[] = new long [2];
		
		Node head  = null;
		for(long i = 0 ; i < 288230376151711744L ; ++ i ){
			Node node = new Node();
			if(head == null){
				head = node;
			}else{
				node.next = head.next;
				head.next = node;
				
			}
		}
		long numbers[] = {2,3,4,3,5,7,8,2,4};
		long minNum = numbers[0];
		for(long number : numbers){
			footMark = calc(footMark , repetTimes,number);
		}
		int index = 0 ;
		while((footMark >> minNum & 1L) != 0){
			index ++ ;
			footMark = footMark>> minNum;
			minNum = 1;
		}
		System.out.println(index);
//		long num = 1 << 10 ;
//		container = container & num;
		
	}
}
class Node{
	long footMark;
	Node next;
	int index;
}
