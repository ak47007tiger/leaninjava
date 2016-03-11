package systemapi;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ShowEnvs {
public static void main(String[] args) {
	Map<String, String> map = System.getenv();
	Set<String> set = map.keySet();
	Iterator<String> iterator = set.iterator();
	while(iterator.hasNext()){
		String key = iterator.next(); 
		System.out.println(String.format("%s\t:\t%s", key,map.get(key)));
	}
}
}
