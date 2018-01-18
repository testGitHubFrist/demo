package com.nilo.netty;

import com.nilo.NIO.bio.TimeServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class NettyTimeServer {

	/**
	 *netty
	 * @param args
	 */
	public static void main(String[] args) {
		//端口 
		int port =8090;
		try {
			new NettyTimeServer().bind(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void bind(int port) {
		//配置服务端NIO线程组 
		/**
		 * 第一个经常被叫做‘boss’，用来接收进来的连接
		 * 第二个经常被叫做‘worker’，用来处理已经被接收的连接，
		 * 一旦‘boss’接收到连接，就会把连接信息注册到‘worker’上。
		 * 如何知道多少个线程已经被使用，如何映射到已经创建的Channels上都需要依赖于EventLoopGroup的实现，
		 * 并且可以通过构造函数来配置他们的关系。
		 */
		EventLoopGroup boss=new NioEventLoopGroup();
		EventLoopGroup work=new NioEventLoopGroup();
		try {
			//ServerBootstrap 是一个启动NIO服务的辅助启动类
			ServerBootstrap b=new ServerBootstrap();
			b.group(boss, work)
			 .channel(NioServerSocketChannel.class)
			 .option(ChannelOption.SO_BACKLOG, 1024)
			 .childHandler(new childChannelHandler());
			
			//绑定端口，同步等待成功
			ChannelFuture f=b.bind(port).sync();
			
			//等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			boss.shutdownGracefully();
			work.shutdownGracefully();
		}
	}
	
	/**
	 * 内部类
	 * @author GaoQun
	 *
	 */
	class  childChannelHandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			System.out.println("处理事务。。。。。");
			ch.pipeline().addLast(new ChannelHandler());
		}
		
	}	
	
}
