package com.nilo.thread;

public class LoginServlet {
	
	private static String usernameRef;
	private static String passwordRef;
	
	public synchronized static void doPost(String username,String password) throws Exception{
		usernameRef=username;
		if("a".equals(username)){
			Thread.sleep(5000);
		}
		passwordRef=password;
		System.out.println("username="+usernameRef+" password="+passwordRef);
	}

}
