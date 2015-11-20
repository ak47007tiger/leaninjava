package threadsynch;

public class TestJoin {

	public static void main(String[] args) throws Exception {
		Thread thread1 = new Thread(){
			@Override
			public void run() {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread1 end work");
			}
		};
		thread1.start();
		System.out.println("main thread");
//		Thread.currentThread().join();
		thread1.join();
		System.out.println("main wait thread end work");
	}
}
