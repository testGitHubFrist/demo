package com.nilo.thread;

import java.util.concurrent.locks.ReentrantLock;

public class HoldCount {

	/**
	 * <p>方法 getHoldCount()的作用是查询当前线程保持此锁的个数，也就是调用Lock的次数
	 */
	
	private ReentrantLock lock=new ReentrantLock();
	
	public void Lock1(){
		try {
			lock.lock();
			System.out.println(" 第一次使用锁   getHoldCount="+lock.getHoldCount());
			Lock2();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}
	
	public void Lock2(){
		try {
			lock.lock();
			System.out.println(" 第二次使用锁   getHoldCount="+lock.getHoldCount());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}
}
