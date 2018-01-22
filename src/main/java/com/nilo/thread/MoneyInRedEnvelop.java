package com.nilo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 实现美团抢红包需求；第九个人抢到的红包最大
 * @author GaoQun
 *
 */
public class MoneyInRedEnvelop {

	public static void main(String[] args) throws Exception {
	    Money money=new Money() ;
		ExecutorService pool=Executors.newCachedThreadPool() ;
		 List<Future<String>> futures = new ArrayList<Future< String>>();  
		//创建10线程进行抢
		for (int i = 0; i < 10;i++) {
			Future<String> future=pool.submit(new Callable<String >() {

				@Override
				public String call() throws Exception {
					return money.get();
				}
			});
			
			futures.add(future);
			
		}
		for (Future<String> future : futures) {
			System.out.println(future.get());
		}
	}
}
