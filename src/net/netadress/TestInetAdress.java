package netadress;

import ipToPosition.IpToPosition;

import java.net.InetAddress;
import java.net.URL;

public class TestInetAdress {

	public static void main(String[] args) throws Exception {
		InetAddress Address = InetAddress.getLocalHost();
		/*System.out.println(Address);
		Address = InetAddress.getByName("www.baidu.com");
		System.out.println(Address);
		
		Address = InetAddress.getByName("www.aoaolu.com");
		System.out.println(Address);
		String ip = Address.getHostAddress();
		System.out.println(ip);
		IpToPosition ipToPosition = new IpToPosition();
		ipToPosition.setIp(ip);
		System.out.println(ipToPosition.getValue("country"));
		
		InetAddress SW[] = InetAddress.getAllByName("www.nba.com");
		for (int i=0; i<SW.length; i++){
			System.out.println(SW[i]);
		}*/
		Address = InetAddress.getByName("204.74.214.253");
		System.out.println(Address);
		System.out.println(Address.getHostName());
		System.out.println(Address.getHostAddress());
		byte[] buf = Address.getAddress();
		for(int i = 0; i < buf.length; i++){
			System.out.print(buf[i] + "-");
		}
		System.out.println();
		
		InetAddress url = InetAddress.getByName("svn.ufgov.com.cn");
		String ip = url.getHostAddress();
		System.out.println(ip);
	}
}
