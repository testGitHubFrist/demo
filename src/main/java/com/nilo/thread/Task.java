package com.nilo.thread;

/**
 * 一半同步、一半异步演示
 * @author 张善闯
 *
 */
public class Task {
 //i是局部变量，所以线程私有
	public void doLongTimeTask(){
		for (int i = 0; i < 100; i++) {
			System.out.println("asynchronized  threadnamee="+Thread.currentThread().getName()+" i="+(i+1));
		}
		
		System.out.println("************************************");
		synchronized (this){
			for (int i = 0; i < 100; i++) {
				System.out.println("	synchronized  threadnamee="+Thread.currentThread().getName()+" i="+(i+1));
			}
		}
		
	}
}
