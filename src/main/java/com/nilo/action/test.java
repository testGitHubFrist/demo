package com.nilo.action;

import java.util.concurrent.Callable;

public class test implements Callable<String>{

	@Override
	public String  call() throws Exception {
		String  j="callable";
		return j;
	}

}
