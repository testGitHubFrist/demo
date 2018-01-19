package com.nilo.netty.push.demo.clinet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClinetHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.copiedBuffer("客户端当被通知",CharsetUtil.UTF_8));
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, ByteBuf msg)
			throws Exception {
		//读取数据
		ByteBuf buffer=(ByteBuf) msg;
		byte[] req=new byte[buffer.readableBytes()];
		buffer.readBytes(req);
		String body=new String(req, CharsetUtil.UTF_8);
		System.out.println("客户端接收到  msg:"+body);		
	}



}
