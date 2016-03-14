package com.bluefish.sockJdk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class TimeServerNio {

	public static void main(String[] args){
		
		MutiplexServer server = new MutiplexServer();
		new Thread(server).start();
		
	}
}


class MutiplexServer implements Runnable{
	private ServerSocketChannel server = null;
	private Selector selector = null;
	private boolean isStop = false;
	public MutiplexServer(){
		try {
			server = ServerSocketChannel.open();
			selector = Selector.open();
			server.configureBlocking(false);
			server.socket().bind(new InetSocketAddress(AppConstants.PORT), AppConstants.MAX_BACKLOG);
			server.register(selector,SelectionKey.OP_ACCEPT);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(!isStop){
			try {
				selector.select(1000);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectedKeys.iterator();
			while(iterator.hasNext()){
				SelectionKey key = iterator.next();
				iterator.remove();
				if(key.isAcceptable()){
					processAccept(key);
				}else if(key.isReadable()){
					processRead(key);
				}
			}
		}
	}
	
	private void processRead(SelectionKey key) {
		SocketChannel coon = (SocketChannel)key.channel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		try {
			int readLength = coon.read(byteBuffer);
			if(readLength > 0){
				byteBuffer.flip();
				byte[] bytes = new byte[byteBuffer.remaining()];
				byteBuffer.get(bytes);
				
				String command = new String(bytes,"utf-8");
				if(command!= null && AppConstants.QUERY_COMMAND.equals(command)){
					String result = new Date(System.currentTimeMillis()).toString();
					ByteBuffer buffer = ByteBuffer.allocate(result.length());
					buffer.put(result.getBytes());
					buffer.flip();
					coon.write(buffer);
				}
			}else if(readLength < 0){
				coon.close();
			}else{
				
			}
		} catch (IOException e) {
			try {
				coon.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	public void processAccept(SelectionKey key){
		ServerSocketChannel connet = (ServerSocketChannel)key.channel();
		try {
			SocketChannel accept = connet.accept();
			accept.configureBlocking(false);
			accept.register(selector, SelectionKey.OP_READ);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}