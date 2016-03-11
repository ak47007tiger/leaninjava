package locnetsearch;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class GetAllLocPc {

	public static void main(String[] args) throws Exception {
		InetAddress inetAddress = InetAddress.getLocalHost();
		SocketAddress socketAddress = new InetSocketAddress(10080);
		NetworkInterface networkInterface = NetworkInterface
				.getByInetAddress(inetAddress);

		for (InterfaceAddress address : networkInterface
				.getInterfaceAddresses()) {
			System.out.println(address.getAddress().getHostAddress());
			byte[] addByte = address.getAddress().getAddress();
			System.out.println(addByte.length);
			System.out.println(address.getNetworkPrefixLength());
		}
//		System.out.println(Byte.toString((byte)254));
//		printAllBrather(24, networkInterface.getInterfaceAddresses().get(0).getAddress().getAddress());
		
	}
	static void isreach(){
		boolean r;
		try {
			r = InetAddress.getByName("192.168.1.5").isReachable(300);
			System.out.println(r);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static void pintF() throws Exception{
		byte[] b = new byte[256];
		String pingCommand = "ping " + "www.google.com.hk" + " -n " + 1    + " -w " + 200;
		Process process = Runtime.getRuntime().exec(pingCommand);
		int count = process.getInputStream().read(b);
		if(count > 0){
			System.out.println(new String(b,0,count,"gbk"));
		}
	}
	static void printAllBrather(int length,byte[] add) {
		byte[] min = new byte[4];
		min[0] = add[0];
		min[1] = add[1];
		min[2] = add[2];
		min[3] = 2;
		byte[] max = new byte[4];
		max[0] = add[0];
		max[1] = add[1];
		max[2] = add[2];
		max[3] = (byte) 254;
		int i = 2;
		byte[] cur = new byte[4];
		cur[0] = add[0];
		cur[1] = add[1];
		cur[2] = add[2];
		byte[] b = new byte[256];
		while(i < 255){
			cur[3] = (byte) i;
			i++;
			try {
				InetAddress inetAddress = InetAddress.getByAddress(cur);
				String pingCommand = "ping " + inetAddress.getHostAddress() + " -n " + 1    + " -w " + 200;
				Process process = Runtime.getRuntime().exec(pingCommand);
				int count = process.getInputStream().read(b);
				while(0 < count){
					System.out.print(new String(b,0,count,"gbk"));
					count = process.getInputStream().read(b);
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
