package com.bluefish.sockJdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class TimeClientAIO {

	public CountDownLatch count = new CountDownLatch(1);
	
	public static void main(String[]args){
		TimeClientAIO client = new TimeClientAIO();
		AsynchronousSocketChannel channel = null;
		try {
			channel = AsynchronousSocketChannel.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
		channel.connect(new InetSocketAddress(AppConstants.HOST, AppConstants.PORT), client, new ConnecCompletionHandler(channel));
		try {
			client.count.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


class ConnecCompletionHandler implements CompletionHandler<Void, TimeClientAIO>{
	
	AsynchronousSocketChannel channel = null;
	public ConnecCompletionHandler(AsynchronousSocketChannel channel){
		this.channel = channel;
	}
	
	@Override
	public void completed(Void result, TimeClientAIO attachment) {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put(AppConstants.QUERY_COMMAND.getBytes());
		buffer.flip();
		channel.write(buffer,buffer,new WriteCompletionHandler(channel) {
		});
	}

	@Override
	public void failed(Throwable exc, TimeClientAIO attachment) {
		attachment.count.countDown();
	}
	
}

class WriteCompletionHandler implements CompletionHandler<Integer, ByteBuffer>{

	private AsynchronousSocketChannel channel;

	public WriteCompletionHandler(AsynchronousSocketChannel channel){
		this.channel = channel;
	}
	@Override
	public void completed(Integer arg0, ByteBuffer arg1) {
		if(arg1.hasRemaining()){
			channel.write(arg1, arg1, this);
		}else{
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			channel.read(buffer,buffer,new ReadCompletionHandler(channel));
		}
	}

	@Override
	public void failed(Throwable arg0, ByteBuffer arg1) {
		try {
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer>{
	private AsynchronousSocketChannel channel;
	
	public ReadCompletionHandler(AsynchronousSocketChannel channel){
		this.channel = channel;
	}
	@Override
	public void completed(Integer arg0, ByteBuffer arg1) {
		arg1.flip();
		byte[] buf = new byte[arg1.remaining()];
		arg1.get(buf);
		String reply = null;
		try {
			 reply = new String(buf,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(reply != null ){
			System.out.println(reply);
			try {
				channel.close();
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void failed(Throwable arg0, ByteBuffer arg1) {
		try {
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

