package com.lky.synch.condition;

public class Control {

	public static void main(String[] args) {
		EventStorage storage=new EventStorage();
		Producer producer=new Producer(storage);
		Consumer consumer=new Consumer(storage);
		Thread thread1=new Thread(producer);
		Thread thread2=new Thread(consumer);
		
		thread1.start();
		thread2.start();
	}

}
