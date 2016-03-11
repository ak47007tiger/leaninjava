package systemapi;

import java.util.Enumeration;
import java.util.Properties;

public class ShowProperties {

	public static void main(String[] args) {
		Properties properties = System.getProperties();
		Enumeration<Object> keys = properties.keys();
		while (keys.hasMoreElements()) {
			String object = (String) keys.nextElement();
			System.out.println(String.format("%s\t:\t%s",object, properties.get(object)));
		}
	}
}
