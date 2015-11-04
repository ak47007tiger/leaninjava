package multhread;

public class TestThreadId implements Runnable{
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getId());
		Thread t = new Thread(new TestThreadId());
		t.start();
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getId());
	}
}
