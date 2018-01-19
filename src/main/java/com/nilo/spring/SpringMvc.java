package com.nilo.spring;

public class SpringMvc {

	/**
	 * 手写spring MVC 架构
	 * 纯手写SpringMVC框架，用注解实现springmvc过程
	 * 1、第一步，首先搭建如下架构，其中，annotation中放置自己编写的注解，主要包括service controller qualifier RequestMapping
	 * 第二步：完成对应的annotation:
	 * 2、第二步：编写对应的servlet类，记得勾选init()方法，用来进行相应的实例化和注解反转控制。
	 * 　① 进行包扫描，就是初始化的时候先将整个项目中的包进行扫描，扫描各个文件分别存起来。

		scanPackage("com.chaoyue");//自己的项目，测试用的 所以 扫描包函数的地址写死了
		
		　　　存在List<String> packageNames=new ArrayList<String>();其中都是这样：com.chaoyue.annotation.Controller.class，com.chaoyue.annotation.Quatifier.class, com.chaoyue.annotation.RequestMapping.class，有.class后缀。
		
		　　②过滤和实例化 ：由于已经将所有的文件都存在了packageNames中了，那么我们必须将对应的Controller实例化才可以进行相应函数调用，然后其中的所有文件并不一定都是对应的controller文件，所以要进行相应的过滤和处理
		
		　　　filterAndInstance();
		
		　　  过滤后的结果保存在：  Map<String,Object> instanceMap=new HashMap<String,Object>();
		
		　　　其中 String是注解的value, Object是所对应类的实例 
		
		　　③建立一个映射关系（地址映射，不同的地址映射到不同的方法）：  
		
		  handerMap();
		
		　　结果： Map<String,Object> handerMap=new HashMap<String,Object>();
		
		　　实例：
		
		　　④ 反转控制，根据注解，把service中的注入到controller中的service;
	 */
}
