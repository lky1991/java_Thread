package com.lky.lock.condition;

//生产者
public class Producer implements Runnable {
	private FileMock mock;
	private Buffer buffer;
	
	public Producer(FileMock mock,Buffer buffer){
		this.mock=mock;
		this.buffer=buffer;
	}

	@Override
	public void run() {
		buffer.setPendingLines(true);
		while(mock.hasMoreLines()){
			String lineString=mock.getLine();
			buffer.insert(lineString);
		}
		buffer.setPendingLines(false);
	}
}
