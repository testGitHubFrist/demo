package com.nilo.thread;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class RunThread {

	public static ThreadLocal<Object> threadLocal=new ThreadLocal<Object>();
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
//		String lock="";
//		Productor productor=new Productor(lock);
//		Consumer consumer=new Consumer(lock);
//		ThreadProductor p=new ThreadProductor(productor);
////		p.setDaemon(true);
//		p.start();
//		ThreadConsumer c=new ThreadConsumer(consumer);
////		c.setDaemon(true);
//		c.start();
////		Thread.sleep(5000);
////		System.out.println("主线程停止");
		
		//*********3、2方法join的使用******************************//
//		MyThread myThread=new MyThread();
//		myThread.start();
//		myThread.join();//等待子线程执行完毕后主线程销毁
//		System.out.println("主线程执行完毕");
		
		//*********3、3类ThreadLocal的使用;主要是解决静态变量共享的问题******************************//	
		//*********3、3、1 方法get()与null******************************//
//		if(threadLocal.get()==null){
//			System.out.println("从未放值！！！");
//			threadLocal.set("放置值");
//		}
//		System.out.println(threadLocal.get());
		
		//*********3、3、2验证线程变量的隔离性******************************//
//		ThreadLocalA A=new ThreadLocalA();
//		A.start();
//		ThreadLocalB B=new ThreadLocalB();
//		B.start();
		
		//*********4、1、1使用ReentrantLock实现同步：测试1******************************//
//		ReentrantLockTest test=new ReentrantLockTest();
//		MyThread t1=new  MyThread(test);
//		MyThread t2=new  MyThread(test);
//		MyThread t3=new  MyThread(test);
//		MyThread t4=new  MyThread(test);
//		MyThread t5=new  MyThread(test);
//		t1.start();
//		t2.start();
//		t3.start();
//		t4.start();
//		t5.start();
		
		//*********4、1、4 正确使用Condition实现等待/通知******************************//
//		ReentrantLockAndCondition reentrant=new ReentrantLockAndCondition();
//		MyThread t1=new  MyThread(reentrant);
//		t1.start();
//		Thread.sleep(3000);
//		reentrant.signal();
		
		//*********4、1、6 使用多个Condition实现等待/通知******************************//
//		ReentrantLockMultiCondition reentrantLockMultiCondition=new ReentrantLockMultiCondition();
//		ReentrantLockMultiConditionA A=new ReentrantLockMultiConditionA(reentrantLockMultiCondition);
//		ReentrantLockMultiConditionB B=new ReentrantLockMultiConditionB(reentrantLockMultiCondition);
//		A.start();
//		Thread.sleep(1000);
//		B.start();
//		Thread.sleep(1000);
//		reentrantLockMultiCondition.signalAll_A();
//		Thread.sleep(1000);
//		reentrantLockMultiCondition.signalAll_B();
		
		//*********4、1、10 getHoldCount()、getQueueLength()、getWaitQueueLength()的使用	HR******************************//
		/**
		 * <p>方法 getHoldCount()的作用是查询当前线程保持此锁的个数，也就是调用Lock的次数
		 */
//		HoldCount getHoldCount=new HoldCount();
//		getHoldCount.Lock1();
		
		//*********4、1、13 方法lockInterruptibly()、tryLock()和tryLock(long timeout、TimeUnit unit)******************************//
//		final TryLockTest test=new TryLockTest();
//		
//		Runnable run=new Runnable(){
//			@Override
//			public void run() {
//				test.waitMethod();
//			}
//			
//		};
//		
//		Thread A=new Thread(run);
//		A.setName("A");
//		A.start();
//		
//		Thread B=new Thread(run);
//		B.setName("B");
//		B.start();
		
		//*********4、2 、1 读读共享******************************//
//		ReentrantReadWriteLockTestRead reentrantReadWriteLockTestRead=new ReentrantReadWriteLockTestRead();
//		
//		ReentrantReadWriteLockTestReadA A=new ReentrantReadWriteLockTestReadA(reentrantReadWriteLockTestRead);
//		ReentrantReadWriteLockTestReadB B=new ReentrantReadWriteLockTestReadB(reentrantReadWriteLockTestRead);
//		A.setName("A");
//		A.start();
//		B.setName("B");
//		B.start();
//		/**
//		 * 几乎同时执行，所以实例变量是共享的，或者是线程安全的
//		 */
//		
//		
		
		//*********6、1立即加载/"饿汉模式"******************************//
//		MyThread t1=new  MyThread();
//		MyThread t2=new  MyThread();
//		t1.start();
//		t2.start();
//		//控制台打印的hasCode是同一个值，说明对象是同一个，也就是实现了立即加载单例模式
		
		//*********7.1.1 验证new 、runnable、terminatd******************************//
		
//		MyThread t1=new  MyThread();
//		System.out.println("main方法中的状态1： "+t1.getState());
//		Thread.sleep(1000);
//		t1.start();
//		Thread.sleep(1000);
//		System.out.println("main方法中的状态2： "+t1.getState());
//		
		//*********7.1.2 验证Timed_waiting******************************//
		/**
		 * 改状态为线程执行了sleep方法呈等待状态
		 */
		
		//*********7.1.3验证blocked******************************//
		/**
		 * 某个线程在等待锁的时候
		 */
		
		//*********7.1.4 验证WAITING******************************//
		/**
		 * 是线程执行了Object。wait方法后所处于的状态
		 */
		
		//*********7.2线程组******************************//
		/**
		 * 线程组的作用是，可以批量的管理线程或线程组对象，有效地对线程或线程组对象进行组织
		 */
//		Queue ArraysBlockingQueue=new ConcurrentLinkedQueue();
	} 
	
}

