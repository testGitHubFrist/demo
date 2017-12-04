package com.nilo.thread;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 日志功能的实现
 * @author 张善闯
 *
 */
public class LogWriter {

	private final BlockingQueue<String> queue;
	private final LoggerThread  logger;
	private final int CAPACITY=16;
	
	public LogWriter(){
		this.queue=new LinkedBlockingDeque<String>(CAPACITY);
		this.logger=new LoggerThread();
	}
	
	public void start(){
		logger.start();
	}
	
	public void log(String msg) throws InterruptedException{
		queue.put(msg);
	}
	
	/**
	 * 异步写
	 * @author 张善闯
	 *
	 */
	private class LoggerThread extends Thread{

		@Override
	    public void run() {
			try {
				while (true) {
					PrintWriter  writer = new PrintWriter ("C:\\Users\\GaoQun\\Desktop\\新建文本文档.txt");
					writer.write(queue.take());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
			}
	    }
	}
}
