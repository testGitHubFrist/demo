package com.nilo.source.invocation;

public class Main {

	public static void main(String[] args) {
		//1、JDK的动态代理机制只能代理实现了接口的类，而不能实现接口的类就不能实现JDK的动态代理，
		//2、cglib是针对类来实现代理的，他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理。
		
		/**
		 * 一、原理区别：
			java动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
			
			而cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
			
			1、如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP 
			2、如果目标对象实现了接口，可以强制使用CGLIB实现AOP 
			3、如果目标对象没有实现了接口，必须采用CGLIB库，spring会自动在JDK动态代理和CGLIB之间转换
			
			如何强制使用CGLIB实现AOP？
			 （1）添加CGLIB库，SPRING_HOME/cglib/*.jar
			 （2）在spring配置文件中加入<aop:aspectj-autoproxy proxy-target-class="true"/>
			
			JDK动态代理和CGLIB字节码生成的区别？
			 （1）JDK动态代理只能对实现了接口的类生成代理，而不能针对类
			 （2）CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法
			   因为是继承，所以该类或方法最好不要声明成final 
		 */
		math3 xx=new math3Impl();
		math3 proxy1=(math3) new math3factoryproxy(xx).getMath3Proxy();
		int resuIt=	proxy1.add(4, 9);
		System.out.println("---->"+resuIt);
		int resuIt1=proxy1.div(10, 2);
		System.out.println("---->"+resuIt1);
		System.out.println(proxy1.add(2, 3));
		
		
		
		BookFacadeCglibImpl cglib=new BookFacadeCglibImpl();  
	    BookFacadeCglibImpl1 bookCglib=(BookFacadeCglibImpl1)cglib.getInstance(new BookFacadeCglibImpl1());  
        bookCglib.addBook();  
	}
	
	
	/**
	 * 拓展
	 * 1.代理模式
	          代理(Proxy)是一种设计模式,提供了对目标对象另外的访问方式;即通过代理对象访问目标对象.这样做的好处是:可以在目标对象实现的基础上,增强额外的功能操作,即扩展目标对象的功能.
	         这里使用到编程中的一个思想:不要随意去修改别人已经写好的代码或者方法,如果需改修改,可以通过代理的方式来扩展该方法
	   2、静态代理在使用时,需要定义接口或者父类,被代理对象与代理对象一起实现相同的接口或者是继承相同父类.可以做到在不修改目标对象的功能前提下,对目标功能扩展.；
	                           缺点：因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护.
	   3、动态代理1.代理对象,不需要实现接口；2.代理对象的生成,是利用JDK的API,动态的在内存中构建代理对象(需要我们指定创建代理对象/目标对象实现的接口的类型)
	          3.动态代理也叫做:JDK代理,接口代理
	   总结：代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用动态代理
	   
	   4、Cglib代理：上面的静态代理和动态代理模式都是要求目标对象是实现一个接口的目标对象,但是有时候目标对象只是一个单独的对象,并没有实现任何的
	     接口,这个时候就可以使用以目标对象子类的方式类实现代理,这种方法就叫做:Cglib代理。
	     Cglib代理,也叫作子类代理,它是在内存中构建一个子类对象从而实现对目标对象功能的扩展.
	      1、JDK的动态代理有一个限制,就是使用动态代理的对象必须实现一个或多个接口,如果想代理没有实现接口的类,就可以使用Cglib实现.
	      2、Cglib是一个强大的高性能的代码生成包,它可以在运行期扩展java类与实现java接口.它广泛的被许多AOP的框架使用,例如Spring AOP和synaop,为他们提供方法的interception(拦截)
	      3、Cglib包的底层是通过使用一个小而块的字节码处理框架ASM来转换字节码并生成新的类.不鼓励直接使用ASM,因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉.
	      Cglib子类代理实现方法:1.需要引入cglib的jar文件,但是Spring的核心包中已经包括了Cglib功能,
	      2.引入功能包后,就可以在内存中动态构建子类
	      3.代理的类不能为final,否则报错
	      4.目标对象的方法如果为final/static,那么就不会被拦截,即不会执行目标对象额外的业务方法.
	 */
}
