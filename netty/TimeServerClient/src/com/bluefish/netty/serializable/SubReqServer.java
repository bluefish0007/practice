package com.bluefish.netty.serializable;

import com.bluefish.sockJdk.AppConstants;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class SubReqServer {

	public void start() throws InterruptedException{
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		NioEventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap boot = new ServerBootstrap();
		boot.group(bossGroup, workerGroup)
		.channel(NioServerSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new SubReqServerHandler());
			}
		});
		
		ChannelFuture f = boot.bind(AppConstants.PORT).sync();
		f.channel().closeFuture().sync();
		
	}
	
	private class SubReqServerHandler extends ChannelHandlerAdapter{

		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			System.out.println("Client Come !!");
		}
		
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			System.out.println("Client Down !!");
		}
		
	}
	
	
	
	
	public static void main(String[]args) throws InterruptedException{
		
		SubReqServer server = new SubReqServer();
		server.start();
	}
}
