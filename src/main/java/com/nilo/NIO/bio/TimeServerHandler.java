package com.nilo.NIO.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 业务处理类
 * @author GaoQun
 *
 */
public class TimeServerHandler implements Runnable {

	private Socket socket;
	public TimeServerHandler(Socket socket) {
		this.socket=socket;
	}

	@Override
	public void run() {

		BufferedReader in=null;
		PrintWriter out=null;
		try {
		    //读取数据
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//写
			out=new PrintWriter(socket.getOutputStream(),true);
			String currentTime=null;
			String body=null;
			while(true){
				body=in.readLine();
				if(body==null ){
					break;
				}
				System.out.println("the time server receive order :"+body);
				currentTime= ("QUERY TIME ORDER".equals(body)?String.valueOf(System.currentTimeMillis()):"BAD ORDER");
				out.println("\r\n"+currentTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(in!=null){
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if(out!=null){
				out.close();
				out=null;
			}
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				socket=null;
			}
		}
	}

}
