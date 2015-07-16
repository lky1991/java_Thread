package com.lky.semaphore;


public class Main {
	public static void main(String args[]){
		PrintQueue printQueue=new PrintQueue();
		Thread thread[]=new Thread[10];
		for(int i=0;i<10;++i){
			thread[i]=new Thread(new Job(printQueue),"Thread"+i);//10个工作线程共用3个打印机
		}
		for(int i=0;i<10;++i){
			thread[i].start();
		}
	}

}
