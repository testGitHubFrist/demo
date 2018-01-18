package com.nilo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

	public static void main(String[] args) {
		int port =8090;
		new NettyClient().connect(port,"127.0.0.1");
	}

	private void connect(int port, String host) {
		EventLoopGroup group=new NioEventLoopGroup();		
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			 .channel(NioSocketChannel.class)
			 .option(ChannelOption.TCP_NODELAY, true)
			 .handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel  ch) throws Exception {
					System.out.println("connected...");
					ch.pipeline().addLast(new ClientHandler());
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
