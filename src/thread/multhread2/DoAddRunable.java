package multhread2;


public class DoAddRunable implements Runnable {
	private String group;

	public DoAddRunable(String group, Results results) {
		super();
		this.group = group;
		this.results = results;
	}

	private Results results;

	@Override
	public void run() {
		DoAddThread thread = null;
		while (!Timer.end()) {
			thread = (DoAddThread) (Thread.currentThread());
			Num num = thread.getNum();
			num.setCount(num.getCount() + 1);
		}
		Num num = thread.getNum();
		results.collectResult(num, group);
	}
}