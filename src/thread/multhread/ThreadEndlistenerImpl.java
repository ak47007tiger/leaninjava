package multhread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadEndlistenerImpl extends ThreadEndListener{

	ReadWriteLock lock = new ReentrantReadWriteLock();
	private int sumThread = 0;
	

	@Override
	public void handleEnd(AddCountRunnable addcount) {
		lock.readLock().lock();
		this.sumThread++;
		if(super.threadSum == this.sumThread)
		System.out.println(Thread.currentThread().getId() + ":" + addcount.getNum(Thread.currentThread().getId()).getCount());
		lock.readLock().unlock();
	}

}
