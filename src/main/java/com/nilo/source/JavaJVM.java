package com.nilo.source;

public class JavaJVM {

	public static int staticVariable=0;//成员变量(公共字段)、常量 存储在方法区    一个类里只有一份，属于对象共有
	public int newVariable=1; //成员变量(字段)、实例变量与对象一起存储在堆中 不用static修饰的成员变量，随对象的创建而创建，每个对象都有自己的独有的实例变量
	public void method(){//普通方法 存储方法区中
		String i="aa";//局部变量 存储在虚拟机栈中
		i.split(",");//这是方法的引用 符号引用；解析是转直接引用
	}
	
	//描述一个Java文件的执行过程
}
