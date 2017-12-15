package com.nilo.thread;

public class ReentrantReadWriteLockTestReadA extends Thread {

	private ReentrantReadWriteLockTestRead reentrantReadWriteLockTestRead;
	
	public ReentrantReadWriteLockTestReadA(ReentrantReadWriteLockTestRead reentrantReadWriteLockTestRead){
		this.reentrantReadWriteLockTestRead=reentrantReadWriteLockTestRead;
	}
	@Override
	public void run(){
		reentrantReadWriteLockTestRead.read();
	}
}
