package multhread;

import java.util.Hashtable;
import java.util.Set;

public class MyThreadLocal<T> {
	Hashtable<Long, T> table = new Hashtable<Long, T>();
	T get(Long id){
		return table.get(id);
	}
	void set(Long id, T t){
		table.put(id, t);
	}
	Set<Long> keySet(){
		return table.keySet();
	}
}
