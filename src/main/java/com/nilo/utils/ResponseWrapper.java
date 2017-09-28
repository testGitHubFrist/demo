package com.nilo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
   
/**
 * 过滤器辅助类
 * @author dengguo
 *
 */
public class ResponseWrapper extends HttpServletResponseWrapper {  
    private ByteArrayOutputStream buffer = null;  
    private ServletOutputStream out = null;  
    private PrintWriter writer = null;  
      
    public ResponseWrapper(HttpServletResponse resp) throws IOException {  
        super(resp);  
        buffer = new ByteArrayOutputStream();// 真正存储数据的流  
        out = new WapperedOutputStream(buffer);  
        writer = new PrintWriter(new OutputStreamWriter(buffer,  
                this.getCharacterEncoding()));  
    }  
      
    // 重载父类获取outputstream的方法  
    public ServletOutputStream getOutputStream() throws IOException {  
        return out;  
    }  
      
    // 重载父类获取writer的方法  
    public PrintWriter getWriter() throws UnsupportedEncodingException {  
        return writer;  
    }  
      
    // 重载父类获取flushBuffer的方法  
    public void flushBuffer() throws IOException {  
        if (out != null) {  
            out.flush();  
        }  
        if (writer != null) {  
            writer.flush();  
        }  
    }  
      
    public void reset() {  
        buffer.reset();  
    }  
      
    public byte[] getResponseData() throws IOException {  
        // 将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据  
        flushBuffer();  
        return buffer.toByteArray();  
    }  
          
     //内部类，对ServletOutputStream进行包装    
    private class WapperedOutputStream extends ServletOutputStream{    
        private ByteArrayOutputStream bos=null;    
        public WapperedOutputStream(ByteArrayOutputStream stream) throws IOException{    
            bos=stream;    
        }    
        public void write(int b) throws IOException{    
            bos.write(b);    
        }

//         @Override
//         public boolean isReady() {
//             return false;
//         }
//
//         @Override
//         public void setWriteListener(WriteListener listener) {
//
//         }
     }
}