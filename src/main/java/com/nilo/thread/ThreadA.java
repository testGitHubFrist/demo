package com.nilo.thread;

public class ThreadA  extends Thread{

	private HasPrivateNum numRef;
	public ThreadA(HasPrivateNum numRef){
		this.numRef=numRef;
	}
	@Override
	public void run() {
		try {
			numRef.addI("a");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
