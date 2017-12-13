package com.nilo.thread;

public class Main {

	public int  i=10;
	synchronized public void operateMainMethod() throws Exception{
		i--;
		System.out.println("Main  thread name="+Thread.currentThread().getName()+" print i="+i);
		Thread.sleep(100);
	}
}
