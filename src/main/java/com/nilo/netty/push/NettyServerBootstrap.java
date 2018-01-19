package com.nilo.netty.push;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import com.nilo.netty.push.common.AskMsg;
import com.nilo.netty.push.common.AskParams;


/**
 *  
 */
public class NettyServerBootstrap {
    private int port;
    private SocketChannel socketChannel;
    public NettyServerBootstrap(int port) throws InterruptedException {
        this.port = port;
        bind();
    }

    private void bind() throws InterruptedException {
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();
        ServerBootstrap bootstrap=new ServerBootstrap();
        bootstrap.group(boss,worker);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline p = socketChannel.pipeline();
              p.addLast(new IdleStateHandler(10,10,20));
                p.addLast(new ObjectEncoder());
                p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                p.addLast(new NettyServerHandler());
            }
        });
        ChannelFuture f= bootstrap.bind(port).sync();
        if(f.isSuccess()){
            System.out.println("服务器 start---------------");
        }
    }
    public static void main(String []args) throws InterruptedException, IOException {
        NettyServerBootstrap bootstrap=new NettyServerBootstrap(9999);
        while (true){
            SocketChannel channel=(SocketChannel)NettyChannelMap.get("001");
            if(channel!=null){

            	BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    String msg = console.readLine();
                    if (msg == null) {
                        break;
                    } else if ("bye".equals(msg.toLowerCase())) {
                        break;
                    } else if ("ping".equals(msg.toLowerCase())) {
                    } else {
                      ReplyMsg replyMsg=new ReplyMsg();
                      ReplyServerBody body=new ReplyServerBody(msg);
                      replyMsg.setBody(body);
                      channel.writeAndFlush(replyMsg);
                    }
                }
            }
        }
    }
}
