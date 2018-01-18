package com.nilo.NIO;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;


public class NIOServer {
    
    private static int BUFF_SIZE=1024;
    private static int TIME_OUT = 2000;
    public static void main(String[] args) throws IOException {
        
        Selector selector = Selector.open();
        //建立服务器
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(10083));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //channel 注册selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        TCPProtocol protocol = new EchoSelectorProtocol(BUFF_SIZE); 
        
        while (true) {
            if(selector.select(TIME_OUT)==0){
                //在等待信道准备的同时，也可以异步地执行其他任务，  这里打印*
                System.out.print("*");  
                continue;  
            }
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next(); 
                //如果服务端信道感兴趣的I/O操作为accept  
                if (key.isAcceptable()){  
                    protocol.handleAccept(key);  
                }  
                //如果客户端信道感兴趣的I/O操作为read  
                if (key.isReadable()){  
                    protocol.handleRead(key);  
                }  
                //如果该键值有效，并且其对应的客户端信道感兴趣的I/O操作为write  
                if (key.isValid() && key.isWritable()) {  
                    protocol.handleWrite(key);  
                }
                
                //这里需要手动从键集中移除当前的key  
                keyIter.remove(); 
            }
            
        }
    }
}