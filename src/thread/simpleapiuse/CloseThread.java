package simpleapiuse;

import java.util.Scanner;

public class CloseThread implements Runnable{

	@Override
	public void run() {
		while(true){
			System.out.println("cur tiem:" + System.currentTimeMillis());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("close while sleep");
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Thread thread = new CloseThread().start();
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		thread.interrupt();
		scanner.close();
	}

	private Thread start() {
		Thread thread = new Thread(this);
		thread.start();
		return thread;
	}
	
}
