package remotecmd;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Scanner;

public class RemoteCmd {
	public static void remoteCmd(){
		Charset cs = Charset.forName(System.getProperty("sun.jnu.encoding"));
		try {
			Process process = Runtime.getRuntime().exec("cmd.exe");
			InputStream in = process.getInputStream();
			InputStreamReader reader = new InputStreamReader(in, cs);
			OutputStream out = process.getOutputStream();
			char[] cbuf = new char[512];
			int count;
			count = reader.read(cbuf);
			System.out.println(new String(cbuf, 0, count));
			Scanner scanner = new Scanner(System.in);
			String line;
			line = scanner.nextLine();
			while (!"_close".equals(line)) {
				System.out.println("read line");
				out.write(line.getBytes(cs));
				out.write("/n".getBytes(cs));
				count = reader.read(cbuf);
				System.out.println("read over");
				System.out.println(count + ":" + new String(cbuf, 0, count));
				line = scanner.nextLine();
			}
			scanner.close();
			reader.close();
			out.close();
			process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void remoteCmd0(){
		Charset cs = Charset.forName(System.getProperty("sun.jnu.encoding"));
		try {
			Process process = Runtime.getRuntime().exec("cmd.exe");
			InputStream in = process.getInputStream();
//			InputStreamReader reader = new InputStreamReader(in, cs);
			PrintWriter writer = new PrintWriter(process.getOutputStream()); 
			System.out.println(getAllResult(in,cs));
			Scanner scanner = new Scanner(System.in);
			String line;
			line = scanner.nextLine();
			while (!"_close".equals(line)) {
				writer.println(line);
				writer.flush();
				System.out.println(getAllResult(in,cs));
				line = scanner.nextLine();
			}
			scanner.close();
			in.close();
			writer.close();
			process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static String getAllResult(BufferedReader reader) throws IOException{
		StringBuffer sb = new StringBuffer(512);
		String line;
		while(null != (line = reader.readLine())){
			System.out.println(line);
			sb.append(line);
		}
		System.out.println("over");
		return sb.toString();
	}
	public static String getAllResult(InputStreamReader reader) throws IOException{
		StringBuffer sb = new StringBuffer(512);
		char[] cbuf = new char[512];
		int count;
		while(-1 != (count = reader.read(cbuf))){
			sb.append(new String(cbuf,0,count));
		}
		return sb.toString();
	}
	public static String getAllResult(InputStream in,Charset cs) throws IOException{
		StringBuffer sb = new StringBuffer(512);
		byte[] b = new byte[1024];
		int count;
		while(-1 != (count = in.read(b))){
			sb.append(new String(b,0,count,cs));
		}
		return sb.toString();
	}
	public static void robot(){
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_WINDOWS);
			robot.keyRelease(KeyEvent.VK_WINDOWS);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
	}
	public static void main(String[] args) {
		remoteCmd0();
	}
}
