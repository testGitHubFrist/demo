package com.nilo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;


public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("client channelActive..");
		ctx.writeAndFlush(Unpooled.copiedBuffer("你好服务端。。。。。", CharsetUtil.UTF_8)); // 必须有flush
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		 cause.printStackTrace();
		 ctx.close();
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, ByteBuf msg)
			throws Exception {
		// TODO Auto-generated method stub
		
	}




}
