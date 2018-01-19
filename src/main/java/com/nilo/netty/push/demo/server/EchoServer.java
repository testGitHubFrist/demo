package com.nilo.netty.push.demo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * 熟悉netty开发流程
 * @author GaoQun
 *
 */
public class EchoServer {

	
	public static void main(String[] args)  throws Exception{
		
		new EchoServer().start();//服务启动方法
	}
	
	/**
	 * 创建服务
	 */
	private void start()  throws Exception{
		
		EventLoopGroup group =new NioEventLoopGroup();//创建EventLoopGroup
		try {
			//创建ServerBootstrap
			ServerBootstrap b=new ServerBootstrap();
			b.group(group)
			    .channel(NioServerSocketChannel.class)//指定所使用的NIO传输Channel
			    .localAddress(new InetSocketAddress(8090))//使用指定的端口设置套接字地址
			    //添加业务处理ChannelHandler 处理业务到子Channel的ChannelPipeline
			    .childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						System.out.println("服务端启动...");
						ch.pipeline().addLast(new EchoServerChannel());
					}
				});
			//绑定端口，同步等待成功
			ChannelFuture f=b.bind(8090).sync();
			
			//等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			group.shutdownGracefully();
		}
	}
}
