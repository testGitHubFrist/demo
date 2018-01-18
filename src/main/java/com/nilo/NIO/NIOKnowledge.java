package com.nilo.NIO;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class NIOKnowledge {
	/**
	 * @date 2018-01-17 nio与netty知识学习
	 * 第一章：JavaI/O基础
	 * 1.1IO基础
	 *    Java1.4版本之前的主要问题：
	 *     a、没有数据缓冲区。IO性能存在问题
	 *     b、没有C或者C++中的channel概念，只有输入输出流。
	 *     c、同步阻塞式IO通信，通常会导致通信线程被长时间阻塞。
	 *     d、支持的字符集有限，硬件移植性不好。
	 *  1.1.1Linux网络IO模型简介
	 *   UNIX提供了5种IO模型
	 *   （1）阻塞IO模型：
	 *   （2）非阻塞IO模型：
	 *   （3）IO复用模型：
	 *   （4）信号驱动IO模型：
	 *   （5）异步IO模型：
	 *  1.1.2 IO多路复用：
	 * 1.2JavaIO演进：
	 * 2.1 传统的BIO编程：
	 *    网络编程的基本模型client/server，也就是两个进程之间的相互通信，其中服务端提供位置信息（绑定的IP地址和监听端口），客户端通过连接操作向服务端监听的地址
	 *    发起连接请求，通过三次握手建立连接(三次握手详情：https://www.zhihu.com/question/24853633)，
	 *    如果连接建立成功，就可以通过网络套接字（socket）进行通信。在基于传统同步阻塞模型开发中，ServerSocket负责绑定IP地址，启动监听端口；
	 *    Socket负责发起连接操作。连接成功后，双方通过输入流和输出流进行同步阻塞式通信。
	 * 2.3NIO编程
	 *   2.3.1类库介绍
	 *    1)缓冲区buffer例如bytebuffer
	 *    2)通道channel  通道是双向，流是单向
	 *    3)多路复用器 selector
	 *   2.3.2NIO服务端序列图
	 *      步骤一：打开ServerSocketChannel，用于监听客户端的连接，它是所有客户端连接的父管道
	 *      ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
	 *      步骤二：绑定监听端口，设置连接为非阻塞模式
            serverSocketChannel.bind(new InetSocketAddress(10083));
            serverSocketChannel.configureBlocking(false);
	 *      步骤三：创建Reactor线程
	 *      Selector selector = Selector.open();
	 *      步骤四：将ServerSocketChannel注册到Reactor线程的多路复用器selector
	 *      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	 *      步骤五：
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}
