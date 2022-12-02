package com.skillsoft.concurrency;

/*
 Volatile:
 	Guarantees that the update to the number variable will not be just
	performed on the cpu cache of the updating thread, but it will be 
	performed in main memory. Any reads on this variable will be performed 
	in main memory. Which means all threads that share this variable will be
	working on the same copy and not on their local copies on the cpu cache.
	
	Some of the compiler optimizations which are carried out are invalidated when
	including a volatile variable, which will have the effect of some times being 
	able to view updates on non-volatile variables. ie if marking number as volatile
	instead of keepLooping which is the appropriate variable to mark in this case.
 */

public class Looper implements Runnable{
	
	public static volatile boolean keepLooping = true;
	public static int number = 0;
	
	public int localNum = 0;

	@Override
	public void run() {
		
		String threadName = Thread.currentThread().getName();
		
		while(keepLooping) {
			if(localNum != number) {
				System.out.println(threadName + " has picked up the change in number");
				localNum = number;
			}
		}
		System.out.println(threadName + " is done!");
	}
	public static void main(String[] args) throws InterruptedException{
		
		for(int i=0; i<10; i++) {
			
			Thread t = new Thread(new Looper(), "Looper-"+i);
			t.start();
		}
		number = 13;
		System.out.println("number changed by MAIN");
		
		Thread.sleep(10000);
		keepLooping = false;
	}
}
