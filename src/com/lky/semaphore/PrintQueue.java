package com.lky.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 用信号量Semaphore来保护一个或者多个共享资源的访问
 * 主要的方法acquire()获取信号量
 * release()释放信号量
 */
public class PrintQueue {
	private final Semaphore semaphore;
	private boolean freePrinters[];
	private Lock lockPrinters;
	public PrintQueue(){
		this.semaphore=new Semaphore(3,true);//可以加入第二个参数为信号量的公平性
		freePrinters=new boolean[3];
		for(int i=0;i<3;++i){
			freePrinters[i]=true;
		}
		lockPrinters=new ReentrantLock();
	}
	
	private int getPrinter(){
		int ret=-1;
		try{
			lockPrinters.lock();
			for(int i=0;i<freePrinters.length;++i){
				if(freePrinters[i]){
					ret=i;
					freePrinters[i]=false;
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lockPrinters.unlock();
		}
		return ret;
	}
	
	public void printJob(Object document){
		try{
			semaphore.acquire();//首先先判断能否获取打印机资源
			int assignedPrinter=getPrinter();//如果可以就获取其编号
			long duration=(long)(Math.random()*10);
			System.out.printf("%s: PrintQueue: Printing a Job in Printer %d during %d seconds\n",Thread.currentThread().getName(),assignedPrinter,duration);
			TimeUnit.SECONDS.sleep(duration);
			Thread.sleep(duration);
			freePrinters[assignedPrinter]=true;//归还打印机资源
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			semaphore.release();//释放信号量
		}
	}
}
