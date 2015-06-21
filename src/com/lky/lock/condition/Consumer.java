package com.lky.lock.condition;

import java.util.Random;

public class Consumer implements Runnable{
	private Buffer buffer;
	public Consumer(Buffer buffer){
		this.buffer=buffer;
	}
	private void ProcessLine(String line){
		try{
		    Random random=new Random();
		    Thread.sleep(random.nextInt(100));
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(buffer.hasPendingLines()){
			String Line=buffer.get();
			ProcessLine(Line);
		}
	}
}
