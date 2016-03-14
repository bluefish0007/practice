package com.bluefish.sockJdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClientSync {
	
	public static final String QUERY_COMMAND = "QUERY TIME ORDER";
	public static void main(String[] args){
		for(int i = 0 ; i < 10 ; ++ i){
			getTimeOnce();
		}
	}
	private static void getTimeOnce() {
		Socket socket = null;
		
		try{
			socket = new Socket("127.0.0.1", 9999);
		}catch(IOException e){
			e.printStackTrace();
		}
		BufferedReader reader = null;
		PrintWriter writer = null;
		try{
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(),true);
			writer.println(QUERY_COMMAND);
			String content = reader.readLine();
			if(content != null && !"BAD COMMAND".equals(content)){
				System.out.println("Now Time is " + content);
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				if(reader != null){
					reader.close();
				}
				if(writer != null){
					writer.close();
				}
				if(socket != null){
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
