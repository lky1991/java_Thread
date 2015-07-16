package com.lky.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/*
 * 并发任务间的数据交换Exchanger(允许在两个线程之间定义同步点，当两个线程都到达同步点时，他们交换数据结构)
 * 主要方法exchange(data),exchange(V data,long time,TimeUnit unit)
 */
public class Producer implements Runnable{
	private List<String>buffer;
	private final Exchanger<List<String>>exchanger;
	
	public Producer(List<String> buffer,Exchanger<List<String>>exchanger){
		this.buffer=buffer;
		this.exchanger=exchanger;
	}
	@Override
	public void run() {
		int cycle=1;
		for(int i=0;i<5;++i){
			System.out.printf("producer: Cycle %d\n", cycle);
			for(int j=0;j<10;j++){
				String message="Event "+(i*10+j);
				System.out.printf("producer: %s\n",message);
				buffer.add(message);
			}
			try{
				buffer=exchanger.exchange(buffer);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			System.out.println("Producer: "+buffer.size());
			cycle++;
		}
	}
}
