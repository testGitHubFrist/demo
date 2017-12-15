package com.nilo.thread;

public class ReentrantReadWriteLockTestReadB extends Thread {

	private ReentrantReadWriteLockTestRead reentrantReadWriteLockTestRead;
	
	public ReentrantReadWriteLockTestReadB(ReentrantReadWriteLockTestRead reentrantReadWriteLockTestRead){
		this.reentrantReadWriteLockTestRead=reentrantReadWriteLockTestRead;
	}
	@Override
	public void run(){
		reentrantReadWriteLockTestRead.read();
	}
}
