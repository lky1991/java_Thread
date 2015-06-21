package com.lky.synch;


public class Control {

	public static void main(String[] args) {
		Account account=new Account();
		account.setBalance(1000);
		
		Company company=new Company(account);
		Thread comanyThread=new Thread(company);
		
		Bank bank=new Bank(account);
		Thread bankThread=new Thread(bank);
		
		System.out.printf("Account : Initial Balance : %f\n",account.getBalance() );
		comanyThread.start();
		bankThread.start();
		
		try {
			comanyThread.join();
			bankThread.join();
	   System.out.printf("Account : Final Balance : %f\n",account.getBalance() );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
