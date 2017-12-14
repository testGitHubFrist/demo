package com.nilo.thread;

public class ReentrantLockMultiConditionA extends Thread {

	private ReentrantLockMultiCondition reentrantLockMultiCondition;

	public ReentrantLockMultiConditionA(ReentrantLockMultiCondition reentrantLockMultiCondition) {
		this.reentrantLockMultiCondition = reentrantLockMultiCondition;
	}

	@Override
	public void run() {
		reentrantLockMultiCondition.awaitA();
	}
}
