package com.lky.countdownlatch;

import java.util.concurrent.CountDownLatch;

/*
 * Java并发API提供了CountDownLatch类，它是一个同步辅助类。在完成一组正在其他线程中执行的操作之前
 * 它允许线程一直等待。主要方法await()，countDown():将CountDownLatch内部计数器减1,当计数器变成0的时候，CountDownLatch类
 * 将唤醒所有因调用await()方法而进入休眠的线程。CountDownLatch只准许进入一次，一旦CountDownLatch的
 * 内部计数器达到为0，在调用这个方法将不起作用。如果要在做类似的同步，就必须创建一个新的CountDownLatch
 */

public class Videoconference implements Runnable{
	private final CountDownLatch controller;
	public  Videoconference(int number) {
		controller=new CountDownLatch(number);//number为该线程要等待完成的操作的数目
	}
	public void arrive(String name){
		System.out.printf("%s has arrived.\n",name);
		controller.countDown();
		System.out.printf("VideoConference: Waiting for %d partcipants.\n", controller.getCount());//打印还需要等待的线程个数
	}
	@Override
	public void run() {
		System.out.printf("VideoConference: Initialization: %d participants.\n", controller.getCount());
		try{
			controller.await();
			System.out.printf("VideoConference: All the participants have come\n");
			System.out.println("VideoConference: Let's start....\n");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
