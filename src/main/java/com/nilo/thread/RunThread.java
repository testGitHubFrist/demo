package com.nilo.thread;

public class RunThread {

	public static void main(String[] args) throws Exception {
		
		//1、执行随机性演示
//		Thread myThread=new MyThread();
//		myThread.setName("MyThread");
//		myThread.start();
//		for (int i = 0; i < 10; i++) {
//			int time=(int) (Math.random()*1000);
//			try {
//				myThread.sleep(time);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println("run="+Thread.currentThread().getName());
//		}
//		System.out.println("运行结束！");
		
		//2、实例变量与线程安全 （不安全演示）
//		Thread a=new MyThread("A");
//		Thread b=new MyThread("B");
//		Thread c=new MyThread("C");
//		a.start();
//		b.start();
//		c.start();
		
		//2、实例变量与线程安全 （共享变量演示）
//		Thread myThread=new MyThread();
//		Thread a=new Thread(myThread,"A");
//		Thread b=new Thread(myThread,"B");
//		Thread c=new Thread(myThread,"C");
//		a.start();
//		b.start();
//		c.start();
		
		//3、实现非线程安全的环境
//		Thread myThread=new MyThread("a","aa");
//		myThread.start();
//		Thread myThread1=new MyThread("b","bb");
//		myThread1.start();
		
		//3、实现安全线程安全的环境
//		Thread myThread=new MyThread("a","aa");
//		myThread.start();
//		Thread myThread1=new MyThread("b","bb");
//		myThread1.start();
		
		//4、isAlive()方法是判断当前线程是否处于活动状态
//		Thread myThread=new MyThread();
//		System.out.println("begin="+myThread.isAlive());
//		myThread.start();
//		System.out.println("end="+myThread.isAlive());
		
		//5、sleep()方法
//		Thread myThread=new MyThread();
//		System.out.println("begin="+System.currentTimeMillis());
//		myThread.start();
//		System.out.println("end="+System.currentTimeMillis());
		
		//6、getId()方法
//		System.out.println("name= "+Thread.currentThread().getName()+" id= "+Thread.currentThread().getId());
//		Thread myThread=new MyThread();
//		myThread.start();
		
		//7、1将方法interrupt()与return结合使用停止线程
//		Thread myThread=new MyThread();
//		myThread.start();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		myThread.interrupt();
		
		//7、2异常法使用停止线程（强烈建议）
//		try {
//			Thread myThread=new MyThread();
//			myThread.start();
//			Thread.sleep(200);
//			myThread.interrupt();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//8、1 suspend、resume
//		try {
//			MyThread myThread=new MyThread();
//			myThread.start();
//			Thread.sleep(5000);
//			//A段
//			myThread.suspend();
//			System.out.println("A= "+System.currentTimeMillis()+" i= "+myThread.getI());
//			Thread.sleep(5000);
//			System.out.println("A= "+System.currentTimeMillis()+" i= "+myThread.getI());
//			
//			//B段
//			myThread.resume();
//			Thread.sleep(5000);
//			
//			//C段
//			myThread.suspend();
//			System.out.println("B= "+System.currentTimeMillis()+" i= "+myThread.getI());
//			Thread.sleep(5000);
//			System.out.println("B= "+System.currentTimeMillis()+" i= "+myThread.getI());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//拓展变量共享
//		try {
//			MyThread myThread=new MyThread();
////			myThread.start();
////			MyThread myThread1=new MyThread();
////			myThread1.start();
//			//针对实例变量来说，上面的代码是实例出不同的对象，每次new时都会在Java堆中分配不同的地址；所以myThread与myThread1中的实例变量是不一样的，所以实例变量是不一样的，在多线程下互不影响。下面是实例同一个对象，在多线程下是不安全的。
//			Thread t=new Thread(myThread);
//			t.start();
//			Thread t2=new Thread(myThread);
//			t2.start();
//		} catch (Exception e) {
//			
//		}
		
		//11、守护线程
//		MyThread myThread=new MyThread();
//		myThread.setDaemon(true);
//		myThread.start();
//		Thread.sleep(5000);
//		System.out.println("主线程停止");
		
		//研究线程不安全是因为实例变量引起的,所以在写多线程是，注意是否引用的同个实例中是否存在实例变量？？？？？？？？？？？？*********
//		 HasPrivateNum numRef=new HasPrivateNum();//实例化一个对象并且实例变量也是一个，传入两个线程中则实例变量共享所以造成线程不安全
//		 ThreadA a=new ThreadA(numRef);
//		 a.start();
//		 ThreadB b=new ThreadB(numRef);
//		 b.start();
		 /**
		  * 总结：关键字synchronized取得的锁都是对象锁，而不是把一段代码或方法当做锁。同步synchronized ,异步asynchronized
		  */
		
		//脏读演示
//		ThreadDirty threadDirty=new ThreadDirty ();
//		MyThread myThread=new MyThread(threadDirty);
//		myThread.start();
//		Thread.sleep(200);
//		threadDirty.getValue();
		
		//*********2.1.6synchronized锁重入******************************//
//		Sub sub=new Sub();
//		MyThread myThread=new MyThread(sub);
//		myThread.start();
		
		//*********2.2.4一半同步，一半异步******************************//
//		Task task=new Task();
//		Thread1 Thread1=new Thread1(task);
//		Thread1.start();
//		Thread2 Thread2=new Thread2(task);
//		Thread2.start();
		
		//*********生产者/消费者演示 操作值******************************//
		String lock="";
		Productor productor=new Productor(lock);
		Consumer consumer=new Consumer(lock);
		ThreadProductor p=new ThreadProductor(productor);
		p.start();
		ThreadConsumer c=new ThreadConsumer(consumer);
		c.start();
		
		
		
		
		
	}
}