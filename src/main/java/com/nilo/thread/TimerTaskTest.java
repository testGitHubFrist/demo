package com.nilo.thread;

import java.util.TimerTask;

public class TimerTaskTest extends TimerTask{

	@Override
	public void run() {
		System.out.println("TimerTask:"+System.currentTimeMillis());
	}

}
