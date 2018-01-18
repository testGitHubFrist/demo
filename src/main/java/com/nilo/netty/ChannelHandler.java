package com.nilo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;



public class ChannelHandler extends ChannelInboundHandlerAdapter   {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println(" server channelReadComplete ............");
		ByteBuf buffer=(ByteBuf) msg;
		byte[] req=new byte[buffer.readableBytes()];
		buffer.readBytes(req);
		String body=new String(req, "utf-8");
		System.out.println("服务端接收到  msg:"+body);
		
		String respData="客户端吃饭了吗？";
		ByteBuf resp=Unpooled.copiedBuffer(respData.getBytes());
		ctx.write(resp);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("server  channelReadComplete ............");
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		System.out.println("server  exceptionCaught ............");
		ctx.close();
	}


}