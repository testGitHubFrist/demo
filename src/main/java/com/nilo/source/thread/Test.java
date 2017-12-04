package com.nilo.source.thread;

public class Test {

	public static void main(String[] args) {
		try {
			myThread1 myThread=new myThread1();
			myThread.setName("myThread");
			myThread.start();
			for (int i = 0; i < 10; i++) {
				int time=(int) (Math.random()*1000);
				Thread.sleep(time);
				System.out.println("run="+Thread.currentThread().getName());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
