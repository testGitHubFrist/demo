package com.nilo.thread;

import java.util.concurrent.locks.ReentrantLock;

public class Money {

	volatile int num=0;
	int[] money=new int [] {1,2,3,4,5,6,7,8,9};
	private ReentrantLock lock=new ReentrantLock();
	public   String get(){
		String msg ="";
		try {
			lock.lock();
			msg = "线程： "+Thread.currentThread().getName()+"  num:"+num;
			if(num>8){
				msg=msg+"红包已抢完了";
			}else{
				msg=msg+" money:"+String.valueOf(money[num]);
				num=num+1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return msg;
	}
}
