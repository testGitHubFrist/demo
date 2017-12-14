package com.nilo.thread;



/**
 * 消费者
 * @author 张善闯
 *
 */
public class Consumer {

	private String lock ;
	
	public Consumer(String lock){
		this.lock=lock;
	}
	
	public void getValue(){
		try {
			synchronized (lock) {
				if(ValueObject.value.equals("")){
					lock.wait();
				}
				System.out.println("get 的值是 "+ValueObject.value);
				ValueObject.value="";
				lock.notify();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
