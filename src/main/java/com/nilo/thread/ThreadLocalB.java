package com.nilo.thread;

public class ThreadLocalB extends Thread {

	@Override
	public void run(){
		for (int i = 0; i < 100; i++) {
			Tools.tl.set(" threadName:"+Thread.currentThread().getName()+" _ "+(i+1));
			System.out.println("threadName="+Thread.currentThread().getName()+" get value="+Tools.tl.get());
		}
	}
}
