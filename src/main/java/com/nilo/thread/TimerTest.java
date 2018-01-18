package com.nilo.thread;

import java.util.Date;
import java.util.Timer;

public class TimerTest {
	public static void main(String[] args) {
		Timer timer=new Timer();
		timer.schedule(new TimerTaskTest(), new Date(),(long) (0.1*60*1000));
   
	}
	
	
}
