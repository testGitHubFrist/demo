package com.nilo.netty.push.demo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * 熟悉netty开发流程
 * @author GaoQun
 *
 */
public class EchoServer {

	private final int port;
	
	public EchoServer(int port){
		this.port=port;
	}
	
	public static void main(String[] args)  throws Exception{
		if(args.length!=1){
			System.err.println("usage:"+EchoServer.class.getSimpleName()+" <port> ");
		}
		int prot=Integer.parseInt(args[0]);//监听端口
		
		new EchoServer(prot).start();//服务启动方法
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
			    .localAddress(new InetSocketAddress(port))//使用指定的端口设置套接字地址
			    //添加业务处理ChannelHandler 处理业务到子Channel的ChannelPipeline
			    .childHandler(new ChannelInitializer<Channel>() {
					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new EchoServerChannel());
					}
				});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
