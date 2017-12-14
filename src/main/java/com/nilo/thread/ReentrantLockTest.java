package com.nilo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁演示
 * @author GaoQun
 *
 */
public class ReentrantLockTest {

	private Lock lock=new ReentrantLock();
	public void testMethod(){
		lock.lock();
		
		for (int i = 0; i < 10; i++) {
			System.out.println("threadname: "+Thread.currentThread().getName()+" _ "+(i+1));
		}
		
		lock.unlock();
	}
}
