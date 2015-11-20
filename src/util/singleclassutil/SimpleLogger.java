package singleclassutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Hashtable;


public class SimpleLogger {

	public static void main(String[] args) {
		String parent = ClassLoader.getSystemResource("log").getPath();
		System.out.println(parent);
		try {
			SimpleLogger.getLogger("testlog").log("first");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private PrintWriter pw;
	private static Hashtable<String,SimpleLogger> loggers = new Hashtable<String,SimpleLogger>();
	private final String parent = ClassLoader.getSystemResource("log").getFile();
	private SimpleLogger(String pathLog) {
		try {
			pw = new PrintWriter(new File(parent + "/" + pathLog + ".txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static SimpleLogger getLogger(String logname){
		if(loggers.containsKey(logname)){
			return loggers.get(logname);
		}
		SimpleLogger sl = new SimpleLogger(logname);
		loggers.put(logname, sl);
		return sl;
	}
	public void log(String s){
		if(null == pw){
			try {
				throw new Exception("PrintWriter is null");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		pw.print(s);
		pw.flush();
	}
	public void logln(String s){
		if(null == pw){
			try {
				throw new Exception("PrintWriter is null");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		pw.println(s);
		pw.flush();
	}
}
