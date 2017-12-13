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
}
