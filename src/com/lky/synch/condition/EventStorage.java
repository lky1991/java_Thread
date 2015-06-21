package com.lky.synch.condition;

import java.util.*;

/*
 * 在同步代码中使用条件，主要使用wait(),notify(),notifyAll()方法。线程只能在同步代码中调用wait()，
 * 否则JVM会抛出异常。当一个线程调用wait()方法时，JVM将这个线程置入休眠，并且释放这个同步代码块中的对象。
 * 为了能够唤醒这个线程，必须在这个对象控制的某个同步代码中调用notify()或者notifyAll()方法。
 * 此外，必须在while循环中调用wait()，并不断查询while的条件，直到条件为真时候才能继续。
 */
public class EventStorage {
	private int maxsize;
	private List<Date> storage;
	
	public EventStorage(){
		maxsize=10;
		storage=new LinkedList<Date>();
	}
	
	public synchronized void set(){
		while(storage.size()==this.maxsize){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		storage.add(new Date());
		System.out.printf("Set: %d\n",storage.size());
		notifyAll();
	}
	public synchronized void get(){
		while(storage.size()==0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Get: %d: %s\n",storage.size(),((LinkedList<?>)storage).poll());
		notifyAll();
	}
}

	
