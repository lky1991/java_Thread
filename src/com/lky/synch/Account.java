package com.lky.synch;

/*
 * synchronized实现同步
 * 每一个用synchronized关键字修饰的方法都是临界区（访问共享资源的代码块）。在
 * java中，同一个对象的临界区，在同一时间只能允许一个线程访问。若synchronized修饰的为静态方法则有所不同，
 * 在同一时间内允许两个线程访问一个对象的两个不同的synchronized方法，即其中一个是静态的一个是非静态的。
 * 如果这两个synchronized方法都对一个共享变量修改，将会带来数据不一致的错误。顾名思义，synchronized虽然可以解决
 * 并发情境中的共享资源问题，但同时它也降低了程序的性能，故应该控制synchronized代码块的粒度。
 */

//模拟一个银行账户
public class Account {
	private double balance;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public synchronized void addAmount(double amount){
		double tmp=this.balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tmp+=amount;
		this.balance=tmp;
	}
	
	public synchronized void subtractAmount(double amount){
		double tmp=this.balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tmp-=amount;
		this.balance=tmp;
	}
}
