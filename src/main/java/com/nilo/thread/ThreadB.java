package com.nilo.thread;

public class ThreadB  extends Thread{

	private HasPrivateNum numRef;
	public ThreadB(HasPrivateNum numRef){
		this.numRef=numRef;
	}
	@Override
	public void run() {
		try {
			numRef.addI("b");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
