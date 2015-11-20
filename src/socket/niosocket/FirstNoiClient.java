package niosocket;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class FirstNoiClient {

	public static void main(String[] args) throws Exception {
		Socket client = new Socket();
		client.bind(new InetSocketAddress(8087));
		client.connect(new InetSocketAddress(8088), 2000);
		Scanner scanner = new Scanner(System.in);
		String scanStr;
		int count;
		while(true){
			System.out.println("enter your world");
			scanStr = scanner.nextLine();
			if(FirstNoiServer.endConnect.equals(scanStr)){
				break;
			}
			client.getOutputStream().write(scanStr.getBytes("utf-8"));
			byte[]bbuf = new byte[1024];
			count = client.getInputStream().read(bbuf);
			System.out.println("recv:" + new String(Arrays.copyOf(bbuf, count),"utf-8"));
		}
		System.out.println("closing");
		client.close();
		System.out.println("closed");
	}
}
