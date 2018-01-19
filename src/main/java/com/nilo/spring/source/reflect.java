	/**
	 *
	 *
	 *
	 *反射常用的方法
	 * 一、概述：
	 * Java反射机制就是在运行状态中，对于任意一个类，都能知道这个类的属性和方法。对于任意一个对象能够调用它的任意一个属性和方法。这种动态获取的信息和动态调用对象的方法的功能称为
	 * Java语言的反射机制。反射机制就是通过class类实现的，Object类是所有类的根类。
	 * 二、运用
	 * Class对象的常用方法：
		Constructor[] getConstructors()：返回此Class对象所表示的类的所有public构造方法
		Method[] getMethods()：返回此Class对象所表示的类的所有public方法
		Method[] getDeclaredMethods()：返回此Class对象所表示的类的所有方法，与方法的访问级别无关
		Field[] getFields()：返回此Class对象所表示的类的所有public属性
		Field[] getDecalaredDields()：返回此Class对象所表示的类的所有属性，与属性访问级别无关
		Object get(Object obj)：得到引用类型属性值
		void set(Object obj,Object val)：将obj对象的该属性设置成val值。针对引用类型赋值
		Object invoke(Object obj,Object args)：调用类的方法，obj是执行该方法的对象，args是执行该方法时传入该方法的参数 
	 */