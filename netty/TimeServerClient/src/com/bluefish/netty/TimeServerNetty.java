package com.bluefish.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.util.Date;

import com.bluefish.sockJdk.AppConstants;

/**
 * @author tianjy
 *
 */
public class TimeServerNetty {
	
	public int count = 0;

	public void bind() throws Exception{
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		NioEventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap boot = new ServerBootstrap();
			boot.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, AppConstants.MAX_BACKLOG)
			.childHandler(new ChildChannelHandler());
			
			ChannelFuture f = boot.bind(AppConstants.PORT).sync();
			f.channel().closeFuture().sync();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
		
	}
	public static void main(String[] args) throws Exception{
		TimeServerNetty server = new TimeServerNetty();
		server.bind();
	}
	
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
			arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
//			arg0.pipeline().addLast(new StringDecoder());
			arg0.pipeline().addLast(new TimeServerHandler());
		}
		
	}
	
	private class TimeServerHandler extends ChannelHandlerAdapter{
		
		 public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			 
			++ count;
		 	ByteBuf buf = (ByteBuf) msg;
		 	byte[] byteCmd = new byte[buf.readableBytes()];
		 	buf.readBytes(byteCmd);
		 	String strCmd = new String(byteCmd,"utf-8");
		 	String resPonse = AppConstants.BAD_COMMAND;
		 	if(strCmd != null && AppConstants.QUERY_COMMAND.equals(strCmd)){
		 		resPonse = new Date(System.currentTimeMillis()).toString();
		 	}
		 	ByteBuf bufResp = Unpooled.copiedBuffer((resPonse + System.getProperty("line.separator")).getBytes());
		 	ctx.writeAndFlush(bufResp);
		 	System.out.println("count : " + count + " " + strCmd);
		 }
		 public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			 ctx.close();
		 }
		 
		 public void channelActive(ChannelHandlerContext ctx) throws Exception {
			 System.out.println("Client Come!!");
		 }
		 
		 public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			 System.out.println("Client Down!!");
		 }
	}
}
