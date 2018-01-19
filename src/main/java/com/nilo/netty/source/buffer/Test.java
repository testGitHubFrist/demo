package com.nilo.netty.source.buffer;

import java.nio.ByteBuffer;


public class Test {

	public static void main(String[] args) {
		ByteBuffer  buffer =ByteBuffer.allocate(100);
		String value ="Netty 权威指南";
		buffer.put(value.getBytes());
		buffer.flip();
		byte[] vArray=new byte[buffer.remaining()];
		buffer.get(vArray);
		String decodeValue=new String (vArray);
		System.out.println(decodeValue);
	}
}
