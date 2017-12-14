package com.nilo.thread;

import java.util.Random;

public class ThreadProductor extends Thread {

	private Productor productor;
	
	public ThreadProductor (Productor productor){
		this.productor=productor;
	}
	@Override
	public void run(){
		while(true){
			productor.setValue(String.valueOf(new Random().nextInt(100)));		
		}
	}
}
