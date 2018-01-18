package com.nilo.thread;

public class MyThread extends Thread{
	
   //********1、执行随机性演示*****************//
	/*
	@Override
	public void run(){
		try {
			for (int i = 0; i < 10; i++) {
				int time=(int) (Math.random()*1000);
				Thread.sleep(time);
				System.out.println("run="+Thread.currentThread().getName());
			}
		} catch (Exception e) {
		}
	}
	*/
	
	//*****2、实例变量与线程安全  （不安全演示）********************//
	/*
	private  int count=5;
	public MyThread(String name){
		super();
		this.setName(name);
	}
	@Override
	public  void run() {
		super.run();
		while(count>0){
			count--;
			System.out.println("由"+this.currentThread().getName()+" 计算 count="+count);
		}
	}*/
	
	//************2、实例变量与线程安全 （共享变量演示）  ****************//
	/**
	 * <ul>
	 *    <li>synchronized 可以在任意对象及方法上加锁，而加锁的这段代码称为“互斥区”或者“临界区”。
	 *    <li>当一个线程想要执行同步方法里的代码时，线程首先尝试去拿这把锁，如果能够拿到这把锁，那么线程就可以执行synchronize里面的代码。
	 *       如果拿不到这个锁，那么线程就会不断的尝试拿这把锁，直到能够拿到为止，而且是有多个线程同时去争抢这把锁。
	 *    <li>非线程安全主要是指多个线程对同一个对象中的同一个（*实例变量*）进行操作时出现值被更改、值不同步的情况。
	 * </ul>
	 */
	/*
	private  int count=5;
	@Override
	public  synchronized void run() {
		super.run();
		count--;
		System.out.println("由"+this.currentThread().getName()+" 计算 count="+count);
	}
	*/
	
	//*************************3、实现非线程安全的环境***************//
	/*
	private  String username;
	private  String password;
	public MyThread(String name,String pd){
		this.username=name;
		this.password=pd;
	}
	@Override
	public void run() { 
		try {
			LoginServlet.doPost(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	//*************************3、实现安全线程安全的环境在doPost加synchronize**//
	/*
	private  String username;
	private  String password;
	public MyThread(String name,String pd){
		this.username=name;
		this.password=pd;
	}
	@Override
	public void run() {
		try {
			LoginServlet.doPost(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	//**************4、isAlive()方法是判断当前线程是否处于活动状态*******************//
	/**
	 * <ul>
	 *    <li>方法isAlive()的作用是测试线程是否处于活动状态。什么是活动状态呢？
	 *    <li>活动状态就是线程已经启动且尚未终止。线程处于正在运行或准备运行的状态，就认为线程是存活的
	 * </ul>
	 */
	/*
	@Override
	public void run() {
		System.out.println("run="+this.isAlive());
	}
	*/
	
	//************************5、sleep()方法***********************************//
	
	/**
	 * <ul>
	 *   <li>Thread的start和run:
	 *   <li> start：用start方法来启动线程，真正实现了多线程运行，这时无需等待run方法体代码执行完毕而直接继续执行下面的代码。
	 *        通过调用Thread类的start()方法来启动一个线程，这时此线程处于就绪（可运行）状态，并没有运行，一旦得到cpu时间片，
	 *        就开始执行run()方法，这里方法run()称为线程体，它包含了要执行的这个线程的内容，Run方法运行结束，此线程随即终止。
	 *   <li>run：run()方法只是类的一个普通方法而已，如果直接调用Run方法，程序中依然只有主线程这一个线程，其程序执行路径还是只有一条，
	 *       还是要顺序执行，还是要等待run方法体执行完毕后才可继续执行下面的代码，这样就没有达到写线程的目的。
	 *   <li>总结：调用start方法方可启动线程，而run方法只是thread的一个普通方法调用，还是在主线程里执行。
	 *   <li>方法sleep()的作用是在指定的毫秒内让当前正在执行的线程休眠
	 * </ul>
	 */
	
	/*
	@Override
	public void run() {
		try {
			System.out.println("run threadName="+this.currentThread().getName()+" begin");
			this.sleep(2000);
			System.out.println("run threadName="+this.currentThread().getName()+" end");
		} catch (Exception e) {
		}
	}
	*/
	
	//***********6、getId()方法*******************//
	/**
	 * <ul>getId()方法的作用是获取线程的唯一标识
	 */
	/*
	@Override
	public void run() {
		System.out.println("name= "+this.getName()+" id= "+this.getId());
	}
	*/
	
	//******************************************7、停止线程*********************************//
	/**
	 * <p>总结：停止一个线程意味着在线程处理完任务之前停掉正在做的操作，也就是放弃当前操作。
	 * <p>停止一个线程可以使用Thread.stop()方法，但是最好不要用。虽然它确实可以停止一个正在运行的线程，但是这个方法是不安全的。对锁定的状态进行了解锁，导致数据得不到同步处理
	 * <p>大多数停止一个线程的操作使用Thread.interrupt()方法，尽管方法的名称是停止、终止的意思、但这个方法不会终止一个正在运行的线程。
	 * <p>java有3种终止正在运行的线程：1、使用退出标志，是线程正常退出，也就是当run方法完成后线程终止；2、使用stop方法强行终止线程，但是不推荐；3、使用interrupt方法中断线程。
	 * <p>调用interrupt()方法仅仅是在当前线程中打了个停止的标记，并不是真正的停止线程。
	 */
	
	/**
	 * <ul>判断线程是否是停止状态
	 *   <li>interrupted():测试当前线程是否已经中断,具有清除状态的功能
	 *   <li>isInterrupt()：测试线程是否已经中断
	 * <ul> 
	 */
	
	//*********7、1将方法interrupt()与return结合使用停止线程******************************//
	/*
	@Override
	public void run() {
		while(true){
			if(this.interrupted()){
				System.out.println("停止了！");
				return;
			}
			System.out.println("timer= "+System.currentTimeMillis());
		}
		
	}
	*/
	
	//*********7、2异常法使用停止线程（强烈建议）******************************//
	/*
	@Override
	public void run() {
		try {
			for (int i = 0; i < 500000000; i++) {
				if(this.interrupted()){
					System.out.println("已经停止状态了！我要退出了！");
					throw new InterruptedException();
				}
				System.out.println("i="+(i+1));
			}
			System.out.println("我再for下面");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	*/
	//*********8、暂停线程******************************//
	/**
	 * <p>暂停线程意味着此线程还可以恢复运行，Java可以使用suspend暂停、resume恢复线程
	 * <p>缺点——独占
	 * <p>缺点——不同步
	 */
	//*********8、1suspend、resume********************//
	/*
	private long i=0;
	
	public long getI(){
		return i;
	}
	
	public void setI(long i){
		this.i=i;
	}
	@Override
	public void run() {
		while(true){
			i++;
		}
	}
	*/
	
	//*********9、yield方法******************************//
	/**
	 * yield()方法的作用是放弃当前的CPU资源。将它让给其他的任务去占用CPU执行时间。但放弃的时间不确定。
	 */
	
	/**
	 * 拓展：静态变量；实例变量、局部变量的存储位置；判断是共享还是线程隔离
	 * 执行结果看，静态变量是线程相互修改的，说明是共享的；实例变量也是多线程相互修改的，说明也是线程共享的；局部变量是线程私有的
	 * 
	 * 实例变量属于某个对象的属性，必须创建了实例对象，其中的实例变量才会被分配空间，才能使用这个实例变量。
	 *    同一个类实例化两次则两次引用的对象不相等。并且实例变量存储在Java堆中
	 * 静态变量不属于某个实例对象，而是属于类，所以也称为类变量，只要程序加载了类的字节码，不用创建任何实例对象，静态变量就会被分配空间，静态变量就可以被使用了。
	 * 总之，实例变量必须创建对象后才可以通过这个对象来使用，静态变量则可以直接使用类名来引用。
	 * jvm
	 * 堆：用来存放动态产生的数据，比如new出来的对象。注意创建出来的对象只包含属于各自的成员变量，并不包括成员方法。
	 *    因为同一个类的对象拥有各自的成员变量，存储在各自的堆中，但是他们共享该类的方法，并不是每创建一个对象就把成员方法复制一次。
	 */
	/*
	static int a=0;
	public int b=10;
	
	@Override
	public void run() {
		int c=100;
		for (int i = 0; i < 100; i++) {
			a++;
			System.out.println("静态变量 a="+a +" Name"+this.currentThread().getName());
		}
		for (int i = 0; i < 100; i++) {
			b++;
			System.out.println("实例变量 b="+b+" Name"+this.currentThread().getName());
		}
		for (int i = 0; i < 100; i++) {
			c++;
			System.out.println("局部变量 c="+c+" Name"+this.currentThread().getName());
		}
	}
	*/
	
	
	//*********10、线程的优先级******************************//
	 /**
	  * <p>线程的优先级具有继承性
	  * <p>高优先级的线程总是大部分先执行。
	  * <p>优先级具有随机性
	  */
	
	//*********11、守护线程******************************//
	/**
	 * 守护线程是一种特殊的线程，当进程中不存在非守护线程了，则守护线程自动销毁
	 */
	/*
	private int i=0;
	@Override
	public void run() {
		try {
			while (true) {
				i++;
				System.out.println("i="+i);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	//*********2章 对象及变量的并发访问******************************//
	/**
	 * <p>synchronized对象监视器为Object时的使用
	 * <p>synchronized对象监视器为Class时的使用
	 * <p>非线程安全是如何
	 * <p>关键字volatile的主要作用
	 * <p>关键字volatile与synchronized的区别使用情况
	 */
	
	//*********2-1synchronized同步方法******************************//
	/**
	 * 非线程安全问题存在于实例变量中
	 */
	
	//*********脏读演示******************************//
	/**
	 * <p>脏读一定会出现操作实例变量的情况下，这就是不同线程争抢实例变量的结果
	 */
	/*
	private ThreadDirty threadDirty;
	public  MyThread(ThreadDirty threadDirty){
		this.threadDirty=threadDirty;
	}
	@Override
	public void run() {
		try {
			threadDirty.setValue("B", "BB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	//*********2.1.6synchronized锁重入******************************//
	/**
	 * <p>关键字synchronized拥有锁重入的功能，也就是在使用synchronized时，当一个线程得到一个对象锁后，再次请求此对象锁时是可以再次得到该对象的锁的
	 *    这也证明在一个synchronized方法/块的内部调用本类的其他synchronized方法/块时，是永远可以得到锁的。
	 * <p>可重入锁的概念：自己可以再次获取自己的内部锁。比如有一个线程获得了某个对象的锁，此时这个对象锁还没有释放，当其再次想要获取这个对象的锁时还是可以获取的，如果不可锁重入，就会造成死锁。
	 * 如下例子，可重入锁也支持在父子集成的环境中
	 */
	/*
	private Sub sub;
	public  MyThread(Sub sub){
		this.sub=sub;
	}
	@Override
	public void run() {
		try {
			sub.operateSubMethod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	//*********2.1.7出现异常，锁自动释放******************************//
	/**
	 * <p>当一个线程的执行出现异常时，其所持有的锁会自动释放
	 */
	
	//*********2.1.8同步不具有集成性******************************//
	/**
	 * 
	 */
		
	//*********2.2synchronized同步语句块******************************//
	//sleep时锁不释放，导致堵塞，并发性不好
	
	//*********2.2.2synchronized同步代码块的使用******************************//
	/**
	 * <p>当两个并发线程访问同一个对象object中的synchronized(this)同步代码块时，一段时间内只能有一个线程被执行，另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块
	 */
	
	//*********2.2.4一半同步，一半异步******************************//
	
	//*********2.2.5 synchronized 代码块间的同步性******************************//
	
	//*********2.2.6 synchronized() 代码块是锁定当前对象的******************************//
	
	//*********2.2.7 将任意对象作为对象监控器******************************//
	
	//*********2.2.8 细化验证3个结论******************************//
	/**
	 * <p>当多线程同时执行synchronized(x){}同步代码块时呈同步效果
	 * <p>当其他线程执行x对象中synchronized同步方法时呈现同步效果
	 * <p>当其他线程执行x对象方法里面的synchronized(x)代码块时也呈现同步效果
	 */
	
	//*********2.2.9 静态同步synchronized方法与synchronized(class)代码块******************************//
	/**
	 * <p>synchronized 关键字加到static静态方法上是给Class类加锁，而synchronized关键字加到非static静态方法是给对象加锁
	 */
	
	//*********2.2.10 数据类型String 的常量池特性******************************//
	/**
	 * <p>同步synchronized代码块都不用String作为锁对象，因为jvm 对String具有缓存例如： String a="a";String b="a";a==b导致死锁；而改用其他，比如new Object() 实例一个Object对象，但它并不放入缓存中
	 */
	
	//*********2.2.11synchronized方法无限等待的决绝办法是用不同的对象监视器******************************//
	
	//*********2.2.12 多线程的死锁******************************//
	
	//*********2.2.13内置类和静态内置类******************************//
	
	//*********2.3volatile******************************//
	/**
	 * <p>关键字volatile的作用是强制从公共堆栈中取得变量的值，而不是从线程私有数据栈中取得变量的值
	 * 解释：执行线程时，jvm会把实例变量从主内存复制工作内存，线程未结束时，工作内存值是不变的，volatile是强制线程从主内存获取值
	 * <p>使用volatile关键字增加了实例变量在多线程之间的可见性。但volatile关键字最致命的缺点是不支持原子性。
	 * <p>关键字synchronized和volatile比较
	 *   <li>关键字volatile是线程同步的轻量级实现，所以volatile性能肯定比synchronized要好，并且volatile只能修饰变量，而synchronized可以修饰方法，以及代码块。
	 *   <li>多线程访问volatile不会发生阻塞，而synchronized会出现阻塞。
	 *   <li>volatile能保证数据的可见性，但不能保证原子性；而synchronized可以保证原子性，也可以间接保证可见性，因为它会将私有内存和公共内存中的数据做同步
	 *   <li>再次重申一下，关键字volatile解决的是变量在多线程之间的可见性；而synchronized关键字是解决多线程之间访问资源的同步性。
	 */
	
	//*********2.3.4 volatile非原子的特性******************************//
	/**
	 * 关键字volatile虽然增加了实例变量在多线程之间的可见性，但它不具备同步性，那么也就不具备原子性
	 * <p>关键字volatile主要使用的场合是在多线程中感知实例变量被更改了，并且可以获取最新的值使用，也就是用多线程读取共享变量时可以获取最新值使用。
	 * <p>关键字volatile提示线程每次从共享内存中读取变量，而不是从私有内存中读取，这样就保证了同步数据的可见性。但在这里需要注意的是 i++ 的操作不是原子性的。
	 * <p>表达式i++ 分三步；1、从内存取出i的值；2计算i的值；3、将i的值写到内存中。使用volatile在第二步可能出现脏读；解决办法是使用synchronized，也可以使用原子类AtomicInteger
	 */
	
	//*********2.3.7synchronized代码块有volatile同步功能******************************//
	/**
	 * 关键字synchronized可以保证在同一刻，只有一个线程可以执行某个方法或代码块，它包含两个特征：互斥和可见；最后一句学习多线程并发，要着重外练互斥、内修可见
	 */
	
	
	//*********3、线程间通信******************************//
	/**
	 * 主要掌握以下几点
	 * <p>使用wait/notify实现线程间的通信
	 * <p>生产者/消费者模式的实现
	 * <p>方法join的使用
	 * <p>ThreadLocal类的使用
	 */
	//*********3、1等待/通知机制******************************//
	
	//*********3、3等待/通知机制的实现******************************//
	/**
	 *   方法wait()的作用是使当前执行代码的线程进行等待，wait()方法是Object类的方法，该方法用来将当前线程置入“预执行队列”中，并且在wait()所在的代码处停止执行，直到接到
	 * 通知或者被中断为止。在调用wait()之前，线程必须获得该对象的对象级别锁，即只能在同步方法或者同步块中执行wait()方法。在执行wait()方法后，当前线程释放锁。
	 *   方法notify()也要在同步方法或者同步块中调用，即在调用前，线程也必须获得该对象的对象级别锁。
	 * 该方法用来通知那些可能等待该对象的对象锁的其他线程，如果有多个线程等待，则由线程规划器随机挑选处其中一个呈wait状态的线程，对其发出通知notify，并使他等待获取对象的对象锁。notify()方法执行后不会立即释放锁
	 *   关键字synchronized可以将任何一个object对象作为同步对象来看待，而Java为每个object都实现wait()和notify(),他们必须用在被synchronized同步的object的临界区内。通过调用wait()方法
	 * 可以使处于临界区内的线程进入等待状态。同时释放被同步对象的锁。而notify操作可以唤醒一个因调用了wait操作而处于阻塞状态中的线程，使其进入就绪状态。被重新唤醒的线程会视图重新获取临界区的控制权，也就是锁，
	 * 并继续执行临界区内wait之后的代码。如果发出notify操作时没有处于阻塞状态的线程，那么该命令会被忽略。
	 *   方法wait()可以使调用该方法的线程释放共享资源的锁，然后从运行状态退出，进入等待队列，直到被再次唤醒。
	 *   方法notify()可以随机唤醒等待队列中等待同一共享资源的一个线程，并使该线程退出等待队列，进入可运行状态，也就是notify仅通知一个线程。
	 *   方法notifyAll()可以使所有正在等待队列中等待同一资源的全部线程从等待状态退出，进入可运行状态。
	 *   
	 * 线程通过api改变其状态：
	 * 1）新创建一个新的线程对象后，再调用它的start()方法，系统会为此线程分配CPU资源，使其处于Runnalbe(可运行)状态，这是一个准备运行的阶段。如果线程抢占到CPU资源，此线程就处于Running(运行)状态。
	 * 2）Runnalbe(可运行)状态和Running(运行)可相互切换，因为有可能线程运行一段时间后，有其他优先级高的线程抢占了CPU资源。线程进入Runnable的状态大体分如下5种情况。
	 *   <li>调用sleep()方法后经过的时间超过了指定的休眠时间
	 *   <li>线程调用的阻塞IO已经返回，阻塞方法执行完毕。
	 *   <li>线程成功地获得了试图同步的监视器
	 *   <li>线程正在等待某个通知，其他线程发出了通知
	 *   <li>处于挂起状态的线程调用了resume恢复方法
	 * 3）线程处于阻塞状态大体分如下5种情况。
	 *   <li>线程调用sleep方法，主动放弃占用的处理资源
	 *   <li>线程调用了阻塞式IO方法，在该方法返回前，该线程被阻塞
	 *   <li>线程试图获得一个同步监视器，但该监视器正在被其他线程所持有
	 *   <li>线程等待某个通知
	 *   <li>程序调用了suspend方法将该线程挂起
	 * 4）run()方法运行结束后进入销毁阶段，整个线程执行完毕
	 * 每个线程对象都有两个队列，一个是就绪队列，一个是阻塞队列。就绪队列存储了将要获得锁的线程，阻塞队列存储被阻塞的线程。
	 */
	
	//*********3、1、5当interrupt遇到wait******************************//
	/**
	 * 当线程处于wait状态时，调用线程对象的interrupt会出现异常
	 * 1）执行完同步代码块就会释放对象锁
	 * 2）在执行同步代码块的过程中遇到异常而导致线程终止，锁也会被释放。
	 * 3）在执行同步代码块的过程中，执行了锁所属对象的wait方法，这个线程会释放对象锁。
	 */
	
	//*********3、1、6调用notify一个只随机通知一个线程进行唤醒******************************//
	
	//*********3、1、7notifyAll唤醒所有等待线程******************************//
	
	//*********3、1、8 方法wait(long)的使用******************************//
	/**
	 * 带一个参数的wait(long)方法的功能是等待某一时间内是否有线程对锁进行唤醒，如果超过了这个时间则自然唤醒
	 */
	
	//*********3、1、11生产者/消费者模式  操作值******************************//
	
	//*********3、1、12 通过管道进行线程间通信：字节流******************************//
	/**
	 * 在Java语言中提供各种各样的输入/输出流Stream，使我们能够很方便地对数据进行操作，其中管道流(pipeStream)是一种特殊的流，用于在不同线程间直接传送数据。一个线程发送数据
	 * 到输出管道，另一个线程从输入管道中读数据。
	 */
	
	//*********3、2方法join的使用******************************//
	/**
	 *   在很多情况下，主线程创建并启动子线程，如果子线程中要进行大量的耗时计算，主线程往往将早于子线程结束之前结束。这时，如果主线程想等待子线程执行完成后再结束，比如子线程处理一个数据，
	 * 主线程要取得这个数据表值就要用的join方法了，join的作用是等待线程对象销毁
	 *   join与synchronized的区别：join在内部使用wait()方法进行等待，而synchronized使用的是对象监视器的原理作为同步。
	 */
	/*
	@Override
	public void run() {
		try {
			int secondValue=(int) (Math.random()*10000);
			System.out.println("子线程需要执行的总时间："+secondValue);
			Thread.sleep(secondValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	//*********3、2、3方法join(long)的使用******************************//
	//*********3、2、4方法join()与sleep()区别******************************//
	/**
	 * <p>方法join的功能在内部是使用wait()方法实现的，所以join方法具有释放锁的特点。
	 */
	
	//*********3、3类ThreadLocal的使用;主要是解决静态变量共享的问题******************************//
	/**
	 * 变量值的共享可以使用public static变量的形式，所有的线程都使用同一个public static变量，如果想实现每个线程都有自己的共享变量，则需要是ThreadLocal类。
	 * 类ThreadLocal主要解决的就是每个线程绑定自己的值，可以将ThreadLocal类比喻成全局存放数据的盒子，盒子中可以存储每个线程的私有数据。
	 */
	//*********3、3、1 方法get()与null******************************//
	
	
	//*********4Lock的使用******************************//
	/**
	 * <p>ReentrantLock类的使用
	 * <p>ReentranReadWriteLock类的使用
	 */
	//*********4、1、1使用ReentrantLock实现同步：测试1******************************//
	/*
	public ReentrantLockTest test;
	public MyThread(ReentrantLockTest test){
		this.test=test;
	}
	@Override
	public void run() {
		try {
			test.testMethod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	*/
	//*********4、1、3使用Condition实现等待/通知：错误用法与解决******************************//
	
	/**
	 *   关键字synchronized与wait和notify/notifyAll方法相结合实现等待通知模型，类ReentrantLock也可以实现同样的贡呢，单需要借助Condition对象。
	 *condition类时在JDK5中出现的技术，使用它有更好的灵活性，比如可以实现多路通知功能，也就是在一个Lock对象里面可以创建多个Condition（即对象监视器）实例，
	 *线程对象可以注册在指定的Condition中，从而可以有选择地进行线程通知，在调度线程上更加灵活。
	 *   在使用notify/notifyAll方法进行通知时，被通知的线程却是由jvm随机选择的。但使用ReentrantLock结合Condition类是可以实现前面的介绍的选择性通知
	 *这个功能是非常重要的，而且在Condition类中默认提供的。
	 *   而synchronized就相当于整个Lock对象只有一个单一的Condition对象，所有线程都注册在它一个对象身上。线程开始notifyAll时，通知所有线程WAITING线程，
	 * 没有选择权，会出现相当大的效率问题。备注：Condition.await调用之前必须先lock
	 */
	
	//*********4、1、4 正确使用Condition实现等待/通知******************************//
	/**
	 * <p>Object类中的wait()方法相当于Condition类中的await()方法
	 * <p>Object类中的wait(long)方法相当于Condition类中的await(long)方法
	 * <p>Objetc类中的notify()方法相当于Condition类中的signal()方法
	 * <p>Objetc类中的notifyAll()方法相当于Condition类中的signalAll()方法
	 */
//	private ReentrantLockAndCondition reentrant;
//	public MyThread(ReentrantLockAndCondition reentrant){
//		this.reentrant=reentrant;
//	} 
//	@Override
//	public void run() {
//		try {
//			reentrant.await();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
	//*********4、1、6 使用多个Condition实现等待/通知******************************//
	/**
	 * 通过实验可以得知，使用ReentrantLock对象可以唤醒指定的线程，这是控制部分线程行为的方便方式。
	 */
	
	
	//*********4、1、9 公平锁和非公平锁******************************//
	/**
	 *  公平锁和非公平锁:锁Lock分为：公平锁和非公平锁，公平锁表示线程获取锁的顺序是按照线程加锁的顺序来分配的，即先来先得的FIFO先进先出顺序，而非公平锁就是一种获取锁的
	 *  抢占制，是随机获得锁的，和公平锁不一样的就是先出来的不一定先得到锁
	 */
	
	//*********4、1、10 getHoldCount()、getQueueLength()、getWaitQueueLength()的使用	HR******************************//
	/**
	 * <p>方法 getHoldCount()的作用是查询当前线程保持此锁的个数，也就是调用Lock的次数
	 * <p>方法getQueueLength()的作用是返回正在等待获取此锁定的线程估计数；可以是sleep的等待线程
	 * <p>方法getWaitQueueLength(Condition)的作用是返回等待与此锁定相关的给定条件Condition的线程估计数
	 */
	
	//*********4、1、11 方法hasQueueThread(),hasQueueThreads(),hasWaiters()******************************//
	/**
	 * <p>方法boolean hsaQueueThread(Thread thread)的作用是查询指定线程是否正在等待获取此锁
	 * <p>方法boolean hsaQueueThreads()的作用是查询是否有线程等待获取此锁定
	 * <p>方法boolean hasWaiters(Condition condition)的作用是查询是否有线程正在等待与此锁定有关的Condition条件
	 */
	
	//*********4、1、12 方法isFair()、isHeldByCurrentThread()和isLocked()******************************//
	
	/**
	 * <p>isFair()的作用是判断是不是公平锁，在默认的情况下，ReentrantLock类使用的是非公平锁。
	 * <p>isHeldByCurrentThread()的作用是查询当前线程是否保持此锁定。
	 * <p>isLocked()的作用是查询此锁定是否由任意线程保持。
	 */
	
	//*********4、1、13 方法lockInterruptibly()、tryLock()和tryLock(long timeout、TimeUnit unit)******************************//
	/**
	 * <p>方法lockInterruptibly()的作用是：如果当前线程未被中断，则获取锁定，如果已经被中断则出现异常。
	 * <p>方法Boolean tryLock()的作用是，仅在调用时锁定未被另一个线程保持的情况下，才获取该锁定。
	 * <p>方法tryLock(long timeout,TimeUnit unit)的作用是，如果锁定在给定时间内没有被另一个线程保持，且当前线程未被中断，则获取该锁定。
	 */
	
	//*********4、1、14 方法awaitUniterruptibly******************************//
	
	//*********4、2 使用ReentrantReadWriteLock******************************//
	/**
	 * 类ReentrantLock具有完全互斥排他的效果，即同一时间只有一个线程在执行ReentrantLock.lock方法后面的任务。这样虽然保证了实例变量的线程安全性，但效率
	 * 却是非常低下的，所以在jdk中提供了一种读写锁ReenteantReadWriteLock类，使用它可以加快运行效率，在某些不需要操作实例变量的方法中，完全可以使用读写锁
	 * 来提升代码运行速度。读写锁表示也有两个锁，一个是读操作相关的锁，也称为共享锁；另一个是写操作相关的锁，也叫排他锁。也就是多个读锁之间不互斥，读锁与写锁互斥，写锁与写锁互斥
	 * 。
	 * 
	 * 
	 * 主要应用是缓存 cache
	 */
	
	//*********4、2 、1 读读共享******************************//
	//*********4、2 、2读写互斥******************************//
	//*********4、2 、3写读互斥******************************//
	//*********4、2 、4写写互斥******************************//
	
	
	//*********5、定时器 Timer******************************//
	/**
	 * <p>如何实现指定时间执行任务
	 * <p>如何实现按指定周期执行任务
	 */
	
	//********5.1 定时器Timer的使用******************************//
	//*********5.1.1 方法schedule(TimerTask task,Date time)******************************//
	/**
	 * <p>该方法的作用是在指定的日期执行一次某任务
	 */
	
	
	
	
	
	//*********6、单利模式与多线程******************************//
	
	//*********6、1立即加载/"饿汉模式"******************************//
    /**
     * 什么是立即加载，就是使用类的时候已经将对象创建
     */
	
//	@Override
//	public void run(){
//		System.out.println(Singleton.getInstance().hashCode());
//	}
	
	//*********6、2延迟加载/懒汉模式******************************//
	/**
	 * <p>是在调用方法时实例才被创建
	 * <p>缺点：在多线程的环境下，根本不可能保持单例的状态
	 * <p>解决方案1、synchronized关键字同步方法，缺点运行效率非常低；2、同步代码块，缺点也是效率低；3、对于重要的代码单独同步
	 * 改方法运行效率高，但是无法解决得到的同一个实例对象的结果；4使用DCL双检查锁机制；volatile、synchronized。
	 */
	
	//*********6、3使用静态内置类实现单利模式"******************************//
	//*********6、4序列化与反序列化的单例模式******************************//
	//*********6、5使用static代码块实现单利模式******************************//
	
	//*********7、拾遗增补******************************//
	/**
	 * 线程组的使用
	 * 如何切换线程状态
	 * SimpleDataFormat类与多线程的解决方法
	 * 如何处于线程异常
	 */
	
	//*********7、1线程的状态******************************//
	
	//*********7.1.1 验证new 、runnable、terminatd******************************//
	public MyThread(){
		System.out.println("构造方法中的状态： "+Thread.currentThread().getState());
	}
	@Override
	public void run(){
		System.out.println("run方法中的状态： "+Thread.currentThread().getState());
	}
	
	
//	《Java多线程编程核心技术》 的笔记及自己的一些总结  http://www.54tianzhisheng.cn/2017/06/04/Java-Thread/
	
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
     
     -----------------------------------------------应用-------------------------------------------------------------//
	并发容器类：
	1、ConcurrentHashMap：通过颗粒度更细的分段锁进行加锁。size、isEmpty只是个估计 http://blog.csdn.net/qq_17612199/article/details/51033916
	     原理：
	2、CopyOnWriteArrayList：
	     原理：
	3、ConcurrentLinkedQueue：  http://blog.csdn.net/u013991521/article/details/53068549
	     原理：
	4、PriorityQueue:
	     原理：
	5、LinkedBlockingQueue:使用锁实现生产者消费者  http://blog.csdn.net/x_i_y_u_e/article/details/52513038
	     原理：
	6、ArraysBlockingQueue:
	     原理：
	同步容器：
	1、Vector：
	     原理：
	2、Hashtable:
	    原理：
	
	同步工具类：
	信号量（Semaphore）:可用于线程池
	     原理：
	栅栏(Barrier):CyclicBarrier
	     原理：
	闭锁(Latch): 
	   1、CountDownLatch：
	               原理：
	   2、FutureTask:
	               原理：
	
	任务执行：
	Executor框架: https://zhuanlan.zhihu.com/p/23635167
	  原理：
	线程池：是与工作对列相关；https://zhuanlan.zhihu.com/p/28232722   http://www.54tianzhisheng.cn/2017/07/29/ThreadPool/
	     最佳线程数目 = （线程等待时间与线程CPU时间之比 + 1）* CPU数目
	 1、newFixedThreadPool
	 原理：
	 2、newCachedThreadPool
	 原理：
	 3、newSingleThreadExcutuor
	 原理：
	 4、newScheduledThreadPool
	原理：
	
	Executor生命周期：运行、关闭、已终止
	
	Future：
	原理：
	Callable：
	原理：
	
	线程池的使用：
	原理：
	工作对列饱和：
	
	AbstractQueuedSynchronizer：AQS
	原理：
	
	深入理解java线程池—ThreadPoolExecutor  http://www.jianshu.com/p/ade771d2c9c0
	
	
	锁、volatile、CAS 比较 https://www.cnblogs.com/yuyutianxia/p/3991869.html
*/
}

