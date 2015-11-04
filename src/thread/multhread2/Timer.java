package multhread2;

public class Timer {
	public static long startTime;
	public static long continueTime;
	public static boolean end(){
		return continueTime <= System.currentTimeMillis() - startTime;
	}
	private Timer() {
	}
}
