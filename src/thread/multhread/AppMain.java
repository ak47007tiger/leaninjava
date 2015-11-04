package multhread;

import java.util.ArrayList;
import java.util.List;

public class AppMain {

	public static void testOneClass(){
		AddCountRunnable add1 = new AddCountRunnable();
		add1.addThreadEndListener(new ThreadEndlistenerImpl());
		Thread t1 = new Thread(add1);
		add1.setNum(t1.getId());
		Thread t2 = new Thread(add1);
		add1.setNum(t2.getId());
		TimeEndControl timeEndControl = new TimeEndControl();
		timeEndControl.setContinueTime(1000);
		add1.setTimeEndControl(timeEndControl);
		t1.start();
		t2.start();
	}
	public static void testTwoClass(){
		AddCountRunnable add1 = new AddCountRunnable();
		AddCountRunnable add2 = new AddCountRunnable();
		Thread t1 = new Thread(add1);
		Thread t2 = new Thread(add2);
		TimeEndControl timeEndControl = new TimeEndControl();
		timeEndControl.setContinueTime(1000);
		add1.setTimeEndControl(timeEndControl);
		add2.setTimeEndControl(timeEndControl);
		add1.setNum(t1.getId());
		add2.setNum(t2.getId());
		add1.addThreadEndListener(new ThreadEndlistenerImpl());
		add2.addThreadEndListener(new ThreadEndlistenerImpl());
		t1.start();
		t2.start();
	}
	//一个runnable
	public static void test3(int thredSum, long continueTime){
		List<Thread> threads = new ArrayList<Thread>();
		AddCountRunnable add;
		add = new AddCountRunnable();
		TimeEndControl timeControl;
		timeControl = new TimeEndControl();
		timeControl.setContinueTime(continueTime);
		timeControl.setStartTime(System.currentTimeMillis());
		add.setTimeEndControl(timeControl);
		add.addThreadEndListener(new ThreadEndlistenerImpl());
		Thread thread;
		for(int i = 0; i < thredSum; i++){
			thread = new Thread(add);
			add.setNum(thread.getId());
			threads.add(thread);
		}
		for(Thread t : threads){
			t.start();
		}
	}
	//多个runnable
	public static void test4(int threadSum, long continueTime){
		List<Thread> threads = new ArrayList<Thread>();
		AddCountRunnable add;
		TimeEndControl timeControl;
		timeControl = new TimeEndControl();
		timeControl.setContinueTime(continueTime);
		timeControl.setStartTime(System.currentTimeMillis());
		
		Thread thread;
		ThreadEndListener endListener = new ThreadEndlistenerImpl();
		endListener.setThreadSum(threadSum);
		for(int i = 0; i < threadSum; i++){
			add = new AddCountRunnable();
			add.setTimeEndControl(timeControl);
			add.addThreadEndListener(endListener);
			thread = new Thread(add);
			add.setNum(thread.getId());
			threads.add(thread);
		}
		for(Thread t : threads){
			t.start();
		}
	}
	public static void main(String[] args) {
		test3(4, 1000);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		test4(4, 1000);
//		testTwoClass();
//		testOneClass();
	}
}
