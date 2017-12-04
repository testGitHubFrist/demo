package com.nilo.thread;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Test  {


//	public final synchronized void join(long millis)
//		    throws InterruptedException {
//		        long base = System.currentTimeMillis();
//		        long now = 0;
//
//		        if (millis < 0) {
//		            throw new IllegalArgumentException("timeout value is negative");
//		        }
//
//		        if (millis == 0) {
//		            while (isAlive()) {
//		                wait(0);
//		            }
//		        } else {
//		            while (isAlive()) {
//		                long delay = millis - now;
//		                if (delay <= 0) {
//		                    break;
//		                }
//		                wait(delay);
//		                now = System.currentTimeMillis() - base;
//		            }
//		        }
//		    }
	
	public static void main(String[] args) throws InterruptedException, IOException {
//		PrintWriter  writer = new PrintWriter ("C:\\Users\\GaoQun\\Desktop\\新建文本文档.txt");
//		writer.write("ceshi");
		LogWriter log=new LogWriter();
//		log.log("sas");
//		log.start();
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		threadPool.execute(new Runnable() {
			
			@Override
			public void run() {
				
				for (int i = 0; i <5000; i++) {
					LogWriter log=new LogWriter();
					try {
						log.log("sas"+i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					log.start();
				}
			}
		});
	}
}
