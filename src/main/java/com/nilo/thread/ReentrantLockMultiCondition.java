package com.nilo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMultiCondition {

	private Lock lock=new ReentrantLock();//重入锁
	
	public Condition conditionA=lock.newCondition();//限制A
	
	public Condition conditionB=lock.newCondition();//限制B
	
	/**
	 * 使用多个Condition实现等待/通知
	 */
	
	public void awaitA(){
		try {
			lock.lock();
			System.out.println("awaitA begin 时间： "+System.currentTimeMillis());
			conditionA.await();
			System.out.println("awaitA end 时间： "+System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void awaitB(){
		try {
			lock.lock();
			System.out.println("awaitB begin 时间： "+System.currentTimeMillis());
			conditionB.await();
			System.out.println("awaitB end 时间： "+System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void signalAll_A(){
		try {
			lock.lock();
			System.out.println("signalAll_A  时间： "+System.currentTimeMillis());
			conditionA.signalAll();;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void signalAll_B(){
		try {
			lock.lock();
			System.out.println("signalAll_B  时间： "+System.currentTimeMillis());
			conditionB.signalAll();;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}
