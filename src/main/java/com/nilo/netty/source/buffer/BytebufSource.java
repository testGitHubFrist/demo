package com.nilo.netty.source.buffer;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

public class BytebufSource {

    private int mark = -1;
    private int position = 0;
    private int limit;
    private int capacity;
    
    
    public static void main(String[] args) {
    	ByteBuffer  buffer =ByteBuffer.allocate(100);
		String value ="Netty 权威指南";
		buffer.put(value.getBytes());//put(src, 0, src.length);
	}
	/**
	 * put方法
	 * 第一步 src, 0, src.length
	 */
    
    
	 public BytebufSource put(byte[] src, int offset, int length) {
	        checkBounds(offset, length, src.length);
	        if (length > remaining())
	            throw new BufferOverflowException();
	        int end = offset + length;
	        for (int i = offset; i < end; i++)
	            this.put(src[i]);
	        return this;
	    }
	
	static void checkBounds(int off, int len, int size) { // package-private
	        if ((off | len | (off + len) | (size - (off + len))) < 0)
	            throw new IndexOutOfBoundsException();
	    }
	 
	 public final int remaining() {
	        return limit - position;
	    }
	 private void put(byte b) {
			// TODO Auto-generated method stub
			
		}
}

