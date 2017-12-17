package com.nilo.thread;

/**
 * 饿汉模式实现
 * @author GaoQun
 *
 */
public class Singleton {

	private static Singleton singleton=new Singleton();
	
	private Singleton(){
		
	}
	
	public static Singleton getInstance(){
		return singleton;
	}
}
