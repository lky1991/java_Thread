package com.lky.lock.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Condition 条件属性
 * 要想使用条件，必须先获取这个条件绑定的锁，因此带条件的代码必须在lock()和unlock()方法之间
 * 当线程调用条件的await()时，它将自动释放这个条件绑定的锁。
 */
public class Buffer {
	private LinkedList<String> buffer;
	private int maxSize;
	private ReentrantLock lock;
	private Condition lines;//条件属性
	private Condition space;//条件属性
	private boolean pendingLines;
	
	public Buffer(int maxSize){
		this.maxSize=maxSize;
		buffer=new LinkedList<String>();
		lock=new ReentrantLock();
		lines=lock.newCondition();
		space=lock.newCondition();
		pendingLines=true;
	}
	public boolean hasPendingLines(){
		return pendingLines || buffer.size()>0 ;
	}
	public void insert(String line){
		lock.lock();
		try{
			while(buffer.size() == maxSize){
				space.await();
			}
			buffer.offer(line);
			System.out.printf("%s: Inserted Line: %d\n",Thread.currentThread().getName(),buffer.size());
			lines.signalAll();
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public String get(){
	  String line=null;
	  lock.lock();
	  try{
		  while((buffer.size()==0) &&(hasPendingLines())){
			  lines.await();
		  }
		  if(hasPendingLines()){
			  line=buffer.poll();
			  System.out.printf("%s: Line Readed: %d\n", Thread.currentThread().getName(),buffer.size());
			  space.signalAll();
		  }
	  }catch(InterruptedException e){
		  e.printStackTrace();
	  }finally{
		  lock.unlock();
	  }
	  return line;
	}
	public void setPendingLines(boolean pendingLines) {
		this.pendingLines = pendingLines;
	}
	
 }
	
