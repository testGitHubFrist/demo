package com.nilo.IO;

public class JavaIo {

	/**
	 * Java流操作有关的类或接口：
	 * File:文件类
	 * RandomAccessFile :随机存取文件类
	 * InputStream：字节输入流
	 * OutStream：字节输出流
	 * Reader：字符输入流
	 * Writer：字符输出流
	 * 流的概念和作用：流是一组有顺序的，有起点和终点的字节集合，是对数据传输的总称或抽象。即数据在两设备间的传输称为流，流的本质是数据传输，根据数据传输特性将流
	 *            抽象为各种类，方便更直观的进行数据操作。
	 * IO流的分类：根据处理数据类型的不同分为字符和字节流；根据数据流向不同：输入流和输出流
	 * 字符流和字节流：
	 *    字符流的由来：因为数据编码不同，而有了对字符进行高效操作的流对象。本质其实就是基于字节流读取时，去查了指定的码表。
	 *    区别：读写单位不同：字节以字节为单位，字符流以字符为单位，根据码表映射字符，一次可能读多个字节；处理对象不同：字节流能处理所有类型的数据，而字符流只能处理字符类型数据。
	 *    结论：只要是处理纯文本数据，就优先考虑使用字符流。 除此之外都使用字节流。
	 * 输入流和输出流：
	 *   对输入流只能进行读操作，对输出流只能进行写操作，程序中需要根据待传输数据的不同特性而使用不同的流。 
	 *Java IO流对象
		1.输入字节流InputStreamIO 中输入字节流的继承图可见上图，可以看出：
		InputStream 是所有的输入字节流的父类，它是一个抽象类。
		ByteArrayInputStream、StringBufferInputStream、FileInputStream 是三种基本的介质流，它们分别从Byte 数组、StringBuffer、和本地文件中读取数据。PipedInputStream 是从与其它线程共用的管道中读取数据，与Piped 相关的知识后续单独介绍。
		ObjectInputStream 和所有FilterInputStream 的子类都是装饰流（装饰器模式的主角）。
		
		2.输出字节流OutputStream
		IO 中输出字节流的继承图可见上图，可以看出：
		OutputStream 是所有的输出字节流的父类，它是一个抽象类。
		ByteArrayOutputStream、FileOutputStream 是两种基本的介质流，它们分别向Byte 数组、和本地文件中写入数据。PipedOutputStream 是向与其它线程共用的管道中写入数据，
		ObjectOutputStream 和所有FilterOutputStream 的子类都是装饰流。
	 */
}
