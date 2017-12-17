package com.nilo.thread;

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
	}
	/*
	线程总结：
	1、同步——synchronized机制、Lock和Condition机制和关于线程中断
	  Java在Java SE5.0引入不同与synchronized 的另一种加锁的方式，java.cncurrent.lock 包中的两个锁类ReentrantLock为可重入锁，还有ReentrantReadWriteLock
	  首先介绍synchronized关键字和内部锁。
	     首先理解每个对象都有一个内部锁，如果一个方法用synchronized声明，那么该对象的锁将保护整个方法，也就是说线程要调用该方法，必须获得内部的对象锁，与Lock和Condition相比，内部对象锁
	     只有一个相关条件和一个等待集，Object的wait方法添加一个线程的等待集，notify和notifyAll解除等待线程的阻塞状态。由锁来管理synchronized方法的线程，由条件来管理那些调用
	  wait线程。	
	2、java.util.concurrent.locks
	 接口Lock实现比使用synchronized方法和语句可获得的更广泛的锁定操作。此实现允许更灵活的结构。可以具有差别很大的属性，可以支持多个相关的Condition对象
	接口Condition ：Condition将Object监视器方法（wait、notify和notifyAll）分成截然不同的对象，以便通过将这些对象与任意Lock实现组合使用，每个条件有自己的等待，其中
	  Lock替代了synchronized方法和语句的使用，Condition替代了Object监视器方法的使用（await、signal和signalAll）。
	3、ReentrantLock类，为可重入锁
	  锁是可重入的，锁保持一个持有技数器（HoldCount)来跟踪一个线程对Lock方法的嵌套调用，线程应保证每次调用Lock都要调用unlock方法来释放锁。由于可重入特性，被一个锁保护的对象
	 可以使用另一个使用了相同的锁的方法。此类的构造方法接受一个可选的公平参数。当设置为true时，在多线程的竞争作用下，这些锁倾向于将访问权授予等待时间最长的线程。否则此锁将无法保证任何
	特定的访问顺序。与采用默认设置（使用不公平锁）相比，使用公平锁的程勋在许多线程访问时表现为很低的总体吞吐量，但是在获得锁和 保证锁分配的均衡性时差异较小。
	4、ReentrantReadWriteLock读写锁
	  把解锁操作放在finally子局中是至关重要的，如果在临界区的代码抛出异常，锁必须释放。否则其他线程将永远阻塞。
	  读写锁应用场合：我们有时候遇到对同一个内存区如数组或者链表进行多线程读写的情况，一般来说有以下处理：1、不加任何限制，多见于读取写入很快的情况，但有时也会出现问题，2、对读写函数
	  都加以同步互斥。读写锁的本意是分别对读写状态进行互斥区分，有互斥时才加锁，否则放行，互斥情况：读写、写写、
   5、stop、suspend、resume被弃用的原因：
     stop终止所有未结束的方法，包括run方法，当线程被终止时，立即释放被其他锁住的所有对象锁。这会导致对象处于不一致状态。
     suspend挂起线程不释放持有的锁，容易造成死锁，
     ————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
   一、  synchronized和Lock区别：
    1、 synchronized：是属于Java的关键字，synchronized实在jvm层面实现的，它的工作原理是这样的,当A获取对象O的锁，则B不能对O进行操作，必须一直等到A释放了O对象的锁才可以
     使用，除非发生异常，此时jvm线程自动释放锁。
     wait():释放占有的对象锁，线程进入等待池，释放CPU，而其他正在等待的线程可抢占此锁，获得锁的线程即可运行程序。而sleep()不同的是，线程调用此方法后，会休眠一段时间，休眠期间
     会暂时释放CPU，但并不释放对象锁。也就是说，在休眠期间，其他线程依然无法进入此代码内部。休眠结束，线程重新获得CPU，执行代码。wait()和sleep()最大的不同在于前者释放对象锁。
     notify():改方法会唤醒因调用对象的wait()而等待的线程，其实就是对对象锁的唤醒，从而使得wait()的线程可以有机会获取对象锁。调用notify()后，并不会立即释放锁，而是继续执行
     当前代码，直到synchronized中的代码全部执行完，才会释放对象锁。jvm则会在等待的线程调度一个线程去获得对象锁，执行代码。需要注意:wait() 、notify()必须在synchronized代码
     块中。
    2、Lock
    Lock与synchronized不同的是Lock并发Java内置的，而是一个类
    Lock四种方法获取锁：
    Lock()方法与synchronized相同。
    tryLock方法是有返回值的，它表示用来尝试获取锁，如果获取成功则返回true，如果获取失败则返回false，也就是说这个方法这个方法无论如何都会立即返回。在拿不到锁时不会一直在那等待。
    tryLock(long time,TimeUnit unit)和tryLock相似，如果一开始拿到锁或者在等待期间拿到锁则返回true。
    LockInterruptiby() 当通过这个方法区获得锁时，如果线程正在等待获取锁，则这个线程能够相应中断，即中断线程的等待状态。假若线程A获取锁，而线程B只能等待，那么对线程B调用Lock.LockInterruptiby
    那么B中断线程。
     
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
*/}
