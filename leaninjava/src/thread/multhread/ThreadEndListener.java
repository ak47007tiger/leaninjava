package multhread;

public abstract class ThreadEndListener {
	protected int threadSum;
	abstract void handleEnd(AddCountRunnable addcount);
	public int getThreadSum() {
		return threadSum;
	}
	public void setThreadSum(int threadSum) {
		this.threadSum = threadSum;
	}
}
