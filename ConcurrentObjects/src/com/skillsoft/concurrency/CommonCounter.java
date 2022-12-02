package com.skillsoft.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/*
 Atomic vs Volatile:
 	*Volatile- Read/Write to main memory instead of threads local cache for all threads to see. Can still lead to 
 	race conditions.
 	
 	*Atomic- Read/Write to main memory instead of threads local cache for all threads to see and does so in an 
 	atomic/synchronized way to prevent race conditions.
 */

public class CommonCounter {
	
	private AtomicInteger firstNum = new AtomicInteger(0);
	private AtomicInteger secondNum = new AtomicInteger(0);
	
	public void incrementCounter() {
		
		firstNum.incrementAndGet();//guaranteed to update and read its own update	
		secondNum.incrementAndGet();
	}
	
	public int getFirstNum() {
		return firstNum.get();
	}
	
	public int getSecondNum() {
		return secondNum.get();
	}
}
