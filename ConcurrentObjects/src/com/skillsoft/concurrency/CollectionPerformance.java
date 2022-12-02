package com.skillsoft.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionPerformance {
	
	private ArrayList<String> myList = new ArrayList<String>();
	List<String> mySynchList = 
			Collections.synchronizedList(new ArrayList<String>());
	private Vector<String> myVector = new Vector<String>();//Thread safe structure. Lock will need to be acquired
	private CopyOnWriteArrayList<String> myCOWList = new CopyOnWriteArrayList<String>();
	
	private HashMap<String, String> myHashMap = new HashMap<String, String>();
	private Hashtable<String, String> myHashTable = new Hashtable<String, String>();//Synchronized DS
	private ConcurrentHashMap<String, String> myConcurrentHashMap = new ConcurrentHashMap<String, String>();//Preferred
																		//map DS because it divides the entire map into a number
																		//of buckets and these buckets are locked when these
																		//operations are performed. Multiple threads can
																		//write to the map to different buckets at the
																		//same time
	
	private static final int NUM_ITERATIONS = 100000;
	
	public void testArrayList() {
		
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			myList.add("data-"+i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time ArrayList: "+ (endTime -startTime)+ "ms");
	}
	
	public void testSynchronizedList() {
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			mySynchList.add("data-"+i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time synchronizedList: "+ (endTime -startTime)+ "ms");
	}
	
	public void testVector() {
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			myVector.add("data-"+i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time myVector: "+ (endTime -startTime)+ "ms");
	}
	
	public void testCopyOnWriteArrayList() {
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			myCOWList.add("data-"+i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time CopyOnWriteArrayList: "+ (endTime -startTime)+ "ms");
	}
	
	public void testHashMap() {
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			myHashMap.put("key-"+i, "data-"+i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time testHashMap: "+ (endTime -startTime)+ "ms");
	}
	
	public void testHashTable() {
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			myHashTable.put("key-"+i, "data-"+i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time testHashTable: "+ (endTime -startTime)+ "ms");
	}
	public void testConcurrentHashMap() {
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			myConcurrentHashMap.put("key-"+i, "data-"+i);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time testConcurrentHashMap: "+ (endTime -startTime)+ "ms");
	}
	
	public static void main(String[] args) {
		CollectionPerformance cp = new CollectionPerformance();
		
//		cp.testArrayList();
//		cp.testSynchronizedList();
//		cp.testVector();
//		cp.testCopyOnWriteArrayList();
		
		cp.testHashMap();
		cp.testHashTable();
		cp.testConcurrentHashMap();
	}
}
