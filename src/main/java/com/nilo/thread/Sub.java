package com.nilo.thread;

public class Sub extends Main{

	/**
	 * 知识扩展，集成可以继承父类的哪些东西
	 * 当子类继承了某个类之后，便可以使用父类中的成员变量，但是并不是完全继承父类的所有成员变量
	 * 1）能够继承父类的public和protected成员变量；不能够继承父类的private成员变量；
	 * 2）对于父类的包访问权限成员变量，如果子类和父类在同一个包下，则子类能够继承；否则，子类不能够继承；
	 * 3）对于子类可以继承的父类成员变量，如果在子类中出现了同名称的成员变量，则会发生隐藏现象，即子类的成员变量会屏蔽掉父类的同名成员变量。如果要在子类中访问父类中同名成员变量，需要使用super关键字来进行引用。
	 * 子类继承父类的方法
	 * 1）能够继承父类的public和protected成员方法；不能够继承父类的private成员方法；
	 * 2）对于父类的包访问权限成员方法，如果子类和父类在同一个包下，则子类能够继承；否则，子类不能够继承
	 * 3）对于子类可以继承的父类成员方法，如果在子类中出现了同名称的成员方法，则称为覆盖，即子类的成员方法会覆盖掉父类的同名成员方法。如果要在子类中访问父类中同名成员方法，需要使用super关键字来进行引用。
	 * @throws Exception
	 */
	synchronized public void operateSubMethod() throws Exception{
		while (i>0){
			i--;
			System.out.println("sub thread name="+Thread.currentThread().getName()+" print i="+i);
			Thread.sleep(100);
			this.operateMainMethod();
		}
	}
}
