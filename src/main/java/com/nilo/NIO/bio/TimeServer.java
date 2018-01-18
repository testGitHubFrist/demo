package com.nilo.NIO.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeServer {

	/**
	 * 传统IO
	 * @param args
	 */
	public static void main(String[] args) {
		int port =8090;
		ServerSocket server=null;
		ExecutorService pool=Executors.newFixedThreadPool(5);
		try {
			//创建服务器
			server=new ServerSocket(port);
			System.out.println("The time server is start in port :"+port);
			Socket socket=null;
			while(true){
				//监听端口
				socket=server.accept();
				//异步处理业务
				pool.execute(new Thread(new TimeServerHandler(socket)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(server==null){
				System.out.println("The time server close !!!");
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				server=null;
			}
		}
	}
}
