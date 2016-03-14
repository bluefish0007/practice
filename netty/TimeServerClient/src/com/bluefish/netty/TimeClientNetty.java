package com.bluefish.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.io.File;
import java.net.InetSocketAddress;

import com.bluefish.sockJdk.AppConstants;

public class TimeClientNetty {
	
	public int count = 0;
	public void connect()throws Exception{
		NioEventLoopGroup worker = new NioEventLoopGroup();
		Bootstrap boot = new Bootstrap();
		boot.channel(NioSocketChannel.class)
		.group(worker)
		.option(ChannelOption.TCP_NODELAY, true)
		.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
//				ch.pipeline().addLast(new StringDecoder());
				ch.pipeline().addLast(new TimeClientHandler());
			}
		});
		ChannelFuture f = boot.connect(new InetSocketAddress(AppConstants.HOST, AppConstants.PORT)).sync();
		f.channel().closeFuture().sync();
	}

	public static void main(String[] args)throws Exception{
		
		TimeClientNetty client = new TimeClientNetty();
		client.connect();
		
	}
	
	private class TimeClientHandler extends ChannelHandlerAdapter{
		
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			for(int i = 0 ; i < 100 ; ++ i){
				ByteBuf response = Unpooled.copiedBuffer((AppConstants.QUERY_COMMAND + System.getProperty("line.separator")).getBytes());
				ctx.writeAndFlush(response);
			}
		}
		 public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			 ++ count;
			 ByteBuf byteBuf = (ByteBuf)msg;
			 byte[] byteCmd = new byte[byteBuf.readableBytes()];
			 byteBuf.readBytes(byteCmd);
			 String strCmd = new String(byteCmd, "utf-8");
			 System.out.println("count : " + count + strCmd);
		 }
		 public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//			 ctx.close();
		 }
	}
}
