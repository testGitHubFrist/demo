package com.nilo.netty.push.demo.clinet;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import com.nilo.netty.push.demo.server.EchoServer;

public class EchoClinet {

	private final int port; 
	 
	private final String  host;

	public EchoClinet(String host, int port) {
		this.port = port;
		this.host = host;
	}
	
	public static void main(String[] args) {
		new EchoClinet("127.0.0.1",8090).start();
	}

	private void start() {
		EventLoopGroup group= new NioEventLoopGroup();
		try {
			//创建ServerBootstrap
			Bootstrap b=new Bootstrap();
			b.group(group)
			    .channel(NioSocketChannel.class)
			    .remoteAddress(new InetSocketAddress(host, port))
			    .handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch)
							throws Exception {
						System.out.println("客户端连接...");
						ch.pipeline().addLast(new EchoClinetHandler());
					}
				});
			//用connect()方法代替了bind()方法
			ChannelFuture f = b.connect(host, port).sync();
			//等到运行结束，关闭
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			group.shutdownGracefully();
		}
	}
}
