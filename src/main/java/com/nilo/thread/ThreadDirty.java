package com.nilo.thread;

/**
 * 多线程脏读演示
 * @author 张善闯
 *
 */
public class ThreadDirty {

	public String username="A";
	public String password="AA";
	
	synchronized public void setValue(String username,String password) throws Exception{
		this.username=username;
		Thread.sleep(5000);
		this.password=password;
		System.out.println("setValue thread name="+Thread.currentThread().getName()+" username="+username +" password="+password);
	}
	public void getValue(){
		System.out.println("getValue thread name="+Thread.currentThread().getName()+" username="+username +" password="+password);
	}
}
