package http文件传输;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * mark字符流
 * reset
 * 对字节流的影响
 * @author Administrator
 *
 */
public class TestMark {

	/*
	 * mark，读一个字符，reset，读一个字节
	 */
	public void test1 () throws Exception{
		String name = "TestMark.txt";
		File f = new File(ClassLoader.getSystemResource(name).getFile());
		long lenFile = f.length();
		System.out.println(lenFile);
		InputStream in = new FileInputStream(f);
		System.out.println(in.markSupported());
		InputStreamReader reader = new InputStreamReader(in, "utf-8");
		BufferedReader br = new BufferedReader(reader);
		br.mark((int)lenFile);
		char cReader = (char)br.read();
		System.out.println("cReader:"+(int)cReader);
		br.reset();
		char cIn = (char)in.read();
		System.out.println("cIn:" + (int)cIn);
		reader.close();
		in.close();
	}
	public static void main(String[] args) {
		TestMark obj = new TestMark();
		try {
			obj.test1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
