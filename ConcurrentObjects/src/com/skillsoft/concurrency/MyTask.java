package com.skillsoft.concurrency;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class MyTask implements Runnable{
	
	SharedResource sr;
	Semaphore sem;
	String threadName;
	int numPermits = 1;//the threads priority
	
	public MyTask(SharedResource sr, Semaphore sem, int numPermits) {
		this.sr = sr;
		this.sem = sem;
		this.numPermits = numPermits;
	}

	@Override
	public void run() {
		try {
			threadName = Thread.currentThread().getName();
			
			System.out.println(threadName + " is waiting for "+ numPermits + " sem permits.");
			
			sem.acquire(numPermits);//possible for a task to acquire all the permits and have exclusive access to the resource
									//can run without passing it anything
			
			System.out.println(threadName + " has ACQUIRED the semaphore " + numPermits + " permits!");
			
			//Do work.. probably call a function in the real world
			Thread.sleep((long)(Math.random() * 1000)*5);
			
			sem.release(numPermits);
			
			System.out.println(threadName + " has RELEASED the semaphore. "+ numPermits + " permits.");
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		
//		SharedResource sharedRes = new SharedResource();//probably a DB call
//		
//		int maxPermits = 4;
//		Semaphore sem = new Semaphore(maxPermits);
//		
//		Random random = new Random();
//		
//		for(int i = 0; i<10; i++) {
//			
//			int permits = random.nextInt(maxPermits) + 1;
//			
//			Thread t = new Thread(new MyTask(sharedRes, sem, permits), "Task-"+i);
//			t.start();
//		}
//	}
}
