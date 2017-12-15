package com.nilo.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTestRead {

	private ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
	
	public void read(){
		try {
			readWriteLock.readLock().lock();
			System.out.println("获得读锁 "+Thread.currentThread().getName()+"_"+System.currentTimeMillis());
			Thread.sleep(10000);
			System.out.println("sleep 后 "+Thread.currentThread().getName()+"_"+System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
