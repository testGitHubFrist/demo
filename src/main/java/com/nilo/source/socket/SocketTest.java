package com.nilo.source.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class SocketTest {

//	public static void main(String[] args) throws Exception{
//		Socket s=new Socket("114.114.114.114",80);
//		InputStream inputStream= s.getInputStream();
//		byte b[] = new byte[1024*8] ;        // 数组大小由文件决定
//		int len = inputStream.read(b) ;        // 读取内容
//		// 第4步、关闭输出流
//		inputStream.close() ;                        // 关闭输出流\
//		System.out.println("读入数据的长度：" + len) ;
//		System.out.println("内容为：" + new String(b)) ;    // 把byte数组变为字符串输出
//	}
	
	public static void main(String[] args) throws Exception {
		URL url=new URL("http:\\www.baidu.com");
		URLConnection con=url.openConnection();
		con.setConnectTimeout(200);// 连接超时时间
		con.setReadTimeout(200);// 读取结果超时时间
		con.setUseCaches(false);// 取消缓存
		con.setRequestProperty("Content-type",
				"application/x-www-form-urlencoded;charset=utf-8" );
		con.connect();
		OutputStream inputStream= con.getOutputStream();
	}
}
