package systemproterty;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class TestSysPro {

	public void printAllProperties(){
		Properties p = System.getProperties();
		Enumeration<Object> _enum = p.keys();
		Object key;
		Object value;
		while(_enum.hasMoreElements()){
			key = _enum.nextElement();
			value = p.get(key);
			System.out.println(key + "___:___" + value);
		}
	}
	public void printfAllEnv(){
		String key;
		String value;
		Map<String, String> env = System.getenv();
		Iterator<String> iterator = env.keySet().iterator();
		while(iterator.hasNext()){
			key = iterator.next();
			value = env.get(key);
			System.out.println(key + "___:___" + value);
		}
	}
	public Object getSysProperty(String key){
		return System.getProperty(key);
	}
	public static void main(String[] args) {
		TestSysPro tsp = new TestSysPro();
		System.out.println(tsp.getSysProperty("user.dir"));
//		tsp.printAllProperties();
//		tsp.printfAllEnv();
	}
}
