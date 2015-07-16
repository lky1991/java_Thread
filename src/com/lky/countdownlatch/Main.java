package com.lky.countdownlatch;

public class Main {

	public static void main(String[] args) {
		Videoconference conference=new Videoconference(10);
		Thread threadConferenceThread =new Thread(conference);
		threadConferenceThread.start();
		
		for(int i=0;i<10;++i){
			Participant participant=new Participant(conference, "Participant"+i);
			Thread thread=new Thread(participant);
			thread.start();
		}
	}
}
