package multhread2;

import java.util.ArrayList;
import java.util.List;

public class MainApp {

	public void oneRunMulThread(int threadSum, long continueTime, String group){
		List<Thread> threads = new ArrayList<Thread>();
		Results.golableResults().addGroup(group, threadSum);
		DoAddRunable runnable = new DoAddRunable(group, Results.golableResults());
		for(int i = 0; i < threadSum; i++){
			DoAddThread thread = new DoAddThread(runnable);
			threads.add(thread);
		}
		
		Timer.startTime = System.currentTimeMillis();
		Timer.continueTime = continueTime;
		for(Thread t : threads){
			t.start();
		}
	}
	public void mulRunMulThread(int threadSum, long continueTime, String group){
		List<Thread> threads = new ArrayList<Thread>();
		Results.golableResults().addGroup(group, threadSum);
		for(int i = 0; i < threadSum; i++){
			DoAddRunable runnable = new DoAddRunable(group, Results.golableResults());
			DoAddThread thread = new DoAddThread(runnable);
			threads.add(thread);
		}
		
		Timer.startTime = System.currentTimeMillis();
		Timer.continueTime = continueTime;
		for(Thread t : threads){
			t.start();
		}
	}
	public static void main(String[] args) {
		int threadSum = 2;
		long continueTime = 1000;
		MainApp app = new MainApp();
		app.oneRunMulThread(threadSum, continueTime, "oneRunMulThread");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		app.mulRunMulThread(threadSum, continueTime, "mulRunMulThread");
	}
}
