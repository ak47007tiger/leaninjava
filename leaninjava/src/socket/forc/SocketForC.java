package forc;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketForC {

	final private static int port = 7878;
	public void startClient1(){
		Socket client = null;
		SocketAddress endpoint = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			client = new Socket();
			endpoint = new InetSocketAddress(port);
			client.connect(endpoint);
			out = client.getOutputStream();
			
			out.write("this is client build by java.\n中文的".getBytes("utf-8"));
			out.write("this is client build by java.\n中文的".getBytes("gbk"));
			out.flush();
			in = client.getInputStream();
			byte buf[] = new byte[1024];
			in.read(buf);
			System.out.println(new String(buf,"utf-8"));
			System.out.println(new String(buf,"gbk"));
			out.close();
			in.close();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void test1(){
		String china = new String("");
	}
	public static void main(String[] args) {
		SocketForC sfc = new SocketForC();
		sfc.startClient1();
	}
}
