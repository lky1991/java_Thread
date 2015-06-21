package com.lky.synch.condition;

/*
 * 生产者
 */
public class Producer implements Runnable {
	private EventStorage storage;
	public Producer(EventStorage storage){
		this.storage=storage;
	}
	@Override
	public void run() {
		for(int i=0;i<100;++i){
			storage.set();
		}
	}
}
