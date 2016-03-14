package com.bluefish.sockJdk;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeServerThreadPool {

	public static void main(String [] args){
		
		int port = 9999;
		ServerSocket server = null;
		MyThreadPool poll = new MyThreadPool();
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true){
			Socket socket = null;
			try {
				socket = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			poll.commit(new TimeServerHandler(socket));
			
		}
	}
	
	
	
}

class MyThreadPool {
	private ExecutorService threadPool = null;
	public MyThreadPool(){
		threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}
	
	public void commit(Runnable task){
		threadPool.execute(task);
	}
}
