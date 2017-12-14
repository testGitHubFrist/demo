package com.nilo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockAndCondition {

	private Lock lock=new ReentrantLock();
	public Condition condition=lock.newCondition();
	/**
	 * 等待
	 */
	public void await(){
		try {
			lock.lock();
			System.out.println("await时间是："+System.currentTimeMillis());
			condition.await();
			System.out.println("被唤醒时间是："+System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	/**
	 * 唤醒
	 */
	public void signal(){
		try {
			lock.lock();
			System.out.println("signal时间是："+System.currentTimeMillis());
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}
