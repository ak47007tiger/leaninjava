package webSpiderLoc1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test {

	static public void test1(String[] args) throws Exception {
		int c;
		Socket s = new Socket("www.baidu.com", 80);
		InputStream in = s.getInputStream();
		OutputStream out = s.getOutputStream();
		String str = (args.length == 0 ? "osborne.com" : args[0]) + "\n";
		System.out.println(str);
		byte buf[] = str.getBytes();
		out.write(buf);
		while ((c = in.read()) > 0) {
			System.out.println("no word");
			System.out.print((char) c);
		}
		System.out.println(c);
		s.close();
		System.out.println("end");
	}
	static public void test3(String[] args) throws Exception {
		int c;
		Socket s = new Socket("http://api.map.baidu.com", 8080);
		InputStream in = s.getInputStream();
		OutputStream out = s.getOutputStream();
		String str = (args.length == 0 ? "osborne.com" : args[0]) + "\n";
		byte buf[] = str.getBytes();
		out.write(buf);
		while ((c = in.read()) != -1) {
			System.out.print((char) c);
		}
		s.close();
	}

	public static void test2(){
		Socket socket = null;
		InputStream ins = null;
		BufferedReader bfr = null;
		try {
			socket = new Socket(InetAddress.getByName("www.baidu.com"), 80);
			ins = socket.getInputStream();
			bfr = new BufferedReader(new InputStreamReader(ins));
			System.out.println(bfr.readLine());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bfr) {
					bfr.close();
				}
				if (null != socket) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		try {
			Test.test1(new String[]{"wd=hehe"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
