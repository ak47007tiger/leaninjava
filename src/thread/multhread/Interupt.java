package multhread;

public class Interupt {

	static class Thread1 extends Thread{
		int i = 0;
		@Override
		public void run() {
			while(true){
				i ++;
				System.out.print(getName() + ":" + i);
				if(isInterrupted()){
					System.out.println();
					System.out.println("command interrupt run");
					System.out.println(isInterrupted());
					Thread.interrupted();
					System.out.println(isInterrupted());
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread1();
		t1.start();
		try {
			Thread.sleep(100);
			t1.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread t12 = new Thread1();
		System.out.println(t12.isInterrupted());
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
