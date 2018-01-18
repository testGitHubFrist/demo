package com.nilo.NIO;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 传统BIO模型代码演示
 * @author GaoQun
 *
 */
public class BIOModel {

	public static void main(String[] args) {
		ExecutorService pool =Executors.newCachedThreadPool();
		int port=8088;
		try {
			//1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
			ServerSocket serverSocket=new ServerSocket(port);	
			System.out.println("服务已启动！！！！");
			while(true){
				//2、调用accept()方法开始监听，等待客户端的连接
				final Socket socket=serverSocket.accept();//该方法阻塞
				System.out.println("新增客户端！！！！");
				//3、阻塞式处理业务逻辑
				//handle(socket);
				 
			   //4、伪异步IO处理业务
				pool.execute(new Runnable() {
					
					@Override
					public void run() {
						try {
							handle(socket);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 读取数据
	 * @param socket
	 */
	private static void handle(Socket socket) throws Exception {
		//获取数据流
		InputStream inputStream=socket.getInputStream();
		
		byte[] b = new byte[1024];
		int i=inputStream.read(b);
		if(i!=-1){
			System.out.println(new String(b, 0, i));
		}
	}
}
