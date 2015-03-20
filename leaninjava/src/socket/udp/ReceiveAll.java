package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveAll {

	public static void main(String[] args) throws Exception {
		DatagramSocket rec = new DatagramSocket(8987);
		int length = 2048;
		byte[]buf = new byte[length];
		DatagramPacket dp = new DatagramPacket(buf, length);
		while(true){
			rec.receive(dp);
			System.out.println(dp.getPort());
			System.out.println(new String(dp.getData(),0,dp.getLength()));
		}
	}
}
