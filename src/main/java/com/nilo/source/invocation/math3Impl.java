package com.nilo.source.invocation;

public class math3Impl implements math3 {

	@Override
	public int add(int i, int j) {
		int resuIt=i+j;
		return resuIt;
	}

	@Override
	public int sub(int i, int j) {
		int resuIt=i-j;
		return resuIt;
	}

	@Override
	public int mul(int i, int j) {
		int resuIt=i*j;
		return resuIt;
	}

	@Override
	public int div(int i, int j) {
		int resuIt=i/j;
		return resuIt;
	}

}
