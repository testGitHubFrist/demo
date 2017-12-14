package com.nilo.thread;

public class ReentrantLockMultiConditionB extends Thread {

	private ReentrantLockMultiCondition reentrantLockMultiCondition;

	public ReentrantLockMultiConditionB(ReentrantLockMultiCondition reentrantLockMultiCondition) {
		this.reentrantLockMultiCondition = reentrantLockMultiCondition;
	}

	@Override
	public void run() {
		reentrantLockMultiCondition.awaitB();
	}
}
