package com.bluefish.sockJdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable{

	private Socket socket;
	public static final String QUERY_COMMAND = "QUERY TIME ORDER";
	public TimeServerHandler(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run() {
		if(socket == null){
			return;
		}
		System.out.println(Thread.currentThread());
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(),true);
			String content = null;
			while((content = reader.readLine()) != null){
				String retValue = "BAD COMMAND";
				if(QUERY_COMMAND.equals(content)){
					retValue = new Date(System.currentTimeMillis()).toString();
				}
				writer.println(retValue);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
