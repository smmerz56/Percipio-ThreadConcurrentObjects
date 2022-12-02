package com.skillsoft.concurrency;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Iterator;

/*
 Collection<String> commonRes = 
				Collections.synchronizedCollection(new ArrayList<String>());
		
 List<String> commonRes = 
				Collections.synchronizedList(new ArrayList<String>());

These might not be the optimal thread safe DS because they lock the entire structure. Even if one
element just needs to be updated. If the number of writes are a lot less frequent than the number of 
reads, this can lead to performance issues. A CopyOnWriteArrayList might be better.

*****************************************************************************************

CopyOnWriteArrayList: When a write is being done it does it on a copy, while the OG version is still 
available for other threads to read. Means reads may not be able to view latest updates and writes can 
prove to be costly since a new List is created. Can be iterated over when a write is being performed.

*****************************************************************************************
 
 */

public class ConcurrentTask implements Runnable{
	
	private static final int NUM_ITERATIONS = 10000;
	
//	public Collection<String> commonResource;//More generic...doesnt have many methods
//	public List<String> commonResource;
//	public CopyOnWriteArrayList<String> commonResource;
//	public ArrayList<String> commonResource;
	public ConcurrentHashMap<String, String> commonResource;//Allows iteration over the keys, while writes are happening

	public ConcurrentTask(ConcurrentHashMap<String, String> commonResource) {
		this.commonResource = commonResource;
	}

	@Override
	public void run() {
		
		String threadName = Thread.currentThread().getName();
		
		try {
			for(int i = 0; i<20; i++) {
				Thread.sleep(100);
				commonResource.put(threadName + "-key-"+i, 
						threadName + "-val-"+i);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(threadName + " has finished execution");
	}
	
	public static void main(String[] args) throws InterruptedException{
		
//		Collection<String> commonRes = 
//				Collections.synchronizedCollection(new ArrayList<String>());
//		List<String> commonRes = 
//				Collections.synchronizedList(new ArrayList<String>());//difficult time reading and writing concurrently
		
//		CopyOnWriteArrayList<String> commonRes = new CopyOnWriteArrayList<String>();
//		ArrayList<String> commonRes = new ArrayList<String>();
		ConcurrentHashMap<String, String> commonRes = new ConcurrentHashMap<String, String>();
		
		Thread firstThread = new Thread(new ConcurrentTask(commonRes), "firstThread");
		Thread secondThread = new Thread(new ConcurrentTask(commonRes), "secondThread");
		
		firstThread.start();
		secondThread.start();
		
		Thread.sleep(1000);
		
		Iterator<String> itr = commonRes.keySet().iterator();//Reads values (not written to the copy) from the original array
		
		while(itr.hasNext()) {
			String str = (String) itr.next();
			
			System.out.println("main: "+str);
			Thread.sleep(100);
			
		}
	}

}
