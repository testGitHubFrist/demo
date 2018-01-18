package com.nilo.netty.push.demo.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

public class EchoServerChannel extends  ChannelHandlerAdapter{

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		System.err.println("服务器出现异常。。。。。。。。。。。。。");
		cause.printStackTrace();
		ctx.close(); //关闭channel
	}


	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//当Channel上的某个读操作完成时被调用
		ctx.flush();
	}


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		//读取数据
		ByteBuf buffer=(ByteBuf) msg;
		byte[] req=new byte[buffer.readableBytes()];
		buffer.readBytes(req);
		String body=new String(req, CharsetUtil.UTF_8);
		System.out.println("服务端接收到  msg:"+body);
	}


}
