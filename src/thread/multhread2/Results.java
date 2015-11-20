package multhread2;

import java.util.Hashtable;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Results {
	private static Results results;
	private Results() {
	}
	public static Results golableResults(){
		if(null == results){
			results = new Results();
		}
		return results;
	}
	private Hashtable<String, Integer> counts = new Hashtable<String, Integer>();
	private Hashtable<String, Integer> threadSums = new Hashtable<String, Integer>();
	private Hashtable<String, Integer> threadCurSums = new Hashtable<String, Integer>();
	
	public void addGroup(String group, int threadSum){
		counts.put(group, 0);
		threadSums.put(group, threadSum);
		threadCurSums.put(group, 0);
	}
	
	ReadWriteLock lock = new ReentrantReadWriteLock();
	
	public void collectResult(Num num, String group){
		if(null == counts.get(group)){
			throw new RuntimeException("no such group");
		}
		
		//add counts by group
		counts.put(group, counts.get(group) + num.getCount());
		//add sum by group
		threadCurSums.put(group, threadCurSums.get(group) + 1);
		
		//if sum end print counts
		if(threadSums.get(group) == threadCurSums.get(group))
			System.out.println(group + ":" +counts.get(group));
	}
	public void collectResult0(Num num, String group){
		lock.readLock().lock();
		if(null == counts.get(group)){
			throw new RuntimeException("no such group");
		}
		lock.readLock().unlock();
		
		lock.writeLock().lock();
		//add counts by group
		counts.put(group, counts.get(group) + num.getCount());
		//add sum by group
		threadCurSums.put(group, threadCurSums.get(group) + 1);
		lock.writeLock().unlock();
		
		lock.readLock().lock();
		//if sum end print counts
		if(threadSums.get(group) == threadCurSums.get(group))
			System.out.println(group + ":" +counts.get(group));
		lock.readLock().unlock();
	}
}
