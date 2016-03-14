package com.bluefish.sockJdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class TimeServerAIO {

	public CountDownLatch count = new CountDownLatch(1);
	public static void main(String[] args){
		TimeServerAIO server = new TimeServerAIO();
		AsynchronousServerSocketChannel serverChannel = null;
		try {
			serverChannel = AsynchronousServerSocketChannel.open();
			serverChannel.bind(new InetSocketAddress(AppConstants.HOST, AppConstants.PORT));
			serverChannel.accept(server,new AcceptHandler(serverChannel));
			try {
				server.count.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) 
		{
			try {
				serverChannel.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	

}
class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, TimeServerAIO>{

	private AsynchronousServerSocketChannel server;
	
	public AcceptHandler(AsynchronousServerSocketChannel server){
		this.server = server;
	}
	
	@Override
	public void completed(AsynchronousSocketChannel channel, TimeServerAIO attachment) {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		channel.read(buffer,buffer,new ReadCompletion(channel));
		server.accept(attachment, this);
		
	}

	@Override
	public void failed(Throwable exc, TimeServerAIO attachment) {
		attachment.count.countDown();
	}
	
}

class ReadCompletion implements CompletionHandler<Integer, ByteBuffer>{

	
	private AsynchronousSocketChannel channel;

	public ReadCompletion(AsynchronousSocketChannel channel){
		this.channel = channel;
	}
	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		attachment.flip();
		byte bytes[] = new byte[attachment.remaining()];
		attachment.get(bytes);
		String command = null;
		try {
			command = new String(bytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(command != null && AppConstants.QUERY_COMMAND.equals(command)){
			String resPonse = new Date(System.currentTimeMillis()).toString();
			ByteBuffer bufResPonse = ByteBuffer.allocate(resPonse.length());
			bufResPonse.put(resPonse.getBytes());
			bufResPonse.flip();
			channel.write(bufResPonse);
		}
		
		System.out.println("Server Read Complete");
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
//		attachment.count.countDown();
	}
	
}

