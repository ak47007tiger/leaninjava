package readConfig.propertiesReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;

/**
 * 生成properties文件
 * 加载properties文件
 * 读出properties文件的键值对
 * 向文件追加键值对
 * 
 * @author Administrator
 *
 */
public class PropertiesOp {
	Properties properties;
	URL url;
	public PropertiesOp() {
		properties = new Properties();
	}
	public Properties getProperties(){
		return properties;
	}
	public Properties load(URL url){
		try {
			this.url = url;
			properties.load(new FileInputStream(new File(url.getFile())));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	public String getProperty(String key){
		if(null == properties){
			return null;
		}
		return properties.getProperty(key);
	}
	public void addOrUpdateProperties(Properties properties){
		Enumeration<Object> enums = properties.keys();
		while(enums.hasMoreElements()){
			Object key = enums.nextElement();
			this.properties.put(key, properties.getProperty((String)key));
		}
	}
	public void addOrUpdateProperties(String key,String value){
		properties.put(key, value);
	}
	
	public void store(URL url ,String comments){
		this.url = url;
		this.store(comments);
	}
	public void store(String comments){
		if(null == url){
			throw new NullPointerException("url is null!");
		}
		try {
			properties.store(new FileOutputStream(new File(url.getFile())), comments);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void test(){
		Properties properties = new Properties();
		properties.put("a", "a");
		properties.put("b", "b");
		String value;
		Scanner sc = new Scanner(System.in);
		System.out.println("enter new key");
		value = sc.nextLine();
		System.out.println(properties.containsKey(value));//true mean compare value
		System.out.println(properties.containsKey("b"));
		properties.put("b", "c");
		System.out.println(properties.get("b"));//print c mean can update self
		System.out.println(properties.size());
	}
	public static void main(String[] args) throws Exception {
		test();
	}
}
