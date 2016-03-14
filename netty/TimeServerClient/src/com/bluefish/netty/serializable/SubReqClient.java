package com.bluefish.netty.serializable;

import com.bluefish.sockJdk.AppConstants;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SubReqClient {

	public void start() throws InterruptedException{
		NioEventLoopGroup group = new NioEventLoopGroup();
		Bootstrap boot = new Bootstrap();
		boot.group(group)
		.channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>(){

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new SubReqClientHandler());
			}
		}
		)
		.option(ChannelOption.TCP_NODELAY, true);
		ChannelFuture f = boot.connect(AppConstants.HOST, AppConstants.PORT);
		f.channel().closeFuture().sync();
	}
	
	public static void main (String[] args) throws InterruptedException{
		
		SubReqClient client = new SubReqClient();
		client.start();
	}
	
	private class SubReqClientHandler extends ChannelHandlerAdapter{

		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			System.out.println("Client Come !!");
		}
		
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			System.out.println("Client Down !!");
		}
		
	}
	
	
}
