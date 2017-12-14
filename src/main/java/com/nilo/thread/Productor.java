package com.nilo.thread;



/**
 * 生产者
 * @author 张善闯
 *
 */
public class Productor {

	private String lock ;
	
	public Productor(String lock){
		this.lock=lock;
	}
	
	public void setValue(String value){
		try {
			synchronized (lock) {
				if(!ValueObject.value.equals("")){
					lock.wait();
				}
				System.out.println("set 的值是 "+value);
				ValueObject.value=value;
				lock.notify();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
