package com.bluefish.sockJdk;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author tianjy
 *
 */
public class TimeServerSync {

	public static void main(String [] args){
		int port = 9999;
		ServerSocket server = null;
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
			new Thread(new TimeServerHandler(socket)).start();
			
		}
		
	}
}
