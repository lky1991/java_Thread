package com.lky.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 使用锁实现同步，相比synchronized关键字，更强大也更灵活的机制。
 * 其中的一个新功能就是tryLock()方法，这个方法试图获得锁，如果锁已经被
 * 其他线程获取，他将返回false并继续往下执行代码。
 */
public class PrintQueue {
	private final Lock queueLock=new ReentrantLock(true);
	public void printJob(Object document){
		queueLock.lock();
	    try{
	    	Long duration=(long)(Math.random()*10000);
	    	System.out.println(Thread.currentThread().getName()+": PrintQueue: Printing a Job during "+(duration/1000)+"seconds");
	    	Thread.sleep(duration);
	    }catch(InterruptedException e){
	    	e.printStackTrace();
	    }finally{
	    	queueLock.unlock();
	    }
	    
	    queueLock.lock();
	    try{
	    	Long duration=(long)(Math.random()*10000);
	    	System.out.println(Thread.currentThread().getName()+": PrintQueue: Printing a Job during "+(duration/1000)+"seconds");
	    	Thread.sleep(duration);
	    }catch(InterruptedException e){
	    	e.printStackTrace();
	    }finally{
	    	queueLock.unlock();
	    }
	}	
}
