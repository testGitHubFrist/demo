package com.nilo.source.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class math3factoryproxy {

	private math3 target;

	public math3factoryproxy(math3 target) {

		super();
		this.target = target;
	}
	
	public math3 getMath3Proxy(){
		math3 proxy=null;
		
		//确定代理对象由那个类加载
		ClassLoader loader=target.getClass().getClassLoader();
		
		// 代理对象的类型，即类中都存在方法清单
		Class[] interfaces=new Class[]{math3.class};
		
		//当调用代理对象方法时，执行以下代码
		InvocationHandler h=new InvocationHandler() {

			/**
			 * 1 proxy 正在使用的代理，一般不使用，以免死循环
			 * 2 method 正在被调用的方法
			 * 3 args 调用方法时参与的参数
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
				
				//取得当前方法的名字
				String methName=method.getName();
				
				//日志
				System.out.println("the method"+methName+"begin with"+Arrays.asList(args));
				
				//执行该方法
				Object resuItin=method.invoke(target, args);
				
				//日志
				System.out.println("the method"+methName+"end with"+":"+resuItin);
				return resuItin;
			}
			
		};
		
		/**
		 * 以上都是为了使以下的proXY执行
		 */
		
		proxy=(math3)Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}
}
