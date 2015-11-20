package multhread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class MulIoThread extends Thread{
	InputStreamReader reader;
	long millis = 1000;
	public void init(File file, Charset cs) throws FileNotFoundException{
		reader = new InputStreamReader(new FileInputStream(file), cs);
	}
	
	public void showFileContent(String content){
		String tag = Thread.currentThread().getName();
		System.out.println(tag + ":" + content);
	}
	
	@Override
	public void run() {
		char[] cbuf = new char[8];
		int count;
		try {
			while(-1 != (count = reader.read(cbuf))){
				showFileContent(new String(cbuf,0,count));
				Thread.sleep(millis);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(null != reader){
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		MulIoThread ioThread1 = new MulIoThread();
		MulIoThread ioThread2 = new MulIoThread();
		Charset cs = Charset.forName("utf-8");
		File file = new File(System.getProperty("user.dir"), "source1/contentfile");
		try {
			ioThread1.init(file, cs);
			ioThread2.init(file, cs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ioThread1.start();
		ioThread2.start();
	}
}
