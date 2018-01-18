package com.nilo.source;

public class JavaJVM {

	public static int staticVariable=0;//成员变量(公共字段)、常量 存储在方法区    一个类里只有一份，属于对象共有
	public int newVariable=1; //成员变量(字段)、实例变量与对象一起存储在堆中 不用static修饰的成员变量，随对象的创建而创建，每个对象都有自己的独有的实例变量
	public void method(){//普通方法 存储方法区中
		String i="aa";//局部变量 存储在虚拟机栈中
		i.split(",");//这是方法的引用 符号引用；解析是转直接引用
	}
	
	//描述一个Java文件的执行过程
	
	//详细深入分析 Java ClassLoader 工作机制
    // http://www.54tianzhisheng.cn/2017/03/26/%E8%AF%A6%E7%BB%86%E6%B7%B1%E5%85%A5%E5%88%86%E6%9E%90%20Java%20ClassLoader%20%E5%B7%A5%E4%BD%9C%E6%9C%BA%E5%88%B6/

}
