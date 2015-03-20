package multhread;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;



public class AddCountRunnable implements Runnable{
	private static MyThreadLocal<Num> nums = new MyThreadLocal<Num>();
	public Num getNum(Long id){
		return nums.get(id);
	}
	public void setNum(Long id){
		Num num = new Num();
		num.setCount(0);
		nums.set(id, num);
	}
	private Hashtable<String, Boolean> endConditions = new Hashtable<String, Boolean>();
	public void addEndCondition(String conditions, Boolean val){
		endConditions.put(conditions, val);
	}
	private LinkedList<ThreadEndListener> listeners = new LinkedList<ThreadEndListener>();
	TimeEndControl timeEndControl;
	
	public TimeEndControl getTimeEndControl() {
		return timeEndControl;
	}
	public void setTimeEndControl(TimeEndControl timeEndControl) {
		this.timeEndControl = timeEndControl;
	}
	private boolean continue_ask(){
		if(timeEndControl.isTimeEnd()){
			return false;
		}
		/*synchronized (endConditions) {
			Iterator<String> iterator = endConditions.keySet().iterator();
			while(iterator.hasNext()){
				String condition = iterator.next();
				if(endConditions.get(condition)){
					return false;
				}
			}
		}*/
		return true;
	}
	@Override
	public void run() {
		while(continue_ask()){
			Num num = nums.get(Thread.currentThread().getId());
			if(null == num){
				throw new NullPointerException("num not init");
			}
			num.setCount(num.getCount() + 1);
		}
		for(ThreadEndListener listener : listeners){
			listener.handleEnd(this);
		}
	}
	public void addThreadEndListener(ThreadEndListener listener){
		this.listeners.add(listener);
	}
	public int totalCount(){
		int count = 0;
		Iterator<Long> iterator = nums.keySet().iterator();
		while(iterator.hasNext()){
			Long id = (Long) iterator.next();
			count += nums.get(id).getCount();
		}
		return count;
	}
}
