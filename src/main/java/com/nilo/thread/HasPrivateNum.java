package com.nilo.thread;

public class HasPrivateNum {

	private int num = 0;
	
    public void addI(String username) throws Exception{
		if(username.equals("a")){
			num++;
			System.out.println("a set over");
			Thread.sleep(2000);
		}else{
			num++;
			System.out.println("b set over");
		}
		System.out.println(username+" num="+num);
	}
}
