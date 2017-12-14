package com.nilo.thread;

public class Thread2 extends Thread{

	private Task task;
	
	public Thread2(Task task){
		this.task=task;
	}
	
	@Override
	public void run(){
		task.doLongTimeTask();
	}
}
