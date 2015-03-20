package multhread2;
import multhread.Num;
public class DoAddRunable implements Runnable{
	private String group;
	public DoAddRunable(String group, Results results) {
		super();
		this.group = group;
		this.results = results;
	}
	private Results results;
	private Object syn = new Object();
	@Override
	public void run() {
		synchronized (syn) {
		while(!Timer.end()){
			DoAddThread thread = (DoAddThread)(Thread.currentThread());
			Num num = thread.getNum();
			num.setCount(num.getCount() + 1);
		}
		DoAddThread thread = (DoAddThread)(Thread.currentThread());
		Num num = thread.getNum();
		results.collectResult(num, group);
		}
	}
}