package com.nilo.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock使用演示
 * @author GaoQun
 *
 */
public class TryLockTest {

	private ReentrantLock lock=new ReentrantLock();
	
	public void waitMethod(){
		if(lock.tryLock())
		    System.out.println(Thread.currentThread().getName()+"_获得锁");
		else 
			System.out.println(Thread.currentThread().getName()+"_未获得锁");
	}
}
